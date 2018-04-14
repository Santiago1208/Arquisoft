package implementacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.osoa.sca.annotations.Reference;

import interfaces.ISort;

public class Ejecutable implements Runnable {

	@Reference(name = "iSort")
	private ISort sort;
	
	public void setSort(ISort sort) {
		this.sort = sort;
	}

	@Override
	public void run() {
		int numeroArchivo = 1;
		File archivoSalida = new File("./datos/salida.txt");
		try {
			PrintWriter escritor = new PrintWriter(archivoSalida);
			while (numeroArchivo < 13) {
				try {
					ArrayList<Integer> vector = inicializarVector(numeroArchivo);
					System.out.println("Vector del archivo " + numeroArchivo + " inicializado!");
					long t1 = System.currentTimeMillis();
					sort.sort(vector);
					long t2 = System.currentTimeMillis();
					long t = t2 - t1;
					String tiempo = presentarTiempoExcel(t);
					escritor.println(vector.size() + "\t" + tiempo);
					tiempo = presentarTiempo(t);
					if (tiempo.equals("")) {
						tiempo = "O (1)";
					}
					System.out.println("Vector de " + vector.size() + " posiciones ordenado en " + tiempo);
				} catch (Exception e) {
					System.out.println("Error de ordenamiento: " + e.getClass());
					System.out.println("Error de ordenamiento: " + e.getClass());
					imprimirTraza(e.getStackTrace());
				}
				numeroArchivo++;
			}
			escritor.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Error creando el archivo de salida: " + e1.getClass());
		}
		System.out.println("Todos los vectores se ordenaron sin problemas!");
	}
	
	private ArrayList<Integer> inicializarVector(int numeroArchivo) {
		ArrayList<Integer> vector = new ArrayList<Integer>();
		File archivoActual = new File("./datos/datos-" + numeroArchivo + ".txt");
		try {
			migrarDatos(archivoActual, vector);
		} catch (IOException e) {
			System.out.println("Error al migrar los datos: " + e.getMessage());
		}
		return vector;
	}
	
	private void migrarDatos(File archivo, ArrayList<Integer> vector) throws IOException{
		BufferedReader lector = new BufferedReader(new FileReader(archivo));
		String lineaActual = lector.readLine();
		Integer numeroActual = new Integer(0);
		do {
			numeroActual = Integer.parseInt(lineaActual);
			vector.add(numeroActual);
			lineaActual = lector.readLine();
		} while (lineaActual != null);
		lector.close();
	}
	
	private String presentarTiempo(long t) {
		String tiempo = "";
		long horas = TimeUnit.MILLISECONDS.toHours(t);
		long minutos = TimeUnit.MILLISECONDS.toMinutes(t);
		long segundos = TimeUnit.MILLISECONDS.toSeconds(t) % 60;
		long milisegundos = TimeUnit.MILLISECONDS.toMillis(t) % 60;
		if(horas > 1) {
			tiempo += horas + " horas ";
		}else if (horas == 1) {
			tiempo += horas + " hora ";
		}
		if (minutos > 1) {
			tiempo += minutos + " minutos ";
		}else if (minutos == 1) {
			tiempo += minutos + " minuto ";
		}
		if (segundos > 1) {
			tiempo += segundos + " segundos ";
		}else if (segundos == 1) {
			tiempo += segundos + " segundo ";
		}
		if (milisegundos > 1) {
			tiempo += milisegundos + " milisegundos.";
		}else if (milisegundos == 1) {
			tiempo += milisegundos + " milisegundo.";
		}
		return tiempo;
	}
	
	private String presentarTiempoExcel(long t) {
		String tiempo = "";
		long horas = TimeUnit.MILLISECONDS.toHours(t);
		long minutos = TimeUnit.MILLISECONDS.toMinutes(t);
		long segundos = TimeUnit.MILLISECONDS.toSeconds(t) % 60;
		long milisegundos = TimeUnit.MILLISECONDS.toMillis(t) % 60;
		if (horas < 10) {
			tiempo += "0"+ horas + ":";
		}else {
			tiempo += horas + ":";
		}
		if (minutos < 10) {
			tiempo += "0" + minutos + ":";
		}else {
			tiempo += minutos + ":";
		}
		if (segundos < 10) {
			tiempo += "0" + segundos + ":";
		}else {
			tiempo += "" + segundos + ":";
		}
		if (milisegundos < 10) {
			tiempo += "0" + milisegundos;
		}else {
			tiempo += "" + milisegundos;
		}
		return tiempo;
	}
	
	private void imprimirTraza(StackTraceElement[] pila) {
		for (int i = 0; i < pila.length; i++) {
			StackTraceElement e = pila[i];
			System.out.println(e.getMethodName() + " " + e.getFileName() + " " + e.getLineNumber() + e.getFileName());
		}
	}

}

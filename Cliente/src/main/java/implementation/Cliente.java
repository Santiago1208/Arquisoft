package implementation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.osoa.sca.annotations.Reference;

import interfaces.ISort;

public class Cliente implements Runnable {
	
	@Reference(name="ISort")
	private ISort sort;
	
	private GeneradorArchivos generador;
	
	public void setSort(ISort sort) {
		this.sort = sort;
	}

	@Override
	public void run() {
		
		System.out.println("Inicio...");
		
		generador = new GeneradorArchivos();
		generador.generarArhivos();
		System.out.println("Se generaron los archivos");
		
		try {
			for (int i = 0; i < GeneradorArchivos.tamanos.length; i++) {
				ArrayList<Integer> numeros = new ArrayList<Integer>();
				BufferedReader br = new BufferedReader(new FileReader(GeneradorArchivos.ruta+"archivo"+(i+1)));
				for (int j = 0; j < GeneradorArchivos.tamanos[i]; j++) {
					numeros.add(Integer.parseInt(br.readLine()));
				}
				br.close();
				System.out.println("Se obtuvo el arreglo "+(i+1)+" de tamano "+GeneradorArchivos.tamanos[i]);
				long t1 = System.currentTimeMillis();
				numeros = sort.sort(numeros);
				long t2 = System.currentTimeMillis();
				long tiempo = t2-t1;
				System.out.println("Se ordeno el arreglo "+(i+1)+" en "+tiempo+" ms");
			}
			System.out.println("Fin... :)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

}

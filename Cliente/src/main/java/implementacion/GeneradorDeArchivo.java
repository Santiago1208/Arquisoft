package implementacion;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class GeneradorDeArchivo {

	public static void main(String[] args) {
		int numArchivos = 1;
		Random r = new Random();
		int cantidad = 0;
		int cantidadArchivos = 12;
		while (numArchivos <= cantidadArchivos) {
			try {
				File archivo = new File("./datos/datos-" + numArchivos + ".txt");
				FileWriter escribir = new FileWriter(archivo, true);
				cantidad = cantidad + 30000;
				for (int i = 0; i < cantidad; i++) {
					if (i < (cantidad - 1)) {
						escribir.write(r.nextInt() + "\n");
					} else {
						escribir.write(r.nextInt() + "");
					}
				}
				escribir.close();
			}
			catch (Exception e) {
				System.out.println("Error al escribir");
			}
			numArchivos++;
		}
	}
}

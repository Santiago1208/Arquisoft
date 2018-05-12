package implementation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneradorArchivos {
	
	public final static String ruta = "./src/main/resources/";
	
	public final static int[] tamanos = {30000,60000,90000,120000,150000, 180000,210000,240000,270000,300000};
	
	public final static int lowerBoundAscii = 33;
	
	public final static int upperBoundAscii = 126;
	
	public GeneradorArchivos() {
		
	}
	
	public void generarArhivos() {
		for (int i = 0; i < tamanos.length; i++) {
			File archivo = new File(ruta+"archivo"+(i+1));
			if (archivo.exists() == false) {
				try {
					archivo.createNewFile();
					PrintWriter pw = new PrintWriter(new FileWriter(archivo));
					for (int j = 0; j < tamanos[i]; j++) {
						if(j<tamanos[i]-1) {
							// Valor entre M y N, ambos incluidos.
							int asciiCode = (int) Math.floor(Math.random()*(upperBoundAscii - lowerBoundAscii + 1) + lowerBoundAscii);
							Character character = new Character((char) asciiCode);
							pw.write("" + character + "\n");
						}
						else {
							// Valor entre M y N, ambos incluidos.
							int asciiCode = (int) Math.floor(Math.random()*(upperBoundAscii - lowerBoundAscii + 1) + lowerBoundAscii);
							Character character = new Character((char) asciiCode);
							pw.write("" + character);
						}
					
					}
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}
	

}

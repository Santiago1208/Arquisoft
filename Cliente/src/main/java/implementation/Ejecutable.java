package implementation;

public class Ejecutable {
	
	private static GeneradorArchivos generador;

	public static void main(String[] args) {
		generador = new GeneradorArchivos();
		generador.generarArhivos();
	}

}

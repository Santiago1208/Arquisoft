package implementation;

import java.util.ArrayList;

import interfaces.IMerge;

public class Merge implements IMerge{
	
	private int tamanioLista;

	@Override
	public ArrayList<Character> merge(ArrayList<ArrayList<Character>> listas, int tamanioListafinal) {
		
		ArrayList<Character> listaOrdenada = new ArrayList<>();
		
		/**
		 * No lo hice como Ana dec�a porque cada vez que se encuentre el menor de los cuatro arrays
		 * se elimina del array que lo conten�a, pero al hacerlo, recorre los indices posteriores para 
		 * correrlos una posici�n a la izquierda y eso no es eficiente comparado al m�todo que se 
		 * encuentra a continuaci�n:
		 */
		
		int w = 0;
		int x = 0;
		int y = 0;
		int z = 0;
		ArrayList<Character> lista1 = listas.get(0);
		ArrayList<Character> lista2 = listas.get(1);
		ArrayList<Character> lista3 = listas.get(2);
		ArrayList<Character> lista4 = listas.get(3);
		for (int i = 0; i < tamanioLista; i++) {

			if(lista1.get(w).compareTo(lista2.get(x))<=0
			&& lista1.get(w).compareTo(lista3.get(y))<=0
			&& lista1.get(w).compareTo(lista4.get(z))<=0) {
				listaOrdenada.add(lista1.get(w));
				if(w<lista1.size()-1) {
					w++;
				}
			}
			
			else if(lista2.get(x).compareTo(lista1.get(w))<=0
			&& lista2.get(x).compareTo(lista3.get(y))<=0
			&& lista2.get(x).compareTo(lista4.get(z))<=0) {
				listaOrdenada.add(lista2.get(x));
				if(x<lista2.size()-1) {
				x++;	
				}
			}
			
			else if(lista3.get(y).compareTo(lista1.get(w))<=0
			&& lista3.get(y).compareTo(lista2.get(x))<=0
			&& lista3.get(y).compareTo(lista4.get(z))<=0) {
				listaOrdenada.add(lista3.get(y));
				if(y<lista3.size()-1) {
					y++;
				}
			}
			
			else if(lista4.get(z).compareTo(lista1.get(w))<=0
			&& lista4.get(z).compareTo(lista2.get(x))<=0
			&& lista4.get(z).compareTo(lista3.get(y))<=0) {
				listaOrdenada.add(lista4.get(z));
				if(z<lista4.size()-1) {
					z++;
				}
			}
		}	
		
		return listaOrdenada;
	}
	


}

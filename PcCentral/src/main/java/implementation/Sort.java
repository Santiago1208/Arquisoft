package implementation;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.osoa.sca.annotations.Reference;

import interfaces.IPartialSort;
import interfaces.ISort;

public class Sort implements ISort {
	
	@Reference(name="IPartialSort")
	private IPartialSort partialSort1;
	@Reference(name="IPartialSort")
	private IPartialSort partialSort2;
	@Reference(name="IPartialSort")
	private IPartialSort partialSort3;
	@Reference(name="IPartialSort")
	private IPartialSort partialSort4;
	
	//Este hilo es el que me permitirá hacer los sorts paralelamente entregándoles los TPartialSorts
	private ArrayList<ArrayList<Character>> listasArrayList;
	
	private CyclicBarrier barreraFin;
	
	private int tamanioLista;

	
	@Override
	public ArrayList<Character> sort(ArrayList<Character> lista) {
		// TODO Auto-generated method stub
		tamanioLista= lista.size();
        final CyclicBarrier barreraFin = new CyclicBarrier(5);
		System.out.println("****La operación fue recibida, tamanio: " + lista.size() +" #s****");		
//		System.out.println("La lista contiene: " + listarItems(lista));
		
		listasArrayList = new ArrayList<ArrayList<Character>>();

		
		int m1 = (lista.size()-1)/2; //50%
		int m2 = m1/2; //25%
		int m3 = m1+m2; //75%
		
		ArrayList<Character> lista1 = (ArrayList<Character>)lista.subList(0, m2);
		ArrayList<Character> lista2 = (ArrayList<Character>)lista.subList(m2+1, m1);
		ArrayList<Character> lista3 = (ArrayList<Character>)lista.subList(m1+1, m3);
		ArrayList<Character> lista4 = (ArrayList<Character>)lista.subList(m3+1, lista.size()-1);
		
		partialSort1.setLista(lista1);
		partialSort1.setBarreraFin(barreraFin);
		partialSort2.setLista(lista2);
		partialSort2.setBarreraFin(barreraFin);
		partialSort3.setLista(lista3);
		partialSort3.setBarreraFin(barreraFin);
		partialSort4.setLista(lista4);
		partialSort4.setBarreraFin(barreraFin);
		partialSort4.run();
		
		
		try {
			barreraFin.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Todos los nodos ya ordenaron");
        

        
        listasArrayList.add(lista1);
        listasArrayList.add(lista2);
        listasArrayList.add(lista3);
        listasArrayList.add(lista4);
        
        lista = merge(listasArrayList);
        
		
		System.out.println("****La operación finalizada****");
//		System.out.println("La lista contiene: " + listarItems(lista));		
		
		return lista;
	}
	
	public ArrayList<Character> merge(ArrayList<ArrayList<Character>> listas) {
		
		ArrayList<Character> listaOrdenada = new ArrayList<>();
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
				w++;
			}
			
			else if(lista2.get(x).compareTo(lista1.get(w))<=0
			&& lista2.get(x).compareTo(lista3.get(y))<=0
			&& lista2.get(x).compareTo(lista4.get(z))<=0) {
				listaOrdenada.add(lista2.get(x));
				x++;
			}
			
			else if(lista3.get(y).compareTo(lista1.get(w))<=0
			&& lista3.get(y).compareTo(lista2.get(x))<=0
			&& lista3.get(y).compareTo(lista4.get(z))<=0) {
				listaOrdenada.add(lista3.get(y));
				y++;
			}
			
			else if(lista4.get(z).compareTo(lista1.get(w))<=0
			&& lista4.get(z).compareTo(lista2.get(x))<=0
			&& lista4.get(z).compareTo(lista3.get(y))<=0) {
				listaOrdenada.add(lista4.get(z));
				z++;
			}
		}	
		
		return listaOrdenada;
	}

}

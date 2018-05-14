package implementation;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.osoa.sca.annotations.Reference;

import interfaces.IMerge;
import interfaces.IPartialSort;
import interfaces.ISort;

public class Control implements ISort {
	
	@Reference(name="IPartialSort1")
	private IPartialSort partialSort1;
	@Reference(name="IPartialSort2")
	private IPartialSort partialSort2;
	@Reference(name="IPartialSort3")
	private IPartialSort partialSort3;
	@Reference(name="IPartialSort4")
	private IPartialSort partialSort4;
	@Reference(name="IMerge")
	private IMerge merge;
	
	//Este hilo es el que me permitir� hacer los sorts paralelamente entreg�ndoles los TPartialSorts
	private ArrayList<ArrayList<Character>> listasArrayList;
	
	private CyclicBarrier barreraFin;
	
	@Override
	public ArrayList<Character> sort(ArrayList<Character> lista) {
		// TODO Auto-generated method stub
		barreraFin = new CyclicBarrier(5);
		System.out.println("****La operacion fue recibida, tamanio: " + lista.size() +" #s****");		
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
		Thread t1= new Thread(partialSort1);
		Thread t2= new Thread(partialSort2);
		Thread t3= new Thread(partialSort3);
		Thread t4= new Thread(partialSort4);
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		
		try {
			barreraFin.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Todos los nodos ya ordenaron");
        lista1= partialSort1.getLista();
        lista2= partialSort2.getLista();
        lista3= partialSort3.getLista();
        lista4= partialSort4.getLista();

        
        listasArrayList.add(lista1);
        listasArrayList.add(lista2);
        listasArrayList.add(lista3);
        listasArrayList.add(lista4);
        
        lista = merge.merge(listasArrayList, lista.size());
        
		
		System.out.println("****La operacion finalizada****");
//		System.out.println("La lista contiene: " + listarItems(lista));		
		
		return lista;
	}
	
}

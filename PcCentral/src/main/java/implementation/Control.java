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
	
	private ArrayList<Character> lista1;
	private ArrayList<Character> lista2;
	private ArrayList<Character> lista3;
	private ArrayList<Character> lista4;
	
	//Este hilo es el que me permitir hacer los sorts paralelamente entregndoles los TPartialSorts
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
		
		lista1 = new ArrayList<Character>(lista.subList(0, m2));
		lista2 = new ArrayList<Character>(lista.subList(m2+1, m1));
		lista3 = new ArrayList<Character>(lista.subList(m1+1, m3));
		lista4 = new ArrayList<Character>(lista.subList(m3+1, lista.size()-1));
		

		Thread t1 = new Thread() {
            public void run() {
            	lista1=partialSort1.partialSort(lista1,0,lista1.size()-1);
                System.out.println("hilo ejecutandose");
                try {                	
                    barreraFin.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
		};
		
		
		Thread t2 = new Thread() {
            public void run() {
            	
            	lista2=partialSort2.partialSort(lista2,0,lista2.size()-1);
                try {
                    
                    System.out.println("hilo ejecutandose");
                    barreraFin.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
		};
		Thread t3 = new Thread() {
            public void run() {
            	
            	lista3=partialSort3.partialSort(lista3,0,lista3.size()-1);
                try {
                    
                    System.out.println("hilo ejecutandose");
                    barreraFin.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
		};
		Thread t4 = new Thread() {
            public void run() {
            	
            	lista4=partialSort4.partialSort(lista4,0,lista4.size()-1);
                try {
                    
                    System.out.println("hilo ejecutandose");
                    barreraFin.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
		};

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		
		try {
			barreraFin.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Todos los nodos ya ordenaron");
        

        
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

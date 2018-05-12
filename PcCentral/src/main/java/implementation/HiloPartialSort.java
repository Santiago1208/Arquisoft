package implementation;

import java.util.ArrayList;
import org.osoa.sca.annotations.Reference;
import java.util.concurrent.CyclicBarrier;
import interfaces.IPartialSort;

public class HiloPartialSort extends Thread {
	
	@Reference(name="IPartialSort")
	private IPartialSort partialSort;
	
    private CyclicBarrier barreraFin;
    
	private ArrayList<Character> lista;
	
	private ArrayList<Character> listaOrdenada;
	
	public HiloPartialSort(ArrayList<Character> lista, CyclicBarrier barreraFin) {
		this.barreraFin = barreraFin;
		this.lista = lista;
	
	}
	
	@Override
	public void run() {
		try {
            System.out.println("hilo ejecutandose");
    		listaOrdenada = partialSort.partialSort(lista, 0, lista.size()-1);
            barreraFin.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
		super.run();
	}

	public ArrayList<Character> getListaOrdenada() {
		return listaOrdenada;
	}
	

}

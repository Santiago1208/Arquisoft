package interfaces;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

import org.osoa.sca.annotations.Service;

@Service
public interface IPartialSort extends Runnable {

	public ArrayList<Character> partialSort(ArrayList<Character> lista, int l, int r);
	
	public void setLista(ArrayList<Character> lista);
	
    public void setBarreraFin(CyclicBarrier barreraFin);
    public ArrayList<Character> getLista();
   
}

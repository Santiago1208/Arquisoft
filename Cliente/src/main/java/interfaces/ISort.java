package interfaces;

import java.util.ArrayList;

import org.osoa.sca.annotations.Service;

@Service
public interface ISort {
	
	public ArrayList<Character> sort(ArrayList<Character> arreglo);
	
}

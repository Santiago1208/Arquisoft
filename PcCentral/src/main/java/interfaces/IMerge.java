package interfaces;

import java.util.ArrayList;

import org.osoa.sca.annotations.Service;

@Service
public interface IMerge {

	public ArrayList<Character> merge(ArrayList<ArrayList<Character>> listas, int tamanioListafinal);
	
	
}

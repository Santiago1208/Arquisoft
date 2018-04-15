package interfaces;

import java.util.ArrayList;

import org.osoa.sca.annotations.Service;

@Service
public interface IPartialSort {
	
	public ArrayList<Character> partialSort(ArrayList<Character> lista, int l, int r);


}

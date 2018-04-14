package interfaces;

import java.util.ArrayList;

import org.osoa.sca.annotations.Service;

@Service
public interface ISort {
	
	public ArrayList<Integer> sort(ArrayList<Integer> vector);

}

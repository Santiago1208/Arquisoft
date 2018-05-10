package implementation;

import java.util.ArrayList;

import org.osoa.sca.annotations.Reference;

import interfaces.IPartialSort;
import interfaces.ISort;

public class OrdenarImplementacion implements ISort {

	@Reference(name="IPartialSort")
	private IPartialSort partialSort1;
	@Reference(name="IPartialSort")
	private IPartialSort partialSort2;
	@Reference(name="IPartialSort")
	private IPartialSort partialSort3;
	@Reference(name="IPartialSort")
	private IPartialSort partialSort4;
	
	private Thread particionamientos;
	private ArrayList<ArrayList<Character>> listasArrayList;
	
	@Override
	public ArrayList<Character> sort(ArrayList<Character> lista) {
		// TODO Auto-generated method stub
		
		System.out.println("****La operación fue recibida, tamanio: " + lista.size() +" #s****");		
//		System.out.println("La lista contiene: " + listarItems(lista));
		
		listasArrayList = new ArrayList<ArrayList<Character>>();

		
		int m1 = (lista.size()-1)/2;
		int m2 = m1/2;
		int m3 = m1+m2;
		
		
		listasArrayList.add(partialSort1.partialSort(lista, 0, m2));
		listasArrayList.add(partialSort2.partialSort(lista, m2+1, m1));
		listasArrayList.add(partialSort3.partialSort(lista, m1+1, m3));
		listasArrayList.add(partialSort4.partialSort(lista, m3+1, lista.size()-1));
		
		int com = 1;
		int i = 0;
		int tamArrayList = listasArrayList.size();
		while(i<tamArrayList) {
			if(listasArrayList.get(i+com)!=null) {
				boolean seUnio = listasArrayList.get(i).addAll(listasArrayList.get(i).size(), listasArrayList.get(i+com));
				if(seUnio) {
					merge(listasArrayList.get(i), 0, (listasArrayList.get(i).size()-1)/2, listasArrayList.get(i).size()-1);
				}
			}
			i+=2;
			if(i==listasArrayList.size() || i==listasArrayList.size()-1) {
				com = com+1;;
				if(com!=listasArrayList.size()-1) {
					i= 0;
				}
		}
		
		}
			
		System.out.println("****La operación finalizada****");
//		System.out.println("La lista contiene: " + listarItems(lista));		
		
		return listasArrayList.get(0);
	}
	
	
	public void merge(ArrayList<Character> lista, int l, int m, int r) {
		
		// Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = lista.get(l + i);
        for (int j=0; j<n2; ++j)
            R[j] = lista.get(m + 1+ j);
 
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                lista.set(k, (char) L[i]);
                i++;
            }
            else
            {
                lista.set(k, (char) R[j]);
                j++; 
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            lista.set(k, (char) L[i]);
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            lista.set(k, (char) R[j]);
            j++;
            k++;
        }	
	}
	
	
	public String listarItems(ArrayList lista) {
		String cadena = "";	
		
		for (int i = 0; i < lista.size(); i++) {			
			cadena += lista.get(i) + "-";			
		}		
		return cadena;
	}

}

package implementation;

import java.util.ArrayList;

import interfaces.ISort;

public class OrdenarImplementacion implements ISort {

	
	@Override
	public ArrayList<Character> sort(ArrayList<Character> lista) {
		// TODO Auto-generated method stub
		
		System.out.println("****La operación fue recibida, tamanio: " + lista.size() +" #s****");		
//		System.out.println("La lista contiene: " + listarItems(lista));
		
		sortMerge(lista, 0, lista.size() - 1);
		
		System.out.println("****La operación finalizada****");
//		System.out.println("La lista contiene: " + listarItems(lista));		
		
		return lista;
	}
	
	public void sortMerge(ArrayList<Character> lista, int l, int r) {
		
		if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;
 
            // Sort first and second halves
            sortMerge(lista, l, m);
            sortMerge(lista , m+1, r);
 
            // Merge the sorted halves
            merge(lista, l, m, r);
        }
		
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

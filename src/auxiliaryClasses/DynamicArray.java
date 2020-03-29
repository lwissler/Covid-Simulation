package auxiliaryClasses;

/**
 * 
 * @author Lars Wissler
 *
 */
@SuppressWarnings("unchecked")
public class DynamicArray <T>{
	public int size = 0;
	public T[] array = (T[]) new Object[0];
	
	public void add(T o){
		T[] tmp = array;
		array = (T[])new Object[array.length + 1];
		array[size] = o;
		size++;
		for(int i = 0; i < size-1; i++){
			array[i] = tmp[i];
		}
	}

	public void remove(int x){
		T[] tmp = array;
		array = (T[])new Object[size-1];
		int c = 0;
		for(int i = 0; i < size; i++){
			if(i!=x){
				array[c] = tmp[i];
				c++;
			}
		}
		size--;
	}
	
	public T get(int x) {
		if(size>x)
			return array[x];
		else
			return null;
	}
}

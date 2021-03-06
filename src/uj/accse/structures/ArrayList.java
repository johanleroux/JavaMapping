package uj.accse.structures;

import java.util.Iterator;

/**
 * ArrayList Data Structure Implementation
 * 
 * @author Johan le Roux (201577296)
 *
 * @param <T>
 */
public class ArrayList<T> implements Iterable<T> {

	private T[] array;
	private Integer size;
	private Integer length;

	/**
	 * Create empty array list
	 */
	public ArrayList() {
		this(1);
	}

	/**
	 * Create empty array list of param length
	 * 
	 * @param length
	 */
	public ArrayList(int length) {
		this.length = length;
		this.array = createArray(this.length);
		this.size = 0;
	}

	/**
	 * Clone ArrayList
	 * 
	 * @param old
	 */
	public ArrayList(ArrayList<T> old) {
		this(old.length);
		this.size = old.size;
		this.array = (T[]) old.array;

	}

	/**
	 * Create Array Helper
	 * 
	 * @param length
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private T[] createArray(int length) {
		return (T[]) new Object[length];
	}

	/**
	 * Ensure Array is the Correct Length Otherwise Increase Size by Doubling
	 */
	private void ensureLength() {
		if (size < length)
			return;

		T[] temp = this.createArray(length * 2);
		for (int i = 0; i < this.length; i++)
			temp[i] = this.get(i);

		length *= 2;
		array = temp;
	}

	/**
	 * Add Element to End of Array
	 * 
	 * @param e
	 */
	public void add(T e) {
		add(size, e);
	}

	/**
	 * Add Element to Position i
	 * 
	 * @param i
	 * @param e
	 */
	public void add(Integer i, T e) {
		ensureLength();

		if (!this.isEmpty() && i < size)
			for (int r = size; r >= i; r--)
				this.array[r + 1] = this.array[r];

		this.size++;
		this.set(i, e);
	}

	/**
	 * Remove Element to Position i
	 * 
	 * @param i
	 * @return
	 */
	public T remove(Integer i) {
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException("Index out of bounds.");

		T remove = array[i];

		T[] temp = createArray(length);

		for (int x = 0, j = 0; x < size; x++)
			if (x != i)
				temp[j++] = array[x];

		array = temp;
		size--;

		return remove;
	}

	/**
	 * Return Element at Position i
	 * 
	 * @param i
	 * @return
	 */
	public T get(Integer i) {
		return array[i];
	}

	/**
	 * Set Element to Array at Position i
	 * 
	 * @param i
	 * @param e
	 */
	public void set(Integer i, T e) {
		try {
			array[i] = e;
		} catch (Exception ex) {
			System.out.println(size + "/" + length);
			System.out.println("Pos " + i + " element: " + e);
		}
	}

	/**
	 * Return Size of Array
	 * 
	 * @return
	 */
	public Integer size() {
		return size;
	}

	/**
	 * Check Whether Array is Empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * ArrayList toString Override
	 */
	public String toString() {
		String str = "[";

		for (int i = 0; i < size; i++) {
			if (i < (size - 1))
				str += array[i] + ", ";
			else
				str += array[i];
		}

		str += "]";

		return str;
	}

	/**
	 * Return Index of Element e
	 * 
	 * @param e
	 * @return
	 */
	public int indexOf(T e) {
		for (int i = 0; i < size; i++) {

			if (e instanceof Vertex) {
				if (((Vertex) e).getLabel().equals(((Vertex) array[i]).getLabel()))
					return i;
			} else if (e.equals(array[i]))
				return i;
		}

		return -1;
	}

	/**
	 * Return Last Index of Element e
	 * 
	 * @param e
	 * @return
	 */
	public int lastIndexOf(T e) {
		for (int i = size; i >= 0; i--) {
			if (e.equals(array[i]))
				return i;
		}

		return -1;
	}

	/**
	 * Check if ArrayList Contains Element e
	 * 
	 * @param e
	 * @return
	 */
	public boolean contains(T e) {
		return indexOf(e) > 0 ? true : false;
	}

	/**
	 * ArrayList Iterator
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Iterator<T> iterator() {
		return new ArrayListIterator(this);
	}

	/**
	 * Clear ArrayList
	 */
	public void clear() {
		this.length = 1;
		this.array = createArray(this.length);
		this.size = 0;
	}
}

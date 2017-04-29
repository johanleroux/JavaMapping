package uj.accse.structures;

import java.util.Iterator;

public class ArrayList<T> implements List<T>, Iterable<T> {

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

	public ArrayList(ArrayList<T> old) {
		this(old.length);
		this.size = old.size;
		this.array = (T[]) old.array;

	}

	@SuppressWarnings("unchecked")
	private T[] createArray(int length) {
		return (T[]) new Object[length];
	}

	private void ensureLength() {
		if (size < length)
			return;

		T[] temp = this.createArray(length * 2);
		for (int i = 0; i < this.length; i++)
			temp[i] = this.get(i);

		length *= 2;
		array = temp;
	}

	public void add(T e) {
		add(size, e);
	}

	@Override
	public void add(Integer i, T e) {

		ensureLength();

		if (!this.isEmpty() && i < size)
			for (int r = size; r >= i; r--)
				this.array[r + 1] = this.array[r];

		this.size++;
		this.set(i, e);
	}

	@Override
	public T remove(Integer i) {
		T temp = array[i];
		if (!this.isEmpty() && i < size)
			for (int r = i; r < size; r++)
				this.array[r] = this.array[r + 1];

		this.size--;
		return temp;
	}

	@Override
	public T get(Integer i) {
		return array[i];
	}

	@Override
	public void set(Integer i, T e) {
		array[i] = e;
	}

	@Override
	public Integer size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

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

	public int lastIndexOf(T e) {
		for (int i = size; i >= 0; i--) {
			if (e.equals(array[i]))
				return i;
		}

		return -1;
	}

	public boolean contains(T e) {
		return indexOf(e) > 0 ? true : false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Iterator<T> iterator() {
		return new ArrayListIterator(this);
	}

	public void clear() {
		this.length = 1;
		this.array = createArray(this.length);
		this.size = 0;
	}
}

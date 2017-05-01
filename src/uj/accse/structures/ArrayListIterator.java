package uj.accse.structures;

import java.util.Iterator;

/**
 * ArrayList Iterator Implementation
 * 
 * @author Johan le Roux (201577296)
 *
 * @param <T>
 */
public class ArrayListIterator<T> implements Iterator<T> {
	private ArrayList<T> list;
	private int cursor;

	/**
	 * Constructor
	 * 
	 * @param list
	 */
	public ArrayListIterator(ArrayList<T> list) {
		this.list = list;
		if (!list.isEmpty()) {
			this.cursor = 0;
		}
	}

	@Override
	/**
	 * Return if there is a next element
	 */
	public boolean hasNext() {
		return (this.list.size() > cursor) ? true : false;
	}

	@Override
	/**
	 * Return next element
	 */
	public T next() {
		T temp = null;
		if (this.hasNext()) {
			temp = this.list.get(cursor);
			this.cursor++;
		}

		return temp;
	}

	@Override
	public void remove() {
		// do nothing - keep java happy
	}

}

package uj.accse.structures;

import java.util.Iterator;

/**
 * An iterator over a ArrayList. This will return the Item that is contained in
 * the list
 * 
 */
public class ArrayListIterator<T> implements Iterator<T> {
	private ArrayList<T> list;
	private int cursor;

	/**
	 * The constructor
	 * 
	 * @param list
	 *            the list to iterate over
	 */
	public ArrayListIterator(ArrayList<T> list) {
		this.list = list;
		if (!list.isEmpty()) {
			this.cursor = 0;
		}
	}

	@Override
	/**
	 * Returns true if there next() will return an element ********** 2 marks
	 * ****************************
	 */
	public boolean hasNext() {
		return (this.list.size() > cursor) ? true : false;
	}

	@Override
	/**
	 * Return the "next" item in the list and then advance the cursor.
	 * ********** 3 marks ****************************
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
	/**
	 * Should be used to remove the item from the list, but for now we do not
	 * include an implementation.
	 */
	public void remove() {
		// do nothing - keep java happy
	}

}

package uj.accse.structures;

import java.util.Iterator;

/**
 * The Position List itself, this is just a doubly-linked list that make use of
 * Positions
 *
 * @param <T>
 *            the type of the objects in the list.
 */
public class PositionList<T> implements Iterable<T> {

	private Node<T> header = null;
	private Node<T> trailer = null;
	private Integer size = 0;

	/**
	 * Default constructor
	 */
	public PositionList() {
		trailer = new Node<T>(null, null, null);
		header = new Node<T>(trailer, null, null);
		trailer.setPrev(header);
		size = 0;
	}

	/**
	 * Add an element after a given node in the list
	 */
	public Position<T> addAfter(Position<T> p, T item) {
		Node<T> elem = checkPosition(p);

		Node<T> newNode = new Node<T>(null, null, item);
		newNode.setPrev(elem);
		newNode.setNext(elem.getNext());
		elem.getNext().setPrev(newNode);
		elem.setNext(newNode);
		size++;
		return newNode;
	}

	/**
	 * Add an element before a given node in a list
	 */
	public Position<T> addBefore(Position<T> p, T item) {
		Node<T> elem = checkPosition(p);

		Node<T> newNode = new Node<T>(null, null, item);
		newNode.setPrev(elem.getPrev());
		newNode.setNext(elem);
		elem.getPrev().setNext(newNode);
		elem.setPrev(newNode);
		size++;
		return newNode;
	}

	/**
	 * Add an element to the start of the list
	 */
	public Position<T> addFirst(T item) {
		return addAfter(header, item);
	}

	/**
	 * Add an element to the end of the list
	 */
	public Position<T> addLast(T item) {
		return addBefore(trailer, item);
	}

	/**
	 * Remove a specified node from the list. The removed element is returned
	 */
	public T remove(Position<T> p) {
		Node<T> elem = checkPosition(p);

		T element = elem.element();
		elem.getPrev().setNext(elem.getNext());
		elem.getNext().setPrev(elem.getPrev());
		elem.setNext(null);
		elem.setPrev(null);
		size--;
		return element;
	}

	/**
	 * Return the front of the list
	 * 
	 * @return the first Position<T> in the list
	 */
	public Position<T> first() {
		if (header.getNext() == trailer) {
			throw new PositionListException("List is empty");
		}
		return header.getNext();
	}

	/**
	 * Returns the last element in the list
	 * 
	 * @return the last Position<T> element in the list
	 */
	public Position<T> last() {
		if (trailer.getPrev() == header) {
			throw new PositionListException("List is empty");
		}
		return trailer.getPrev();
	}

	/**
	 * Get the next element in the list
	 * 
	 * @param p
	 *            an existing Position in the list
	 * @return the Position<T> that corresponds to the next element
	 */
	public Position<T> next(Position<T> p) {
		Node<T> node = checkPosition(p);
		return node.getNext();
	}

	/**
	 * Get the previous element in the list
	 * 
	 * @param p
	 *            an exisiting Position in the list
	 * @return the Posiiton<T> that corresponds to the prev elements
	 */
	public Position<T> prev(Position<T> p) {
		Node<T> node = checkPosition(p);
		return node.getPrev();
	}

	/**
	 * Returns the node that contains the element that is specified as a
	 * parameter
	 */
	public Position<T> search(T elem) {
		Node<T> currentNode = header.getNext();
		while (currentNode != trailer) {
			if (currentNode.element().equals(elem)) {
				return currentNode;
			}
			currentNode = currentNode.getNext();
		}
		return null;
	}

	/**
	 * Returns true if the list is empty
	 */
	public boolean isEmpty() {
		return (header.getNext() == trailer);
	}

	/**
	 * Return the size of the list
	 */
	public Integer size() {
		return size;
	}

	@Override
	public String toString() {
		String result = "";
		Node<T> currentNode = header.getNext();

		while (currentNode != trailer) {
			result += currentNode.toString() + " ";
			currentNode = currentNode.getNext();
		}

		return result;
	}

	/**
	 * Convert a Position to a Node and perform the necessary checks to make
	 * sure very thing is OK
	 * 
	 * @param position
	 *            a Position<T> that was passed by the user
	 * @return a Node<T> that corresponds to the Position<T>
	 */
	private Node<T> checkPosition(Position<T> position) {
		if (!(position instanceof Node<?>)) {
			throw new PositionListException("Invalid Position");
		}

		// normally other checks are also done, but for brevity just the casting
		// is performed.

		Node<T> newNode = (Node<T>) position;
		return newNode;
	}

	/**
	 * Return a new iterator over the elements in this list
	 */
	public Iterator<T> iterator() {
		return new PositionListIterator<T>(this);
	}
}

package uj.accse.structures;

import java.nio.ByteBuffer;
import java.util.Iterator;

public class Hashtable<K, V> {
	Object[] table;
	int size;
	int capacity;

	/**
	 * Default constructor
	 */
	public Hashtable() {
		this(1);
	}

	/**
	 * Constructor
	 * 
	 * @param initialSize
	 */
	public Hashtable(int initialSize) {
		this.capacity = initialSize;
		this.table = createArray(this.capacity);
	}

	/**
	 * Create an array of PositionList
	 * 
	 * @param size
	 * @return
	 */
	private Object[] createArray(int size) {
		Object[] arr = new Object[size];
		// PositionList<Entry<K,V>>[] objArray =
		// (PositionList<Entry<K,V>>[])arr;

		// Initialise the array
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new PositionList<Entry<K, V>>();
		}
		return arr;
	}

	/**
	 * Hash a string input
	 * 
	 * @param str
	 *            The input string
	 * @return the hash code for the integer
	 */
	private long hash(String str) {
		return hash(str.getBytes());
	}

	/**
	 * A hash an integer input
	 * 
	 * @param inputInt
	 *            the input input
	 * @return the hash code for the integer
	 */
	private long hash(int inputInt) {
		byte[] bytes = ByteBuffer.allocate(4).putInt(inputInt).array();
		return hash(bytes);
	}

	/**
	 * Calculate a hash code using the djb2 hash function This hash function was
	 * created by Dan Bernstein, however normally it works with string inputs,
	 * this has been modified to work with byte inputs
	 * 
	 * @param input
	 *            the input array of bytes
	 * @return a hash value for the input
	 */
	private long hash(byte[] input) {
		long hash = 5381;
		for (int i = 0; i < input.length; i++) {
			hash = ((hash << 5) + hash) + input[i];
		}
		return hash;
	}

	/**
	 * Calculate a hash for either a string or an Integer
	 * 
	 * @param item
	 *            the item to hash
	 * @return a compressed hash code for the item
	 */
	private long hash(K item) {
		if (item instanceof Integer) {
			return hash((Integer) item) % capacity;
		}

		if (item instanceof String) {
			return hash((String) item) % capacity;
		}

		return (long) item.hashCode() % capacity;
	}

	/**
	 * Remove an item from the hash table
	 * 
	 * @param key
	 */
	public V remove(K key) {
		V temp = null;

		for (int i = 0; i < table.length; i++) {
			PositionList<Entry<K, V>> bucket = (PositionList<Entry<K, V>>) table[i];
			for (Entry<K, V> entry : bucket) {
				if (entry.getKey().equals(hash(key))) {
					temp = entry.getValue();
					bucket.remove(bucket.search(entry));
					size--;
				}
			}
		}

		return temp;
	}

	/**
	 * Get the value for a given key
	 * 
	 * @param key
	 *            the key for the item
	 * @returns
	 * 
	 */
	public V get(K key) {
		for (int i = 0; i < table.length; i++) {
			PositionList<Entry<K, V>> bucket = (PositionList<Entry<K, V>>) table[i];
			for (Entry<K, V> entry : bucket) {
				if (entry.getKey().equals(hash(key)))
					return entry.getValue();
			}
		}

		return null;
	}

	/**
	 * Put an item into the hash table
	 * 
	 * @param key
	 *            the key for the item (unique)
	 * @param value
	 */
	public void put(K key, V value) {
		Entry<K, V> item = new Entry<K, V>(key, value);

		for (int i = 0; i < table.length; i++) {
			PositionList<Entry<K, V>> bucket = (PositionList<Entry<K, V>>) table[i];
			for (Entry<K, V> entry : bucket) {
				if (hash(entry.getKey()) == hash(key)) {
					entry.setValue(value);
					break;
				}
			}
			bucket.addLast(item);
		}
	}

	/**
	 * Returns an iterator over the keys of the hash table 6 marks
	 * ***********************************************
	 */
	public Iterator<K> keys() {
		PositionList<K> val = new PositionList<K>();
		for (int i = 0; i < table.length; i++) {
			PositionList<Entry<K, V>> bucket = (PositionList<Entry<K, V>>) table[i];
			Iterator<Entry<K, V>> bucketIterator = bucket.iterator();
			while (bucketIterator.hasNext()) {
				Entry<K, V> item = bucketIterator.next();
				val.addLast(item.getKey());
			}
		}

		return val.iterator();
	}

	/**
	 * Returns an iterator over the values in the hash table
	 */
	public Iterator<V> values() {
		PositionList<V> val = new PositionList<V>();
		for (int i = 0; i < table.length; i++) {
			PositionList<Entry<K, V>> bucket = (PositionList<Entry<K, V>>) table[i];
			Iterator<Entry<K, V>> bucketIterator = bucket.iterator();
			while (bucketIterator.hasNext()) {
				Entry<K, V> item = bucketIterator.next();
				val.addLast(item.getValue());
			}
		}

		return val.iterator();
	}

	/**
	 * Returns the size of the hashtable
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns true if the hashtable is empty;
	 */
	public boolean isEmpty() {
		return size == 0;
	}

}

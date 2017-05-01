package uj.accse.structures;

public interface IList<T> extends Iterable<T> {
	public Position<T> addAfter(Position<T> p, T item);

	public Position<T> addBefore(Position<T> p, T item);

	public Position<T> addFirst(T item);

	public Position<T> addLast(T item);

	public T remove(Position<T> p);

	public Position<T> search(T p);

	public Position<T> next(Position<T> p);

	public Position<T> prev(Position<T> p);

	public Position<T> first();

	public Position<T> last();

	public boolean isEmpty();

	public Integer size();
}

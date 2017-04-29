package uj.accse.structures;

public interface List<T> {
	public T get(Integer i);

	public void set(Integer i, T e);

	public void add(Integer i, T e);

	public T remove(Integer i);

	public Integer size();

	public boolean isEmpty();
}

package application;

public interface List<T> {

    boolean add(T value);

    void add(T value, int index);

    boolean addAll(java.util.List<T> list);

    T get(int index);

    T set(T value, int index);

    T remove(int index);

    boolean remove(T t);

    int size();

    boolean isEmpty();
}

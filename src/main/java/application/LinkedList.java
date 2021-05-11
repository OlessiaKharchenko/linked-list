package application;

public class LinkedList<T> implements List<T> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size;

    @Override
    public boolean add(T value) {
        addLast(value);
        return true;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            addLast(value);
        } else {
            addBefore(value, findNodeByIndex(index));
        }
    }

    @Override
    public boolean addAll(java.util.List<T> list) {
        for (T value : list) {
            add(value);
        }
        return true;
    }

    @Override
    public T get(int index) {
        return findNodeByIndex(index).value;
    }

    @Override
    public T set(T value, int index) {
        checkIndex(index);
        Node<T> element = findNodeByIndex(index);
        T elementToReplace = element.value;
        element.value = value;
        return elementToReplace;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return unlink(findNodeByIndex(index));
    }

    @Override
    public boolean remove(T t) {
        for (Node<T> node = firstNode; node != null; node = node.nextElement) {
            if (node.value == t || node.value != null
                    && node.value.equals(t)) {
                unlink(node);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void addLast(T value) {
        // силаємося на останню ноду
        Node<T> prev = lastNode;
        // створюємо нову і силкою на попередню, наступна - нал
        Node<T> newNode = new Node<>(prev, value, null);
        // останньою нодою ліста робимо - нову
        lastNode = newNode;
        //  якщо список пустий
        if (prev == null) {
            // створюємо першу ноду
            firstNode = newNode;
        } else {
            // якщо список не пустий, змінюємо силку
            prev.nextElement = newNode;
        }
        size++;
    }

    private void addBefore(T t, Node<T> element) {
        // силаємося на ноду перед елементом
        Node<T> prev = element.prevElement;
        // створюємо цю ноду
        Node<T> newNode = new Node<>(prev, t, element);
        // змінюємо одну силку
        element.prevElement = newNode;
        //якщо попередній елемент - нал, то у лісті одна нода
        if (prev == null) {
            firstNode = newNode; // замінюємо першу  ????
        } else {
            // змінюємо другу силку
            prev.nextElement = newNode;
        }
        size++;
    }

    private Node<T> findNodeByIndex(int index) {
        checkIndex(index);
        if (index < (size >> 1)) { //  якщо індекс менший за size/2
            // йдемо від першої ноди
            Node<T> nodeToReturn = firstNode;
            for (int i = 0; i < index; i++) {
                nodeToReturn = nodeToReturn.nextElement;
            }
            return nodeToReturn;
        } else {
            // йдемо від останної ноди
            Node<T> nodeToReturn = lastNode;
            for (int i = size - 1; i > index; i--) {
                nodeToReturn = nodeToReturn.prevElement;
            }
            return nodeToReturn;
        }
    }

    private T unlink(Node<T> node) {
        final T element = node.value;
        Node<T> next = node.nextElement;
        Node<T> prev = node.prevElement;
        if (prev == null) {
            firstNode = next;
        } else {
            prev.nextElement = next;
            node.prevElement = null;
        }
        if (next == null) {
            lastNode = prev;
        } else {
            next.prevElement = prev;
            node.nextElement = null;
        }
        node.value = null;
        size--;
        return element;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Element with such index doesn't exist!");
        }
    }

    private static class Node<T> {
        T value;
        Node<T> nextElement;
        Node<T> prevElement;

        Node(Node<T> prevElement, T currentElemet, Node<T> nextElement) {
            this.prevElement = prevElement;
            this.value = currentElemet;
            this.nextElement = nextElement;
        }
    }
}

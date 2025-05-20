package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {

    private class ListElement {
        T value;
        ListElement next;

        ListElement(T value) {
            this.value = value;
        }
    }

    private ListElement first;
    private ListElement last;

    @Override
    public int size() {
        int count = 0;
        ListElement current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public T set(int index, T element) {
        ListElement current = getElement(index);
        if (current == null) throw new IndexOutOfBoundsException();
        T old = current.value;
        current.value = element;
        return old;
    }

    @Override
    public void add(int index, T element) {
        ListElement newElement = new ListElement(element);

        if (index == 0) {
            newElement.next = first;
            first = newElement;
            if (last == null) {
                last = newElement;
            }
            return;
        }

        ListElement prev = getElement(index - 1);
        if (prev == null) throw new IndexOutOfBoundsException();

        newElement.next = prev.next;
        prev.next = newElement;

        if (newElement.next == null) {
            last = newElement;
        }
    }

    @Override
    public T remove(int index) {
        if (index == 0) {
            if (first == null) throw new IndexOutOfBoundsException();
            T value = first.value;
            first = first.next;
            if (first == null) {
                last = null;
            }
            return value;
        }

        ListElement prev = getElement(index - 1);
        if (prev == null || prev.next == null) throw new IndexOutOfBoundsException();

        T value = prev.next.value;
        prev.next = prev.next.next;

        if (prev.next == null) {
            last = prev;
        }

        return value;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            ListElement next = first;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public T next() {
                T ret = next.value;
                next = next.next;
                return ret;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return this.toArray(new Object[this.size()]);
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size()) {
            a = (E[]) new Object[size()];
        }
        int i = 0;
        for (T t : this) {
            a[i++] = (E) t;
        }
        return a;
    }

    @Override
    public boolean add(T e) {
        ListElement newListElement = new ListElement(e);
        if (first == null) {
            first = newListElement;
            last = first;
        } else {
            last.next = newListElement;
            last = last.next;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c)
            this.add(t);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean r = true;
        for (Object o : c)
            r = r || this.remove(o);
        return r;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        first = null;
        last = null;
    }

    @Override
    public T get(int index) {
        ListElement element = getElement(index);
        if (element == null) throw new IndexOutOfBoundsException();
        return element.value;
    }

    @Override
    public int indexOf(Object o) {
        if (isEmpty())
            return -1;
        ListElement current = first;
        int index = 0;
        while (current != null) {
            if (current.value.equals(o))
                return index;
            index++;
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (isEmpty())
            return -1;
        ListElement current = first;
        int index = 0;
        int lastFoundIndex = -1;
        while (current != null) {
            if (current.value.equals(o))
                lastFoundIndex = index;
            index++;
            current = current.next;
        }
        return lastFoundIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private ListElement getElement(int index) {
        if (index < 0) return null;
        ListElement current = first;
        while (current != null) {
            if (index == 0)
                return current;
            index--;
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean contains(Object o) {
        for (T t : this) {
            if (t.equals(o)) return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (first == null) return false;

        if (first.value.equals(o)) {
            first = first.next;
            if (first == null) last = null;
            return true;
        }

        ListElement current = first;
        while (current.next != null) {
            if (current.next.value.equals(o)) {
                current.next = current.next.next;
                if (current.next == null) last = current;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public static void main(String[] args) {
        MyLinkedList<String> ll = new MyLinkedList<>();
        ll.add("Hallo");
        ll.add("Welt");
        ll.add(1, "neue");
        ll.set(0, "Hi");
        ll.remove(2);

        for (String s : ll) {
            System.out.println(s);
        }

        System.out.println("Größe: " + ll.size());
    }
}

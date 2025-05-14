package idh.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {

    /**
     * Helper class for the list elements
     */
    private class ListElement {
        T value;
        ListElement next;

        ListElement(T value) {
            this.value = value;
        }
    }

    /**
     * We only need to store the very first element of our list, because it will
     * know whether there is a next element.
     */
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
        if (c.isEmpty()) return false;
        int idx = index;
        for (T e : c) {
            add(idx++, e);
        }
        return true;
    }

    @Override
    public T set(int index, T element) {
        ListElement elem = getElement(index);
        if (elem == null) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        T old = elem.value;
        elem.value = element;
        return old;
    }

    @Override
    public void add(int index, T element) {
        int sz = size();
        if (index < 0 || index > sz) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        ListElement newElem = new ListElement(element);
        if (index == 0) {
            newElem.next = first;
            first = newElem;
            if (last == null) {
                last = newElem;
            }
        } else {
            ListElement prev = getElement(index - 1);
            newElem.next = prev.next;
            prev.next = newElem;
            if (newElem.next == null) {
                last = newElem;
            }
        }
    }

    @Override
    public T remove(int index) {
        int sz = size();
        if (index < 0 || index >= sz) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        T removed;
        if (index == 0) {
            removed = first.value;
            first = first.next;
            if (first == null) {
                last = null;
            }
        } else {
            ListElement prev = getElement(index - 1);
            ListElement toRemove = prev.next;
            removed = toRemove.value;
            prev.next = toRemove.next;
            if (prev.next == null) {
                last = prev;
            }
        }
        return removed;
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
        return toArray(new Object[size()]);
    }

    @Override
    public <E> E[] toArray(E[] a) {
        int sz = size();
        if (a.length < sz) {
            a = (E[]) new Object[sz];
        }
        int i = 0;
        for (T e : this) {
            a[i++] = (E) e;
        }
        return a;
    }

    @Override
    public boolean add(T e) {
        ListElement newElem = new ListElement(e);
        if (first == null) {
            first = last = newElem;
        } else {
            last.next = newElem;
            last = newElem;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;
        for (Object o : c) {
            while (remove(o)) {
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        first = last = null;
    }

    @Override
    public T get(int index) {
        ListElement elem = getElement(index);
        if (elem == null) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return elem.value;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (ListElement curr = first; curr != null; curr = curr.next) {
            if (curr.value.equals(o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastFound = -1;
        int index = 0;
        for (ListElement curr = first; curr != null; curr = curr.next) {
            if (curr.value.equals(o)) {
                lastFound = index;
            }
            index++;
        }
        return lastFound;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        if (first.value.equals(o)) {
            first = first.next;
            if (first == null) {
                last = null;
            }
            return true;
        }
        ListElement prev = first;
        ListElement curr = first.next;
        while (curr != null) {
            if (curr.value.equals(o)) {
                prev.next = curr.next;
                if (prev.next == null) {
                    last = prev;
                }
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    /**
     * Internal method to get the list element (not the value) of the list at the
     * specified index position.
     */
    private ListElement getElement(int index) {
        if (index < 0) return null;
        ListElement current = first;
        while (current != null && index > 0) {
            current = current.next;
            index--;
        }
        return current;
    }

    public static void main(String[] args) {
        MyLinkedList<String> ll = new MyLinkedList<>();
        ll.add("Hallo");
        ll.add("Welt");
        ll.add("Welt");
        System.out.println(ll.get(0));
        for (String s : ll) {
            System.out.println(s);
        }
    }
}

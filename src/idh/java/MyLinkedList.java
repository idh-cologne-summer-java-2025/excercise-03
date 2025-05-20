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
	    if (index < 0 || index > size()) {
	        throw new IndexOutOfBoundsException();
	    }
	    if (c.isEmpty()) return false;

	    ListElement current = first;
	    ListElement before = null;

	    for (int i = 0; i < index; i++) {
	        before = current;
	        current = current.next;
	    }

	    ListElement firstNew = null;
	    ListElement lastNew = null;

	    for (T element : c) {
	        ListElement newNode = new ListElement(element);
	        if (firstNew == null) {
	            firstNew = newNode;
	        } else {
	            lastNew.next = newNode;
	        }
	        lastNew = newNode;
	    }

	    if (before != null) {
	        before.next = firstNew;
	    } else {
	        first = firstNew;
	    }

	    lastNew.next = current;

	    if (current == null) {
	        last = lastNew;
	    }

	    return true;
	}


	@Override
	public T set(int index, T element) {
	    ListElement target = getElement(index);
	    if (target == null) throw new IndexOutOfBoundsException();
	    T oldValue = target.value;
	    target.value = element;
	    return oldValue;
	}


	@Override
	public void add(int index, T element) {
	    if (index < 0 || index > size()) throw new IndexOutOfBoundsException();

	    ListElement newNode = new ListElement(element);

	    if (index == 0) {
	        newNode.next = first;
	        first = newNode;
	        if (last == null) last = newNode; // list is empty
	        return;
	    }

	    ListElement prev = getElement(index - 1);
	    newNode.next = prev.next;
	    prev.next = newNode;

	    if (newNode.next == null) {
	        last = newNode;
	    }
	}


	@Override
	public T remove(int index) {
	    if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();

	    T removedValue;

	    if (index == 0) {
	        removedValue = first.value;
	        first = first.next;
	        if (first == null) last = null;
	        return removedValue;
	    }

	    ListElement prev = getElement(index - 1);
	    ListElement toRemove = prev.next;
	    removedValue = toRemove.value;
	    prev.next = toRemove.next;

	    if (prev.next == null) {
	        last = prev;
	    }

	    return removedValue;
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
		}
		
		else {
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
	}

	@Override
	public T get(int index) {
		return getElement(index).value;
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
		return new ListIterator<T>() {

			ListElement previous = null;
			ListElement next = first;
			int index;

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public T next() {
				previous = next;
				T ret = next.value;
				next = next.next;
				index++;
				return ret;
			}

			@Override
			public boolean hasPrevious() {
				return false;
			}

			@Override
			public T previous() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int nextIndex() {
				return index + 1;
			}

			@Override
			public int previousIndex() {
				return index - 1;
			}

			@Override
			public void remove() {
				previous.next = next.next;
			}

			@Override
			public void set(T e) {
				next.value = e;
			}

			@Override
			public void add(T e) {
				throw new UnsupportedOperationException();
			}

		};
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}


	/**
	 * Internal method to get the list element (not the value) of the list at the
	 * specified index position.
	 * 
	 * @param index
	 * @return
	 */
	private ListElement getElement(int index) {
		if (isEmpty())
			return null;
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
		// Diese Methode können Sie erst einmal ignorieren
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// Diese Methode können Sie erst einmal ignorieren
		return false;
	}
	
	public static void main(String[] args) {
	    MyLinkedList<String> list = new MyLinkedList<>();

	    // Test: add(T e)
	    list.add("Hallo");
	    list.add("Welt");
	    list.add("Java");

	    // Test: size()
	    System.out.println("Größe: " + list.size()); // Erwartet: 3

	    // Test: get(index)
	    System.out.println("Element an Index 1: " + list.get(1)); // Erwartet: Welt

	    // Test: add(index, T)
	    list.add(1, "NEU");
	    System.out.println("Element an Index 1 nach Einfügen: " + list.get(1)); // Erwartet: NEU
	    System.out.println("Element an Index 2: " + list.get(2)); // Erwartet: Welt

	    // Test: remove(index)
	    String entfernt = list.remove(1);
	    System.out.println("Entferntes Element: " + entfernt); // Erwartet: NEU
	    System.out.println("Element an Index 1 nach Entfernen: " + list.get(1)); // Erwartet: Welt

	    // Test: set(index, T)
	    String alt = list.set(1, "Geändert");
	    System.out.println("Ersetztes Element: " + alt); // Erwartet: Welt
	    System.out.println("Neuer Wert an Index 1: " + list.get(1)); // Erwartet: Geändert

	    // Test: addAll(index, Collection)
	    java.util.List<String> neu = java.util.Arrays.asList("X", "Y");
	    list.addAll(1, neu);
	    System.out.println("Nach addAll an Index 1:");
	    for (String s : list) {
	        System.out.println(s);
	    }
	}

}

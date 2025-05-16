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

	public int size() {
	    int count = 0;
	    ListElement current = first;
	    while (current != null) {
	        count++;
	        current = current.next;
	    }
	    return count;
	}

	public boolean addAll(int index, Collection<? extends T> c) {
	    if (index < 0 || index > size()) {
	        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
	    }

	    if (c.isEmpty()) {
	        return false;
	    }

	    ListElement current = getElement(index - 1); // Element before the insertion point
	    ListElement next = (current == null) ? first : current.next; // Element at the insertion point

	    for (T element : c) {
	        ListElement newElement = new ListElement(element); // Create a new ListElement
	        if (current == null) {
	            // Inserting at the beginning
	        } else {
	            current.next = newElement;
	        }
	        current = newElement;
	    }

	    if (next == null) {
	        // If inserting at the end, update the last reference
	        last = current;
	    } else {
	        current.next = next;
	    }

	    return true;
	}

	@Override
	public T set(int index, T element) {
		ListElement current = getElement(index);
		if (current != null) {
			T oldValue = current.value;
			current.value = element;
			return oldValue;
		}
		return null;
	}

	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
		}

		ListElement newElement = new ListElement(element);
		if (index == 0) {
			newElement.next = first;
			first = newElement;
			if (last == null) {
				last = first;
			}
		} else {
			ListElement current = getElement(index - 1);
			newElement.next = current.next;
			current.next = newElement;
			if (newElement.next == null) {
				last = newElement;
			}
		}
	}

	@Override
	public T remove(int index) {
	    if (index < 0 || index >= size()) {
	        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
	    }

	    ListElement removedElement;

	    if (index == 0) {
	        removedElement = first;
	        first = first.next;
	        if (first == null) {
	            last = null; // List is now empty
	        }
	    } else {
	        ListElement previous = getElement(index - 1);
	        removedElement = previous.next;
	        previous.next = removedElement.next;
	        if (removedElement.next == null) {
	            last = previous; // Removed the last element
	        }
	    }

	    return removedElement.value;
	}

	@Override
	public boolean isEmpty() {
		
		return first == null;
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
	public Object[] toArray() {
	    Object[] array = new Object[size()];
	    ListElement current = first;
	    int index = 0;
	    while (current != null) {
	        array[index++] = current.value;
	        current = current.next;
	    }
	    return array;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E> E[] toArray(E[] a) {
	    if (a.length < size()) {
	        a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size());
	    }
	    int i = 0;
	    Object[] result = a;
	    for (T t : this) {
	        result[i++] = t;
	    }
	    if (a.length > size()) {
	        a[size()] = null; 
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
		
		return false;
	}

	@Override
	public boolean remove(Object o) {
		
		return false;
	}
	
	public static void main(String[] args) {
		MyLinkedList<String> ll = new MyLinkedList<String>();
		ll.add("Hallo");
		ll.add("Welt");
		ll.add("Welt");
		ll.get(0);
		for (String s : ll) {
			System.out.println(s);
		}

	}
}

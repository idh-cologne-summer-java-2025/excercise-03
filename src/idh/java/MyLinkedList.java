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
		int counter = 0;
		while(this.iterator().hasNext()) {
			counter++;
		}
		return counter;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
/*		- get collection 
		- iterate over 
	    - put in list at specified index, shift all elements to the right if necessary, return true if list changed
		- Does it even make sense to use the already existing method addAll? Seems redundant
*/
		int counter  = 0;
	
		while (this.iterator().hasNext()){
			counter++;
			if(counter==index) {
				this.addAll(counter, c); //if index is reached, add all elements starting from that index
			}
			return true;
		}
		return false;
	}

	@Override
	public T set(int index, T element) {
		/* TODO: Implement
		- create new Element
		- access element at index
		- make oldElement.next point at new Element
		*/
		ListElement l = new ListElement(element);
		T rep = (T) this.getElement(index);
		for(T t: this) {
			if(index==this.indexOf(t)) {
				rep = (T) l; //what should the replaced value point to? Can't be null even though it will be deleted afterwards
			}
		}
		return rep;
	}

	@Override
	public void add(int index, T element) {
		// TODO: Implement
//		create new Node element
//		add to list at index 
		ListElement l = new ListElement(element);
//		this.add(index, (T) l);
		if(index==0) {
			l.next = first;
			first = l;
		} else {
			ListElement current = getElement(index-1);
			l.next = current.next;
			current.next = l;
		} 
	}

	@Override
	public T remove(int index) {
		/* TODO: Implement
		 * - iterate over List
		 * - reach List [index]
		 * - make List[index-1].next point elsewhere/or at List[index]?*/
		for(T t: this) {
			if(this.indexOf(t)==index) {
				ListElement prev = this.getElement(index-1); //ListEle prev pointing 1 index in front of he current element
				this.getElement(index).next = null; //set current element to null, discard via Java GC
				prev = prev.next; //set prev.next (current) as new node
				
			}
		}
		return null;
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
		MyLinkedList<String> ll = new MyLinkedList<String>();
		ll.add("Hallo");
		ll.add("Welt");
		ll.add("Welt");
		ll.get(0);
		for (String s : ll) {
			System.out.println(s);
		}
//		does nothing apparently
		System.out.println(ll.size());

	}
}

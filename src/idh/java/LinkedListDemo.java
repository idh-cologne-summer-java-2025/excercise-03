package idh.java;

public class LinkedListDemo {

/*	public static void main(String[] args) {
		MyLinkedList<String> ll = new MyLinkedList<String>();
		ll.add("Hallo");
		ll.add("Welt");
		ll.add("Welt");
		ll.get(0);
		for (String s : ll) {
			System.out.println(s);
		}*/
		
		public static void main(String[] args) {
			MyLinkedList<String> ll = new MyLinkedList<>();
			ll.add("Hallo");
			ll.add("Welt");
			ll.add("Java");
			ll.add(1, "neues Element");
			System.out.println("Size: " + ll.size());
			System.out.println("Element at index 2: " + ll.get(2));
			ll.remove(0);
			ll.set(1, "geändert");
			for (String s : ll) {
				System.out.println(s);
			}
	}

}

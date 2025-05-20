package idh.java;

public class LinkedListDemo {

	public static void main(String[] args) {
		MyLinkedList<String> ll = new MyLinkedList<String>();
		ll.add("Hallo");
		ll.add("Welt");
		ll.add("Welti");
		ll.get(2);
		for (String s : ll) {
			System.out.println(s);
		}

	}

}

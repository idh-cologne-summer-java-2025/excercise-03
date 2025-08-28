package idh.java;

public class LinkedListDemo {

	public static void main(String[] args) {
		MyLinkedList<String> ll = new MyLinkedList<>();
		ll.add("Hallo");
		ll.add("aus");
		ll.add("Dracula");
		ll.add(1, "der");
		System.out.println("listen größe" + ll.size());
		for (String s : ll) {
			System.out.println(s);
		}

	}

}

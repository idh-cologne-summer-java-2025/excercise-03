package idh.java;

public class LinkedListDemo {

	public static void main(String[] args) {
		MyLinkedList<String> ll = new MyLinkedList<String>();
		ll.add("Hallo");
		ll.add("Welt");
		ll.add("Welt");
		ll.get(0);
		ll.add("X"); 
		ll.add(2, "Hallo"); //setzt das zweite hallo an den zweiten platz (0,1,2,3)
	//	ll.remove(2); entfernt den zweiten platz


		for (String s : ll) {
			System.out.println(s);
		}

	}

}

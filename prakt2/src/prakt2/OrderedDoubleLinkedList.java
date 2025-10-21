package prakt2;

public class OrderedDoubleLinkedList<T  extends Comparable<T>> extends DoubleLinkedList<T > implements OrderedListADT<T>{
	
	public void add(T elem) {
	    Node<T> berria = new Node<T>(elem);

	    if (isEmpty()) {
	        last = berria;
	        last.next = last;
	        last.prev = last;
	    } else {
	        Node<T> current = last.next;
	        boolean aurkitua = false;

	        do {
	            if (current.data.compareTo(elem) > 0) {
	                // Insertar antes de current
	                berria.next = current;
	                berria.prev = current.prev;
	                current.prev.next = berria;
	                current.prev = berria;

	                if (current == last.next) {
	                    last.next = berria; // nuevo primero
	                }

	                count++;
	                aurkitua = true;
	                break; // salir del bucle
	            }
	            current = current.next;
	        } while (current != last.next); // recorre toda la lista circular

	        if (!aurkitua) {
	            // Insertar al final
	            berria.next = last.next;
	            berria.prev = last;
	            last.next.prev = berria;
	            last.next = berria;
	            last = berria;
	            count++;
	        }
	    }
	}

	public OrderedDoubleLinkedList<T> intersection(OrderedDoubleLinkedList<T> zerrenda){
		// KODEA OSATU ETA KOSTUA KALKULATU
		
		return null;

	}


}

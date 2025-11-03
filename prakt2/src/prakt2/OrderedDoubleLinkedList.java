package prakt2;

public class OrderedDoubleLinkedList<T  extends Comparable<T>> extends DoubleLinkedList<T > implements OrderedListADT<T>{
	
	@Override
	public void add(T elem) {
	    Node<T> berria = new Node<>(elem);

	    // leheenengo kasua: zerrenda hutsa O(1);
	    if (isEmpty()) {
	        last = berria;
	        last.next = last;
	        last.prev = last;
	        count = 1;
	        return;
	    }

	    Node<T> current = last.next; 
	    // bigarren kasua: elementu txikiena → lehenengoan O(1)
	    if (elem.compareTo(current.data) < 0) {
	        berria.next = current;
	        berria.prev = last;
	        last.next = berria;
	        current.prev = berria;
	        last.next = berria; // nuevo primero
	        count++;
	        return;
	    }

	    // hirugarren kasua: elementu handiena → azkenean O(1)
	    if (elem.compareTo(last.data) >= 0) {
	        berria.next = last.next;
	        berria.prev = last;
	        last.next.prev = berria;
	        last.next = berria;
	        last = berria;
	        count++;
	        return;
	    }

	    // kasu orokorra: erdian O(n-1)
	    while (current.next != last.next && current.next.data.compareTo(elem) < 0) {
	        current = current.next;
	    }

	    berria.next = current.next;
	    berria.prev = current;
	    current.next.prev = berria;
	    current.next = berria;
	    count++;
	}


	public OrderedDoubleLinkedList<T> intersection(OrderedDoubleLinkedList<T> zerrenda) {
	    OrderedDoubleLinkedList<T> emaitza = new OrderedDoubleLinkedList<>();

	    
	    if (this.isEmpty() || zerrenda == null || zerrenda.isEmpty()) {
	        return emaitza; // lista vacía
	    }

	    Node<T> lista1 = this.last.next;      
	    Node<T> lista2 = zerrenda.last.next;   

	    int tam1 = this.count;
	    int tam2 = zerrenda.count;

	    // bi listak zeharkatu eta elementu komun guztiak bilatu O(n+m)
	    while (tam1 > 0 && tam2 > 0) {
	        int Konparaketa = lista1.data.compareTo(lista2.data);

	        if (Konparaketa == 0) {
	            
	            emaitza.add(lista1.data);

	            lista1 = lista1.next;
	            lista2 = lista2.next;
	            tam1--;
	            tam2--;
	        } else if (Konparaketa < 0) {
	            
	            lista1 = lista1.next;
	            tam1--;
	        } else {
	            
	            lista2 = lista2.next;
	            tam2--;
	        }
	    }

	    return emaitza;
	}



}

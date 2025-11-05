package prakt2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {
	
	public void addToFront(T elem) {
	// hasieran gehitu
		// KODEA OSATU ETA KOSTUA KALKULATU
		Node<T> berria= new Node<T>(elem);
		if(isEmpty()) {
			last=berria;
			last.next=last;
			last.prev=last;
		}else {
			berria.next=last.next;
			last.next.prev=berria;
			berria.prev=last;
			last.next=berria;
		}
		count++;
		//KOSTUA=KONSTANTEA O(1)
	}

	public void addToRear(T elem) {
	// bukaeran gehitu
		// KODEA OSATU ETA KOSTUA KALKULATU
		Node<T> berria= new Node<T>(elem);
		if(isEmpty()) {
			last=berria;
			last.next=last;
			last.prev=last;
		}else {
			berria.next=last.next;
			last.next.prev=berria;
			berria.prev=last;
			last.next=berria;
			last=berria;
		}
		count++;
	}
	
	public void addAfter(T elem, T target) {
		// KODEA OSATU ETA KOSTUA KALKULATU
		if(isEmpty()) {
			throw new NoSuchElementException("Errorea: zerrenda hutsa");
		}
		Node<T> berria =new Node<T>(elem);
		Node<T> current= last.next;
		boolean aurkitua=false;
		
		do {
			if(current.data.equals(target)) {
				if(current==last) {
					addToRear(elem);
				}else {
					berria.next=current.next;
					current.next.prev=berria;
					berria.prev=current;
					current.next=berria;
					count++;
				}
				aurkitua=true;
			}else {
				current=current.next;
			}
		}while(current!=last.next && !aurkitua);
		if(!aurkitua) {
			throw new NoSuchElementException("Errorea: ez dago elementu hori");
		}
	}//KOSTUA=LINEALA O(n) kasu txarrenean, n=count;
	
	@Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = last;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T elem = current.data;
                current = current.prev;
                return elem;
            }
        };
    }

    // --- toString ---
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = last;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.prev;
        }
        sb.append("]");
        return sb.toString();
    }
}

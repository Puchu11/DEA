package prakt2;

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

}

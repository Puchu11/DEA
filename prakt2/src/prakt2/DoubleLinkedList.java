package prakt2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements ListADT<T> {

	// Atributuak
	protected Node<T> last;  // azkenengoaren erreferentzia
	protected String deskr;  // deskribapena
	protected int count;
	public DoubleLinkedList() {
		last = null;
		deskr = "";
		count = 0;
	}
	
	public void setDeskr(String ize) {
	  deskr = ize;
	}

	public String getDeskr() {
	  return deskr;
	}
	
	public T removeFirst() {
	// listako lehen elementua kendu da
	// Aurrebaldintza: 
		// KODEA OSATU ETA KOSTUA KALKULATU
		if(isEmpty()) {
			throw new NoSuchElementException("Errorea: zerrenda hutsa");
		}
		Node<T> lehenengoa= last.next;
		if(count==1) {
			last=null;
		}else {
			last.next=lehenengoa.next;
			lehenengoa.next.prev=last;
		}
		count--;
		return lehenengoa.data;
		//KOSTUA=CONSTANTEA O(1)
		
	}

	public T removeLast() {
		// listako azken elementua kendu da
		// Aurrebaldintza: 
			// KODEA OSATU ETA KOSTUA KALKULATU
			if(isEmpty()) {
				throw new NoSuchElementException("Errorea: zerrenda hutsa");
			}
			Node<T> azkena= last;
			if(count==1) {
				last=null;
			}
			else {
				last.prev.next=last.next;
				last.next.prev=last.prev;
				last=last.prev;
			}
			count--;
			return azkena.data;
		}
	//KOSTUA=CONSTANTEA O(1)


	public T remove(T elem) {
		// Aurrebaldintza: 
		// Balio hori listan baldin badago, bere lehen agerpena ezabatuko dut. Kendutako objektuaren erreferentzia 
	        //  bueltatuko du (null ez baldin badago)

			// KODEA OSATU ETA KOSTUA KALKULATU
			if (isEmpty()) {
				throw new NoSuchElementException("Errorea: zerrenda hutsa");
			}
			Node<T> current = last.next; // lehenengo elementura
				boolean aurkitua=false;
				do {
					if(current.data.equals(elem)) {
						aurkitua=true;
					}else {
						current = current.next; // Hurrengo elementura
					}
				} while (current != last.next && !aurkitua);
				// Elementua aurkitu da
				if(!aurkitua) {
					throw new NoSuchElementException("Errorea: ez dago elementu hori");
				}else {
				if(count==1) {
					last=null;
					count--;
				}
				//azkena bada
				else if(current==last) {
					removeLast();
				}
				//lehenengoa bada
				else if(current==last.next) {
					removeFirst();
				}
				//erdian
				else {
					current.prev.next=current.next;
					current.next.prev=current.prev;
					count--;
				}
				return current.data; // Elementua aurkitu da
			}
			//KOSTUA=O(n) kasu txarrenean, n=count;
	        };
	public void removeAll(T elem) {
		if (isEmpty()) {
			throw new NoSuchElementException("Errorea: zerrenda hutsa");
		}
		Node<T> current = last.next; // Lehenengo elementura
		int luzera=count;
		while(count >0 && luzera>0) {
			luzera--;
			if (current.data.equals(elem)) {
				if (count==1) {
					last=null;
					count--;
				}
				// Azkena bada
				else if (current==last) {
					removeLast();
				}else if(current==last.next) {
					removeFirst();
					current=last.next;
				}else {
					Node<T> temp=current.next;
					current.prev.next=current.next;
					current.next.prev=current.prev;
					current=temp;
					count--;
				}
			}else {
				current = current.next; // Hurrengo elementura
			}
		}
	}
	public T first() {
	// listako lehen elementua ematen du
	   // KODEA OSATU ETA KOSTUA KALKULATU
		return last.next.data;
	}

	public T last() {
		// listako azken elementua ematen du
		   // KODEA OSATU ETA KOSTUA KALKULATU
			return last.data;
		}

	public DoubleLinkedList<T> clone(){
		// Zerrendaren kopia bat itzultzen du (ez du punteroa bikoizten)
	   // KODEA OSATU ETA KOSTUA KALKULATU		
	} 

	public boolean contains(T elem) {
	// Egiazkoa bueltatuko du aurkituz gero, eta false bestela
  		// KODEA OSATU ETA KOSTUA KALKULATU      
		if (isEmpty()) {return false;}
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			if(it.next().equals(elem)) {
				return true; // Elementua aurkitu da
			}
		}
		
		return false; // Elementua ez da aurkitu
		
	}// KOSTUA=O(n) kasu txarrenean, n=count;

	

	public T find(T elem) {
		// Elementua bueltatuko du aurkituz gero, eta null bestela
			boolean aurkitua=false;
			Iterator<T> it = iterator();
			while(it.hasNext() && !aurkitua) {
				if(it.next().equals(elem)) {
					aurkitua=true;
				}
			}
			if(aurkitua) {
				return elem;
			}else {
				return null;
			}
		}//KOSTUA=O(n), kasu txarrenean, n=count;

	public boolean isEmpty() { 
	// KODEA OSATU ETA KOSTUA KALKULATU
		if(count ==0) {
			return true;
			
		}else {
			return false;
		}
		//KOSTUA=KONSTANTEA O(1)
	}

	public int size() { 
	// KODEA OSATU ETA KOSTUA KALKULATU
		return count;
	}
	//KOTUA=KOSTANTEA O(1)
	/** Return an iterator to the stack that iterates through the items . */ 
	   public Iterator<T> iterator() { return new ListIterator(); } 

	   // an iterator, doesn't implement remove() since it's optional 
	   private class ListIterator implements Iterator<T> { 
		   
		   private Node<T> current = last != null ? last.next : null; // lehenengo elementua
	       private boolean Hasieran = true; // zerrendaren hasieran gauden ala ez
	       
	       public boolean hasNext()  { 
	    	if (isEmpty()) { return false;} // zerrenda hutsa bada
	    	return current != null && (Hasieran || current != last.next); 
	       }
	       
	       public T next() {
	           if (!hasNext()) throw new NoSuchElementException();
	           T elem = current.data;
	           current = current.next; 
	           Hasieran = false;
	           return elem;
	       }

	       public void remove() { 
	           throw new UnsupportedOperationException();  
	       }
		
	   } // private class
		
		
		public void adabegiakInprimatu() {
			System.out.println(this.toString());
		}

		
		@Override
		public String toString() {
			String result = new String();
			Iterator<T> it = iterator();
			while (it.hasNext()) {
				T elem = it.next();
				result = result + "[" + elem.toString() + "] \n";
			}	
			return "DoubleLinkedList " + result + "]";
		}

}

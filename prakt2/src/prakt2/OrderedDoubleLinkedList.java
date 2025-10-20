package prakt2;

public class OrderedDoubleLinkedList<T extends Comparable<T>> extends DoubleLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T elem){
		Node<T> berria = new Node<T>(elem);
		// empty list -> set as only node
		if (isEmpty()) {
			last = berria;
			last.next = last;
			last.prev = last;
			count++;
			return;
		}
		// if elem is greater or equal than last -> add at end
		if (last.data.compareTo(elem) <= 0) {
			// insert after last (addLast)
			berria.next = last.next;
			last.next.prev = berria;
			berria.prev = last;
			last.next = berria;
			last = berria;
			count++;
			return;
		}
		// otherwise find insertion point (first node with data > elem) and insert before it
		Node<T> current = last.next; // first
		do {
			if (current.data.compareTo(elem) > 0) {
				// insert before current
				berria.next = current;
				berria.prev = current.prev;
				current.prev.next = berria;
				current.prev = berria;
				// if inserting at first, update last.next
				if (current == last.next) {
					last.next = berria;
				}
				count++;
				return;
			}
			current = current.next;
		} while (current != last.next);
	}

	public OrderedDoubleLinkedList<T> intersection(OrderedDoubleLinkedList<T> zerrenda){
		OrderedDoubleLinkedList<T> res = new OrderedDoubleLinkedList<>();
		if (this.isEmpty() || zerrenda.isEmpty()) return res;
		java.util.Iterator<T> it1 = this.iterator();
		java.util.Iterator<T> it2 = zerrenda.iterator();
		T a = null, b = null;
		if (it1.hasNext()) a = it1.next();
		if (it2.hasNext()) b = it2.next();
		while (a != null && b != null) {
			int cmp = a.compareTo(b);
			if (cmp == 0) {
				res.add(a);
				// advance both
				a = it1.hasNext() ? it1.next() : null;
				b = it2.hasNext() ? it2.next() : null;
			} else if (cmp < 0) {
				a = it1.hasNext() ? it1.next() : null;
			} else {
				b = it2.hasNext() ? it2.next() : null;
			}
		}
		return res;
	}

}
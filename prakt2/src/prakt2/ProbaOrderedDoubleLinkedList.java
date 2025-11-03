package prakt2;

import java.util.Iterator;

public class ProbaOrderedDoubleLinkedList {	

    public static void visualizarNodos(OrderedDoubleLinkedList<Integer> l) {
        if (l.isEmpty()) {
            System.out.println("Zerrenda hutsa");
        } else {
            Iterator<Integer> it = l.iterator();
            while (it.hasNext()) {
                Integer num = it.next();
                System.out.print(num + " ");
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        
        OrderedDoubleLinkedList<Integer> l = new OrderedDoubleLinkedList<Integer>();
        l.add(1);
        l.add(3);
        l.add(6);
        l.add(7);
        l.add(9);
        l.add(0);
        l.add(20);
        try {
            l.remove(7);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.print("Lista ............... ");
        visualizarNodos(l);
        System.out.println("Elementu-kopurua: " + l.size());
                
        System.out.println("Proba Find ...............");
        System.out.println("20? " + l.find(20));
        System.out.println("9? " + l.find(9));
        System.out.println("0? " + l.find(0));
        System.out.println("7? " + l.find(7));
        
        OrderedDoubleLinkedList<Pertsona> l2 = new OrderedDoubleLinkedList<Pertsona>();
        l2.add(new Pertsona("jon", "1111"));
        l2.add(new Pertsona("ana", "7777"));
        l2.add(new Pertsona("amaia", "3333"));
        l2.add(new Pertsona("unai", "8888"));
        l2.add(new Pertsona("pedro", "2222"));
        l2.add(new Pertsona("olatz", "5555"));

        l2.remove(new Pertsona("", "8888"));

        System.out.print("Lista ............... ");
        l2.adabegiakInprimatu();
        System.out.println("Elementu-kopurua: " + l2.size());
                
        System.out.println("Proba Find ...............");
        System.out.println("2222? " + l2.find(new Pertsona("", "2222")));
        System.out.println("5555? " + l2.find(new Pertsona("", "5555")));
        System.out.println("7777? " + l2.find(new Pertsona("", "7777")));	
        System.out.println("8888? " + l2.find(new Pertsona("", "8888")));	

     
        System.out.println("\n=== PROBA INTERSECTION ===");

        OrderedDoubleLinkedList<Integer> a = new OrderedDoubleLinkedList<Integer>();
        OrderedDoubleLinkedList<Integer> b = new OrderedDoubleLinkedList<Integer>();

        a.add(1);
        a.add(3);
        a.add(5);
        a.add(7);
        a.add(9);
        b.add(2);
        b.add(3);
        b.add(5);
        b.add(6);
        b.add(9);
        b.add(10);

        System.out.print("Lista A: ");
        visualizarNodos(a);
        System.out.print("Lista B: ");
        visualizarNodos(b);

        OrderedDoubleLinkedList<Integer> inter = a.intersection(b);
        System.out.print("Intersekzioa: ");
        visualizarNodos(inter);
        System.out.println("Elementu-kopurua: " + inter.size());

        // Caso extra: una lista vacía
        OrderedDoubleLinkedList<Integer> vacia = new OrderedDoubleLinkedList<Integer>();
        OrderedDoubleLinkedList<Integer> inter2 = a.intersection(vacia);
        System.out.print("A ∩ lista hutsa: ");
        visualizarNodos(inter2);
        System.out.println("Elementu-kopurua: " + inter2.size());
    }
}

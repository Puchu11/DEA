package prakt2;

import java.util.Iterator;


public class ProbaDoubleLinkedList {
	
	public static void visualizarNodos(UnorderedDoubleLinkedList<Integer> l) {
		if(l.isEmpty()) {System.out.println("Zerrenda hutsa");}
		else{
			Iterator<Integer> it = l.iterator();
		
			while (it.hasNext()) {
				Integer num = it.next();
				System.out.print(num);
				System.out.print(" ");
			}
		}
		System.out.println("");
	}
	
	
	public static void main(String[] args)  {
		
		UnorderedDoubleLinkedList<Integer> l =new UnorderedDoubleLinkedList<Integer>();
		UnorderedDoubleLinkedList<Integer> l2 =new UnorderedDoubleLinkedList<Integer>(); //lista hutsa probak egiteko
		l.addToRear(1);
		l.addToRear(3);
		l.addToRear(6);
		l.addToRear(7);
		l.addToRear(9);
		l.addToRear(0);
		l.addToRear(20);
		l.addToFront(8);

		//PROBA ADDTOFRONT ETA ADDTOREAR
		System.out.print(" Lista ...............");
		visualizarNodos(l);
		System.out.println("Elementu-kopurua: " + l.size());
		System.out.println("ADD PROBAK AMAITUTA................");
		
		//PROBA ADDAFTER
		System.out.println("PROBAK ADD AFTER................");
		System.out.println("exception probak addAfter................");
		System.out.println("ez dago elemtu hori:");
		try {
		l.addAfter(1, 2);}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("lista hutsa:");
		try {
			l2.addAfter(1, 2);}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("addAfter probak................");
		System.out.println("hasierako lista:");
		visualizarNodos(l);
		System.out.println("lista berria");
		l.addAfter(33, 20); //bukaeran
		l.addAfter(33, 3); //erdian
		l.addAfter(33, 8); //hasieran
		visualizarNodos(l);
		System.out.println("luzera:"+l.size());
		System.out.println("ADDAFTER PROBAK AMAITUTA................");
		System.out.println("");
		
		//PROBA REMOVE
		//EXCEPTIONS
		System.out.println("REMOVE PROBAK HASI................");
		System.out.println("");
		System.out.println("REMOVE PROBAK EXCEPTIONS................");
		System.out.println("lista hutsa:");
		try {
			l2.removeFirst();}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("elementua ez aurkitua:");
		try {
			l.remove(100);}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//HASIERAKO ETA BUKAERAKO KENKETAK
		System.out.println("");
		System.out.println("HASIERAKO ETA BUKAERAKO KENKETAK................");
		System.out.println("hasierako lista:");
		visualizarNodos(l);
		System.out.println("bukaerako lista:");
		l.removeFirst();
		l.removeLast();
		visualizarNodos(l);
		System.out.println("luzera:"+l.size());
		
		//ELEMENTU BAT KENDU
		System.out.println("ELEMENTU BAT KENDU");
		l.addToFront(40);
		l.addToRear(55);
		System.out.println("hasierako lista:");
		visualizarNodos(l);
		System.out.println("hasieran:");
		l.remove(40);
		visualizarNodos(l);
		System.out.println("bukaeran:");
		l.remove(55);
		visualizarNodos(l);
		System.out.println("erdian");
		l.remove(9);
		visualizarNodos(l);
		System.out.println("luzera:"+l.size());
		System.out.println("REMOVE PROBAK AMAITUTA................");
		
		
		
		
		//PROBA REMOVEALL
		System.out.println("PROBAK REMOVEALL................");
		System.out.println("");
		//EXCEPTION PROBAK
		System.out.println("EXCEPTION PROBAK REMOVE ALL................");
		try {
			l2.removeAll(1);}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//HASIERA/BUKAERAN KENKETAK
		System.out.println("");
		System.out.println("REMOVE ALL(HASIERAN/BUKAERAN)................");
		l.addToFront(41);
		l.addToRear(41);
		System.out.println("hasierako lista:");
		visualizarNodos(l);
		System.out.println("41 kenduta:");
		l.removeAll(41);
		visualizarNodos(l);
		System.out.println("luzera:"+l.size());
		//ERDIAN
		System.out.println("");
		System.out.println("REMOVE ALL(ERDIAN)................");
		l.addAfter(41, 6);
		l.addAfter(41, 6);
		l.addAfter(41, 6);
		l.addAfter(41, 6);
		System.out.println("hasierako lista:");
		visualizarNodos(l);
		System.out.println("41 kenduta:");
		l.removeAll(41);
		visualizarNodos(l);
		System.out.println("luzera:"+l.size());
		
		//DENAK ELEMENTU BERDINAK
		System.out.println("");
		System.out.println("REMOVE ALL(ELEMENTU GUZTIAK)................");
		l2.addToRear(1);
		l2.addToRear(1);
		l2.addToRear(1);
		l2.addToRear(1);
		l2.addToRear(1);
		l2.addToRear(1);
		System.out.println("hasierako lista:");
		visualizarNodos(l2);
		System.out.println("1 kenduta:");
		l2.removeAll(1);
		visualizarNodos(l2);
		System.out.println("luzera:"+l2.size());
		System.out.println("REMOVEALL PROBAK AMAITUTA................");
		
		//PROBA FIND
		System.out.println("FIND PROBAK HASI................");
		visualizarNodos(l);
		System.out.println("");
		System.out.println("1? " + l.find(1));
		System.out.println("9? " + l.find(9));
		System.out.println("0? " + l.find(0));
		System.out.println("8? " + l.find(8));
		System.out.println("7? " + l.find(7));
		System.out.println("33 LEHENENGOA? " + l.find(33));
		System.out.println("7 AZKENA? " + l.find(20));
		System.out.println("FIND PROBAK BUKATU................");
		System.out.println("");
	
		//PROBA CONTAINS
		System.out.println("CONTAINS PROBAK HASI................");
		System.out.println("hasierako lista:");
		visualizarNodos(l);
		System.out.println("");

		// Elementu batzuk probatu
		System.out.println("1? " + l.contains(1));
		System.out.println("9? " + l.contains(20));
		System.out.println("0? " + l.contains(0));
		System.out.println("8? " + l.contains(8));
		System.out.println("7? " + l.contains(7));
		System.out.println("33? " + l.contains(33));
		System.out.println("100? " + l.contains(100));

	}	
}

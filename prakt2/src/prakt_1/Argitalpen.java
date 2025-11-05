package prakt_1;

import java.util.ArrayList;
import prakt2.UnorderedDoubleLinkedList;

public class Argitalpen implements Comparable<Argitalpen> {
	private String id;
	private String titulua;
	private ArrayList<Autorea> autoreak;
	private ArrayList<String> aipamenak;
	
	public Argitalpen(String pId, String pTitulua, ArrayList<Autorea> pAutoreak,ArrayList<String> pAipamenak) {
		this.id=pId;
		this.titulua=pTitulua;
		
		if (pAutoreak!=null){
			this.autoreak=pAutoreak;
		}else {
			this.autoreak=new ArrayList<Autorea>();
		}
		
		if (pAipamenak!=null){
			this.aipamenak=pAipamenak;
		}else {
			this.aipamenak=new ArrayList<String>();
		}
	}

	public String getId() {
		return id;
	}
	public boolean autoreaDauka(Autorea a) {
		return this.getAutoreak().contains(a);
	}
	public boolean aipatzenDu(String a) {
		return this.getAipamenak().contains(a);
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTitulua() {
		return titulua;
	}

	public void setTitulua(String titulua) {
		this.titulua = titulua;
	}

	public ArrayList<Autorea> getAutoreak() {
		return autoreak;
	}

	public void setAutoreak(ArrayList<Autorea> autoreak) {
		this.autoreak = autoreak;
	}

	public ArrayList<String> getAipamenak() {
		return aipamenak;
	}

	public void setAipamenak(ArrayList<String> aipamenak) {
		this.aipamenak = aipamenak;
	}
	public void gehituAutorea(Autorea a) {
		this.autoreak.add(a);
	}
	public void gehituAipamena(String a) {
		this.aipamenak.add(a);
	}
	public void ezabatuAipamena(String a) {
		this.aipamenak.remove(a);
	}
	public void idatziAutoreak() {
		for(Autorea a:autoreak) {
			System.out.print("#" +a.getIzena());
			System.out.print(" ");
		}
		System.out.println("");
	}
	public void idatziAipamenak() {
		if(aipamenak.isEmpty()) {
			System.out.print("ez du aipamenik");
		}else {
			for(String a:aipamenak) {
					System.out.print("#" +a);
					System.out.print(" ");
			}
		System.out.println("");
		}
	}
	@Override
    public int compareTo(Argitalpen other) {
        return this.id.compareTo(other.id);
    }
	public void egileEzabatu(Autorea a){
		autoreak.remove(a);
	}
}

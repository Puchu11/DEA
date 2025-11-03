package prakt_1;

public class Autorea implements Comparable<Autorea>{
	private String id;
	private String izena;
	
	public Autorea(String pId,String pIzena) {
		this.id=pId;
		this.izena=pIzena;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}
	@Override
    public int compareTo(Autorea other) {
        return this.id.compareTo(other.id);
    }
}

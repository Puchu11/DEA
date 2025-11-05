package prakt_1;

import prakt2.UnorderedDoubleLinkedList;

public class Argitalpen implements Comparable<Argitalpen> {
    private String id;
    private String titulua;
    private UnorderedDoubleLinkedList<Autorea> autoreak;
    private UnorderedDoubleLinkedList<String> aipamenak;

    public Argitalpen(String pId, String pTitulua, UnorderedDoubleLinkedList<Autorea> pAutoreak, UnorderedDoubleLinkedList<String> pAipamenak) {
        this.id = pId;
        this.titulua = pTitulua;

        if (pAutoreak != null) {
            this.autoreak = pAutoreak;
        } else {
            this.autoreak = new UnorderedDoubleLinkedList<>();
        }

        if (pAipamenak != null) {
            this.aipamenak = pAipamenak;
        } else {
            this.aipamenak = new UnorderedDoubleLinkedList<>();
        }
    }

    public String getId() {
        return id;
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

    public UnorderedDoubleLinkedList<Autorea> getAutoreak() {
        return autoreak;
    }

    public void setAutoreak(UnorderedDoubleLinkedList<Autorea> autoreak) {
        this.autoreak = autoreak;
    }

    public UnorderedDoubleLinkedList<String> getAipamenak() {
        return aipamenak;
    }

    public void setAipamenak(UnorderedDoubleLinkedList<String> aipamenak) {
        this.aipamenak = aipamenak;
    }

    public boolean autoreaDauka(Autorea a) {
        return this.autoreak.contains(a);
    }

    public boolean aipatzenDu(String a) {
        return this.aipamenak.contains(a);
    }

    public void gehituAutorea(Autorea a) {
        this.autoreak.addToRear(a);
    }

    public void gehituAipamena(String a) {
        this.aipamenak.addToRear(a);
    }

    public void ezabatuAipamena(String a) {
        this.aipamenak.remove(a);
    }

    public void egileEzabatu(Autorea a) {
        this.autoreak.remove(a);
    }

    public void idatziAutoreak() {
        if (autoreak.isEmpty()) {
            System.out.println("ez du autorearik");
        } else {
            for (Autorea a : autoreak) {
                System.out.print("#" + a.getIzena() + " ");
            }
            System.out.println();
        }
    }

    public void idatziAipamenak() {
        if (aipamenak.isEmpty()) {
            System.out.println("ez du aipamenik");
        } else {
            for (String a : aipamenak) {
                System.out.print("#" + a + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int compareTo(Argitalpen other) {
        return this.id.compareTo(other.id);
    }
}

package prakt_1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ArgitaelpenZerrenda {
    private static ArgitaelpenZerrenda ema;

    
    private HashMap<String, Argitalpen> argitalpenak;
    private HashMap<String, Autorea> autoreak;

    private ArgitaelpenZerrenda() {
        argitalpenak = new HashMap<>();
        autoreak = new HashMap<>();
    }

    public static ArgitaelpenZerrenda getEma() {
        if (ema == null) {
            ema = new ArgitaelpenZerrenda();
        }
        return ema;
    }

    public HashMap<String, Argitalpen> getArgitalpenak() {
        return argitalpenak;
    }
    public HashMap<String, Autorea> getAutoreak() {
        return autoreak;
    }
    public int luzera() {
        return argitalpenak.size();
    }
    public void setArgitalpenak(HashMap<String, Argitalpen> argitalpenak) {
        this.argitalpenak = argitalpenak;
    }

    public void datuakKargatu(String publFile, String autoreFile, String publAutoreFile, String aipamenFile) {
        argitalpenak.clear();
        autoreak.clear();

        ArgitalpenakKargatu(publFile);
        System.out.println("Aipamenak kargatuta");

        AutoreakKargatu(autoreFile, publAutoreFile);
        System.out.println("Autores kargatuta eta erlazionatuta.");

        AipamenakKargatu(aipamenFile);
        System.out.println("Aipamenak kargatuta.");
    }

  
    private void ArgitalpenakKargatu(String izena) {
        try (Scanner sarrera = new Scanner(new FileReader(izena))) {
            while (sarrera.hasNext()) {
                String[] parts = sarrera.nextLine().split("#", 2);
                if (parts.length == 2) {
                    String id = parts[0].trim();
                    String titulua = parts[1].trim();
                    Argitalpen publ = new Argitalpen(id, titulua, null, null);
                    argitalpenak.put(id, publ);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    private void AutoreakKargatu(String autoreFile, String publAutoreFile) {

        try (Scanner sarrera = new Scanner(new FileReader(autoreFile))) {
            while (sarrera.hasNext()) {
                String[] parts = sarrera.nextLine().split("#", 2);
                if (parts.length == 2) {
                    String id = parts[0].trim();
                    String izena = parts[1].trim();
                    autoreak.put(id, new Autorea(id, izena));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (Scanner sarrera = new Scanner(new FileReader(publAutoreFile))) {
            while (sarrera.hasNext()) {
                String[] parts = sarrera.nextLine().split("#", 2);
                if (parts.length == 2) {
                    String pubId = parts[0].trim();
                    String autId = parts[1].trim();

                    Argitalpen publ = argitalpenak.get(pubId);
                    Autorea aut = autoreak.get(autId);

                    if (publ != null && aut != null) {
                        publ.gehituAutorea(aut);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void AipamenakKargatu(String izena) {
        try (Scanner sarrera = new Scanner(new FileReader(izena))) {
            while (sarrera.hasNext()) {
                String[] parts = sarrera.nextLine().split("#", 2);
                if (parts.length == 2) {
                    String pubId = parts[0].trim();
                    String aipId = parts[1].trim();

                    Argitalpen publ = argitalpenak.get(pubId);
                    if (publ != null) {
                        publ.gehituAipamena(aipId);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void ArgitalpenGehitu(String id, String titulua) throws ArgitalpenExistitzenDaException{
    	if (id.isBlank()||titulua.isBlank()) {
	        throw new IllegalArgumentException("Id-a eta titulua ezin dira hutzik egon");
	    }
    	if(!id.startsWith("Q")) {
    		throw new IllegalArgumentException("Id Q hasi behar da");
    	}
    	if(argitalpenak.containsKey(id)) {
    		throw new ArgitalpenExistitzenDaException("Argitalpen existitzen da");
    	}
    	Argitalpen a= new Argitalpen(id,titulua,null,null);
    	argitalpenak.put(id, a);
    }
    
    public boolean argitalpenDauka(String id) {
    	return argitalpenak.containsKey(id);
    }
    
    public void aipamenGehitu(String id, String aipamenId) throws ArgitalpenEzAurkituaException,AipatzenDuException{
    	if(!(argitalpenak.containsKey(id))||!(argitalpenak.containsKey(aipamenId))) {
    		throw new ArgitalpenEzAurkituaException("jarri duzun id ez da egokia");
    	}
    	Argitalpen a=argitalpenak.get(id);
    	if(a.aipatzenDu(aipamenId)) {
    		throw new AipatzenDuException("iaz aipatzen du");
    	}
    	a.gehituAipamena(aipamenId);
    }
    
    public void egileGehitu(String aId, String eId) throws ArgitalpenEzAurkituaException,AutoreaEzAukituaException,EgileaDuException{
    	
    	if(!(argitalpenak.containsKey(aId))) {
    		throw new ArgitalpenEzAurkituaException("jarri duzun Argitalpen id ez da egokia");
    	}
    	
    	if(!(autoreak.containsKey(eId))) {
    		throw new AutoreaEzAukituaException("jarri duzun autore id ez da egokia");
    	}
    	Argitalpen a=argitalpenak.get(aId);
    	Autorea b=autoreak.get(eId);
    	if(a.autoreaDauka(b)) {
    		throw new EgileaDuException("iaz egialea du");
    	}
    	a.gehituAutorea(b);
    }
    
    public void aipamenakImprimatu(String id) throws ArgitalpenEzAurkituaException {
    	if(!(argitalpenak.containsKey(id))) {
    		throw new ArgitalpenEzAurkituaException("jarri duzun autore id ez da egokia");
    	}
    	argitalpenak.get(id).idatziAipamenak();
    }
    
    public void autoreakImprimatu(String id) throws ArgitalpenEzAurkituaException{
    	if(!(autoreak.containsKey(id))) {
    		throw new ArgitalpenEzAurkituaException("jarri duzun autore id ez da egokia");
    	}
    	argitalpenak.get(id).idatziAutoreak();
    }
    
    public void egileBatenArgitalpenakImprimatu(String AutoreId) throws AutoreaEzAukituaException{
    	if(!(autoreak.containsKey(AutoreId))) {
    		throw new AutoreaEzAukituaException("jarri duzun autore id ez da egokia");
    	}
    	Autorea b=autoreak.get(AutoreId);
    	for (String argitalpenId : argitalpenak.keySet()) {
    		Argitalpen a=argitalpenak.get(argitalpenId);
    		if(a.autoreaDauka(b)) {
    			System.out.print("#" +a.getTitulua());
    			System.out.print(" ");
    		}
    	}

    }
    
    public void ArgitalpenEzabatau(String id) throws ArgitalpenEzAurkituaException {
    	if(!(argitalpenak.containsKey(id))) {
    		throw new ArgitalpenEzAurkituaException("jarri duzun autore id ez da egokia");
    	}
    	for (String argitalpenId : argitalpenak.keySet()) {
    		Argitalpen a=argitalpenak.get(argitalpenId);
    		if(a.aipatzenDu(id)) {
    			a.ezabatuAipamena(id);
    		}
    	}
    	argitalpenak.remove(id);
    	
    }
    
    public void egileEzabatu(String AutoreId) throws AutoreaEzAukituaException{
    	if(!(autoreak.containsKey(AutoreId))) {
    		throw new AutoreaEzAukituaException("jarri duzun autore id ez da egokia");
    	}
    	Autorea b=autoreak.get(AutoreId);
    	autoreak.remove(AutoreId);
    	for (String argitalpenId : argitalpenak.keySet()) {
    		Argitalpen a=argitalpenak.get(argitalpenId);
    		if(a.autoreaDauka(b)) {
    			a.egileEzabatu(b);
    		}
    	}

    }

    public void eguneratuFitxategiak(String publFile, String publAutFile, String aipFile) {
        try {
        	
        	//gorde argitalpenak eguneratuta
            try (FileWriter fw = new FileWriter(publFile)) {
                for (Argitalpen p : argitalpenak.values()) {
                    fw.write(p.getId() + "#" + p.getTitulua() + "\n");
                }
            }

            // gorde argitalpen eta egilen arremana
            try (FileWriter fw = new FileWriter(publAutFile)) {
                for (Argitalpen p : argitalpenak.values()) {
                    if (p.getAutoreak() != null) {
                        for (Autorea a : p.getAutoreak()) {
                            fw.write(p.getId() + "#" + a.getId() + "\n");
                        }
                    }
                }
            }

            // Gorde aipamenak
            try (FileWriter fw = new FileWriter(aipFile)) {
                for (Argitalpen p : argitalpenak.values()) {
                    if (p.getAipamenak() != null) {
                        for (String aip : p.getAipamenak()) {
                            fw.write(p.getId() + "#" + aip + "\n");
                        }
                    }
                }
            }

            System.out.println("✅ Fitxategi guztiak eguneratuta ondo!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Argitalpen> argitalpenakAlfabetikoki() {
        // Sortzen dugu Arraylista non argitalpenak gordetzen ditugu, hashmap aldatu gabe.
        ArrayList<Argitalpen> kopia = new ArrayList<>(argitalpenak.values());

        // QuickSort batekin arraylistak alfabetikoki ordenatzen ditugu.
        quickSortAlfabetikoki(kopia, 0, kopia.size() - 1);

        return kopia;
    }

    private void quickSortAlfabetikoki(ArrayList<Argitalpen> lista, int ezk, int esk) {
        if (ezk < esk) {
            int ind = zatituAlfabetikoki(lista, ezk, esk);
            quickSortAlfabetikoki(lista, ezk, ind - 1);
            quickSortAlfabetikoki(lista, ind + 1, esk);
        }
    }

    private int zatituAlfabetikoki(ArrayList<Argitalpen> lista, int ezk, int esk) {
        // Lehenengo pozizioko elemntua pibotea izango da
        Argitalpen pibotea = lista.get(ezk);
        int i = ezk;
        int j = esk;

        while (i < j) {
            while (i < j && lista.get(i).getTitulua().compareToIgnoreCase(pibotea.getTitulua()) <= 0) {
                i++;
            }
            while (lista.get(j).getTitulua().compareToIgnoreCase(pibotea.getTitulua()) > 0) {
                j--;
            }
            if (i < j) {
                swap(lista, i, j);
            }
        }

        // pibotea bere posizioan gordetzen dugu
        lista.set(ezk, lista.get(j));
        lista.set(j, pibotea);
        return j;
    }

    private void swap(ArrayList<Argitalpen> lista, int i, int j) {
        Argitalpen tmp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, tmp);
    }
  
}

package prakt_1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import prakt2.UnorderedDoubleLinkedList;

public class ArgitaelpenZerrenda {
    private static ArgitaelpenZerrenda ema;

    private UnorderedDoubleLinkedList<Argitalpen> argitalpenak;
    private HashMap<String, Autorea> autoreak;

    private ArgitaelpenZerrenda() {
        argitalpenak = new UnorderedDoubleLinkedList<>();
        autoreak = new HashMap<>();
    }

    public static ArgitaelpenZerrenda getEma() {
        if (ema == null) {
            ema = new ArgitaelpenZerrenda();
        }
        return ema;
    }

    public UnorderedDoubleLinkedList<Argitalpen> getArgitalpenak() {
        return argitalpenak;
    }

    public HashMap<String, Autorea> getAutoreak() {
        return autoreak;
    }

    public int luzera() {
        return argitalpenak.size();
    }

    public void setArgitalpenak(UnorderedDoubleLinkedList<Argitalpen> lista) {
        this.argitalpenak = lista;
    }

    // === KARGAK ===
    public void datuakKargatu(String publFile, String autoreFile, String publAutoreFile, String aipamenFile) {
        argitalpenak.clear();
        autoreak.clear();

        ArgitalpenakKargatu(publFile);
        AutoreakKargatu(autoreFile, publAutoreFile);
        AipamenakKargatu(aipamenFile);
    }

    private void ArgitalpenakKargatu(String izena) {
        try (Scanner sarrera = new Scanner(new FileReader(izena))) {
            while (sarrera.hasNext()) {
                String[] parts = sarrera.nextLine().split("#", 2);
                if (parts.length == 2) {
                    String id = parts[0].trim();
                    String titulua = parts[1].trim();
                    Argitalpen publ = new Argitalpen(id, titulua, null, null);
                    argitalpenak.addToRear(publ);
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

                    Argitalpen publ = argitalpenak.findById(pubId);
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

                    Argitalpen publ = argitalpenak.findById(pubId);
                    if (publ != null) {
                        publ.gehituAipamena(aipId);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // === METODOAK ===
    public void ArgitalpenGehitu(String id, String titulua) throws ArgitalpenExistitzenDaException {
        if (id.isBlank() || titulua.isBlank()) {
            throw new IllegalArgumentException("Id-a eta titulua ezin dira hutsik egon");
        }
        if (!id.startsWith("Q")) {
            throw new IllegalArgumentException("Id Q hasi behar da");
        }
        if (argitalpenak.existsById(id)) {
            throw new ArgitalpenExistitzenDaException("Argitalpen existitzen da");
        }
        Argitalpen a = new Argitalpen(id, titulua, null, null);
        argitalpenak.addToRear(a);
    }

    public boolean argitalpenDauka(String id) {
        return argitalpenak.existsById(id);
    }

    public void aipamenGehitu(String id, String aipamenId) throws ArgitalpenEzAurkituaException, AipatzenDuException {
        Argitalpen a = argitalpenak.findById(id);
        Argitalpen aip = argitalpenak.findById(aipamenId);

        if (a == null || aip == null) {
            throw new ArgitalpenEzAurkituaException("Id ez da egokia");
        }
        if (a.aipatzenDu(aipamenId)) {
            throw new AipatzenDuException("Iaz aipatzen du");
        }
        a.gehituAipamena(aipamenId);
    }

    public void ArgitalpenEzabatu(String id) throws ArgitalpenEzAurkituaException {
        if (!argitalpenak.existsById(id)) {
            throw new ArgitalpenEzAurkituaException("Id ez da egokia");
        }
        argitalpenak.forEach(a -> {
            if (a.aipatzenDu(id)) a.ezabatuAipamena(id);
        });
        argitalpenak.removeById(id);
    }
}

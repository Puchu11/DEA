package prakt_1;

import java.util.ArrayList;

public class Menu {
		private static Menu ema;

	    private Menu() {
	    }
	    
	    public static Menu getEma() {
	        if (ema == null) {
	            ema = new Menu();
	        }
	        return ema;
	    }

	
	public void ekintzak() {
		boolean irten=false;
		ArgitaelpenZerrenda zerrenda = ArgitaelpenZerrenda.getEma();
		System.out.println("\n===============================");
		System.out.println("");
		zerrenda.datuakKargatu("publications-titles-all.txt", "authors-name-all.txt", "publications-authors-all-final.txt","publications-citedPubs-all.txt");
		while(!irten){
			System.out.println("\n===============================");
			System.out.println("        📚 MENU NAGUSIA");
			System.out.println("===============================");
			System.out.println("1. Argitalpen baten bilaketa (ID bidez)");
			System.out.println("2. Argitalpen berria txertatu (ID eta izenburua emanda)");
			System.out.println("3. Aipamen bat gehitu argitalpen bati (bi ID emanda)");
			System.out.println("4. Egile bat gehitu argitalpen bati (bi ID emanda)");
			System.out.println("5. Argitalpen baten aipamenak erakutsi (ID emanda)");
			System.out.println("6. Argitalpen baten egileak erakutsi (ID emanda)");
			System.out.println("7. Egile baten argitalpenak erakutsi (egilearen ID emanda)");
			System.out.println("8. Argitalpen bat ezabatu (ID emanda)");
			System.out.println("9. Egile bat ezabatu (ID emanda)");
			System.out.println("10. Argitalpenen eta egileen zerrendak fitxategietan gorde");
			System.out.println("11. Argitalpenen zerrenda alfabetikoki erakutsi");
			System.out.println("0. Irten");
			System.out.println("===============================");
			int aukera=teklatua.irakurriInt("Aukeratu aukera bat (0-12)");
			System.out.println("");
			System.out.println("===============================");
			switch(aukera) {
	    		case 0: {
	    			System.out.println("Agur :)");
	    			irten=true;
	    			break;
	    		}
	    		case 1: {
	    			try {
	    				String a=teklatua.irakurriString("Emaidazu id bat");
	    				if(!(zerrenda.getArgitalpenak().containsKey(a))) {
	    					throw new ArgitalpenEzAurkituaException("argitalpena ez aurkitua, saiatu berriro");
	    				}
	    				Argitalpen b=zerrenda.getArgitalpenak().get(a);
	    				System.out.println("#"+b.getTitulua());
	    			}catch(ArgitalpenEzAurkituaException e) {
	    				System.out.println(e.getMessage());
	    			}catch(NullPointerException e) {
	    				System.out.println(e.getMessage());
	    			}
	    			break;
	    		}
	    		case 2: {
	    			try {
	    				String id=teklatua.irakurriString("Emaidazu id bat");
	    				String izenburua=teklatua.irakurriString("Emaidazu bere izenburua");
	    				zerrenda.ArgitalpenGehitu(id, izenburua);
	    				System.out.println(id +"#"+izenburua+"gehituta");
	    			}catch(ArgitalpenExistitzenDaException e) {
	    				System.out.println(e.getMessage());
	    			}catch(IllegalArgumentException e) {
	    				System.out.println(e.getMessage());
	    			}catch(NullPointerException e) {
	    				System.out.println(e.getMessage());
	    			}
	    			break;
	    		}
	    		case 3: {
	    			try {
	    				String argiId=teklatua.irakurriString("Emaidazu Argitalpen id bat");
	    				String aipamenId=teklatua.irakurriString("Emaidazu aipatzen duen argitalpen ida");
	    				zerrenda.aipamenGehitu(argiId, aipamenId);
	    				System.out.println(argiId +"#"+aipamenId+"gehituta");
	    			}catch(ArgitalpenEzAurkituaException e) {
	    				System.out.println(e.getMessage());
	    			}catch(AipatzenDuException e) {
	    				System.out.println(e.getMessage());
	    			}catch(NullPointerException e) {
	    				System.out.println(e.getMessage());
	    			}
	    			
	    			break;
	    		}
	    		case 4: {
	    			try {
	    				String argiId=teklatua.irakurriString("Emaidazu Argitalpen id bat");
	    				String egileId=teklatua.irakurriString("Emaidazu aipatzen duen argitalpen ida");
	    				zerrenda.egileGehitu(argiId, egileId);
	    			}catch(ArgitalpenEzAurkituaException e) {
	    				System.out.println(e.getMessage());
	    			}catch(AutoreaEzAukituaException e) {
	    				System.out.println(e.getMessage());
	    			}catch(EgileaDuException e) {
	    				System.out.println(e.getMessage());
	    			}catch(NullPointerException e) {
	    				System.out.println(e.getMessage());
	    			}
	    			break;
	    		}
	    		case 5: {
	    			try {
	    				String id=teklatua.irakurriString("Emaidazu Argitalpenaren id");
	    				zerrenda.aipamenakImprimatu(id);
	    			}catch(ArgitalpenEzAurkituaException e) {
	    				System.out.println(e.getMessage());
	    			}
	    			break;
	    		}
	    		case 6: {
	    			try {
	    				String id=teklatua.irakurriString("Emaidazu Argitalpenaren id");
	    				zerrenda.autoreakImprimatu(id);
	    			}catch(ArgitalpenEzAurkituaException e) {
	    				System.out.println(e.getMessage());
	    			}
	    			break;
	    		}
	    		case 7: {
	    			try {
	    				String id=teklatua.irakurriString("Emaidazu egileare id");
	    				zerrenda.egileBatenArgitalpenakImprimatu(id);
	    			}
	    			catch(AutoreaEzAukituaException e) {
	    				System.out.println(e.getMessage());
	    			}
	    		}
	    			break;
	    		
	    		case 8: {
	    			try {
	    				String id=teklatua.irakurriString("Emaidazu Argitalpenaren id");
	    				zerrenda.ArgitalpenEzabatau(id);
	    			}
	    			catch(ArgitalpenEzAurkituaException e) {
	    				System.out.println(e.getMessage());
	    			}
	    			break;
	    		}
	    		case 9: {
	    			try {
	    				String id=teklatua.irakurriString("Emaidazu Egilearen id");
	    				zerrenda.egileEzabatu(id);
	    			}
	    			catch(AutoreaEzAukituaException e) {
	    				System.out.println(e.getMessage());
	    			}
	    			break;
	    		}
	    		case 10: {
	    			System.out.println("itxaron pizkatzo bat mesedez");
	    			zerrenda.eguneratuFitxategiak("publications-titles-all.txt", "publications-authors-all-final.txt","publications-citedPubs-all.txt");
	    			break;
	    		}
	    		case 11: {
	    			ArrayList<Argitalpen> a=zerrenda.argitalpenakAlfabetikoki();
	    			for(Argitalpen b: a) {
	    				System.out.println("#"+b.getTitulua());
	    			}
	    			break;
	    		}
	    		
	    }
		System.out.println("\n===============================");
	}
	}
}
	

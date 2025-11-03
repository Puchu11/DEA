package prakt_1;

import java.util.Scanner;

public class teklatua {
	private static Scanner sc = new Scanner(System.in);

    public static int irakurriInt(String mezua) {
        System.out.print(mezua + ": ");
        while (!sc.hasNextInt()) {
            System.out.println("❌ Sartu zenbaki balioduna (número válido).");
            sc.nextLine();
            System.out.print(mezua + ": ");
        }
        int zenbakia = sc.nextInt();
        sc.nextLine(); // limpiar salto de línea
        return zenbakia;
    }

    public static String irakurriString(String mezua) {
        System.out.print(mezua + ": ");
        String id = sc.nextLine().trim();
        while (id.isEmpty()) {
            System.out.print("❌ Id ez da baliozkoa. Saiatu berriro: ");
            id = sc.nextLine().trim();
        }
        return id;
    }

}

package DataHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    MenuMethods methods = new MenuMethods();

    private void userMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("* Hovedmeny *");
            System.out.println("Vennligst velg ett av alternativene under.");
            System.out.println("1. Se spillerkort for en bestemt sport.");
            System.out.println("2. Se hvor mange samlerkort vi har.");
            System.out.println("3. Se hvilke kort vi har som er i mint tilstand.");
            System.out.println("4. Avslutt.");

            try {
                int options = sc.nextInt();
                sc.nextLine(); // Konsumerer linjeskift etter nextInt()

                switch (options) {
                    case 1 -> {
                        System.out.println("Vennligst velg Fotball, Basketball eller Baseball.");
                        String sportOption = sc.nextLine();
                        methods.showSport(sportOption);
                    }
                    case 2 -> methods.countObjects();
                    case 3 -> methods.condition();
                    case 4 -> {
                        exit = true;
                        System.out.println("Avslutter, velkommen tilbake!");
                        sc.close(); // Lukker scanneren ved avslutning
                    }
                    default -> System.out.println("Ikke et gyldig valg, velg noe annet.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig input. Vennligst skriv inn et heltall.");
                sc.next();
            }
        }
    }

    //Offentlig metode for å gi tilgang til menyen.
    public void startMenu(){
        userMenu();
    }
}

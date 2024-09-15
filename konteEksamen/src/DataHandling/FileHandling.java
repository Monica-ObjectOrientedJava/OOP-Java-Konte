package DataHandling;
import Classes.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandling {
    private ArrayList<Collection> collectionList = new ArrayList<>();
    private ArrayList<Sports> sportsList = new ArrayList<>();

    public ArrayList<Collection> getCollectionList() {
        return collectionList;
    }
    public ArrayList<Sports> getSportsList() {
        return sportsList;
    }


    //Offentling metode for parsing av tekstfilen.
    public void parseFile() {
        //Bruker try-with slik at ressursene lukkes automatisk, å jeg må derfor ikke lukke de manuelt.
        try (Scanner sc = new Scanner(new File("files/samlerkort.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                switch (line) {
                    case "Samlerkortserier:" -> {
                        //henter inn antall samlinger her, for og bruke i addCollection metoden min.
                        int number = Integer.parseInt(sc.nextLine());
                        addCollection(sc, number);
                    }
                    case "Kort:" -> {
                        //Henter inn antall registrerte kort her, for og konsumere tallet å skippe det.
                        int number = Integer.parseInt(sc.nextLine());
                        addCards(sc, number);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The textfile was not found, please check filepath. " + e);
        }
    }

    private void addCollection(Scanner sc, int number) {
        for (int i = 0; i < number; i++) {
            ArrayList<String> data = new ArrayList<String>();
            for (int j = 0; j < 5; j++) {
                data.add(sc.nextLine());
            }
            collectionList.add(createCollection(data));
        }
    }

    private void addCards(Scanner sc, int number) {
        while (sc.hasNextLine()) {
            ArrayList<String> data = new ArrayList();

            String lines = sc.nextLine();

            while (!lines.equals("-------")) {
                data.add(lines);
                lines = sc.nextLine();
            }
                switch (data.get(7)) {
                    case "Fotball" -> sportsList.add(createFootball(data));
                    case "Baseball" -> sportsList.add(createBaseball(data));
                    case "Basketball" -> sportsList.add(createBasketball(data));

            }
        }

    }

    //Oppretter et objekt av seriekort kolleksjonen
    private Collection createCollection(ArrayList<String> data) {
        int id = Integer.parseInt(data.get(0));
        String publisher = data.get(1);
        int releaseYear = Integer.parseInt(data.get(2));
        String sports = data.get(3);
        int number = Integer.parseInt(data.get(4));
        return new Collection(id, publisher, releaseYear, sports, number);
    }
    //Oppretter et objekt av fotball kortene.
    private Football createFootball(ArrayList<String> data) {
        int id = Integer.parseInt(data.get(0));
        int series = Integer.parseInt(data.get(1));
        String condition = data.get(2);
        String player = data.get(3);
        String club = data.get(4);
        int season = Integer.parseInt(data.get(5));
        int games = Integer.parseInt(data.get(6));
        String type = data.get(7);
        int serieScore = Integer.parseInt(data.get(8));
        int cupScore = Integer.parseInt(data.get(9));
        return new Football(serieScore, cupScore, id,series, condition, player, club, season, games);

    }
    //Oppretter et objekt av Baseball kortene.
    private Baseball createBaseball(ArrayList<String> data) {
        int id = Integer.parseInt(data.get(0));
        int series = Integer.parseInt(data.get(1));
        String condition = data.get(2);
        String player = data.get(3);
        String club = data.get(4);
        int season = Integer.parseInt(data.get(5));
        int games = Integer.parseInt(data.get(6));
        String type = data.get(7);
        int homeRuns = Integer.parseInt(data.get(8));
        return new Baseball(homeRuns, id,series, condition, player, club, season, games);

    }
    //Oppretter et objejt av basketball kortene.
    private Basketball createBasketball(ArrayList<String> data) {
        int id = Integer.parseInt(data.get(0));
        int series = Integer.parseInt(data.get(1));
        String condition = data.get(2);
        String player = data.get(3);
        String club = data.get(4);
        int season = Integer.parseInt(data.get(5));
        int games = Integer.parseInt(data.get(6));
        String type = data.get(7);
        int field = Integer.parseInt(data.get(8));
        int free = Integer.parseInt(data.get(9));
        double points = Double.parseDouble(data.get(10));
        return new Basketball(field, free, points, id, series, condition, player, club, season, games);

    }
    //Legger til alt data i arrayliste
    private void printCollection(){
        System.out.println("Samlingsliste:");
        for (Collection collection : collectionList) {
            System.out.println(collection);
        }
    }
    private void printSports(){
        for (Sports sports : sportsList) {
            System.out.println("Kort liste:");
            System.out.println(sports);
        }
    }
    //Metode for og printe ut dataen
    public void print(){
        printCollection();
        printSports();
    }
}

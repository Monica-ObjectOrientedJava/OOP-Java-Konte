import DataHandling.*;

public class Main {
    public static void main(String[] args) {
        FileHandling reader = new FileHandling();
        DataHandling importData = new DataHandling();
        Menu menu = new Menu();

        reader.parseFile(); //Parser tekstfilen
        importData.insertData(reader); //Metode for og legge til data i databasen. denne må kommenteres ut når programmet har kjørt en gang
        menu.startMenu();
        //reader.print(); //Metode for og printe ut ArrayListene dersom man ønsker det.
    }
}

package Classes;

public record Collection(int id, String publisher, int released, String sport, int number){

    @Override
    public String toString() {
        return "ID: " + id + "\nUtgiver: " + publisher + "\nUtgitt: " + released + "\nSport: " + sport + "\nAntall ulike kort i serien: " + number + "\n---";
    }
}

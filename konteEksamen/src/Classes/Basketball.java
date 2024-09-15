package Classes;

public class Basketball extends Sports{
    private int fieldGoals;
    private int freeThrow;
    private double points;;

    public Basketball(int fieldGoals, int freeThrow, double points, int id, int series, String condition, String playerName, String club, int seasons, int games) {
        super(id, series, condition, playerName, club, seasons, games);
        this.fieldGoals = fieldGoals;
        this.freeThrow = freeThrow;
        this.points = points;
    }
    public int getFieldGoals() {
        return fieldGoals;
    }
    public int getFreeThrow() {
        return freeThrow;
    }
    public double getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return super.toString() +"\nType kort: Basketball"+ "\nSkuddforsøk: " + fieldGoals + "\nStraffekast: " + freeThrow +  "\nPoeng: " + points + "\n---";
    }
}

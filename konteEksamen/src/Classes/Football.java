package Classes;

public class Football extends Sports{
    int serieScores;
    int cupScores;

    public Football(int serieScores, int cupScores, int id, int series, String condition, String playerName, String club, int seasons, int games) {
        super(id, series, condition, playerName, club, seasons, games);
        this.serieScores = serieScores;
        this.cupScores = cupScores;
    }
    public int getSerieScores() {
        return serieScores;
    }
    public int getCupScores() {
        return cupScores;
    }

    public void setSerieScores(int serieScores) {
        this.serieScores = serieScores;
    }

    public void setCupScores(int cupScores) {
        this.cupScores = cupScores;
    }


    @Override
    public String toString() {
        return super.toString() + "\nType kort: Fotballkort" +"\nSeriemål: " + serieScores + "\nCupmål: " + cupScores + "\n---";
    }
}

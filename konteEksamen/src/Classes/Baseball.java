package Classes;

public class Baseball extends Sports{
    private int homeRun;

    public Baseball(int homeRun, int id, int series, String condition, String playerName, String club, int seasons, int games) {
        super(id, series, condition, playerName, club, seasons, games);
        this.homeRun = homeRun;
    }
    public int getHomeRun() {
        return homeRun;
    }

    @Override
    public String toString() {
        return super.toString() + "\nType kort: Baseball" + "\nHomeruns:" + homeRun + "\n---";
    }
}

package Classes;

public abstract class Sports {
    private int id;
    private int series;
    private String condition;
    private String playerName;
    private String club;
    private int seasons;
    private int games;

    public Sports(int id, int series, String condition, String playerName, String club, int seasons, int games) {
        this.id = id;
        this.series = series;
        this.condition = condition;
        this.playerName = playerName;
        this.club = club;
        this.seasons = seasons;
        this.games = games;
    }
    public int getId() {
        return id;
    }
    public int getSeries() {
        return series;
    }
    public String getCondition() {
        return condition;
    }
    public String getPlayerName() {
        return playerName;
    }
    public String getClub() {
        return club;
    }
    public int getSeasons() {
        return seasons;
    }
    public int getGames() {
        return games;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setSeries(int series) {
        this.series = series;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setClub(String club) {
        this.club = club;
    }
    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }
    public void setGames(int games) {
        this.games = games;
    }
    @Override
    public String toString() {
        return "ID: " + id + "\nSerie kortet tilhører: " + series + "\nTilstand: " + condition + "\nSpiller: " + playerName + "\nKlubb: " + club + "\nSesonger spilt: " + seasons + "\nKamper spilt: " + games;
    }
}

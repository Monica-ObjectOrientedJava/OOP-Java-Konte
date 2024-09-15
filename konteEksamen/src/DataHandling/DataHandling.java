package DataHandling;
import Classes.*;
import DatabaseCon.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//KLasse for og håndtere import av data til databasen.
public class DataHandling {

    //Importerer data til samlerkort serien.
    private void importCollection(Collection collection) {
        try(Connection conn = Database.getConnection()){
            String insertColl = "INSERT INTO SamlerkortSerie(id, Utgiver, Utgitt, Sport, Antall) VALUES (?,?,?,?,?)";
            PreparedStatement collStmt = conn.prepareStatement(insertColl);
            collStmt.setInt(1, collection.id());
            collStmt.setString(2, collection.publisher());
            collStmt.setInt(3, collection.released());
            collStmt.setString(4, collection.sport());
            collStmt.setInt(5, collection.number());
            collStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error importing collection: " + collection.id() + e);
        }
    }
    //metode for og oppdatere fotball tabellen.
    private void importFootball(Football football) {
        try(Connection conn = Database.getConnection()){
            String insertFoot = "INSERT INTO FotballKort(id, Serie, Tilstand, Spillernavn, Klubb, Sesonger, Kamper, Seriescoringer, Cupscoringer) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement fotStmt = conn.prepareStatement(insertFoot);
            fotStmt.setInt(1, football.getId());
            fotStmt.setInt(2, football.getSeries());
            fotStmt.setString(3, football.getCondition());
            fotStmt.setString(4, football.getPlayerName());
            fotStmt.setString(5, football.getClub());
            fotStmt.setInt(6, football.getSeasons());
            fotStmt.setInt(7, football.getGames());
            fotStmt.setInt(8, football.getSerieScores());
            fotStmt.setInt(9, football.getCupScores());
            fotStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error importing Footballcard" + football.getId() + e);
        }
    }
    //Metode for og oppdatere Basketball tabellen
    private void importBasketball(Basketball basketball) {
        try(Connection conn = Database.getConnection()){
            String insertBask = "INSERT INTO Basketballkort(id, Serie, Tilstand, Spillernavn, Klubb, Sesonger, Kamper, FGPercent, FTPercent, Poengsnitt) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement basStmt = conn.prepareStatement(insertBask);
            basStmt.setInt(1, basketball.getId());
            basStmt.setInt(2, basketball.getSeries());
            basStmt.setString(3, basketball.getCondition());
            basStmt.setString(4, basketball.getPlayerName());
            basStmt.setString(5, basketball.getClub());
            basStmt.setInt(6, basketball.getSeasons());
            basStmt.setInt(7, basketball.getGames());
            basStmt.setInt(8, basketball.getFieldGoals());
            basStmt.setInt(9, basketball.getFreeThrow());
            basStmt.setDouble(10, basketball.getPoints());
            basStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error importing Basketball card " + basketball.getId() + e);
        }
    }
    //Metode for og oppdatere baseball tabellen
    private void importBaseball(Baseball baseball) {
        try(Connection conn = Database.getConnection()){
            String insertBase = "INSERT INTO Baseballkort(id, Serie, Tilstand, Spillernavn, Klubb, Sesonger, Kamper, Homeruns) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement baseStmt = conn.prepareStatement(insertBase);
            baseStmt.setInt(1, baseball.getId());
            baseStmt.setInt(2, baseball.getSeries());
            baseStmt.setString(3, baseball.getCondition());
            baseStmt.setString(4, baseball.getPlayerName());
            baseStmt.setString(5, baseball.getClub());
            baseStmt.setInt(6, baseball.getSeasons());
            baseStmt.setInt(7, baseball.getGames());
            baseStmt.setInt(8, baseball.getHomeRun());
            baseStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error importing baseballcard " + baseball.getId() + e);
        }
    }
    //Offentlig metode for og fylle databasen
    public void insertData(FileHandling reader){
        for(Collection collection : reader.getCollectionList()){
            importCollection(collection);
        }
        System.out.println("Samlerserie tabell oppdatert. ");

        for(Sports sports : reader.getSportsList()){
            if(sports instanceof Football){
                importFootball((Football)sports);
            } else if(sports instanceof Basketball){
                importBasketball((Basketball)sports);
            } else if(sports instanceof Baseball){
                importBaseball((Baseball)sports);
            }
        }   System.out.println("Alle kortene er lagt til i databasen.");
    }
}

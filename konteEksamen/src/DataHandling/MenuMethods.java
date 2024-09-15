package DataHandling;

import Classes.*;
import DatabaseCon.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Klasse for å håndtere spørringer til menyen til brukeren.
public class MenuMethods {

    private void counter() {
        try {
            count("Baseballkort", "Vi har %d forskjellige baseballkort registrert.", "SELECT COUNT(*) AS count FROM Baseballkort");
            count("Fotballkort", "Vi har %d forskjellige fotballkort registrert.", "SELECT COUNT(*) AS count FROM Fotballkort");
            count("Basketballkort", "Vi har %d forskjellige basketballkort registrert.", "SELECT COUNT(*) AS count FROM Basketballkort");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Metode for og telle objekter i databasen.
    private void count(String tableName, String message, String sql) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("count");
                System.out.println(String.format(message, count));
                System.out.println("---");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to count cards." + e);
        }
    }

    //Metode som bestemmer hvilken metode som skal kalles på når bruker skriver inn valget sitt.
    public void showSport(String sport) {
        switch (sport.toLowerCase()) {
            case "fotball":
                showFootballInfo();
                break;
            case "basketball":
                showBasketballInfo();
                break;
            case "baseball":
                showBaseballInfo();
                break;
            default:
                System.out.println("Desverre er ikke dette et alternativ. Vennligst velg noe annet.");
                break;
        }
    }

    //Metode for og vise info om fotball
    private void showFootballInfo() {
        try (Connection conn = Database.getConnection();
             PreparedStatement fotStmt = conn.prepareStatement("SELECT * FROM Fotballkort");
             ResultSet fotRs = fotStmt.executeQuery()) {

            while (fotRs.next()) {
                Football football = new Football(
                        fotRs.getInt("Seriescoringer"),
                        fotRs.getInt("Cupscoringer"),
                        fotRs.getInt("id"),
                        fotRs.getInt("Serie"),
                        fotRs.getString("Tilstand"),
                        fotRs.getString("Spillernavn"),
                        fotRs.getString("Klubb"),
                        fotRs.getInt("Sesonger"),
                        fotRs.getInt("Kamper")
                );
                System.out.println(football);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load footballcard." + e);
        }
    }

    //Metode for og vise info om basketball
    private void showBasketballInfo() {
        try (Connection conn = Database.getConnection();
             PreparedStatement basStmt = conn.prepareStatement("SELECT * FROM Basketballkort");
             ResultSet basRs = basStmt.executeQuery()) {

            while (basRs.next()) {
                Basketball basketball = new Basketball(
                        basRs.getInt("FGPercent"),
                        basRs.getInt("FTPercent"),
                        basRs.getDouble("Poengsnitt"),
                        basRs.getInt("id"),
                        basRs.getInt("Serie"),
                        basRs.getString("Tilstand"),
                        basRs.getString("Spillernavn"),
                        basRs.getString("Klubb"),
                        basRs.getInt("Sesonger"),
                        basRs.getInt("Kamper")
                );
                System.out.println(basketball);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load basketball cards." + e);
        }
    }

    //Metode for og vise baseball info
    private void showBaseballInfo() {
        try (Connection conn = Database.getConnection();
             PreparedStatement baseStmt = conn.prepareStatement("SELECT * FROM Baseballkort");
             ResultSet baseRs = baseStmt.executeQuery()) {

            while (baseRs.next()) {
                Baseball baseball = new Baseball(
                        baseRs.getInt("Homeruns"),
                        baseRs.getInt("id"),
                        baseRs.getInt("Serie"),
                        baseRs.getString("Tilstand"),
                        baseRs.getString("Spillernavn"),
                        baseRs.getString("Klubb"),
                        baseRs.getInt("Sesonger"),
                        baseRs.getInt("Kamper")
                );
                System.out.println(baseball);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load baseballcards." + e);
        }
    }

    //Metode for og se alle kortene som er i mint condition
    private void cardsByCondition() {
        try (Connection conn = Database.getConnection()) {
            // Fotballkort
            String footballSql = "SELECT * FROM Fotballkort WHERE Tilstand = 'mint'";
            try (PreparedStatement fotStmt = conn.prepareStatement(footballSql);
                 ResultSet fotRs = fotStmt.executeQuery()) {

                while (fotRs.next()) {
                    int id = fotRs.getInt("id");
                    int series = fotRs.getInt("Serie");
                    String condition = fotRs.getString("Tilstand");
                    String name = fotRs.getString("Spillernavn");
                    String club = fotRs.getString("Klubb");
                    int seasons = fotRs.getInt("Sesonger");
                    int games = fotRs.getInt("Kamper");
                    int serieScores = fotRs.getInt("Seriescoringer");
                    int cupScores = fotRs.getInt("Cupscoringer");
                    System.out.println("Korttype: Fotball" + "\nID: " + id + "\nSerie: " + series + "\nTilstand: " + condition + "\nSpillerens navn: " + name + "\nKlubb: " + club + "\nSesonger spilt: " + seasons + "\nKamper: " + games + "\nSeriescoringer: " + serieScores + "\nCupscoringer: " + cupScores + "\n---");
                }
            }

            // Basketballkort
            String basketSql = "SELECT * FROM Basketballkort WHERE Tilstand = 'mint'";
            try (PreparedStatement baskStmt = conn.prepareStatement(basketSql);
                 ResultSet baskRs = baskStmt.executeQuery()) {

                while (baskRs.next()) {
                    int id = baskRs.getInt("id");
                    int series = baskRs.getInt("Serie");
                    String condition = baskRs.getString("Tilstand");
                    String name = baskRs.getString("Spillernavn");
                    String club = baskRs.getString("Klubb");
                    int seasons = baskRs.getInt("Sesonger");
                    int games = baskRs.getInt("Kamper");
                    int percent1 = baskRs.getInt("FGPercent");
                    int percent2 = baskRs.getInt("FTPercent");
                    double points = baskRs.getDouble("Poengsnitt");
                    System.out.println("Korttype: Basketball" + "\nID: " + id + "\nSerie: " + series + "\nTilstand: " + condition + "\nSpillerens navn: " + name + "\nKlubb: " + club + "\nSesonger spilt: " + seasons + "\nKamper: " + games + "\nFGProsent: " + percent1 + "\nFTProsent: " + percent2 + "\nPoengsnitt: " + points+ "\n---");
                }
            }

            // Baseballkort
            String baseballSql = "SELECT * FROM Baseballkort WHERE Tilstand = 'mint'";
            try (PreparedStatement baseStmt = conn.prepareStatement(baseballSql);
                 ResultSet baseRs = baseStmt.executeQuery()) {
                while (baseRs.next()) {
                    int id = baseRs.getInt("id");
                    int series = baseRs.getInt("Serie");
                    String condition = baseRs.getString("Tilstand");
                    String name = baseRs.getString("Spillernavn");
                    String club = baseRs.getString("Klubb");
                    int seasons = baseRs.getInt("Sesonger");
                    int games = baseRs.getInt("Kamper");
                    int homeRun = baseRs.getInt("Homeruns");
                    System.out.println("Korttype: Baseball" + "\nID: " + id + "\nSerie: " + series + "\nTilstand: " + condition + "\nSpillerens navn: " + name + "\nKlubb: " + club + "\nSesonger spilt: " + seasons + "\nKamper: " + games + "\nHomeruns: " + homeRun+ "\n---");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Oh no, failed to search for cards in mint condition! " + e);
        }
    }

    public void countObjects() {
        counter();
    }

    public void sports(String sport) {
        showSport(sport);
    }
    public void condition(){
        cardsByCondition();
    }
}

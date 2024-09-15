package DatabaseCon;
/*
Database tilkoblingen har tatt utgangspunkt i tilkoblingskoden fra forelesning 18.
https://github.com/kristiania/PGR112v24/blob/main/code/lectures/_18/DatabaseExample.java
*/
import com.mysql.cj.jdbc.Driver;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private final static Properties databaseProperties = new Properties();
    private static final String[] Keys = {"host", "port", "database", "username", "password"};

    static {
        try {
            // Register JDBC driver
            DriverManager.registerDriver(new Driver());

            // Leser database properties fra filen
            try (FileInputStream input = new FileInputStream("files/database.properties")) {
                databaseProperties.load(input);
            }

            // Sjekk om alle nødvendige nøkler er tilgjengelige
            for (String key : Keys) {
                if (!databaseProperties.containsKey(key)) {
                    throw new IllegalArgumentException("Following key is missing: " + key);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting JDBC driver", e);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String host = databaseProperties.getProperty("host");
        String port = databaseProperties.getProperty("port");
        String database = databaseProperties.getProperty("database");
        String username = databaseProperties.getProperty("username");
        String password = databaseProperties.getProperty("password");
        return DriverManager.getConnection(
                String.format("jdbc:mysql://%s:%s/%s?allowPublicKeyRetrieval=true&useSSL=false",
                        host, port, database),
                username, password);
    }
}

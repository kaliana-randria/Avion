package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Maconnexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/gestion_avion2";
    private static final String USER = "postgres";
    private static final String PASSWORD = "mdp";

    public static Connection getConnexion() throws SQLException {
        Connection connexion = null;
        try {
            Class.forName("org.postgresql.Driver");
            connexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
            throw new SQLException("Driver PostgreSQL non trouvé", e);
        }
        return connexion;
    }
}   


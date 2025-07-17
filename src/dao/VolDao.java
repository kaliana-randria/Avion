package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Vol;

public class VolDao {
    private Vol vol;

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public void save(Connection connection) throws Exception {
        String sql = "INSERT INTO vol (id_avion, ville_depart, ville_arrivee, date_depart, date_arrivee, duree, id_statut_vol) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, vol.getId_avion());
            pstmt.setString(2, vol.getVille_depart());
            pstmt.setString(3, vol.getVille_arrivee());

            pstmt.setTimestamp(4, Timestamp.valueOf(vol.getDate_depart()));
            pstmt.setTimestamp(5, Timestamp.valueOf(vol.getDate_arrivee()));

            Duration duration = Duration.between(vol.getDate_depart(), vol.getDate_arrivee());
            int dureeEnMinutes = (int) duration.toMinutes();

            pstmt.setInt(6, dureeEnMinutes);
            pstmt.setInt(7, vol.getId_statut_vol());

            pstmt.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw e;
        }
    }

    public void save() throws Exception {
        try (Connection connection = Maconnexion.getConnexion()) {
            save(connection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Vol> findAll() throws Exception {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Maconnexion.getConnexion();
            List<Vol> VolList = new ArrayList<>();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM vol");
            Vol vol;
            while (rs.next()) {
                vol = new Vol();

                vol.setId_vol(rs.getInt("id_vol"));
                vol.setId_avion(rs.getInt("id_avion"));
                vol.setVille_depart(rs.getString("ville_depart"));
                vol.setVille_arrivee(rs.getString("ville_arrivee"));
                Timestamp tsDepart = rs.getTimestamp("date_depart");
                if (tsDepart != null) {
                    vol.setDate_depart(tsDepart.toLocalDateTime());
                }
                Timestamp tsArrivee = rs.getTimestamp("date_arrivee");
                if (tsArrivee != null) {
                    vol.setDate_arrivee(tsArrivee.toLocalDateTime());
                }
                vol.setDuree(rs.getInt("duree"));
                vol.setId_statut_vol(rs.getInt("id_statut_vol"));

                VolList.add(vol);
            }

            return VolList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            st.close();
            rs.close();
            connection.close();
        }
    }

    public Vol findById(int idVol) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vol vol = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "SELECT * FROM vol WHERE id_vol = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVol);
            rs = stmt.executeQuery();

            if (rs.next()) {
                vol = new Vol(
                        rs.getInt("id_vol"),
                        rs.getInt("id_avion"),
                        rs.getString("ville_depart"),
                        rs.getString("ville_arrivee"),
                        rs.getTimestamp("date_depart").toLocalDateTime(),
                        rs.getTimestamp("date_arrivee").toLocalDateTime(),
                        rs.getInt("duree"),
                        rs.getInt("id_statut_vol"));
            }
            return vol;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
    }

    public void update(Vol vol) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "UPDATE vol SET id_avion = ?, ville_depart = ?, ville_arrivee = ?, date_depart = ?, date_arrivee = ?, id_statut_vol = ? WHERE id_vol = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vol.getId_avion());
            stmt.setString(2, vol.getVille_depart());
            stmt.setString(3, vol.getVille_arrivee());
            stmt.setTimestamp(4, Timestamp.valueOf(vol.getDate_depart()));
            stmt.setTimestamp(5, Timestamp.valueOf(vol.getDate_arrivee()));
            stmt.setInt(6, vol.getId_statut_vol());
            stmt.setInt(7, vol.getId_vol());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
    }

    public void delete() throws Exception {
        Connection connection = null;
        try {
            connection = Maconnexion.getConnexion();
            connection.setAutoCommit(false);

            String sqlEnregistrement = "DELETE FROM enregistrement_reservation WHERE id_reservation IN " +
                    "(SELECT r.id_reservation FROM reservation r " +
                    "JOIN param_vol p ON r.id_param_vol = p.id_param_vol " +
                    "JOIN classe_vol c ON p.id_classe_vol = c.id_classe_vol " +
                    "WHERE c.id_vol = ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sqlEnregistrement)) {
                stmt.setInt(1, vol.getId_vol());
                stmt.executeUpdate();
            }

            String sqlReservation = "DELETE FROM reservation WHERE id_param_vol IN " +
                    "(SELECT p.id_param_vol FROM param_vol p " +
                    "JOIN classe_vol c ON p.id_classe_vol = c.id_classe_vol " +
                    "WHERE c.id_vol = ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sqlReservation)) {
                stmt.setInt(1, vol.getId_vol());
                stmt.executeUpdate();
            }

            String sqlParam = "DELETE FROM param_vol WHERE id_classe_vol IN " +
                    "(SELECT id_classe_vol FROM classe_vol WHERE id_vol = ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sqlParam)) {
                stmt.setInt(1, vol.getId_vol());
                stmt.executeUpdate();
            }

            String sqlClasse = "DELETE FROM classe_vol WHERE id_vol = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sqlClasse)) {
                stmt.setInt(1, vol.getId_vol());
                stmt.executeUpdate();
            }

            String sqlVol = "DELETE FROM vol WHERE id_vol = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sqlVol)) {
                stmt.setInt(1, vol.getId_vol());
                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted == 0) {
                    throw new SQLException("Aucun vol trouvé avec cet ID");
                }
            }

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new Exception("Erreur lors de la suppression du vol: " + e.getMessage(), e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Vol> rechercheMulticritere(Date date_depart, String compagnie, String depart, String arrivee)
            throws Exception {
        List<Vol> resultats = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Maconnexion.getConnexion();

            StringBuilder query = new StringBuilder(
                    "SELECT v.* FROM vol v " +
                            "JOIN avion a ON v.id_avion = a.id_avion " +
                            "JOIN compagnie c ON a.id_compagnie = c.id_compagnie " +
                            "WHERE 1=1");

            List<Object> params = new ArrayList<>();

            if (date_depart != null) {
                query.append(" AND DATE(v.date_depart) = ?");
                params.add(new java.sql.Date(date_depart.getTime()));
            }

            if (compagnie != null && !compagnie.isEmpty()) {
                query.append(" AND LOWER(c.nom_compagnie) LIKE ?");
                params.add("%" + compagnie.toLowerCase() + "%");
            }

            if (depart != null && !depart.isEmpty()) {
                query.append(" AND LOWER(v.ville_depart) LIKE ?");
                params.add("%" + depart.toLowerCase() + "%");
            }

            if (arrivee != null && !arrivee.isEmpty()) {
                query.append(" AND LOWER(v.ville_arrivee) LIKE ?");
                params.add("%" + arrivee.toLowerCase() + "%");
            }

            ps = con.prepareStatement(query.toString());

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Vol v = new Vol();
                v.setId_vol(rs.getInt("id_vol"));
                v.setVille_depart(rs.getString("ville_depart"));
                v.setVille_arrivee(rs.getString("ville_arrivee"));
                v.setDate_depart(rs.getTimestamp("date_depart").toLocalDateTime());
                v.setDate_arrivee(rs.getTimestamp("date_arrivee").toLocalDateTime());
                v.setDuree(rs.getInt("duree"));
                v.setId_statut_vol(rs.getInt("id_statut_vol"));
                v.setId_avion(rs.getInt("id_avion"));
                resultats.add(v);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (con != null)
                con.close();
        }

        return resultats;
    }

    public static String convertionMinEnHeure(int minutes) {
        int heure = minutes / 60;
        int resteMin = minutes % 60;

        if (heure == 0) {
            return resteMin + "min";
        } else if (resteMin == 0) {
            return heure + "h";
        } else {
            return heure + "h " + resteMin + "min";
        }
    }

    public double sommeNbrPlaceVol(Vol vol) throws Exception {
        double somme = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "SELECT SUM(nbr_place) as total FROM classe_vol WHERE id_vol = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vol.getId_vol());

            rs = stmt.executeQuery();

            if (rs.next()) {
                somme = rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors du calcul de la somme des places: " + e.getMessage());
            throw new Exception("Erreur d acces a la base de donnees", e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources: " + e.getMessage());
            }
        }

        return somme;
    }

    public double sommeQuantiteReservationVol(Vol vol) throws Exception {
        double somme = 0;
        String sql = "SELECT SUM(r.quantite) as total " +
                "FROM reservation r " +
                "JOIN param_vol p ON r.id_param_vol = p.id_param_vol " +
                "JOIN classe_vol cv ON p.id_classe_vol = cv.id_classe_vol " +
                "WHERE cv.id_vol = ?";

        try (Connection conn = Maconnexion.getConnexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vol.getId_vol());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    somme = rs.getDouble("total");
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors du calcul des reservations", e);
        }

        return somme;
    }

    public boolean estVolPlein(Vol vol) throws Exception {
        double totalPlaces = this.sommeNbrPlaceVol(vol);
        double totalReservations = this.sommeQuantiteReservationVol(vol);

        return totalPlaces <= totalReservations;
    }

    public void updateStatutVol(Vol vol) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Maconnexion.getConnexion();

            if (vol.getDate_depart().isBefore(LocalDateTime.now())) {
                String sql = "UPDATE vol SET id_statut_vol = 3 WHERE id_vol = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, vol.getId_vol());
                stmt.executeUpdate();
            } else if (this.estVolPlein(vol)) {
                String sql = "UPDATE vol SET id_statut_vol = 2 WHERE id_vol = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, vol.getId_vol());
                stmt.executeUpdate();
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de la mise a jour du statut du vol: " + e.getMessage());
            throw new SQLException("Erreur de mise a jour du statut", e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                System.err.println("Erreur fermeture PreparedStatement: " + e.getMessage());
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.err.println("Erreur fermeture Connection: " + e.getMessage());
            }
        }
    }
}

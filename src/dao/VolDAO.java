package dao;
// Data Access Object

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Vols;

public class VolDAO {
    private Vols vols;

    public void setVols(Vols vols) {
        this.vols = vols;
    }

    public void save(Connection connection) throws Exception {
        String sql = "INSERT INTO vols (numero_vol, compagnie, ville_depart, ville_arrivee, date_depart, date_arrivee, duree, id_statut_vol, id_avion) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, vols.getNumero_vol());
            pstmt.setString(2, vols.getCompagnie());
            pstmt.setString(3, vols.getVille_depart());
            pstmt.setString(4, vols.getVille_arrivee());

            pstmt.setTimestamp(5, Timestamp.valueOf(vols.getDate_depart()));
            pstmt.setTimestamp(6, Timestamp.valueOf(vols.getDate_arrivee()));

            Duration duration = Duration.between(vols.getDate_depart(), vols.getDate_arrivee());
            int dureeEnMinutes = (int) duration.toMinutes();

            pstmt.setInt(7, dureeEnMinutes);
            pstmt.setInt(8, vols.getId_statut_vol());
            pstmt.setInt(9, vols.getId_avion());

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
            throw e; 
        }
    }

    public List<Vols> findAll() throws Exception {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Maconnexion.getConnexion();
            List<Vols> VolsList = new ArrayList<>();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM vols");
            Vols vols;
            while (rs.next()) {
                vols = new Vols();

                vols.setId_vol(rs.getInt("id_vol"));
                vols.setNumero_vol(rs.getString("numero_vol"));
                vols.setCompagnie(rs.getString("compagnie"));
                vols.setVille_depart(rs.getString("ville_depart"));
                vols.setVille_arrivee(rs.getString("ville_arrivee"));
                Timestamp tsDepart = rs.getTimestamp("date_depart");
                if (tsDepart != null) {
                    vols.setDate_depart(tsDepart.toLocalDateTime());
                }
                Timestamp tsArrivee = rs.getTimestamp("date_arrivee");
                if (tsArrivee != null) {
                    vols.setDate_arrivee(tsArrivee.toLocalDateTime());
                }
                vols.setDuree(rs.getInt("duree"));
                vols.setId_statut_vol(rs.getInt("id_statut_vol"));
                vols.setId_avion(rs.getInt("id_avion"));


                VolsList.add(vols);
            }
        
            return VolsList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            st.close();
            rs.close();
            connection.close();
        }
    }

    public void update(Vols vols) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "UPDATE vols SET numero_vol = ?, compagnie = ?, ville_depart = ?, ville_arrivee = ?, date_depart = ?, date_arrivee = ?, id_statut_vol = ?, id_avion = ? WHERE id_vol = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vols.getNumero_vol());
            stmt.setString(2, vols.getCompagnie());
            stmt.setString(3, vols.getVille_depart());
            stmt.setString(4, vols.getVille_arrivee());
            stmt.setTimestamp(5, Timestamp.valueOf(vols.getDate_depart()));
            stmt.setTimestamp(6, Timestamp.valueOf(vols.getDate_arrivee()));
            stmt.setInt(7, vols.getId_statut_vol());
            stmt.setInt(8, vols.getId_avion());
            stmt.setInt(9, vols.getId_vol());
            
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public void delete() throws Exception {
        PreparedStatement pstmt = null;
        Connection connection = null;
        String sql = "DELETE FROM vols WHERE id_vol = ?";

        try {
            connection = Maconnexion.getConnexion();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, vols.getId_vol());
            
            pstmt.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e; 
        } finally {
            pstmt.close();
            connection.close();
        }
    }

    public List<Vols> rechercheMulticritere(String numero, String compagnie, String depart, String arrivee) throws Exception {
        List<Vols> resultats = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Maconnexion.getConnexion();
            StringBuilder query = new StringBuilder("SELECT * FROM vols WHERE 1=1");
            List<Object> params = new ArrayList<>();

            if (numero != null && !numero.isEmpty()) {
                query.append(" AND numero_vol LIKE ?");
                params.add("%" + numero + "%");
            }
            if (compagnie != null && !compagnie.isEmpty()) {
                query.append(" AND compagnie LIKE ?");
                params.add("%" + compagnie + "%");
            }
            if (depart != null && !depart.isEmpty()) {
                query.append(" AND ville_depart LIKE ?");
                params.add("%" + depart + "%");
            }
            if (arrivee != null && !arrivee.isEmpty()) {
                query.append(" AND ville_arrivee LIKE ?");
                params.add("%" + arrivee + "%");
            }

            ps = con.prepareStatement(query.toString());

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Vols v = new Vols();
                v.setId_vol(rs.getInt("id_vol"));
                v.setNumero_vol(rs.getString("numero_vol"));
                v.setCompagnie(rs.getString("compagnie"));
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
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }

        return resultats;
    }

    public Vols findById(int idVol) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vols vols = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "SELECT * FROM vols WHERE id_vol = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVol);
            rs = stmt.executeQuery();

            if (rs.next()) {
                vols = new Vols(
                    rs.getInt("id_vol"),
                    rs.getString("numero_vol"),
                    rs.getString("compagnie"),
                    rs.getString("ville_depart"),
                    rs.getString("ville_arrivee"),
                    rs.getTimestamp("date_depart").toLocalDateTime(),
                    rs.getTimestamp("date_arrivee").toLocalDateTime(),
                    rs.getInt("duree"),
                    rs.getInt("id_statut_vol"),
                    rs.getInt("id_avion")
                );
            }
            return vols;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}

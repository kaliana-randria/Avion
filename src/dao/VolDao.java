package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Duration;
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
        String sql = "INSERT INTO vol (id_avion, ville_depart, ville_arrivee, date_depart, date_arrivee, duree, id_statut_vol) " +
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
                    rs.getInt("id_statut_vol")
                );
            }
            return vol;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
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
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public void delete() throws Exception {
        PreparedStatement pstmt = null;
        Connection connection = null;
        String sql = "DELETE FROM vol WHERE id_vol = ?";

        try {
            connection = Maconnexion.getConnexion();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, vol.getId_vol());
            
            pstmt.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            System.out.println(e.getMessage());
            throw e; 
        } finally {
            pstmt.close();
            connection.close();
        }
    }

    public List<Vol> rechercheMulticritere(Date date_depart, String depart, String arrivee, String compagnie) throws Exception {
        List<Vol> resultats = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Maconnexion.getConnexion();
            StringBuilder query = new StringBuilder("SELECT * FROM vol WHERE 1=1");
            List<Object> params = new ArrayList<>();

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
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }

        return resultats;
    }

}

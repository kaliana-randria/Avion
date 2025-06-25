package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Tarifs;

public class TarifDAO {
    private Tarifs tarifs;

    public void setTarifs(Tarifs tarifs) {
        this.tarifs = tarifs;
    }
    public Tarifs getTarifs() {
        return tarifs;
    }

    public void save(Connection conn) throws Exception {
        String sql = "INSERT INTO tarifs (id_vol, id_classe, quantite, prix, pourcentage, date_limite_paiement) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tarifs.getId_vol());
            pstmt.setInt(2, tarifs.getId_classe());
            pstmt.setInt(3, tarifs.getQuantite());
            pstmt.setDouble(4, tarifs.getPrix());
            pstmt.setInt(5, tarifs.getPourcentage());
            pstmt.setDate(6, new Date(tarifs.getDate_limite_paiement().getTime()));

            pstmt.executeUpdate();
            conn.setAutoCommit(false);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }

    public List<Tarifs> findAll() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Maconnexion.getConnexion();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM tarifs");

            List<Tarifs> list = new ArrayList<>();
            while (rs.next()) {
                Tarifs t = new Tarifs();
                t.setId_tarif(rs.getInt("id_tarif"));
                t.setId_vol(rs.getInt("id_vol"));
                t.setId_classe(rs.getInt("id_classe"));
                t.setQuantite(rs.getInt("quantite"));
                t.setPrix(rs.getDouble("prix"));
                t.setPourcentage(rs.getInt("pourcentage"));
                t.setDate_limite_paiement(rs.getDate("date_limite_paiement"));
                list.add(t);
            }
            return list;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public void update(Tarifs tarif) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Maconnexion.getConnexion();
            String sql = "UPDATE tarifs SET id_vol = ?, id_classe = ?, quantite = ?, prix = ?, pourcentage = ?, date_limite_paiement = ? WHERE id_tarif = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, tarif.getId_vol());
            stmt.setInt(2, tarif.getId_classe());
            stmt.setInt(3, tarif.getQuantite());
            stmt.setDouble(4, tarif.getPrix());
            stmt.setInt(5, tarif.getPourcentage());
            stmt.setDate(6, new Date(tarif.getDate_limite_paiement().getTime()));
            stmt.setInt(7, tarif.getId_tarif());

            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public void delete(int id_tarif) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Maconnexion.getConnexion();
            String sql = "DELETE FROM tarifs WHERE id_tarif = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_tarif);
            
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<Tarifs> findByVolId() throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Tarifs> list = new ArrayList<>();

        try {
            conn = Maconnexion.getConnexion();
            String sql = "SELECT * FROM tarifs WHERE id_vol = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tarifs.getId_vol());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Tarifs t = new Tarifs();
                t.setId_tarif(rs.getInt("id_tarif"));
                t.setId_vol(rs.getInt("id_vol"));
                t.setId_classe(rs.getInt("id_classe"));
                t.setQuantite(rs.getInt("quantite"));
                t.setPrix(rs.getDouble("prix"));
                t.setPourcentage(rs.getInt("pourcentage"));
                t.setDate_limite_paiement(rs.getDate("date_limite_paiement"));
                list.add(t);
            }
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }

        return list;
    }

}

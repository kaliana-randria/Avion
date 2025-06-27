package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Param_vol;

public class Param_volDao {
    private Param_vol param_vol;

    public Param_vol getParam_vol() {
        return param_vol;
    }

    public void setParam_vol(Param_vol param_vol) {
        this.param_vol = param_vol;
    }

    public List<Param_vol> findAll() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Maconnexion.getConnexion();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM param_vol");

            List<Param_vol> list = new ArrayList<>();
            while (rs.next()) {
                Param_vol param_vol = new Param_vol();
                param_vol.setId_param_vol(rs.getInt("id_param_vol"));
                param_vol.setId_classe_vol(rs.getInt("id_classe_vol"));
                param_vol.setPrix(rs.getDouble("prix"));
                param_vol.setQuantite(rs.getInt("quantite"));
                Timestamp dateLimite = rs.getTimestamp("date_limite_paiement");
                if (dateLimite != null) {
                    param_vol.setDate_limite_paiement(dateLimite.toLocalDateTime());
                }
                param_vol.setEn_cours(rs.getBoolean("en_cours"));
                list.add(param_vol);
            }
            return list;
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
    }

    public List<Param_vol> findByVolId(int idVol) throws Exception {
        List<Param_vol> list = new ArrayList<>();
        Connection con = Maconnexion.getConnexion();

        String sql = "SELECT p.* FROM param_vol p " +
                "JOIN classe_vol cv ON p.id_classe_vol = cv.id_classe_vol " +
                "WHERE cv.id_vol = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, idVol);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Param_vol p = new Param_vol();
            p.setId_param_vol(rs.getInt("id_param_vol"));
            p.setId_classe_vol(rs.getInt("id_classe_vol"));
            p.setPrix(rs.getDouble("prix"));
            p.setQuantite(rs.getInt("quantite"));
            p.setDate_limite_paiement(rs.getTimestamp("date_limite_paiement").toLocalDateTime());
            p.setEn_cours(rs.getBoolean("en_cours"));
            list.add(p);
        }

        rs.close();
        pst.close();
        con.close();

        return list;
    }

    public void updateEnCoursParamVol(int idClasseVol) throws Exception {
        Connection con = Maconnexion.getConnexion();

        String disableAll = "UPDATE param_vol SET en_cours = FALSE WHERE id_classe_vol = ?";
        PreparedStatement ps1 = con.prepareStatement(disableAll);
        ps1.setInt(1, idClasseVol);
        ps1.executeUpdate();
        ps1.close();

        String sql = """
                    SELECT pv.id_param_vol, pv.quantite, COALESCE(SUM(r.quantite), 0) AS total_reserve
                    FROM param_vol pv
                    LEFT JOIN reservation r ON pv.id_param_vol = r.id_param_vol
                    LEFT JOIN enregistrement_reservation er ON r.id_reservation = er.id_reservation AND er.est_annule = false
                    WHERE pv.id_classe_vol = ? AND pv.date_limite_paiement > NOW()
                    GROUP BY pv.id_param_vol, pv.quantite, pv.date_limite_paiement
                    HAVING (pv.quantite - COALESCE(SUM(r.quantite), 0)) > 0
                    ORDER BY pv.id_param_vol ASC
                    LIMIT 1
                """;

        PreparedStatement ps2 = con.prepareStatement(sql);
        ps2.setInt(1, idClasseVol);
        ResultSet rs = ps2.executeQuery();

        if (rs.next()) {
            int idToEnable = rs.getInt("id_param_vol");

            String enable = "UPDATE param_vol SET en_cours = TRUE WHERE id_param_vol = ?";
            PreparedStatement ps3 = con.prepareStatement(enable);
            ps3.setInt(1, idToEnable);
            ps3.executeUpdate();
            ps3.close();
        }

        rs.close();
        ps2.close();
        con.close();
    }

    public Param_vol findEnCoursByClasseVol(int idClasseVol) throws Exception {
        updateEnCoursParamVol(idClasseVol);

        Connection con = Maconnexion.getConnexion();
        String sql = "SELECT * FROM param_vol WHERE id_classe_vol = ? AND en_cours = TRUE LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idClasseVol);
        ResultSet rs = ps.executeQuery();

        Param_vol p = null;
        if (rs.next()) {
            p = new Param_vol();
            p.setId_param_vol(rs.getInt("id_param_vol"));
            p.setId_classe_vol(rs.getInt("id_classe_vol"));
            p.setPrix(rs.getDouble("prix"));
            p.setQuantite(rs.getInt("quantite"));
            p.setDate_limite_paiement(rs.getTimestamp("date_limite_paiement").toLocalDateTime());
            p.setEn_cours(rs.getBoolean("en_cours"));
        }

        rs.close();
        ps.close();
        con.close();

        return p;
    }

    public Param_vol findById(int id) throws Exception {
        Connection con = Maconnexion.getConnexion();
        String sql = "SELECT * FROM param_vol WHERE id_param_vol = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        Param_vol param = null;
        if (rs.next()) {
            param = new Param_vol();
            param.setId_param_vol(rs.getInt("id_param_vol"));
            param.setId_classe_vol(rs.getInt("id_classe_vol"));
            param.setPrix(rs.getDouble("prix"));
            param.setQuantite(rs.getInt("quantite"));
            param.setDate_limite_paiement(rs.getTimestamp("date_limite_paiement").toLocalDateTime());
            param.setEn_cours(rs.getBoolean("en_cours"));
        }

        rs.close();
        ps.close();
        con.close();

        return param;
    }

    public void updateQuantite(Param_vol param) throws Exception {
        String sql = "UPDATE param_vol SET quantite = ? WHERE id_param_vol = ?";

        try (Connection connection = Maconnexion.getConnexion();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, param.getQuantite());
            pstmt.setInt(2, param.getId_param_vol());

            int updatedRows = pstmt.executeUpdate();

            if (updatedRows == 0) {
                throw new Exception("Aucun param_vol trouve avec l'id : " + param.getId_param_vol());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.Maconnexion;
import models.Compagnie;

public class CompagnieDao {

    public Compagnie findById(int id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Compagnie compagnie = null;

        try {
            con = Maconnexion.getConnexion();
            String sql = "SELECT * FROM compagnie WHERE id_compagnie = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                compagnie = new Compagnie();
                compagnie.setId_compagnie(rs.getInt("id_compagnie"));
                compagnie.setNom_compagnie(rs.getString("nom_compagnie"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }

        return compagnie;
    }
}

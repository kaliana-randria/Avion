package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Statut_vol;

public class Statut_volDao {

    public List<Statut_vol> findAll() throws Exception {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Maconnexion.getConnexion();
            List<Statut_vol> StatutVolsList = new ArrayList<>();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM statut_vol");
            Statut_vol Statutvols;
            while (rs.next()) {
                Statutvols = new Statut_vol();

                Statutvols.setId_statut_vol(rs.getInt("id_statut_vol"));
                Statutvols.setStatut(rs.getString("statut"));

                StatutVolsList.add(Statutvols);
            }
        
            return StatutVolsList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            st.close();
            rs.close();
            connection.close();
        }
    }

    public Statut_vol findById(int idStatut) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Statut_vol statut_vol = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "SELECT * FROM statut_vol WHERE id_statut_vol = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idStatut);
            rs = stmt.executeQuery();

            if (rs.next()) {
                statut_vol = new Statut_vol(
                    rs.getInt("id_statut_vol"),
                    rs.getString("statut")
                );
            }
            return statut_vol;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Statuts_vol;

public class Statut_volDAO {
    public List<Statuts_vol> findAll() throws Exception {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Maconnexion.getConnexion();
            List<Statuts_vol> StatutVolsList = new ArrayList<>();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM statuts_vol");
            Statuts_vol Statutvols;
            while (rs.next()) {
                Statutvols = new Statuts_vol();

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


    public Statuts_vol findById(int idStatut) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Statuts_vol statuts_vol = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "SELECT * FROM statuts_vol WHERE id_statut_vol = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idStatut);
            rs = stmt.executeQuery();

            if (rs.next()) {
                statuts_vol = new Statuts_vol(
                    rs.getInt("id_statut_vol"),
                    rs.getString("statut")
                );
            }
            return statuts_vol;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}

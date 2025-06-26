package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Avion;

public class AvionDao {
        public List<Avion> findAll() throws Exception {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Maconnexion.getConnexion();
            List<Avion> avionList = new ArrayList<>();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM avion");
            Avion avion;
            while (rs.next()) {
                avion = new Avion();
                
                avion.setId_avion(rs.getInt("id_avion"));
                avion.setId_compagnie(rs.getInt("id_compagnie"));
                avion.setNom_avion(rs.getString("nom_avion"));
                avion.setModele(rs.getString("modele"));

                avionList.add(avion);
            }

            return avionList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            st.close();
            rs.close();
            connection.close();
        }
    }

    public Avion findById(int idAvion) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Avion avions = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "SELECT * FROM avion WHERE id_avion = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAvion);
            rs = stmt.executeQuery();

            if (rs.next()) {
                avions = new Avion(
                    rs.getInt("id_avion"),
                    rs.getInt("id_compagnie"),
                    rs.getString("nom_avion"),
                    rs.getString("modele")
                );
            }
            
            return avions;

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

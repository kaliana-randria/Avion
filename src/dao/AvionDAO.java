package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Avions;

public class AvionDAO {
        public List<Avions> findAll() throws Exception {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Maconnexion.getConnexion();
            List<Avions> avionList = new ArrayList<>();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM avions");
            Avions avion;
            while (rs.next()) {
                avion = new Avions();
                
                avion.setId_avion(rs.getInt("id_avion"));
                avion.setNom(rs.getString("nom"));
                avion.setCode(rs.getString("code"));
                avion.setModele(rs.getString("modele"));
                avion.setBusiness_seats(rs.getInt("business_seats"));
                avion.setEconomy_seats(rs.getInt("economy_seats"));

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

    public Avions findById(int idAvion) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Avions avions = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "SELECT * FROM avions WHERE id_avion = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAvion);
            rs = stmt.executeQuery();

            if (rs.next()) {
                avions = new Avions(
                    rs.getInt("id_avion"),
                    rs.getString("nom"),
                    rs.getString("code"),
                    rs.getString("modele"),
                    rs.getInt("business_seats"),
                    rs.getInt("economy_seats")
                );
            }
            return avions;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}

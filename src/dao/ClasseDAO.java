package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.Maconnexion;
import models.Classes;

public class ClasseDAO {
    public Classes findByName(String nomClasse) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Classes classes = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "SELECT * FROM classes WHERE nom = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomClasse);
            rs = stmt.executeQuery();

            if (rs.next()) {
                classes = new Classes(
                    rs.getInt("id_classe"),
                    rs.getString("nom")
                );
            }
            return classes;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}

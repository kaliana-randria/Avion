package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.Maconnexion;
import models.Classe;

public class ClasseDao {

    public Classe findById(int idClasse) throws Exception {
        Connection con = Maconnexion.getConnexion();
        String sql = "SELECT * FROM classe WHERE id_classe = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idClasse);
        ResultSet rs = ps.executeQuery();
        Classe c = null;
        if (rs.next()) {
            c = new Classe();
            c.setId_classe(rs.getInt("id_classe"));
            c.setNom_classe(rs.getString("nom_classe"));
        }
        rs.close();
        ps.close();
        con.close();
        return c;
    }

    public Classe findByName(String nomClasse) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Classe classe = null;

        try {
            conn = Maconnexion.getConnexion();
            String sql = "SELECT * FROM classe WHERE nom_classe = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomClasse);
            rs = stmt.executeQuery();

            if (rs.next()) {
                classe = new Classe(
                    rs.getInt("id_classe"),
                    rs.getString("nom_classe")
                );
            }
            return classe;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
    }
}

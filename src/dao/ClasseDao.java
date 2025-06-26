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

}

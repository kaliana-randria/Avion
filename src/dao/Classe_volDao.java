package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Classe_vol;

public class Classe_volDao {

    public List<Classe_vol> findByVolId(int idVol) throws Exception {
        List<Classe_vol> liste = new ArrayList<>();
        Connection con = Maconnexion.getConnexion();
        String sql = "SELECT * FROM classe_vol WHERE id_vol = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idVol);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Classe_vol cv = new Classe_vol();
            cv.setId_classe_vol(rs.getInt("id_classe_vol"));
            cv.setId_vol(rs.getInt("id_vol"));
            cv.setId_classe(rs.getInt("id_classe"));
            cv.setNbr_place(rs.getInt("nbr_place"));
            liste.add(cv);
        }
        rs.close();
        ps.close();
        con.close();
        return liste;
    }

}

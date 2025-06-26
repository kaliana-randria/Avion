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

    public Classe_vol findByVolAndClasse(int idVol, int idClasse) throws Exception {
        Classe_vol result = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Maconnexion.getConnexion();
            String sql = "SELECT * FROM classe_vol WHERE id_vol = ? AND id_classe = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVol);
            ps.setInt(2, idClasse);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = new Classe_vol();
                result.setId_classe_vol(rs.getInt("id_classe_vol"));
                result.setId_vol(rs.getInt("id_vol"));
                result.setId_classe(rs.getInt("id_classe"));
                result.setNbr_place(rs.getInt("nbr_place"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (con != null)
                con.close();
        }

        return result;
    }

    public Classe_vol findById(int id) throws Exception {
        Connection con = Maconnexion.getConnexion();
        String sql = "SELECT * FROM classe_vol WHERE id_classe_vol = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        Classe_vol classeVol = null;
        if (rs.next()) {
            classeVol = new Classe_vol();
            classeVol.setId_classe_vol(rs.getInt("id_classe_vol"));
            classeVol.setId_classe(rs.getInt("id_classe"));
            classeVol.setId_vol(rs.getInt("id_vol"));
        }

        rs.close();
        ps.close();
        con.close();

        return classeVol;
    }

}

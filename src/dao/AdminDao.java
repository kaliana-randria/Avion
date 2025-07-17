package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.Maconnexion;
import models.Admin;

public class AdminDao {
    public boolean login(Admin admin) throws Exception {
        Connection connection = Maconnexion.getConnexion();
        String sql = "SELECT * FROM admin WHERE email = ? AND mdp = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, admin.getEmail());
        pst.setString(2, admin.getMdp());
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }
}

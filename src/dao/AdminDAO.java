package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.sql.SQLException;

import connection.Maconnexion;
import models.Admins;

public class AdminDAO {
    // public Admins login(Admins admins) throws SQLException {
    //     Admins admin = null;
    //     String sql = "SELECT * FROM admins WHERE email = ? AND mdp = ?";
    //     try (Connection conn = Maconnexion.getConnexion();
    //          PreparedStatement pst = conn.prepareStatement(sql)) {
             
    //         pst.setString(1, admins.getEmail());
    //         pst.setString(2, admins.getMdp());
            
    //         try (ResultSet rs = pst.executeQuery()) {
    //             if (rs.next()) {
    //                 admin = new Admins();
    //                 admin.setId_admin(rs.getInt("id_admin"));
    //                 admin.setNom_admin(rs.getString("nom_admin"));
    //                 admin.setEmail(rs.getString("email"));
    //                 admin.setMdp(rs.getString("mdp"));
    //             }
    //         }
    //     }
    //     return admin;
    // }

    public boolean login(Admins admin) throws Exception {
        Connection connection = Maconnexion.getConnexion();
        String sql = "SELECT * FROM admins WHERE email = ? AND mdp = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, admin.getEmail());
        pst.setString(2, admin.getMdp());
        ResultSet rs = pst.executeQuery();
        return rs.next(); // vrai si au moins un admin trouvé
    }

}


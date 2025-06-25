package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.Maconnexion;
import models.Clients;

public class ClientDAO {
     public List<Clients> findAll() throws Exception {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Maconnexion.getConnexion();
            List<Clients> clientList = new ArrayList<>();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM clients");
            Clients clients;
            while (rs.next()) {
                clients = new Clients();

                // clients.setId_statut_vol(rs.getInt("id_statut_vol"));

                clientList.add(clients);
            }
        
            return clientList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            st.close();
            rs.close();
            connection.close();
        }
    }
}

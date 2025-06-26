<%@ page import="models.*, java.util.List" %>
<%
    List<Vol> listVol = (List<Vol>) request.getAttribute("listes");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Home Admin</title>
</head>
<body>
    <h1>Home Admin</h1>
    <p><a href="ajoutVol">Ajouter vol</a></p>
    <h2>Les Vol</h2>
        <table border='1'>
            <tr>
                <th>id_avion</th>
                <th>ville_depart</th>
                <th>ville_arrivee</th>
                <th>date_depart</th>
                <th>date_arrivee</th>
                <th>duree (min)</th>
                <th>id_statut_vol</th>
                <th>action</th>
            </tr>
            <% for (Vol vol : listVol) { %>
            <tr>
                <td><%= vol.getId_avion() %></td>
                <td><%= vol.getVille_depart()%></td>
                <td><%= vol.getVille_arrivee()%></td>
                <td><%= vol.getDate_depart()%></td>
                <td><%= vol.getDate_arrivee()%></td>
                <td><%= vol.getDuree() %></td>
                <td><%= vol.getId_statut_vol()%></td>
                <td>
                    <a href="volAdmin?action=tarif&idVol=<%= vol.getId_vol() %>">Tarif</a> |
                    <a href="volAdmin?action=update&idVol=<%= vol.getId_vol() %>">Update</a> |
                    <a href="volAdmin?action=delete&idVol=<%= vol.getId_vol() %>">Delete</a> 
                </td>
            </tr>
            <% } %>
        </table>
</body>
</html>
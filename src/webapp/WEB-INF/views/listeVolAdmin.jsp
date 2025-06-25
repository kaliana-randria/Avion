<%@ page import="models.*, java.util.List" %>
<%
    List<Vols> listVols = (List<Vols>) request.getAttribute("listes");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>liste vols</title>
</head>
<body>
    <p><a href="admin">Admin</a></p>
    <h2>Liste Vol</h2>
    <form action="ajoutVol" method="get">
        <button type="submit">Ajouter vol</button>
    </form>

        <table border='1'>
            <tr>
                <th>numero_vol</th>
                <th>compagnie</th>
                <th>ville_depart</th>
                <th>ville_arrivee</th>
                <th>date_depart</th>
                <th>date_arrivee</th>
                <th>duree</th>
                <th>id_statut_vol</th>
                <th>id_avion</th>
            </tr>
            <% for (Vols vol : listVols) { %>
            <tr>
                <td><%= vol.getNumero_vol() %></td>
                <td><%= vol.getCompagnie() %></td>
                <td><%= vol.getVille_depart()%></td>
                <td><%= vol.getVille_arrivee()%></td>
                <td><%= vol.getDate_depart()%></td>
                <td><%= vol.getDate_arrivee()%></td>
                <td><%= vol.getDuree() %></td>
                <td><%= vol.getId_statut_vol()%></td>
                <td><%= vol.getId_avion() %></td>
                <td><a href="listeFilm?action=delete&idFilm=<%= film.getId_film()%>">Delete</a></td>
                <td><a href="listeFilm?action=modify&idFilm=<%= film.getId_film()%>">Update</a></td>
            </tr>
            <% } %>
        </table>
</body>
</html>
<%@ page import="models.*, java.util.List" %>
<%
    List<Vol> listVol = (List<Vol>) request.getAttribute("listes");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil Vol</title>
</head>
<body>
    <header id="main-header">
        <nav>
            <ul>
                <li><a href="accueil">Accueil</a></li>
                <li><a href="panier">Panier_Reservation</a></li>
                <li><a href="admin">Admin</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h1>Liste de tous les vols</h1>
        
        <form action="recherche" method="get">
            <input type="date" name="date_depart" placeholder="Date depart">
            <input type="text" name="compagnie" placeholder="Compagnie">
            <input type="text" name="ville_depart" placeholder="Ville de depart">
            <input type="text" name="ville_arrivee" placeholder="Ville d arrivee">
            <button type="submit">Rechercher</button>
        </form>

        <table border="1">
            <tr>
                <th>Avion</th>
                <th>Ville de depart</th>
                <th>Ville d arrivee</th>
                <th>Date de depart</th>
                <th>Date d arrivee</th>
                <th>Duree (min)</th>
                <th>Statut du vol</th>
                <th>Action</th>
            </tr>
            <% for (Vol vol : listVol) { %>
                <tr>
                    <td><%= vol.getId_avion() %></td>
                    <td><%= vol.getVille_depart() %></td>
                    <td><%= vol.getVille_arrivee() %></td>
                    <td><%= vol.getDate_depart()%></td>
                    <td><%= vol.getDate_arrivee()%></td>
                    <td><%= vol.getDuree() %></td>
                    <td><%= vol.getId_statut_vol() %></td>
                    <td><a href="detailVol?action=detail&idVol=<%= vol.getId_vol() %>">Voir details</a></td>
                </tr>
            <% } %>
        </table>
    </main>
    <footer id="main-footer">
        <div>
            <p>&copy; 2025 - Gestion de vols - Projet Web Dynamique</p>
        </div>
    </footer>
</body>
</html>

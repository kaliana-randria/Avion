<%@ page import="models.*, java.util.List" %>
<%
    // String style = request.getContextPath();
    List<Vols> listVols = (List<Vols>) request.getAttribute("listes");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%-- <link rel="stylesheet" href="style.css"> --%>
    <title>Liste des Vols</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f4f6f8;
        }

        h2 {
            color: #2c3e50;
            margin-bottom: 20px;
        }

        a {
            color: #2980b9;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        form {
            margin-bottom: 30px;
        }

        form input[type="text"],
        form button {
            padding: 8px 12px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        form button {
            background-color: #3498db;
            color: white;
            border: none;
            cursor: pointer;
        }

        form button:hover {
            background-color: #2980b9;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        th,
        td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .top-link {
            margin-bottom: 20px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <p class="top-link"><a href="admin">Admin</a></p>

    <h2>Liste des Vols</h2>

    <form action="rechercheVols" method="get">
        <input type="text" name="numero_vol" placeholder="Numero de vol">
        <input type="text" name="compagnie" placeholder="Compagnie">
        <input type="text" name="ville_depart" placeholder="Ville de depart">
        <input type="text" name="ville_arrivee" placeholder="Ville d arrivee">
        <button type="submit">Rechercher</button>
    </form>

    <table>
        <tr>
            <th>Numero vol</th>
            <th>Compagnie</th>
            <th>Ville depart</th>
            <th>Ville arrivee</th>
            <th>Date depart</th>
            <th>Date arrivee</th>
            <th>Duree (min)</th>
            <th>Statut vol</th>
            <th>Avion</th>
            <th>Detail</th>
        </tr>
        <% for (Vols vol : listVols) { %>
        <tr>
            <td><%= vol.getNumero_vol() %></td>
            <td><%= vol.getCompagnie() %></td>
            <td><%= vol.getVille_depart() %></td>
            <td><%= vol.getVille_arrivee() %></td>
            <td><%= vol.getDate_depart() %></td>
            <td><%= vol.getDate_arrivee() %></td>
            <td><%= vol.getDuree() %></td>
            <td><%= vol.getId_statut_vol() %></td>
            <td><%= vol.getId_avion() %></td>
            <td><a href="detailVol?action=detail&idVol=<%= vol.getId_vol() %>">Voir details</a></td>
        </tr>
        <% } %>
    </table>
</body>
</html>

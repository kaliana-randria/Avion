<%@ page import="models.*, java.util.List" %>
<%
    List<Vols> listVols = (List<Vols>) request.getAttribute("listes");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Home Admin</title>
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
    <h1>Home Admin</h1>
    <p><a href="ajoutVol">Ajouter vol</a></p>
    <h2>Les Vols</h2>
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
                <th>action</th>
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
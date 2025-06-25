<%@ page import="models.*, java.util.List" %>
<%
    List<Vols> listVols = (List<Vols>) request.getAttribute("listes");
    List<Tarifs> tarifList = (List<Tarifs>) request.getAttribute("TarifList");
    Vols vol = (Vols) request.getAttribute("volDetail");
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
    <% for (Tarifs tarif : tarifList) { %>
        <h1>Tarif du vol : <%= tarif.getId_tarif()%></h1>
    <% } %>
    <p><a href="ajoutTarif">Ajouter tarif</a></p>
    <h2>Les Tarifs</h2>
        <table border='1'>
            <tr>
                <th>Vol</th>
                <th>Classe</th>
                <th>Quantite</th>
                <th>prix</th>
                <th>pourcentage (%)</th>
                <th>date limite paiement</th>
                <th>action</th>
            </tr>
            <% for (Tarifs tarif : tarifList) { %>
            <tr>
                <td><%= tarif.getId_vol() %></td>
                <td><%= tarif.getId_classe() %></td>
                <td><%= tarif.getQuantite()%></td>
                <td><%= tarif.getPrix()%></td>
                <td><%= tarif.getPourcentage()%></td>
                <td><%= tarif.getDate_limite_paiement()%></td>
                <td>
                    <a href="tarifAdmin?action=update&idTarif=<%= tarif.getId_vol() %>">Update</a> |
                    <a href="tarifAdmin?action=delete&idTarif=<%= tarif.getId_vol() %>">Delete</a> 
                </td>
            </tr>
            <% } %>
        </table>
</body>
</html>
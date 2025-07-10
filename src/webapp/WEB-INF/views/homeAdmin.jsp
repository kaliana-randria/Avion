<%@ page import="models.*, java.util.List" %>
<%
    List<Vol> listVol = (List<Vol>) request.getAttribute("listes");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Home Admin</title>
    <style> 
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        header {
            background-color:#222;
            color: white;
            padding: 1rem;
        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 1rem;
            padding: 0;
            margin: 0;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        nav a:hover {
            text-decoration: underline;
        }

        main {
            padding: 2rem;
        }

        h1 {
            color: #333;
        }

        form {
            margin-bottom: 2rem;
        }

        input, button {
            padding: 0.5rem;
            margin-right: 1rem;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        th, td {
            padding: 0.75rem;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }

        th {
            background-color: #222;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        footer {
            background-color: #222;
            color: white;
            text-align: center;
            padding: 1rem;
            margin-top: 2rem;
        }
    </style>
</head>
<body>
    <header id="main-header">
        <nav>
            <ul>
                <li><a href="accueil">Accueil</a></li>
                <li><a href="panier">Panier_Reservation</a></li>
            </ul>
        </nav>
    </header>

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
                    <a href="volAdmin?action=delete&idVol=<%= vol.getId_vol() %>">Delete</a> |
                    <a href="detailVolAdmin?action=detail&idVol=<%= vol.getId_vol() %>">Voir details</a>
                </td>
            </tr>
            <% } %>
        </table>

    <footer id="main-footer">
        <div>
            <p>&copy; 2025 - Gestion de vols - Projet Web Dynamique</p>
        </div>
    </footer>
</body>
</html>
<%@ page import="models.*, java.util.List" %>
<%
    List<Vol> listVol = (List<Vol>) request.getAttribute("listes");
    List<Avion> listAvions = (List<Avion>) request.getAttribute("listAvions");
    List<Compagnie> listCompagnies = (List<Compagnie>) request.getAttribute("listCompagnies");
    List<Statut_vol> listStatuts = (List<Statut_vol>) request.getAttribute("listStatuts");
    List<String> listHeures = (List<String>) request.getAttribute("listHeures");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil Vol</title>
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
                <th>Compagnie</th>
                <th>Ville de depart</th>
                <th>Ville d arrivee</th>
                <th>Date de depart</th>
                <th>Date d arrivee</th>
                <th>Duree (min)</th>
                <th>Statut du vol</th>
                <th>Action</th>
            </tr>
            <% for (int i = 0; i < listVol.size(); i++) { 
                Vol vol = listVol.get(i);
                Avion avion = listAvions.get(i);
                Compagnie compagnie = listCompagnies.get(i);
                Statut_vol statut = listStatuts.get(i);
                String heure = listHeures.get(i);
            %>
                <tr>
                    <td><%= avion.getNom_avion() %></td>
                    <td><%= compagnie.getNom_compagnie() %></td>
                    <td><%= vol.getVille_depart() %></td>
                    <td><%= vol.getVille_arrivee() %></td>
                    <td><%= vol.getDate_depart() %></td>
                    <td><%= vol.getDate_arrivee() %></td>
                    <td><%= vol.getDuree() %> ou (<%= heure %>)</td>
                    <td><%= statut.getStatut() %></td>
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

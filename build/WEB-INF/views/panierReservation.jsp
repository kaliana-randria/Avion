<%@ page import="models.*, java.util.List" %>
<%
    List<PanierDetail> panier = (List<PanierDetail>) request.getAttribute("panier");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panier des Reservations</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #222;
            color: white;
            padding: 1rem;
        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 1rem;
            margin: 0;
            padding: 0;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        nav a:hover {
            text-decoration: underline;
        }

        h1 {
            text-align: center;
            margin-top: 2rem;
            color: #333;
        }

        table {
            width: 95%;
            margin: 2rem auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 0.75rem;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #444;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        input[type="submit"] {
            background-color: #c0392b;
            color: white;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #e74c3c;
        }

        .non-annulable {
            color: gray;
            font-style: italic;
        }

        footer {
            background-color: #222;
            color: white;
            text-align: center;
            padding: 1rem;
            margin-top: 3rem;
        }
    </style>
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="accueil">Accueil</a></li>
                <li><a href="panier">Panier_Reservation</a></li>
                <li><a href="admin">Admin</a></li>
            </ul>
        </nav>
    </header>

    <h1>Panier des Reservations</h1>

    <table>
        <tr>
            <th>ID Reservation</th>
            <th>Reference</th>
            <th>Vol</th>
            <th>Avion</th>
            <th>Compagnie</th>
            <th>Depart</th>
            <th>Arrivee</th>
            <th>Date Depart</th>
            <th>Date Arrivee</th>
            <th>Classe</th>
            <th>Tarif (euro)</th>
            <th>Paiement</th>
            <th>Action</th>
        </tr>
        <% if (panier != null && !panier.isEmpty()) {
            for (PanierDetail p : panier) {
        %>
        <tr>
            <td><%= p.getIdReservation() %></td>
            <td><%= p.getReference() %></td>
            <td><%= p.getVol().getId_vol() %></td>
            <td><%= p.getAvion().getNom_avion() %></td>
            <td><%= p.getCompagnie().getNom_compagnie() %></td>
            <td><%= p.getVol().getVille_depart() %></td>
            <td><%= p.getVol().getVille_arrivee() %></td>
            <td><%= p.getDateDepart() %></td>
            <td><%= p.getDateArrivee() %></td>
            <td><%= p.getClasse().getNom_classe() %></td>
            <td><%= p.getTarif() %></td>
            <td><%= p.isEstPaye() ? "Paye" : "Non paye" %></td>
            <td>
                <% if (!p.isAnnule() && !p.isEstPaye()) { %>
                    <form action="annuler" method="post">
                        <input type="hidden" name="idEnregistrement" value="<%= p.getIdEnregistrement() %>">
                        <input type="submit" value="Annuler">
                    </form>
                <% } else if (p.isAnnule()) { %>
                    Annule
                <% } else if (p.isEstPaye()) { %>
                    <span class="non-annulable">Non annulable</span>
                <% } %>
            </td>
        </tr>
        <% } 
        } else { %>
        <tr>
            <td colspan="13">Aucune reservation trouvee.</td>
        </tr>
        <% } %>
    </table>

    <footer>
        <p>&copy; 2025 - Gestion de vols - Projet Web Dynamique</p>
    </footer>
</body>
</html>

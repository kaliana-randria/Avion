<%@ page import="models.*, java.util.List" %>
<%
    Vol vol = (Vol) request.getAttribute("volDetail");
    Statut_vol statutVol = (Statut_vol) request.getAttribute("statutVol");
    Avion avion = (Avion) request.getAttribute("avion");
    Compagnie compagnie = (Compagnie) request.getAttribute("compagnie");
    List<ClasseTarifDispo> tarifsDispo = (List<ClasseTarifDispo>) request.getAttribute("tarifsDispo");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Details Vol</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
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

        input,
        button {
            padding: 0.5rem;
            margin-right: 1rem;
        }

        .container {
            max-width: 800px;
            margin: auto;
            background-color: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .container p {
            margin-bottom: 1rem;
            line-height: 1.5;
        }

        .btn {
            display: inline-block;
            margin-top: 0.5rem;
            padding: 0.6rem 1rem;
            background-color:#222;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color:#222;
        }

        th,
        td {
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

    <div class="container">
        <h1>Details du Vol</h1>
        <p><strong>Vol :</strong> <%= vol.getId_vol()%></p>
        <p><strong>Compagnie :</strong> <%= compagnie.getNom_compagnie()%></p>
        <p><strong>Avion :</strong> <%= avion.getNom_avion()%></p>
        <p><strong>Trajet :</strong> <%= vol.getVille_depart()%> - <%= vol.getVille_arrivee()%></p>
        <p><strong>Horaires :</strong> <%= vol.getDate_depart()%> - <%= vol.getDate_arrivee()%></p>
        <p><strong>Duree :</strong> <%= vol.getDuree()%> min</p>
        <p><strong>Statut :</strong> <%= statutVol.getStatut()%></p>

        <p>
            Faire une reservation :

            <% for (ClasseTarifDispo tarifDispo : tarifsDispo) { %>
                <p>
                    <strong>Classe :</strong> <%= tarifDispo.getClasse().getNom_classe() %><br>
                    Tarif en cours : <%= tarifDispo.getParamVol().getPrix() %> Ar<br>
                    Quantite dispo : <%= tarifDispo.getRestePlace() %><br>
                    <a class="btn" href="reservationAdmin?action=<%= tarifDispo.getClasse().getNom_classe() %>&idVol=<%= vol.getId_vol()%>">
                        Reserver en <%= tarifDispo.getClasse().getNom_classe() %>
                    </a>
                </p>
            <% } %>

        </p>
    </div>

    <footer id="main-footer">
        <div>
            <p>&copy; 2025 - Gestion de vols - Projet Web Dynamique</p>
        </div>
    </footer>
</body>
</html>

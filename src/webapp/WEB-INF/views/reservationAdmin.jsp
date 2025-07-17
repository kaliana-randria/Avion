<%@ page import="models.*, java.util.List" %>
<%
    Vol vol = (Vol) request.getAttribute("volDetail");
    String classe = (String) request.getAttribute("classe");
    Classe donneesClasse = (Classe) request.getAttribute("classeDonnees");
    ClasseTarifDispo tarifDispo = (ClasseTarifDispo) request.getAttribute("tarifDispo");
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Vol</title>
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

        form {
            max-width: 600px;
            margin: 2rem auto;
            padding: 2rem;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        p {
            margin-bottom: 1rem;
        }

        input[type="number"] {
            padding: 0.5rem;
            width: 100%;
            box-sizing: border-box;
        }

        input[type="submit"] {
            padding: 0.7rem 1.5rem;
            background-color: #222;
            color: white;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #222;
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
                <li><a href="affaireVol">Chiffre_Affaire_Vol</a></li>
            </ul>
        </nav>
    </header>

    <form action="reserverAdmin" method="post">
        <h1>Reservation Vol</h1>

        Tarif en cours : <%= tarifDispo.getParamVol().getPrix() %> euro<br>
        Quantite dispo : <%= tarifDispo.getRestePlace() %><br>

        <p>Vol : <%= vol.getId_vol()%></p>
        <p>Classe : <%= classe%></p>
        <input type="hidden" name="idVol" value="<%= vol.getId_vol() %>">
        <input type="hidden" name="idClasse" value="<%= donneesClasse.getId_classe() %>">
        <p>Nombre personne : <input type="number" name="quantite" min="0"></p>
        <p><input type="checkbox" name="paiement"> Paiement</p>

        <p><input type="submit" value="valider"></p>

    </form>

    <footer id="main-footer">
        <div>
            <p>&copy; 2025 - Gestion de vols - Projet Web Dynamique</p>
        </div>
    </footer>
</body>
</html>
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

    <form action="reserver" method="post">
        <h1>Reservation Vol</h1>

        Tarif en cours : <%= tarifDispo.getParamVol().getPrix() %> Ar<br>
        Quantite dispo : <%= tarifDispo.getRestePlace() %><br>

        <p>Vol : <%= vol.getId_vol()%></p>
        <p>Classe : <%= classe%></p>
        <input type="hidden" name="idVol" value="<%= vol.getId_vol() %>">
        <input type="hidden" name="idClasse" value="<%= donneesClasse.getId_classe() %>">
        <p>Nombre personne : <input type="number" name="quantite" min="0"></p>

        <p><input type="submit" value="valider"></p>

    </form>

    <footer id="main-footer">
        <div>
            <p>&copy; 2025 - Gestion de vols - Projet Web Dynamique</p>
        </div>
    </footer>
</body>
</html>
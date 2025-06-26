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
</head>
<body>
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

            <% for (ClasseTarifDispo item : tarifsDispo) { %>
                <p>
                    <strong>Classe :</strong> <%= item.getClasse().getNom_classe() %><br>
                    Tarif en cours : <%= item.getParamVol().getPrix() %> Ar<br>
                    Quantite dispo : <%= item.getRestePlace() %><br>
                    <a class="btn" href="reservation?action=<%= item.getClasse().getNom_classe() %>&idVol=<%= vol.getId_vol()%>">
                        Reserver en <%= item.getClasse().getNom_classe() %>
                    </a>
                </p>
            <% } %>

        </p>
    </div>
</body>
</html>

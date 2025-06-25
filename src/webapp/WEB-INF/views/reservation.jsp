<%@ page import="models.*, java.util.List" %>
<%
    Vols vol = (Vols) request.getAttribute("volDetail");
    String classe = (String) request.getAttribute("classe");
    Classes donneesClasse = (Classes) request.getAttribute("classeDonnees");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Vol</title>
</head>
<body>
    <form action="reserver" method="post">
        <h1>Reservation Vol</h1>
        <p>Vol : <%= vol.getNumero_vol()%></p>
        <p>Classe : <%= classe%></p>
        <!-- <p>TARIF : <%= %></p> -->
        <!-- <p>
            client :
            <select name="idClient" id="idClient">
                <option value=""></option>
                <% for (Clients clients : listClient) { %>
                <% } %>
            </select>
        </p> -->
        <p>Nombre personne : <input type="number" name="quantite" min="1"></p>
        <%-- <p>Statut reservation : 
            <select name="statut" id="statut">
                <option value=""></option>
                <% for (Vols vol : listVols) { %>
                    <option value="<%= %>"><%= %></option>
                <% } %>
            </select>
        </p> --%>
        
    </form>
</body>
</html>
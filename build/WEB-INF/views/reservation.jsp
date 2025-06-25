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
        <p>Nombre personne : <input type="number" name="quantite" min="1"></p>
        
    </form>
</body>
</html>
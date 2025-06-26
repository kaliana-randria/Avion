<%@ page import="models.*" %>
<%
    Vol vol = (Vol) request.getAttribute("vol");
    Classe classe = (Classe) request.getAttribute("classe");
    int quantite = (Integer) request.getAttribute("quantite");
    String reference = (String) request.getAttribute("reference");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recapitulatif Reservation</title>
</head>
<body>
    <h1>Recapitulatif de votre Reservation</h1>
    <form action="paiement" method="post">
        <p><strong>Reference :</strong> <%= reference %></p>
        <p><strong>Vol :</strong> <%= vol.getId_vol() %></p>
        <p><strong>Depart :</strong> <%= vol.getVille_depart() %></p>
        <p><strong>Arrivee :</strong> <%= vol.getVille_arrivee() %></p>
        <p><strong>Classe :</strong> <%= classe.getNom_classe() %></p>
        <p><strong>Nombre de personnes :</strong> <%= quantite %></p>
        <p><strong>Paiement :</strong> Non paye</p>
        <p><input type="submit" value="faire Paiement"></p>
    </form>
    <a href="accueil.jsp">Retour a l accueil</a>
</body>
</html>

<%@ page import="models.*, java.util.List" %>
<%
    List<Vol> listVols = (List<Vol>) request.getAttribute("listes");
    List<Param_vol> tarifList = (List<Param_vol>) request.getAttribute("TarifList");
    Vol vol = (Vol) request.getAttribute("volDetail");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Home Admin</title>
</head>
<body>
    <p><a href="ajoutTarif?idVol=<%= vol.getId_vol() %>">Ajouter tarif</a></p>
    <h2>Les Tarifs</h2>
        <table border='1'>
            <tr>
                <th>Quantite</th>
                <th>prix</th>
                <th>date limite paiement</th>
                <th>action</th>
            </tr>
            <% for (Param_vol tarif : tarifList) { %>
            <tr>
                <td><%= tarif.getQuantite()%></td>
                <td><%= tarif.getPrix()%></td>
                <td><%= tarif.getDate_limite_paiement()%></td>
                <td>
                    <a href="tarifAdmin?action=update&idTarif=<%= tarif.getId_param_vol() %>&idVol=<%= vol.getId_vol() %>">Update</a> |
                    <a href="tarifAdmin?action=delete&idTarif=<%= tarif.getId_param_vol() %>&idVol=<%= vol.getId_vol() %>">Delete</a>
                </td>
            </tr>
            <% } %>
        </table>
</body>
</html>
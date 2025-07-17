<%@ page import="models.*, java.util.List" %>
<%
    List<Classe_vol> classes = (List<Classe_vol>) request.getAttribute("classes");
    Param_vol tarif = (Param_vol) request.getAttribute("tarifToUpdate");
    boolean isUpdate = (tarif != null);
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title><%= isUpdate ? "Modification" : "Ajout" %> Tarif</title>
</head>
<body>
    <h1><%= isUpdate ? "Modifier" : "Ajouter" %> un Tarif</h1>

    <form action="tarifAdmin" method="post">
        <input type="hidden" name="idVol" value="<%= request.getParameter("idVol") %>">

        <% if (isUpdate) { %>
            <input type="hidden" name="id_param_vol" value="<%= tarif.getId_param_vol() %>">
        <% } %>

        <p>
            Classe :
            <select name="id_classe_vol" required>
                <option value=""></option>
                <% for (Classe_vol cl : classes) { %>
                    <option value="<%= cl.getId_classe_vol() %>" 
                        <%= isUpdate && cl.getId_classe_vol() == tarif.getId_classe_vol() ? "selected" : "" %>>
                        <%= cl.getId_classe() %>
                    </option>
                <% } %>
            </select>
        </p>

        <p>
            Prix :
            <input type="number" name="prix" step="0.01" required 
                   value="<%= isUpdate ? tarif.getPrix() : "" %>">
        </p>

        <p>
            Quantite :
            <input type="number" name="quantite" required 
                   value="<%= isUpdate ? tarif.getQuantite() : "" %>">
        </p>

        <p>
            Date limite de paiement :
            <input type="datetime-local" name="date_limite_paiement" required
                   value="<%= isUpdate ? tarif.getDate_limite_paiement().toString().replace(" ", "T") : "" %>">
        </p>

        <p>
            <input type="submit" value="<%= isUpdate ? "Modifier" : "Ajouter" %> le tarif">
        </p>
    </form>

    <p><a href="volAdmin">Retour</a></p>
</body>
</html>

<%@ page import="models.*, java.util.List" %>
<%
    List<Statut_vol> statutVols = (List<Statut_vol>) request.getAttribute("satutsVol");
    List<Avion> avions = (List<Avion>) request.getAttribute("avions");
    Vol vol = (Vol) request.getAttribute("volToUpdate");
    boolean isUpdate = (vol != null);
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title><%= isUpdate ? "Modification" : "Ajout" %> Vol</title>
</head>
<body>
    <h1><%= isUpdate ? "Modifier" : "Ajouter" %> un Vol</h1>

    <form action="volAdmin" method="post">
        <% if (isUpdate) { %>
            <input type="hidden" name="idVol" value="<%= vol.getId_vol() %>">
        <% } %>

        <p>
            Avion :
            <select name="id_avion" required>
                <option value=""></option>
                <% for (Avion avion : avions) {
                    boolean selected = isUpdate && avion.getId_avion() == vol.getId_avion();
                %>
                    <option value="<%= avion.getId_avion() %>" <%= selected ? "selected" : "" %>>
                        <%= avion.getNom_avion() %>
                    </option>
                <% } %>
            </select>
        </p>

        <p>
            Ville de depart :
            <select name="ville_depart" required>
                <option value=""></option>
                <% String[] villes = {"Antananarivo", "Paris", "Tulear"};
                   for (String ville : villes) {
                       boolean selected = isUpdate && ville.equals(vol.getVille_depart());
                %>
                    <option value="<%= ville %>" <%= selected ? "selected" : "" %>><%= ville %></option>
                <% } %>
            </select>
        </p>

        <p>
            Ville d arrivee :
            <select name="ville_arrivee" required>
                <option value=""></option>
                <% String[] villesArr = {"Paris", "Antananarivo", "Tamatave"};
                   for (String ville : villesArr) {
                       boolean selected = isUpdate && ville.equals(vol.getVille_arrivee());
                %>
                    <option value="<%= ville %>" <%= selected ? "selected" : "" %>><%= ville %></option>
                <% } %>
            </select>
        </p>

        <p>
            Date de depart :
            <input type="datetime-local" name="date_depart" required
                   value="<%= isUpdate ? vol.getDate_depart().toString().replace(" ", "T") : "" %>">
        </p>

        <p>
            Date d arrivee :
            <input type="datetime-local" name="date_arrivee" required
                   value="<%= isUpdate ? vol.getDate_arrivee().toString().replace(" ", "T") : "" %>">
        </p>

        <p>
            Statut du vol :
            <select name="id_statut_vol" required>
                <option value=""></option>
                <% if (statutVols != null) { %>
                    <% for (Statut_vol statutvol : statutVols) { %>
                        <option value="<%= statutvol.getId_statut_vol() %>"
                            <%= (isUpdate && vol.getId_statut_vol() == statutvol.getId_statut_vol()) ? "selected" : "" %>>
                            <%= statutvol.getStatut() %>
                        </option>
                    <% } %>
                <% } else { %>
                    <p style="color:red;">Erreur : Aucune liste de statuts chargee</p>
                <% } %>
            </select>
        </p>

        <p>
            <input type="submit" value="<%= isUpdate ? "Modifier" : "Ajouter" %> le vol">
        </p>
    </form>

    <p><a href="volAdmin">Retour</a></p>
</body>
</html>


<%@ page import="models.*, java.util.List" %>
<%
    List<Statuts_vol> statutVols = (List<Statuts_vol>) request.getAttribute("satutsVol");
    List<Avions> avions = (List<Avions>) request.getAttribute("avions");
    Vols vol = (Vols) request.getAttribute("volToUpdate");
    boolean isUpdate = (vol != null);
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title><%= isUpdate ? "Modification" : "Ajout" %> tarif</title>
</head>
<body>
    <h1><%= isUpdate ? "Modifier" : "Ajouter" %> un tarif</h1>

    <form action="volAdmin" method="post">
        <% if (isUpdate) { %>
            <input type="hidden" name="idTarif" value="<%= vol.getId_vol() %>">
        <% } %>

        <p>
            <input type="text" name="numero_vol" placeholder="Numero de vol" required
                   value="<%= isUpdate ? vol.getNumero_vol() : "" %>">
        </p>

        <p>
            Compagnie :
            <select name="compagnie" required>
                <option value=""></option>
                <% String[] compagnies = {"Air France", "Tsaradia", "Ethiopian"}; 
                   for (String comp : compagnies) {
                       boolean selected = isUpdate && comp.equals(vol.getCompagnie());
                %>
                    <option value="<%= comp %>" <%= selected ? "selected" : "" %>><%= comp %></option>
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
                <% for (Statuts_vol statutvol : statutVols) { %>
                    <option value="<%= statutvol.getId_statut_vol() %>"
                        <%= (isUpdate && vol.getId_statut_vol() == statutvol.getId_statut_vol()) ? "selected" : "" %>>
                        <%= statutvol.getStatut() %>
                    </option>
                <% } %>
            </select>
        </p>

        <p>
            Avion :
            <select name="id_avion" required>
                <option value=""></option>
                <% for (Avions avion : avions) {
                    boolean selected = isUpdate && avion.getId_avion() == vol.getId_avion();
                %>
                    <option value="<%= avion.getId_avion() %>" <%= selected ? "selected" : "" %>>
                        <%= avion.getNom() %>
                    </option>
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

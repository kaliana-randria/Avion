<%@ page import="models.*, java.util.List" %>
<%
    List<PanierDetail> panier = (List<PanierDetail>) request.getAttribute("panier");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panier des Reservations</title>
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

    <h1>Panier des Reservations</h1>

    <%-- <table border="1">
        <tr>
            <th>ID Reservation</th>
            <th>Reference</th>
            <th>Vol</th>
            <th>Avion</th>
            <th>Compagnie</th>
            <th>Depart</th>
            <th>Arrivee</th>
            <th>Date Depart</th>
            <th>Date Arrivee</th>
            <th>Paiement</th>
            <th>Action</th>
        </tr>
        <% if (panier != null) {
            for (PanierDetail p : panier) {
        %>
        <tr>
            <td><%= p.getIdReservation() %></td>
            <td><%= p.getReference() %></td>
            <td><%= p.getVol().getId_vol() %></td>
            <td><%= p.getAvion().getNom_avion() %></td>
            <td><%= p.getCompagnie().getNom_compagnie() %></td>
            <td><%= p.getVol().getVille_depart() %></td>
            <td><%= p.getVol().getVille_arrivee() %></td>
            <td><%= p.getDateDepart() %></td>
            <td><%= p.getDateArrivee() %></td>
            <td><%= p.isEstPaye() ? "Paye" : "Non paye" %></td>
            <td>
                <% if (!p.isAnnule()) { %>
                    <form action="annuler" method="post">
                        <input type="hidden" name="idEnregistrement" value="<%= p.getIdEnregistrement() %>">
                        <input type="submit" value="Annuler">
                    </form>
                <% } %>
            </td>
        </tr>
        <% 
            }
        } else {
        %>
        <tr>
            <td>Aucune reservation trouvee.</td>
        </tr>
        <% } %>
    </table> --%>

    <table border="1">
        <tr>
            <th>ID Reservation</th>
            <th>Reference</th>
            <th>Vol</th>
            <th>Avion</th>
            <th>Compagnie</th>
            <th>Depart</th>
            <th>Arrivee</th>
            <th>Date Depart</th>
            <th>Date Arrivee</th>
            <th>Classe</th>
            <th>Tarif (Ar)</th>
            <th>Paiement</th>
            <th>Action</th>
        </tr>
        <% if (panier != null) {
            for (PanierDetail p : panier) {
        %>
        <tr>
            <td><%= p.getIdReservation() %></td>
            <td><%= p.getReference() %></td>
            <td><%= p.getVol().getId_vol() %></td>
            <td><%= p.getAvion().getNom_avion() %></td>
            <td><%= p.getCompagnie().getNom_compagnie() %></td>
            <td><%= p.getVol().getVille_depart() %></td>
            <td><%= p.getVol().getVille_arrivee() %></td>
            <td><%= p.getDateDepart() %></td>
            <td><%= p.getDateArrivee() %></td>
            <td><%= p.getClasse().getNom_classe() %></td>
            <td><%= p.getTarif() %></td>
            <td><%= p.isEstPaye() ? "Paye" : "Non paye" %></td>
            <td>
                <% if (!p.isAnnule()) { %>
                    <form action="annuler" method="post">
                        <input type="hidden" name="idEnregistrement" value="<%= p.getIdEnregistrement() %>">
                        <input type="submit" value="Annuler">
                    </form>
                <% } else { %>
                    Annule
                <% } %>
            </td>
        </tr>
        <% } } else { %>
        <tr>
            <td colspan="13">Aucune reservation trouvee.</td>
        </tr>
        <% } %>
    </table>


    <footer id="main-footer">
        <div>
            <p>&copy; 2025 - Gestion de vols - Projet Web Dynamique</p>
        </div>
    </footer>
</body>
</html>

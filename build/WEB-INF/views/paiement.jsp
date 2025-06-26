<% String message = (String) request.getAttribute("message"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Paiement Reservation</title>
</head>
<body>
    <h1>Paiement de Reservation</h1>
    <form action="paiement" method="post">
        <label for="reference">Numero de reference :</label>
        <input type="text" id="reference" name="reference" required>
        <input type="submit" value="Valider le Paiement">
    </form>
    <% if (message != null) { %>
        <p class="message"><%= message %></p>
    <% } %>
</body>
</html>

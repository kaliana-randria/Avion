<% String erreur = (String) request.getAttribute("erreur"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Admin</title>
</head>
<body>
    <div class="login-container">
        <h1>Login Admin</h1>
        <form action="admin" method="post">
            <input type="text" placeholder="Email" name="email" required>
            <input type="password" placeholder="Mot de passe" name="mdp" required>
            <input type="submit" value="Valider">
        </form>
        <% if (erreur != null) { %>
            <p class="error"><%= erreur %></p>
        <% } %>
    </div>
</body>
</html>

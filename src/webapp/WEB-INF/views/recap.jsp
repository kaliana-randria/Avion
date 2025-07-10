<%@ page import="models.*" %>
<%
    Vol vol = (Vol) request.getAttribute("vol");
    Classe classe = (Classe) request.getAttribute("classe");
    int quantite = (Integer) request.getAttribute("quantite");
    boolean estPaye = request.getAttribute("estPaye") != null && (Boolean) request.getAttribute("estPaye");
    String reference = (String) request.getAttribute("reference");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recapitulatif Reservation</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        header {
            background-color: #222;
            color: white;
            padding: 1rem;
        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 1rem;
            padding: 0;
            margin: 0;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        nav a:hover {
            text-decoration: underline;
        }

        main {
            padding: 2rem;
        }

        h1 {
            color: #333;
        }

        form {
            margin-bottom: 2rem;
        }

        input,
        button {
            padding: 0.5rem;
            margin-right: 1rem;
        }

        .container {
            max-width: 800px;
            margin: auto;
            background-color: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .container p {
            margin-bottom: 1rem;
            line-height: 1.5;
        }

        .btn {
            display: inline-block;
            margin-top: 0.5rem;
            padding: 0.6rem 1rem;
            background-color:#222;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color:#222;
        }

        th,
        td {
            padding: 0.75rem;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }

        th {
            background-color: #222;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        footer {
            background-color: #222;
            color: white;
            text-align: center;
            padding: 1rem;
            margin-top: 2rem;
        }
    </style>
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
    
    <h1>Recapitulatif de votre Reservation</h1>
    <form action="paiement" method="post">
        <p><strong>Reference :</strong> <%= reference %></p>
        <p><strong>Vol :</strong> <%= vol.getId_vol() %></p>
        <p><strong>Depart :</strong> <%= vol.getVille_depart() %></p>
        <p><strong>Arrivee :</strong> <%= vol.getVille_arrivee() %></p>
        <p><strong>Classe :</strong> <%= classe.getNom_classe() %></p>
        <p><strong>Nombre de personnes :</strong> <%= quantite %></p>
        <%-- <p><strong>Paiement :</strong> Non paye</p>
        <p><input type="submit" value="faire Paiement"></p> --%>

        <% if (estPaye) { %>
            <p><strong>Paiement :</strong> Deja paye</p>
        <% } else { %>
            <p><strong>Paiement :</strong> Non paye</p>
            <p><input type="submit" value="faire Paiement"></p>
        <% } %>

    </form>
    <a href="accueil">Retour a l accueil</a>

    <footer id="main-footer">
        <div>
            <p>&copy; 2025 - Gestion de vols - Projet Web Dynamique</p>
        </div>
    </footer>
</body>
</html>

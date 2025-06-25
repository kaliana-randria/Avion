<%@ page import="models.*, java.util.List" %>
<%
    Vols vol = (Vols) request.getAttribute("volDetail");
    Statuts_vol statutVol = (Statuts_vol) request.getAttribute("statutVol");
    Avions avion = (Avions) request.getAttribute("avion");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Details Vol</title>
    <%-- <link rel="stylesheet" href="assets/css/style.css"> --%>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background: #f5f5f5;
            color: #333;
        }

        .container {
            background: #fff;
            padding: 20px;
            max-width: 600px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 8px;
        }

        h1 {
            text-align: center;
            color: #007BFF;
        }

        p {
            margin: 10px 0;
        }

        strong {
            color: #000;
        }

        .reservation-links {
            text-align: center;
            margin-top: 20px;
        }

        .btn {
            background-color: #007BFF;
            color: #fff;
            padding: 8px 12px;
            text-decoration: none;
            border-radius: 5px;
            margin: 0 5px;
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Details du Vol</h1>
        <p><strong>Vol :</strong> <%= vol.getNumero_vol()%></p>
        <p><strong>Compagnie :</strong> <%= vol.getCompagnie()%></p>
        <p><strong>Avion :</strong> <%= avion.getNom()%></p>
        <p><strong>Trajet :</strong> <%= vol.getVille_depart()%> - <%= vol.getVille_arrivee()%></p>
        <p><strong>Horaires :</strong> <%= vol.getDate_depart()%> - <%= vol.getDate_arrivee()%></p>
        <p><strong>Duree :</strong> <%= vol.getDuree()%> min</p>
        <p><strong>Statut :</strong> <%= statutVol.getStatut()%></p>

        <p class="reservation-links">
            Faire une reservation :
            <a class="btn" href="reservation?action=eco&idVol=<%= vol.getId_vol()%>">Classe eco</a> |
            <a class="btn" href="reservation?action=bus&idVol=<%= vol.getId_vol()%>">Classe Business</a>
        </p>
    </div>
</body>
</html>

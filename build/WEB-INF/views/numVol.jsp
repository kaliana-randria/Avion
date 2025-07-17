<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<% String message = (String) request.getAttribute("message"); %>
<%
    List<Vol> listVol = (List<Vol>) request.getAttribute("listes");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Form numero vol</title>
</head>
<body>
    <form action="numVol" method="post">
        <p> Entrer numero vol : 
            <select name="idVol" id="idVol">
                <option value=""></option>
                <% if (listVol != null) {
                    for (Vol v : listVol) { %>
                        <option value="<%= v.getId_vol() %>">numero vol id[<%= v.getId_vol() %>]</option>
                <% } } %>
            </select>
        </p>
        <input type="submit" value="Valider">
    </form>

    <% if (message != null) { %>
        <p class="message"><%= message %></p>
    <% } %>
</body>
</html>

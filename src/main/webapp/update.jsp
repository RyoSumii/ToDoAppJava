<%@ page import="model.Things" %>
<%@ page import="model.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Things thing = (Things) application.getAttribute("thing");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Update Page</h1>
<form action="/ToDoPage" method="post">
    <input type="hidden" value="<%=thing.getId()%>" name="thing_id">
    内容 <input  value="<%=thing.getThing()%>" name="updateToDo" required>
    期日 <input type="date" value="<%=thing.getTimeLimit()%>" name="deadLine" required>
    <input type="submit">
</form>
</body>
</html>

<%@ page import="model.Things" %>
<%@ page import="model.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Users user = (Users) session.getAttribute("user");
    Things thing = (Things) application.getAttribute("thing");
    String todo = thing.getThing();
    String deadLine = thing.getTimeLimit();

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/ToDoPage" method="post">
    <input type="hidden" value="<%=thing.getId()%>" name="thing_id">
    内容 <input  value="<%=thing.getThing()%>" name="updateToDo" required>
    期日 <input type="date" value="<%=thing.getTimeLimit()%>" name="deadLine" required>
    <input type="submit">
</form>
</body>
</html>

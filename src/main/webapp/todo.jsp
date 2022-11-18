<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@page import="model.Users, model.ThingsDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Things" %>
<%
    Users user = (Users) session.getAttribute("user");
    ThingsDAO thingsDAO = new ThingsDAO();
    ArrayList<Things> toDoList = thingsDAO.showAll(user.getId());

%>

<!DOCTYPE html>
<html>
<head>
    <title>ToDo Page</title>
</head>
<body>
    <h1><%=user.getName()%>のToDo</h1>
    <form action="/ToDoPage" method="post">
        <input type="hidden" value="<%=user.getName()%>" name="user_name">
        <input type="hidden" value="<%=user.getId()%>" name="user_id">
        内容 <input name="todo" required>
        期日 <input type="text" name="deadLine" required>
        <input type="submit">
    </form>


    <%
        for (Things thing : toDoList) {
            int id = thing.getId();
            String todo = thing.getThing();
            String timeLimit = thing.getTimeLimit();
            int processed = thing.getProcessed();
    %>
    <form action="/ToDoPage" method="post">
        <%
            if (processed == 0){
        %>
        <p><strong>・</strong> <%=todo%> <%=timeLimit%>
        <button type="submit" value="<%=id%>" name="markAsProcessed">完了</button>
        <button type="submit" value="<%=id%>" name="deleteToDoID">削除</button></p>
        <%
            } else {
        %>
            <p><strong>・</strong><del> <%=todo%> <%=timeLimit%></del>
            <button type="submit" value="<%=id%>" name="deleteToDoID">削除</button></p>
        <%
            }
        %>

    </form>


    <%
        }
    %>

<%--<script>--%>
<%--    function deleteToDo(id) {--%>
<%--        let id = <%%>--%>
<%--    }--%>
<%--</script>--%>

<a href="/">戻る</a>
</body>
</html>
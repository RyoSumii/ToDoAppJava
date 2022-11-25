<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@page import="model.Users, model.ThingsDAO" %>
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
        期日 <input type="date" name="deadLine" required>
        <input type="submit">
    </form>


    <%
        for (Things thing : toDoList) {
            int id = thing.getId();
            String todo = thing.getThing();
            String timeLimit = thing.getTimeLimit();
            int processed = thing.getProcessed();
    %>
    <div>
        <form action="/ToDoPage" method="post" style="display: inline-block;">
            <%
                if (processed == 0){
            %>
            <ul>
                <li>
                     <%=todo%>  <%=timeLimit%>
                    <button type="submit" value="<%=id%>" name="markAsProcessed">完了</button>
                    <button type="submit" value="<%=id%>" name="deleteToDoID">削除</button>
                </li>
            </ul>
        </form>
        <form action="/ToDoPage" method="get" style="display: inline-block;">
            <button type="submit" value="<%=id%>" name="updateID">更新</button>
        </form>
            <%
                } else {
            %>
        <form action="/ToDoPage" method="post">
            <ul>
                <li>
                    <del><%=todo%>  <%=timeLimit%></del>
                    <button type="submit" value="<%=id%>" name="deleteToDoID">削除</button>
                </li>
            </ul>
            <%
                }
            %>

        </form>
    </div>

    <%
        }
    %>

<a href="/">戻る</a>
</body>
</html>

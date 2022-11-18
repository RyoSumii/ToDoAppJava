package com.example.todoapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.ThingsDAO;
import model.Users;
import model.UsersDAO;

import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/ToDoPage")
public class Main extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Userのデータを取得し、リクエストスコープに保存。
        String userName = request.getParameter("user_name");
        if (userName != null) {
            UsersDAO usersDAO = new UsersDAO();
            Users user = usersDAO.checkUserExist(userName);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
//        UsersDAO usersDAO = new UsersDAO();
//        Users user = usersDAO.checkUserExist(userName);
//        HttpSession session = request.getSession();
//        session.setAttribute("user", user);



        //ToDoをデータベースに登録
        ThingsDAO thingsDAO = new ThingsDAO();
        String toDo = request.getParameter("todo");
        String deadLine = request.getParameter("deadLine");
        if (toDo != null) {
            int user_id = parseInt(request.getParameter("user_id"));
            thingsDAO.insertToDo(user_id, toDo, deadLine);
        }

        //削除ボタンの実装
        String todoID = request.getParameter("deleteToDoID");
        if (todoID != null) {
            int ToDoID = parseInt(todoID);
            thingsDAO.deleteToDo(ToDoID);
        }
        //完了ボタンの実装
        String processed = request.getParameter("markAsProcessed");
        if (processed != null) {
            int Processed = parseInt(processed);
            thingsDAO.markAsProcessed(Processed);
        }



        RequestDispatcher dispatcher = request.getRequestDispatcher("todo.jsp");
        dispatcher.forward(request, response);
    }
}

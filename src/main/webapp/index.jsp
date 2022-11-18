<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

<h1>ようこそ</h1>
<p>名前を入力してください</p>
<form action="/ToDoPage" method="post">
        <input type="text" name="user_name" required>
        <input type="submit">
</form>

</body>
</html>
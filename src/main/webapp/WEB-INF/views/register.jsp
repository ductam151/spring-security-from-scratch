<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/20/2024
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">Register Here</h1>
<hr>
<div align="center">
    <form:form action="/process-register" method="post" modelAttribute="registrationDTO">
        <label>Username: </label>
        <form:input path="username"/>
        <br>

        <label>Password: </label>
        <form:password path="password"/>
        <br>
        <button type="submit" name="submit">Register</button>
    </form:form>
</div>


</body>
</html>

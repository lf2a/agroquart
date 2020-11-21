<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
</head>
<body>

<!-- erro no login -->
<c:if test="${param.error == ''}">
    Invalid username and password.
</c:if>

<!-- deslogado -->
<c:if test="${param.logout == ''}">
    You have been logged out.
</c:if>

<%-- usuario inativo --%>
<c:if test="${param.inativo == ''}">
    Usuário inativo
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
    <sec:csrfInput/>
    <!--  nome de usuario ex."luiz.filipy"  -->
    <label for="username">Username</label>:
    <input type="text" id="username" name="username" autofocus="autofocus"/> <br/>

    <!--  senha do usuario  -->
    <label for="password">Password</label>:
    <input type="password" id="password" name="password"/> <br/>

    <input type="submit" value="Login"/>
</form>
</body>
</html>
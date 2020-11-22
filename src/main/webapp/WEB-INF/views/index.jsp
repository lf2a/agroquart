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
    <title>Agroquart</title>
</head>
<body>
olá

<sec:authorize access="hasAuthority('ROLE_ADMIN')">
    <a href="${pageContext.request.contextPath}/admin">Admin</a>
</sec:authorize>

<sec:authorize access="isAnonymous()">
    <a href="${pageContext.request.contextPath}/login">Login</a>
</sec:authorize>
</body>
</html>
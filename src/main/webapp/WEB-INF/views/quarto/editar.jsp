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
    <title>Editar quarto</title>
</head>
<body>

<c:if test="${param.formError != ''}">
    <c:forEach items="${param.formError.split('@')}" var="error">
        <p>error: ${error}</p>
    </c:forEach>
</c:if>

<c:if test="${param.sucesso == ''}">
    <p>Quarto alterado com sucesso!</p>
</c:if>

<form action="${pageContext.request.contextPath}/quarto/${quarto.id}/editar" method="post">
    <sec:csrfInput/>
    <input type="hidden" name="id" value="${quarto.id}">
    <input type="number" name="capacidade" value="${quarto.capacidade}" placeholder="Capacidade" required>
    <input type="hidden" name="casa" value="${quarto.casa.id}">
    <input type="hidden" name="hospedaria" value="${quarto.casa.hospedaria.id}">
    <button type="submit">Alterar quarto</button>
</form>
</body>
</html>
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
    <title>Editar - ${hospedaria.nomeHospedaria}</title>
</head>
<body>

<c:if test="${param.formError != ''}">
    <c:forEach items="${param.formError.split('@')}" var="error">
        <p>error: ${error}</p>
    </c:forEach>
</c:if>

<c:if test="${param.sucesso == ''}">
    <p>Hospedaria atualizada com sucesso!</p>
</c:if>

<form action="${pageContext.request.contextPath}/hospedaria/${hospedaria.id}/editar" method="post">
    <sec:csrfInput/>
    <input type="text" name="id" value="${hospedaria.id}" readonly>
    <input type="text" name="nomeHospedaria" id="" placeholder="Nome da hospedaria" value="${hospedaria.nomeHospedaria}"
           required>
    <button type="submit">Atualizar</button>
</form>
</body>
</html>
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
    <title>Quartos</title>
</head>
<body>

<c:if test="${param.formError != ''}">
    <c:forEach items="${param.formError.split('@')}" var="error">
        <p>error: ${error}</p>
    </c:forEach>
</c:if>

<c:if test="${param.sucesso == ''}">
    <p>Quarto adicionado com sucesso!</p>
</c:if>

<c:if test="${param.excluido == ''}">
    <p>Excluido com sucesso!</p>
</c:if>

<form action="${pageContext.request.contextPath}/quarto" method="post">
    <sec:csrfInput/>
    <input type="hidden" name="id" value="777">
    <input type="number" name="capacidade" placeholder="Capacidade" required>
    <input type="hidden" name="casa" value="${quartos[0].casa.id}">
    <input type="hidden" name="hospedaria" value="${quartos[0].casa.hospedaria.id}">
    <button type="submit">Criar quarto</button>
</form>

<h1>${quartos[0].casa.numero}</h1>

<ul>
    <c:forEach items="${quartos}" var="quarto">
        <li>Capacidade do quarto: ${quarto.capacidade}
            <a href="${pageContext.request.contextPath}/quarto/${quarto.id}/editar">editar</a>
            <form action="${pageContext.request.contextPath}/quarto/${quarto.id}/excluir" method="post">
                <sec:csrfInput/>
                <button type="submit">excluir</button>
            </form>
        </li>
    </c:forEach>
</ul>

</body>
</html>
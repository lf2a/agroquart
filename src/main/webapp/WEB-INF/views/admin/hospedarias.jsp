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
    <title>Hospedarias</title>
</head>
<body>

<c:if test="${param.formError != ''}">
    <c:forEach items="${param.formError.split('@')}" var="error">
        <p>error: ${error}</p>
    </c:forEach>
</c:if>

<c:if test="${param.sucesso == ''}">
    <p>Hospedaria criada com sucesso!</p>
</c:if>

<c:if test="${param.excluido != '' && param.excluido != null}">
    <p>Excluido com sucesso!</p>
</c:if>

<h1>Hospedarias</h1>
<form action="${pageContext.request.contextPath}/hospedaria" method="post">
    <sec:csrfInput/>
    <input type="text" name="nomeHospedaria" id="nome" placeholder="Nome da hospedaria" required>
    <button type="submit">Inserir</button>
</form>

<c:choose>
    <c:when test="${hospedarias.size()==0}">
        <h1>Ainda não existe hospedarias cadastradas no sistema.</h1>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach items="${hospedarias}" var="hospedaria">
                <li>Nome da hospedaria: <a
                        href="${pageContext.request.contextPath}/admin/${hospedaria.id}/casas">${hospedaria.nomeHospedaria}</a>
                    <a href="${pageContext.request.contextPath}/hospedaria/${hospedaria.id}/editar">editar</a>
                    <form action="${pageContext.request.contextPath}/hospedaria/${hospedaria.id}/excluir" method="post">
                        <sec:csrfInput/>
                        <button type="submit">excluir</button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
</body>
</html>
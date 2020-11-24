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
    <title>Casas</title>
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
    <p>Quarto n°${param.excluido} excluido com sucesso!</p>
</c:if>

<form action="${pageContext.request.contextPath}/casa" method="post">
    <sec:csrfInput/>
    <input type="hidden" name="id" value="777">
    <input type="text" name="numero" id="" placeholder="Numero da casa" required>

    <select name="tipo" id="">
        <option value="0">Hospedagem temporária</option>
        <option value="1">Hospedagem de tempo indeterminado</option>
    </select>
    <label for="terceirizado">terceirizado</label>
    <input type="checkbox" name="terceirizado" id="terceirizado">
    <input type="hidden" name="hospedaria" id="" placeholder="id da hospedaria"
           value="${casas[0].hospedaria.id}" readonly>
    <button type="submit">Criar nova casa</button>
</form>

<h1>${casas[0].hospedaria.nomeHospedaria}</h1>

<ul>
    <c:forEach items="${casas}" var="casa">
        <li>Número da casa: <a
                href="${pageContext.request.contextPath}/admin/${casa.hospedaria.id}/${casa.id}/quartos">${casa.numero}</a>
            <a href="${pageContext.request.contextPath}/casa/${casa.id}/editar">editar</a>
            <form action="${pageContext.request.contextPath}/casa/${casa.id}/excluir" method="post">
                <sec:csrfInput/>
                <button type="submit">excluir</button>
            </form>
            <ul>
                <c:forEach items="${casa.quartos}" var="quarto">
                    <li>Número do quarto: ${casa.numero}
                        <a href="${pageContext.request.contextPath}/quarto/${quarto.id}/editar">editar</a>
                        <form action="${pageContext.request.contextPath}/quarto/${quarto.id}/excluir" method="post">
                            <sec:csrfInput/>
                            <button type="submit">excluir</button>
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </li>
    </c:forEach>
</ul>

</body>
</html>
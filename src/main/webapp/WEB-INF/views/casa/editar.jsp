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
    <title>Editar - Casa n° ${casa.numero}</title>
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

<form action="${pageContext.request.contextPath}/casa/${casa.id}/editar" method="post">
    <sec:csrfInput/>
    <input type="hidden" name="id" value="${casa.id}">
    <input type="text" name="numero" id="" placeholder="Numero da casa" value="${empty casa.numero ? "": casa.numero}">

    <select name="tipo" id="">
        <option value="0" ${casa.tipo ? "selected" : ""}>Hospedagem temporária</option>
        <option value="1" ${casa.tipo ? "selected" : ""}>Hospedagem de tempo indeterminado</option>
    </select>
    <label for="terceirizado">terceirizado</label>
    <input type="checkbox" name="terceirizado" id="terceirizado" ${casa.terceirizado ? "checked" : ""}>
    <input type="hidden" name="hospedaria" id="" placeholder="id da hospedaria"
           value="${empty casa.hospedaria.id ? "": casa.hospedaria.id}" readonly>
    <button type="submit">atualizar</button>
</form>
</body>
</html>
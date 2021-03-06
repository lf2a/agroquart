<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Quartos disponiveis</title>
</head>
<body>

<c:if test="${param.sucesso == ''}">
    <p>O quarto foi escolhido com sucesso</p>
</c:if>

<table>
    <tr>
        <th>id</th>
        <th>capacidade total</th>
        <th>capacidade disponivel</th>
        <th>sexo</th>
        <th>casa n°</th>
        <th>hospedaria</th>
        <th>Ação</th>
    </tr>
    <c:forEach items="${quartos}" var="q">
        <tr>
            <td>${q.id}</td>
            <td>${q.capacidade}</td>
            <td>${q.capacidade - q.reservado}</td>
            <td>${q.casa.sexo}</td>
            <td>${q.casa.numero}</td>
            <td>${q.casa.hospedaria.nomeHospedaria}</td>
            <td>
                <form action="${pageContext.request.contextPath}/reserva/${reservaId}/quarto/${q.id}" method="post">
                    <sec:csrfInput/>
                    <button type="submit">Escolher</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
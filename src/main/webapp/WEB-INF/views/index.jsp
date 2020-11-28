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

<c:if test="${param.formError != ''}">
    <c:forEach items="${param.formError.split('@')}" var="error">
        <p>error: ${error}</p>
    </c:forEach>
</c:if>

<c:if test="${param.sucesso == ''}">
    <p>Reserva solicitada!</p>
</c:if>

<sec:authorize access="hasAuthority('ROLE_ADMIN')">
    <a href="${pageContext.request.contextPath}/admin">Admin</a>
</sec:authorize>

<sec:authorize access="isAnonymous()">
    <a href="${pageContext.request.contextPath}/login">Login</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <sec:csrfInput/>
        <button type="submit">Logout</button>
    </form>
</sec:authorize>

<form action="${pageContext.request.contextPath}/reserva" method="post"> <%-- alojamento --%>
    <sec:csrfInput/>
    <input type="text" name="nomeCompleto" id="" placeholder="Seu nome completo" required>
    <input type="number" name="matricula" id="" placeholder="Sua matricula" required>
    <input type="text" name="gerenteResponsavel" id="" placeholder="Gerente responsavel" required>
    <input type="email" name="email" id="" placeholder="Seu email para contato" required>
    <input type="text" name="cargo" id="" placeholder="Seu cargo" required>

    <label for="empresa">Empresa</label>
    <select name="empresa" id="empresa" required>
        <option value="Agropalma">Agropalma</option>
        <option value="Xhara">Xhara</option>
    </select>

    <label for="datai">Data de inicio</label>
    <input type="date" name="dataInicio" id="datai" required>

    <label for="horai">Hora de inicio</label>
    <input type="time" name="horaInicio" id="horai" required>

    <label for="datat">Data de termino</label>
    <input type="date" name="dataTermino" id="datat" required>

    <label for="horat">Hora de termino</label>
    <input type="time" name="horaTermino" id="horat" required>

    <textarea name="motivo" id="" cols="30" rows="10" placeholder="Motivo da ida."></textarea>

    <select name="tipoReserva" id="">
        <option value="0">Hospedagem temporária</option>
        <option value="1">Hospedagem de tempo indeterminado</option>
    </select>

    <button type="submit">Solicitar reserva</button>

</form>

</body>
</html>
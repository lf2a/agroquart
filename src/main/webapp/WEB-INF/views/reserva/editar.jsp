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
    <title>Editar reserva</title>
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

<form action="${pageContext.request.contextPath}/reserva/${reserva.id}/editar" method="post">
    <sec:csrfInput/>

    <input type="hidden" name="id" value="${reserva.id}">

    <input type="text" name="nomeCompleto" id="" placeholder="Seu nome completo" value="${reserva.nomeCompleto}"
           readonly>
    <input type="number" name="matricula" id="" placeholder="Sua matricula" value="${reserva.matricula}" readonly>
    <input type="text" name="gerenteResponsavel" id="" placeholder="Gerente responsavel"
           value="${empty reserva.gerenteResponsavel ? "": reserva.gerenteResponsavel}" readonly>
    <input type="email" name="email" id="" placeholder="Seu email para contato" value="${reserva.email}" readonly>
    <input type="text" name="cargo" id="" placeholder="Seu cargo" value="${reserva.cargo}" readonly>
    <select name="sexo" id="" disabled>
        <option value="homem" ${reserva.sexo=="homem" ? "selected": ""}>Homem</option>
        <option value="mulher" ${reserva.sexo=="mulher" ? "selected": ""}>Mulher</option>
    </select>

    <label for="empresa">Empresa</label>
    <select name="empresa" id="empresa" disabled>
        <option value="Agropalma" ${reserva.empresa=="Agropalma" ? "selected": ""}>Agropalma</option>
        <option value="Xhara" ${reserva.empresa=="Xhara" ? "selected": ""}>Xhara</option>
        <option value="INSS" ${reserva.empresa=="INSS" ? "selected": ""}>INSS</option>
    </select>

    <label for="datai">Data de inicio</label>
    <input type="date" name="dataInicio" id="datai" value="${reserva.getStringDataInicio()}" required>

    <label for="horai">Hora de inicio</label>
    <input type="time" name="horaInicio" id="horai" value="${reserva.getStringHoraInicio()}" required>

    <label for="datat">Data de termino</label>
    <input type="date" name="dataTermino" id="datat" value="${reserva.getStringDataTermino()}" required>

    <label for="horat">Hora de termino</label>
    <input type="time" name="horaTermino" id="horat" value="${reserva.getStringHoraTermino()}" required>

    <textarea name="motivo" id="" cols="30" rows="10" placeholder="Motivo da ida." readonly>${reserva.motivo}</textarea>

    <select name="tipoReserva" id="" disabled>
        <option value="0" ${reserva.tipo=="hospedaria" ? "selected": ""}>Hospedagem temporária</option>
        <option value="1" ${reserva.tipo=="alojamento" ? "selected": ""}>Hospedagem de tempo indeterminado</option>
    </select>

    <button type="submit">Salvar</button>
</form>

</body>
</html>
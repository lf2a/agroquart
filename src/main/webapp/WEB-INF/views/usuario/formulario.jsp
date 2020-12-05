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
    <title>Atualizar informações do usuário</title>
</head>
<body>

<c:if test="${param.formError != ''}">
    <c:forEach items="${param.formError.split('@')}" var="error">
        <p>error: ${error}</p>
    </c:forEach>
</c:if>

<c:if test="${param.sucesso == ''}">
    <p>Usuário atualizado com sucesso</p>
</c:if>

<form action="${pageContext.request.contextPath}/usuario/${usuario.matricula}/editar" method="post">
    <sec:csrfInput/>
    <input class="input" type="text" name="usuario" placeholder="Nome de usuário"
           value="${empty usuario.usuario ? "": usuario.usuario}" readonly>
    <input class="input" type="email" name="email" placeholder="Email do colaborador"
           value="${empty usuario.email ? "": usuario.email}" readonly>
    <input class="input" type="text" name="" placeholder="Matrícula do colaborador"
           value="${empty usuario.matricula ? "": usuario.matricula}" readonly>
    <input class="input" type="text" name="nomeCompleto" placeholder="Nome completo do colaborador"
           value="${empty usuario.nomeCompleto ? "": usuario.nomeCompleto}" readonly>
    <input class="input" type="text" name="empresa" placeholder="Empresa em que o colaborador atua"
           value="${empty usuario.empresa ? "": usuario.empresa}">
    <label for="ativo">Ativo</label>
    <input type="checkbox" id="ativo" name="ativo" ${empty usuario.ativo ? "": "checked"}>

    <!--  Modelos: https://colorlib.com/polygon/concept/pages/multiselect.html  -->
    <select multiple size="5" name="permissoes">
        <c:forEach items="${usuario.permissoes}" var="p">
            <option value="${p.id}" selected>${p.nome}</option>
        </c:forEach>
    </select>

    <select multiple size="5" name="">
        <c:forEach items="${permissoes}" var="p">
            <option value="${p.id}">${p.nome}</option>
        </c:forEach>
    </select>

    <input class="input" id="senha" name="senha" type="password" placeholder="Password">
    <input class="input" id="senha" name="senha2" type="password" placeholder="Password">
    <button type="submit" class="button is-success is-fullwidth">Registrar novo usuário.</button>
</form>


</body>
</html>
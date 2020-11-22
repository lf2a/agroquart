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
    <title>Admin</title>
</head>
<body>


<c:if test="${param.formError != ''}">
    <c:forEach items="${param.formError.split('@')}" var="error">
        <p>error: ${error}</p>
    </c:forEach>
</c:if>

<c:if test="${param.sucesso == ''}">
        <p>Usuário salvo com sucesso!</p>
</c:if>

<form action="${pageContext.request.contextPath}/usuario" method="post">
    <sec:csrfInput/>
    <input class="input" type="text" name="usuario" placeholder="Nome de usuário" required>
    <input class="input" type="email" name="email" placeholder="Email do colaborador">
    <input class="input" type="text" name="matricula" placeholder="Matrícula do colaborador" value="0">
    <input class="input" type="text" name="nomeCompleto" placeholder="Nome completo do colaborador">
    <input class="input" type="text" name="empresa" placeholder="Empresa em que o colaborador atua">
    <label for="ativo">Ativo</label>
    <input type="checkbox" id="ativo" name="ativo" checked>
    <select multiple size="5" name="permissoes">
        <option value="1">ROLE_ADMIN</option>
        <option value="2">ROLE_USUARIO</option>
        <option value="3">ROLE_CRIAR_USUARIO</option>
        <option value="4">ROLE_EDITAR_USUARIO</option>
        <option value="5">ROLE_EXCLUIR_USUARIO</option>
        <option value="6">ROLE_HOSPEDARIA</option>
        <option value="7">ROLE_CRIAR_HOSPEDARIA</option>
        <option value="8">ROLE_ALTERAR_HOSPEDARIA</option>
        <option value="9">ROLE_EXCLUIR_HOSPEDARIA</option>
        <option value="10">ROLE_RESERVA</option>
        <option value="11">ROLE_CRIAR_RESERVA</option>
        <option value="12">ROLE_EDITAR_RESERVA</option>
        <option value="13">ROLE_EXCLUIR_RESERVA</option>
        <option value="14">ROLE_RELATORIO</option>
    </select>
    <input class="input" id="senha" name="senha" type="password" placeholder="Password">
    <input class="input" id="senha" name="senha2" type="password" placeholder="Password confirmation">
    <button type="submit" class="button is-success is-fullwidth">Registrar novo usuário.</button>
</form>

<table>
    <tr>
        <th>Matricula</th>
        <th>Usuário</th>
        <th>Nome completo</th>
        <th>Empresa</th>
        <th>Email</th>
        <th>Ativo</th>
        <th>Ultimo login (AAAA-MM-DD HH:MM:SS)</th>
        <th>Cadastrado em (AAAA-MM-DD HH:MM:SS)</th>
        <sec:authorize access="hasAnyAuthority('ROLE_EDITAR_USUARIO', 'ROLE_EXCLUIR_USUARIO')">
            <th colspan="2">Ação</th>
        </sec:authorize>
    </tr>
    <c:forEach items="${usuarios}" var="usuario">
        <tr>
            <td>${usuario.matricula}</td>
            <td>${usuario.usuario}</td>
            <td>${usuario.nomeCompleto}</td>
            <td>${usuario.empresa}</td>
            <td>${usuario.email}</td>
            <td>${usuario.ativo}</td>
            <td>${usuario.ultimoLogin}</td>
            <td>${usuario.criadaEm}</td>
            <sec:authorize access="hasAnyAuthority('ROLE_EDITAR_USUARIO')">
                <td><a href="http://">editar</a></td>
            </sec:authorize>
            <sec:authorize access="hasAnyAuthority('ROLE_EXCLUIR_USUARIO')">
                <td><a href="http://">excluir</a></td>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>
</body>
</html>
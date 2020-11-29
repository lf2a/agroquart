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
    <title>${tipo}</title>
</head>
<body>

<c:if test="${param.excluido == ''}">
    <p>Excluido com sucesso!</p>
</c:if>

<c:if test="${param.arquivado == ''}">
    <p>Operação realizada com sucesso!</p>
</c:if>

<c:if test="${param.autorizado == ''}">
    <p>Operação realizada com sucesso!</p>
</c:if>

<c:if test="${param.erroArquivar == ''}">
    <p>Autorize a reserva para que ela possa ser arquivada!</p>
</c:if>

<c:if test="${param.erroAutorizar == ''}">
    <p>Não foi possivel autorizar, escolha um quarto para a reserva!</p>
</c:if>

<a href="${pageContext.request.contextPath}/admin/reservas?filtro=autorizadas">Reservas Autorizadas</a>
<a href="${pageContext.request.contextPath}/admin/reservas?filtro=nao-autorizadas">Reservas Não Autorizadas</a>
<a href="${pageContext.request.contextPath}/admin/reservas?filtro=arquivadas">Reservas Arquivadas</a>
<%--<a href="${pageContext.request.contextPath}/admin/reservas?filtro=nao-arquivadas">Reservas Não Arquivadas</a>--%>
<a href="${pageContext.request.contextPath}/admin/reservas?filtro=reservas-em-andamento">Reservas Em andamento</a>

<c:choose>
    <c:when test="${reservas == null}">
        <h1>Selecione um filtro</h1>
    </c:when>
    <c:when test="${reservas.size() == 0}">
        <h1>Sem reservas :(</h1>
    </c:when>
    <c:otherwise>

        <table>
            <tr>
                <th>Id</th>
                <th>Nome completo</th>
                <th>Matrícula</th>
                <th>Gerente Responsável</th>
                <th>Email</th>
                <th>Empresa</th>
                <th>Cargo</th>
                <th>Sexo</th>
                <th>Data de inicio</th>
                <th>Date de termino</th>
                <th>Quarto</th>
                <th>Foi autorizada?</th>
                <th>Foi arquivada?</th>
                <th>Motivo</th>
                <th>Solicitada em</th>
                <th>Tipo da reserva</th>
                <th>Escolher quarto</th>
                <sec:authorize access="hasAnyAuthority('ROLE_EDITAR_RESERVA', 'ROLE_EXCLUIR_RESERVA')">
                    <th colspan="5">Ação</th>
                </sec:authorize>
            </tr>
            <c:forEach items="${reservas}" var="r">
                <tr>
                    <td>${r.id}</td>
                    <td>${r.nomeCompleto}</td>
                    <td>${r.matricula}</td>
                    <td>${empty r.gerenteResponsavel ? "Não informado": r.gerenteResponsavel}</td>
                    <td>${r.email}</td>
                    <td>${r.empresa}</td>
                    <td>${r.cargo}</td>
                    <td>${r.sexo}</td>
                    <td>${r.getDataInicioFormatada()}</td>
                    <td>${r.getDataTerminoFormatada()}</td>
                    <td>${empty r.quarto ? "Não escolhido": r.getDescricaoQuarto()}</td>
                    <td>${r.autorizada ? "sim" : "não"}</td>
                    <td>${r.arquivada ? "sim" : "não"}</td>
                    <td>${r.motivo}</td>
                    <td>${r.getCriadaEmFormatada()}</td>
                    <td>${r.tipo}</td>
                    <sec:authorize access="hasAnyAuthority('ROLE_EDITAR_RESERVA')">
                        <td><a href="${pageContext.request.contextPath}/reserva/${r.id}/quarto">Escolher quarto</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasAnyAuthority('ROLE_EDITAR_RESERVA')">
                        <c:choose>
                            <c:when test="${!r.autorizada}">
                                <td>
                                    <form action="${pageContext.request.contextPath}/reserva/${r.id}/autorizar?filtro=${param.filtro}"
                                          method="post">
                                        <sec:csrfInput/>
                                        <button type="submit">Autorizar</button>
                                    </form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <form action="${pageContext.request.contextPath}/reserva/${r.id}/autorizar?filtro=${param.filtro}"
                                          method="post">
                                        <sec:csrfInput/>
                                        <button type="submit">Remover autorização</button>
                                    </form>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </sec:authorize>
                    <sec:authorize access="hasAnyAuthority('ROLE_EDITAR_RESERVA')">
                        <c:choose>
                            <c:when test="${!r.arquivada}">
                                <td>
                                    <form action="${pageContext.request.contextPath}/reserva/${r.id}/arquivar?filtro=${param.filtro}"
                                          method="post">
                                        <sec:csrfInput/>
                                        <button type="submit">Arquivar</button>
                                    </form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <form action="${pageContext.request.contextPath}/reserva/${r.id}/arquivar?filtro=${param.filtro}"
                                          method="post">
                                        <sec:csrfInput/>
                                        <button type="submit">Desarquivar</button>
                                    </form>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </sec:authorize>
                    <sec:authorize access="hasAnyAuthority('ROLE_EDITAR_RESERVA')">
                        <td><a href="${pageContext.request.contextPath}/reserva/${r.id}/editar">editar</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasAnyAuthority('ROLE_EXCLUIR_RESERVA')">
                        <td>
                            <form action="${pageContext.request.contextPath}/reserva/${r.id}/excluir?filtro=${param.filtro}"
                                  method="post">
                                <sec:csrfInput/>
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
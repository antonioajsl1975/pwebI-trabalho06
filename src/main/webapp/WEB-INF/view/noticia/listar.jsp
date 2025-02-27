<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Notícias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<%@ include file="../nav-bar/nav-bar.jsp" %>
<div class="container mt-5">
    <h2 class="mb-4">Lista de Notícias</h2>

    <c:if test="${not empty mensagem}">
        <div class="alert ${tipoMensagem == 'sucesso' ? 'alert-success' : 'alert-danger'}">
                ${mensagem}
        </div>
    </c:if>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Lide</th>
            <th>Data</th>
            <th>Repórter</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="noticia" items="${noticias}">
            <tr>
                <td>${noticia.id}</td>
                <td>${noticia.titulo}</td>
                <td>${noticia.lide}</td>
                <td>${noticia.data}</td>
                <td>${noticia.reporter.nome}</td>
                <td>
                    <c:if test="${usuarioLogado.id == noticia.reporter.id}">
                        <a href="editar/${noticia.id}" class="btn btn-warning btn-sm">Editar</a>
                        <a href="deletar/${noticia.id}" class="btn btn-danger btn-sm" onclick="return confirm('Tem certeza que deseja excluir?')">Excluir</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="form" class="btn btn-primary">Nova Notícia</a>
</div>
</body>
</html>
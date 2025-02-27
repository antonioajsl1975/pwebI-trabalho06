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
            <div class="card mb-3">
                <c:if test="${not empty noticia.imagem}">
                    <img src="${pageContext.request.contextPath}/imagens/${noticia.imagem}" class="card-img-top" alt="${noticia.titulo}">
                </c:if>
                <div class="card-body">
                    <h5 class="card-title">${noticia.titulo}</h5>
                    <p class="card-text">${noticia.lide}</p>
                    <p class="card-text"><small class="text-muted">Autor: ${noticia.reporter.nome}</small></p>
                </div>
            </div>
        </c:forEach>

        </tbody>
    </table>

    <a href="form" class="btn btn-primary">Nova Notícia</a>
</div>
</body>
</html>
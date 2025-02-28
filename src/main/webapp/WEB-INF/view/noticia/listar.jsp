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

    <div class="row">
        <c:forEach var="noticia" items="${noticias}">
            <div class="col-md-4">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">${noticia.titulo}</h5>
                        <p class="card-text">${noticia.lide}</p>
                        <p class="card-text"><small class="text-muted">Autor: ${noticia.reporter.nome}</small></p>

                        <c:if test="${usuarioLogado != null && usuarioLogado.id == noticia.reporter.id}">
                            <a href="${pageContext.request.contextPath}/noticia/editar/${noticia.id}" class="btn btn-warning btn-sm">Editar</a>
                            <a href="${pageContext.request.contextPath}/noticia/deletar/${noticia.id}"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('Tem certeza que deseja excluir esta notícia?');">Excluir</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <a href="${pageContext.request.contextPath}/noticia/form" class="btn btn-primary">Nova Notícia</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

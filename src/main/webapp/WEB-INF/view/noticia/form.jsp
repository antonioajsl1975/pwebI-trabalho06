<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${noticia.id == null ? "Nova Notícia" : "Editar Notícia"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<%@ include file="../nav-bar/nav-bar.jsp" %>
<div class="container mt-5">
    <h2 class="text-center mb-4">${noticia.id == null ? "Nova Notícia" : "Editar Notícia"}</h2>

    <c:if test="${not empty mensagem}">
        <div class="alert ${tipoMensagem == 'sucesso' ? 'alert-success' : 'alert-danger'}">
                ${mensagem}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/noticia/${noticia.id == null ? 'inserir' : 'atualizar'}" method="post">
        <c:if test="${noticia.id != null}">
            <input type="hidden" name="id" value="${noticia.id}">
        </c:if>

        <div class="mb-3">
            <label class="form-label">Título:</label>
            <input type="text" name="titulo" class="form-control" value="${noticia.titulo}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Lide:</label>
            <textarea name="lide" class="form-control" rows="3" required>${noticia.lide}</textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Corpo da Notícia:</label>
            <textarea name="corpo" class="form-control" rows="5" required>${noticia.corpo}</textarea>
        </div>

        <button type="submit" class="btn btn-primary w-100">
            ${noticia.id == null ? "Publicar Notícia" : "Atualizar Notícia"}
        </button>
    </form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
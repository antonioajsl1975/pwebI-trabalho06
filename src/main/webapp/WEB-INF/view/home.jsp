<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Últimas Notícias - Portal de Notícias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
        }
        .card-text {
            font-size: 0.95rem;
        }
        .autor-data {
            font-size: 0.85rem;
            color: #777;
        }
        .noticia-destaque {
            border-bottom: 3px solid #007bff;
        }
    </style>
</head>
<body>

<%@ include file="nav-bar/nav-bar.jsp" %>

<div class="container mt-4">
    <h1 class="mb-4">Últimas Notícias</h1>

    <div class="row">
        <c:forEach var="noticia" items="${noticias}">
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm h-100">
                    <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Imagem Notícia">
                    <div class="card-body">
                        <h5 class="card-title noticia-destaque">${noticia.titulo}</h5>
                        <p class="card-text">${noticia.lide}</p>
                        <p class="autor-data">Por ${noticia.reporter.nome} em
                            <c:out value="${noticia.data}" /></p>
                        <a href="${pageContext.request.contextPath}/noticia/detalhes/${noticia.id}" class="btn btn-primary btn-sm">Leia mais</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<footer class="text-center mt-5 p-3 bg-light">
    <p>&copy; 2025 Portal de Notícias</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
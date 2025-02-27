<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Página Inicial - Notícias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="../nav-bar/nav-bar.jsp" %>

<div class="container mt-4">
    <h1 class="mb-4">Últimas Notícias</h1>
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Notícia 1">
                <div class="card-body">
                    <h5 class="card-title">Título da Notícia 1</h5>
                    <p class="card-text">Pequena descrição da notícia para chamar atenção.</p>
                    <a href="#" class="btn btn-primary">Leia mais</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Notícia 2">
                <div class="card-body">
                    <h5 class="card-title">Título da Notícia 2</h5>
                    <p class="card-text">Outro breve resumo para despertar interesse.</p>
                    <a href="#" class="btn btn-primary">Leia mais</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Notícia 3">
                <div class="card-body">
                    <h5 class="card-title">Título da Notícia 3</h5>
                    <p class="card-text">Mais uma manchete interessante para os leitores.</p>
                    <a href="#" class="btn btn-primary">Leia mais</a>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="text-center mt-5 p-3 bg-light">
    <p>&copy; 2025 Notícias Simples</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Notícias</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link active" href="/home">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Mundo</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Esportes</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Entretenimento</a></li>
                <c:if test="${not empty usuarioLogado}">
                    <li class="nav-item"><a class="nav-link" href="/noticia/listar">Minhas Notícias</a></li>
                </c:if>
            </ul>
            <ul class="navbar-nav ms-auto">
                <c:choose>
                    <c:when test="${not empty usuarioLogado}">
                        <li class="nav-item">
                            <span class="navbar-text text-light me-3">Bem-vindo, ${usuarioLogado.nome}!</span>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-danger btn-sm" href="/logout">Logout</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="btn btn-success btn-sm me-2" href="/login">Login</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

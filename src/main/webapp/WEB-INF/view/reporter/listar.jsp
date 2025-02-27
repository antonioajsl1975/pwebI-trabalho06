<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Repórteres</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">Lista de Repórteres</h2>

    <c:if test="${not empty mensagem}">
        <div class="alert ${tipoMensagem == 'sucesso' ? 'alert-success' : 'alert-danger'}">
                ${mensagem}
        </div>
    </c:if>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Login</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="reporter" items="${reporters}">
            <tr>
                <td>${reporter.id}</td>
                <td>${reporter.nome}</td>
                <td>${reporter.login}</td>
                <td>
                    <a href="editar/${reporter.id}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="deletar/${reporter.id}" class="btn btn-danger btn-sm" onclick="return confirm('Tem certeza que deseja excluir?')">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="form" class="btn btn-primary">Novo Repórter</a>
</div>
</body>
</html>

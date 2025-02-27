<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Repórter</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<%@ include file="../nav-bar/nav-bar.jsp" %>
<div class="container mt-5">
    <div class="card">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">Cadastro de Repórter</h2>

            <c:if test="${not empty mensagem}">
                <div class="alert ${tipoMensagem eq 'sucesso' ? 'alert-success' : 'alert-danger'}">
                        ${mensagem}
                </div>
            </c:if>

            <form:form modelAttribute="reporter" action="${reporter.id != 0 ? '/reporter/atualizar' : '/reporter/inserir'}" method="post">

                <c:if test="${reporter.id != 0}">
                    <form:hidden path="id" />
                </c:if>


                <div class="mb-3">
                    <label class="form-label">Nome:</label>
                    <form:input path="nome" cssClass="form-control ${not empty fieldErrors['nome'] ? 'is-invalid' : ''}" />
                    <form:errors path="nome" cssClass="invalid-feedback" />
                </div>

                <div class="mb-3">
                    <label class="form-label">Login:</label>
                    <form:input path="login" cssClass="form-control ${not empty fieldErrors['login'] ? 'is-invalid' : ''}" />
                    <form:errors path="login" cssClass="invalid-feedback" />
                </div>


                <div class="mb-3">
                    <label class="form-label">Senha:</label>
                    <form:password path="senha" cssClass="form-control ${not empty fieldErrors['senha'] ? 'is-invalid' : ''}" />
                    <form:errors path="senha" cssClass="invalid-feedback" />
                </div>

                <button type="submit" class="btn btn-primary w-100">
                        ${reporter.id != 0 ? 'Atualizar' : 'Cadastrar'}
                </button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Minha página JSP com SpringMVC</title>
    <link rel="stylesheet" href="estilo.css">
</head>
<body>
    <p>${mensagem}</p>
    <h1>Index Trabalhando com SpringMVC.</h1>



    <form action="agendar">
        <input type="date" name="dia" placeholder="Dia">
        <input type="submit" value="Agendar">
        <output>Dia do agendamento: ${res}</output>
    </form>
    <hr>
    <form action="cadastrar" method="post">
        <input type="text" name="nome" placeholder="Nome">
        <input type="text" name="idade" placeholder="Idade">
        <input type="submit" value="Cadastrar">
        <output>${resposta}</output>
    </form>

</body>
</html>
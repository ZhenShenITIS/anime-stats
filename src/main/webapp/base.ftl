<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container">
        <a class="navbar-brand" href="/index">Главная</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav"
                aria-controls="mainNav" aria-expanded="false" aria-label="Переключить навигацию">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <!-- Добавьте пункты меню при необходимости -->
            </ul>

            <div class="d-flex">
                <#if (Session.email)??>
                    <a class="btn btn-outline-light me-2" href="/profile">Профиль</a>
                    <a class="btn btn-light" href="/logout">Выйти</a>
                <#else>
                    <a class="btn btn-outline-light me-2" href="/login">Войти</a>
                    <a class="btn btn-light" href="/signup">Регистрация</a>
                </#if>
            </div>
        </div>
    </div>
</nav>

<div id="content" class="container py-4">
    <div class="content">
        <@content></@content>
    </div>
</div>

</body>
</html>

<html lang = "en">

<#include "base.ftl">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">
<link rel="stylesheet" href="style.css">
<#macro title>
    Ошибка
</#macro>

<#macro content>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="alert alert-danger text-center" role="alert">
                    <h4 class="alert-heading">Произошла непредвиденная ошибка!</h4>
                    <p>К сожалению, мы столкнулись с проблемой при обработке вашего запроса. Попробуйте обновить страницу или вернуться позже.</p>
                    <hr>
                    <p class="mb-0">Код состояния: <strong>${statusCode}</strong></p>
                </div>
                <div class="text-center mt-4">
                    <a href="/" class="btn btn-success">Вернуться на главную</a>
                </div>
            </div>
        </div>
    </div>
</#macro>

</html>
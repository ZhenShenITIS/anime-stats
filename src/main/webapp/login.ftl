<html lang="en">
<#include "base.ftl">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

<#macro  title>Login</#macro>

<#macro content>
        <div class="container py-5">
                <div class="row justify-content-center">
                        <div class="col-sm-10 col-md-8 col-lg-5">
                                <div class="card shadow-sm">
                                        <div class="card-header bg-success text-white text-center">
                                                Вход в систему
                                        </div>
                                        <div class="card-body">
                                                <form method="post" action="/login" class="needs-validation" novalidate>
                                                        <div class="mb-3">
                                                                <label for="email" class="form-label">Почта</label>
                                                                <input type="email"
                                                                       class="form-control"
                                                                       id="email"
                                                                       name="email"
                                                                       placeholder="name@example.com"
                                                                       required>
                                                                <div class="invalid-feedback">Укажите корректный e-mail.</div>
                                                        </div>

                                                        <div class="mb-3">
                                                                <label for="password" class="form-label">Пароль</label>
                                                                <input type="password"
                                                                       class="form-control"
                                                                       id="password"
                                                                       name="password"
                                                                       placeholder="Введите пароль"
                                                                       required>
                                                                <div class="invalid-feedback">Пароль не может быть пустым.</div>
                                                        </div>

                                                        <div class="d-grid gap-2">
                                                                <button type="submit" class="btn btn-success">Войти</button>
                                                        </div>
                                                </form>
                                        </div>
                                </div>
                        </div>
                </div>
        </div>

        <script>
                // Bootstrap 5 клиентская валидация
                (function () {
                        'use strict';
                        var forms = document.querySelectorAll('.needs-validation');
                        Array.prototype.slice.call(forms).forEach(function (form) {
                                form.addEventListener('submit', function (event) {
                                        if (!form.checkValidity()) {
                                                event.preventDefault();
                                                event.stopPropagation();
                                        }
                                        form.classList.add('was-validated');
                                }, false);
                        });
                })();
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
</#macro>
</html>

<!DOCTYPE html>
<html lang="en">
<#include "base.ftl">

<#macro title>Sign Up</#macro>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

<#macro content>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-sm-10 col-md-8 col-lg-5">
                <div class="card shadow-sm">
                    <div class="card-header bg-success text-white text-center">
                        Регистрация
                    </div>
                    <div class="card-body">
                        <form method="post" action="/signup" class="needs-validation" novalidate autocomplete="off">
                            <div class="mb-3">
                                <label for="signup-name" class="form-label">Имя</label>
                                <input type="text"
                                       name="name"
                                       class="form-control"
                                       id="signup-name"
                                       placeholder="Введите имя"
                                       required>
                                <div class="invalid-feedback">Пожалуйста, укажите имя.</div>
                            </div>

                            <div class="mb-3">
                                <label for="ajax-email" class="form-label">Почта</label>
                                <input type="email"
                                       name="email"
                                       class="form-control"
                                       id="ajax-email"
                                       placeholder="name@example.com"
                                       required
                                       autocomplete="off">
                                <div id="ajax-email-response" class="form-text"></div>
                                <div class="invalid-feedback">Укажите корректный e-mail.</div>
                            </div>

                            <div class="mb-3">
                                <label for="signup-password" class="form-label">Пароль</label>
                                <input type="password"
                                       name="password"
                                       class="form-control"
                                       id="signup-password"
                                       placeholder="Введите пароль"
                                       required>
                                <div class="invalid-feedback">Пароль не может быть пустым.</div>
                            </div>

                            <div class="d-grid gap-2">
                                <button type="submit" id="ajax-button" class="btn btn-success" disabled>Зарегистрироваться</button>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <script>
        // AJAX валидация email
        $(document).on("change", "#ajax-email", function (){
            let email = $(this).val();
            let emailField = $(this);

            $.get("/ajax/validate/email/registration", {email: email}, function (response){
                if (response === "true") {
                    $("#ajax-email-response")
                        .text("Email свободен")
                        .removeClass("text-danger")
                        .addClass("text-success");
                    emailField.removeClass("is-invalid").addClass("is-valid");
                    $("#ajax-button").prop("disabled", false);
                } else {
                    $("#ajax-email-response")
                        .text("Email уже занят")
                        .removeClass("text-success")
                        .addClass("text-danger");
                    emailField.removeClass("is-valid").addClass("is-invalid");
                    $("#ajax-button").prop("disabled", true);
                }
            });
        });

        // Bootstrap валидация формы
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

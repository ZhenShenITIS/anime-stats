<!DOCTYPE html>
<html lang="en">
<#include "base.ftl">

<#macro title>Sign Up</#macro>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
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
                        <form method="post"
                              action="/signup"
                              class="needs-validation"
                              novalidate
                              autocomplete="off">
                            <div class="mb-3">
                                <label for="signup-name" class="form-label">Имя</label>
                                <input type="text"
                                       name="name"
                                       class="form-control"
                                       id="signup-name"
                                       placeholder="Введите имя"
                                       required
                                       minlength="2"
                                       maxlength="50"
                                       pattern="^[A-Za-zА-Яа-яЁё0-9\s'\-\.]{2,50}$"
                                       title="2–50 символов: буквы, цифры, пробел, тире, точка, апостроф">
                                <div class="invalid-feedback">
                                    Имя должно быть от 2 до 50 символов и содержать только допустимые символы.
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="ajax-email" class="form-label">Почта</label>
                                <input type="email"
                                       name="email"
                                       class="form-control"
                                       id="ajax-email"
                                       placeholder="name@example.com"
                                       required
                                       maxlength="256"
                                       autocomplete="email">
                                <div id="ajax-email-response" class="form-text"></div>
                                <div class="invalid-feedback">Укажите корректный e‑mail.</div>
                            </div>

                            <div class="mb-3">
                                <label for="signup-password" class="form-label">Пароль</label>
                                <input type="password"
                                       name="password"
                                       class="form-control"
                                       id="signup-password"
                                       placeholder="Введите пароль"
                                       required
                                       minlength="8"
                                       maxlength="64"
                                       pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z0-9])\S{8,64}$"
                                       title="Минимум 8 символов, хотя бы одна строчная и прописная буква, цифра и спецсимвол, без пробелов">
                                <div class="invalid-feedback">
                                    Минимум 8 символов, хотя бы одна строчная и прописная буква, цифра и спецсимвол, без пробелов.
                                </div>
                            </div>

                            <div class="d-grid gap-2">
                                <button type="submit" id="ajax-button" class="btn btn-success" disabled>
                                    Зарегистрироваться
                                </button>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <script>
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


        $(function () {
            const $form  = $('form.needs-validation');
            const $name  = $('#signup-name');
            const $email = $('#ajax-email');
            const $pwd   = $('#signup-password');
            const $btn   = $('#ajax-button');
            const $msg   = $('#ajax-email-response');

            function updateSaveState() {
                const nativeValid = $form[0].checkValidity();
                const emailOk = !$email.hasClass('is-invalid');
                $btn.prop('disabled', !(nativeValid && emailOk));
            }

            function toggleValidity($el) {
                if ($el[0].checkValidity()) {
                    $el.removeClass('is-invalid').addClass('is-valid');
                } else {
                    $el.removeClass('is-valid').addClass('is-invalid');
                }
            }

            $name.on('input', function () {
                toggleValidity($name);
                updateSaveState();
            });

            $pwd.on('input', function () {
                toggleValidity($pwd);
                updateSaveState();
            });


            $email.on('change', function () {
                if (!$email[0].checkValidity()) {
                    $msg.text('');
                    $email.removeClass('is-valid').addClass('is-invalid');
                    updateSaveState();
                    return;
                }
                const emailVal = $email.val().trim();
                $.get('/ajax/validate/email/registration', { email: emailVal }, function (response) {
                    if (response === 'true') {
                        $msg.text('Email свободен')
                            .removeClass('text-danger')
                            .addClass('text-success');
                        $email.removeClass('is-invalid').addClass('is-valid');
                    } else {
                        $msg.text('Email уже занят')
                            .removeClass('text-success')
                            .addClass('text-danger');
                        $email.removeClass('is-valid').addClass('is-invalid');
                    }
                    updateSaveState();
                });
            });


            updateSaveState();
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"></script>
</#macro>

</html>

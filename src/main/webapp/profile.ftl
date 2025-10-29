<html lang="ru">
<#include "base.ftl">

<#macro title>Профиль</#macro>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

<#macro content>
    <div class="container py-4">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="card shadow-sm">
                    <div class="card-header bg-success text-white d-flex justify-content-between align-items-center">
                        <span>Профиль</span>
                        <button type="button"
                                class="btn btn-light btn-sm"
                                data-bs-toggle="modal"
                                data-bs-target="#editProfileModal">
                            Изменить
                        </button>
                    </div>

                    <div class="card-body">
                        <ul class="list-group list-group-flush mb-0">
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <span class="text-muted">Имя</span>
                                <span class="fw-semibold">
                                  ${(user.name)!''?html}
                                </span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <span class="text-muted">Почта</span>
                                <span class="fw-semibold">
                                  ${(user.email)!''?html}
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="editProfileLabel" class="modal-title">Изменить профиль</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                    </div>

                    <form method="post"
                          action="/profile"
                          id="editProfileForm"
                          class="needs-validation"
                          novalidate
                          autocomplete="off">

                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="name" class="form-label">Имя</label>
                                <input
                                        type="text"
                                        class="form-control"
                                        id="name"
                                        name="name"
                                        value="${(user.name)!''?html}"
                                        required
                                        minlength="2"
                                        maxlength="50"
                                        pattern="^[A-Za-zА-Яа-яЁё0-9\s'\-\.]{2,50}$">
                                <div class="invalid-feedback">
                                    Имя должно быть от 2 до 50 символов и содержать только буквы, цифры, пробел, тире, точку и апостроф.
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="ajax-email" class="form-label">Почта</label>
                                <input
                                        type="email"
                                        class="form-control"
                                        id="ajax-email"
                                        name="email"
                                        value="${(user.email)!''?html}"
                                        required
                                        maxlength="256"
                                        autocomplete="email">
                                <div id="ajax-email-response" class="form-text"></div>
                                <div class="invalid-feedback">Укажите корректный e‑mail.</div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Отмена</button>
                            <button type="submit" id="ajax-button" class="btn btn-success" disabled>Сохранить</button>
                        </div>
                    </form>
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</#macro>
</html>

<html lang="ru">
<#include "base.ftl">

<#macro title>Profile</#macro>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
                                <span class="fw-semibold">${(user.name)!''}</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <span class="text-muted">Почта</span>
                                <span class="fw-semibold">${(user.email)!''}</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal: Изменение профиля -->
        <div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="editProfileLabel" class="modal-title">Изменить профиль</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                    </div>

                    <form method="post" action="/profile" id="editProfileForm" novalidate>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="name" class="form-label">Имя</label>
                                <input
                                        type="text"
                                        class="form-control"
                                        id="name"
                                        name="name"
                                        value="${(user.name)!''}"
                                        required>
                            </div>

                            <div class="mb-3">
                                <label for="email" class="form-label">Почта</label>
                                <input
                                        type="email"
                                        class="form-control"
                                        id="email"
                                        name="email"
                                        value="${(user.email)!''}"
                                        required>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Отмена</button>
                            <button type="submit" class="btn btn-success">Сохранить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</#macro>
</html>

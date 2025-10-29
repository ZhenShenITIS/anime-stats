<html lang="ru">
<#include "base.ftl">

<#macro title>Главная</#macro>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

<#macro content>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-12 col-md-10 col-lg-8">
                <div class="text-center mb-4">
                    <h1 class="display-6">Найди что посмотреть</h1>
                    <p class="lead text-secondary">Персональные рекомендации на основе никнейма Shikimori</p>
                </div>

                <form method="post" action="recommendations" class="bg-white p-4 rounded-3 border">
                    <div class="mb-3">
                        <label for="shikiUsername" class="form-label">Никнейм на Shikimori</label>
                        <div class="input-group">
                            <span class="input-group-text">@</span>
                            <input type="text"
                                   class="form-control"
                                   id="shikiUsername"
                                   name="shikiUsername"
                                   placeholder="Example123"
                                   required
                                   autocomplete="off">
                        </div>
                        <div class="form-text">Укажи свой ник — и получи список рекомендаций.</div>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-success btn-lg">Найти</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</#macro>
</html>

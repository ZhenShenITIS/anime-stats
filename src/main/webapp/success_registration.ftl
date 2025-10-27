<html lang="en">
<#include "base.ftl">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

<#macro title>Успех!</#macro>

<#macro content>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8 col-lg-6">

                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h1 class="h3 mb-2">Успешная регистрация!</h1>
                        <p class="text-secondary mb-4">Теперь вы можете войти в свой аккаунт</p>

                        <form method="get" action="/login" class="d-grid">
                            <button type="submit" class="btn btn-success btn-lg">
                                Войти
                            </button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</#macro>
</html>

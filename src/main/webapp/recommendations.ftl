<html lang="ru">
<#include "base.ftl">

<#macro title>Recommendations</#macro>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

<#macro content>
    <div class="container py-4">
        <h1 class="mb-4">Ваши рекомендации</h1>

        <#if animeList?has_content>
            <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 g-3">
                <#list animeList as a>
                    <div class="col">
                        <a class="anime-card" href="anime?id=${a.id?c}" aria-label="${a.title}">
                            <div class="anime-card__poster">
                                <img src="${a.picturePath}" alt="${a.title}" loading="lazy">
                            </div>
                            <div class="anime-card__title">${a.title}</div>
                        </a>
                    </div>
                </#list>
            </div>
        <#else>
            <div class="alert alert-info" role="alert">
                Список рекомендаций пуст.
            </div>
        </#if>
    </div>

    <script>
        (function () {
            const ENTER_SCALE = 1.02;
            const LEAVE_SCALE = 1.0;

            const BASE_SHADOW = '0 2px 10px rgba(0,0,0,0.08)';
            const HOVER_SHADOW = '0 6px 14px rgba(0,0,0,0.12), 0 12px 24px rgba(0,0,0,0.10)';

            function applyHover(card) {
                card.style.setProperty('--tilt-shadow', HOVER_SHADOW);
                card.style.setProperty('--scale', ENTER_SCALE.toString());
            }

            function clearHover(card) {
                card.style.setProperty('--tilt-shadow', BASE_SHADOW);
                card.style.setProperty('--scale', LEAVE_SCALE.toString());
            }

            function setupCard(card) {
                clearHover(card);
                card.addEventListener('mouseenter', () => applyHover(card));
                card.addEventListener('mouseleave', () => clearHover(card));
                card.addEventListener('focus',     () => applyHover(card));
                card.addEventListener('blur',      () => clearHover(card));
            }

            document.addEventListener('DOMContentLoaded', () => {
                document.querySelectorAll('.anime-card').forEach(setupCard);
            });
        })();
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</#macro>

</html>

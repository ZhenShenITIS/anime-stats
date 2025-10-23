<html lang="ru"> <#include "base.ftl">
<#macro title>Recommendations</#macro>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="style.css">
<#macro content>

    <h1>Ваши рекомендации:</h1>
    <#if animeList?has_content>
        <div class="anime-grid">
            <#list animeList as a>
                <a class="anime-card" href="anime?id=${a.id?c}" aria-label="${a.title}">
                    <div class="anime-card__poster">
                        <img src="${a.picturePath}" alt="${a.title}">
                    </div>
                    <div class="anime-card__title">${a.title}</div>
                </a>
            </#list>
        </div>
    <#else>
        <p>Список рекомендаций пуст.</p>
    </#if>
</#macro>


<script src="js/card-tilt.js?v=1"></script>

</html>
<html lang="en">
<#include "base.ftl">

<#-- Заголовок страницы: название аниме или запасной текст -->
<#macro title>${(anime.title)!'Anime details'}</#macro> <!-- безопасный вывод и запасное значение -->

<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- подключение jQuery по исходнику -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

<#macro content>
    <div id="content">
        <div class="content">
            <div class="container-fluid">
                <div class="row g-4 align-items-start">

                    <div class="col-12 col-md-4">
                        <div class="card shadow-sm">
                            <#-- Путь к картинке, запасной плейсхолдер если пусто -->
                            <img
                                    src="${(anime.picturePath)!'/static/img/placeholder-anime.png'}"
                                    class="card-img-top"
                                    alt="${(anime.title)!'Anime'}">
                        </div>
                    </div>

                    <div class="col-12 col-md-8">
                        <div class="card border-0">
                            <div class="card-body p-0">
                                <h2 class="card-title mb-2">${(anime.title)!'Untitled'}</h2>

                                <div class="d-flex flex-wrap gap-3 mb-3">
                                    <div class="d-flex align-items-center">
                                        <span class="text-muted me-1">Score:</span>
                                        <span class="fw-semibold">${(anime.score)!'—'}</span>
                                    </div>
                                    <div class="d-flex align-items-center">
                                        <span class="text-muted me-1">Rank:</span>
                                        <span class="fw-semibold">#${(anime.rank)!'—'}</span>
                                    </div>
<#--                                    <div class="d-flex align-items-center">-->
<#--                                        <span class="text-muted me-1">ID:</span>-->
<#--                                        <span class="fw-semibold">${(anime.id)!'—'}</span>-->
<#--                                    </div>-->
                                </div>

                                <#-- Жанры -->
                                <#if anime?has_content && anime.genres?has_content>
                                    <div class="mb-3">
                                        <span class="text-muted">Genres:</span>
                                        <div class="mt-2 d-flex flex-wrap gap-2">
                                            <#list anime.genres as g>
                                                <span class="badge text-bg-primary">${(g.name)!'Unknown'}</span>
                                            </#list>
                                        </div>
                                    </div>
                                <#else>
                                    <div class="mb-3">
                                        <span class="text-muted">Genres:</span>
                                        <span class="ms-1">—</span>
                                    </div>
                                </#if>

                                <#-- Синопсис -->
                                <div class="mb-2">
                                    <span class="text-muted d-block mb-1">Synopsis:</span>
                                    <p class="mb-0">${(anime.synopsis)!'No synopsis available.'}</p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <#-- Связанные аниме -->
<#--                <div class="row mt-4">-->
<#--                    <div class="col-12">-->
<#--                        <h4 class="mb-3">Related anime</h4>-->
<#--                    </div>-->

<#--                    <#if anime?has_content && anime.relatedAnime?has_content>-->
<#--                        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">-->
<#--                            <#list anime.relatedAnime as rel>-->
<#--                                <div class="col">-->
<#--                                    <div class="card h-100">-->
<#--                                        <img-->
<#--                                                src="${(rel.picturePath)!'/static/img/placeholder-anime.png'}"-->
<#--                                                class="card-img-top"-->
<#--                                                alt="${(rel.title)!'Related anime'}">-->
<#--                                        <div class="card-body">-->
<#--                                            <h6 class="card-title mb-2">${(rel.title)!'Untitled'}</h6>-->
<#--                                            <p class="card-text small text-muted mb-2">-->
<#--                                                Score: ${(rel.score)!'—'} · Rank: #${(rel.rank)!'—'}-->
<#--                                            </p>-->
<#--                                            &lt;#&ndash; Ссылка на эту же страницу с id связанного тайтла &ndash;&gt;-->
<#--                                            <a href="/anime?id=${(rel.id)!}" class="btn btn-sm btn-primary">Open</a>-->
<#--                                        </div>-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                            </#list>-->
<#--                        </div>-->
<#--                    <#else>-->
<#--                        <div class="col-12">-->
<#--                            <p class="text-muted">No related titles.</p>-->
<#--                        </div>-->
<#--                    </#if>-->
<#--                </div>-->

            </div>
        </div>
    </div>
</#macro>
</html>

<html lang="en">
<#include "base.ftl">

<#macro  title>Recommendatios</#macro>


<#macro content>
    <h1>Ваши рекомендации: </h1>

    <#if animeList?has_content>
        <br>
        Твой лист:
        <#list animeList as a>
            ${a.title}
            <br>
        </#list>
    </#if>

</#macro>
</html>
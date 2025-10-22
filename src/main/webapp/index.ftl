<html lang="en">
<#include "base.ftl">

<#macro title>Main Page</#macro>

<#macro content>
    <p>
        Привет! С помощью данного сервиса ты можешь найти, что посмотреть!
    </p>

    <form method="post" action="recommendations">
        Никнейм на Shikimori: <input type="text"
                                            name="shikiUsername"
                                            placeholder="Example123"/>
        <br>
        <input type="submit" value="Найти" />
    </form>



</#macro>
</html>
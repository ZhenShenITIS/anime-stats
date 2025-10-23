<html lang="en">
<#include "base.ftl">

<#macro title>Main Page</#macro>


<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

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
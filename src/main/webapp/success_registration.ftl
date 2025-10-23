<html lang="en">
<#include "base.ftl">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

<#macro title>Success!</#macro>

<#macro content>
    <h1>Success registration!</h1>

    <form method="get" action="login">
        <input type="submit" value="Login"/>
    </form>
</#macro>
</html>
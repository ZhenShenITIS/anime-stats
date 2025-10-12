<html lang="en">
<#include "base.ftl">

<#macro title>Already Signed Up!</#macro>

<#macro content>
    H1>You have already signed up! Please login: </H1>
    <form method="get" action="login">
        <input type="submit" value="Login"/>
    </form>
</#macro>
</html>
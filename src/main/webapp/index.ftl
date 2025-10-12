<html lang="en">
<#include "base.ftl">

<#macro title>Index Page</#macro>

<#macro content>
    <p>
        Hello! It's a simple app!
    </p>

    <form method="get" action="login">
        <input type="submit" value="Login"/>
    </form>
    <br>
    <form method="get" action="signup">
        <input type="submit" value="SignUp"/>
    </form>


</#macro>
</html>
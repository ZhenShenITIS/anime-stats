<html lang="en">
<#include "base.ftl">

<#macro  title>Login</#macro>


<#macro content>
        <h1>Login</h1>
        <form method="post" action="login">
                Login: <input type="text" name="login"  placeholder="login"/>
                <br>

                Password: <input type="password" name="password" placeholder="password"/>
                <br><br>
                <input type="submit" value="Login" />
        </form>
</#macro>
</html>
<!DOCTYPE html>
<html lang="en">
<#include "base.ftl">

<#macro title>Sign Up</#macro>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">
<style>
    .sign-up-form-container {
        max-width: 430px;
        margin: 56px auto;
        background: #fff;
        border-radius: 18px;
        box-shadow: 0 8px 32px rgba(44,62,80,0.17);
        padding: 36px 28px;
    }
    .sign-up-form-container h1 {
        text-align: center;
        margin-bottom: 24px;
        color: #1a2235;
        font-weight: 700;
        letter-spacing: 1px;
    }
    .form-label {
        font-weight: 600;
        color: #355c7d;
    }
    #ajax-login-response {
        font-size: 0.95em;
        font-weight: 600;
        margin-top: 5px;
        min-height: 22px;
    }
    #ajax-login-response {
        color: #16a34a;
    }
    input#ajax-login:invalid, input:focus:invalid {
        border-color: #e11d48;
    }
    input[type="text"],
    input[type="password"] {
        border-radius: 7px;
        font-size: 1.05em;
    }
    .ui-button, input[type="submit"] {
        width: 100%;
        margin-top: 12px;
        padding: 10px 0;
        border-radius: 7px;
        font-weight: bold;
        font-size: 1.1em;
        background: linear-gradient(90deg, #2772fa 30%, #68e0cf 100%);
        color: #fff;
        border: none;
        transition: background 0.18s;
    }
    .ui-button:disabled {
        opacity: 0.6;
        cursor: not-allowed;
        background: #d1d1d1;
    }
    .ui-button:hover:enabled,
    input[type="submit"]:hover:enabled {
        background: linear-gradient(90deg, #176aa3 20%, #29e090 100%);
        color: #fff;
    }
</style>
<script>
    $(document).on("change", "#ajax-login", function (){
        let login = $(this).val();
        $.get("ajax/validate/login/registration", {login :  login}, function (response){
            if (response === "true") {
                $("#ajax-login-response").text("Логин свободен").css("color","#16a34a")
                $("#ajax-button").prop("disabled", false)
            } else {
                $("#ajax-login-response").text("Логин занят").css("color","#e11d48")
                $("#ajax-button").prop("disabled", true)
            }
        })
    })
</script>

<#macro content>
    <div class="sign-up-form-container shadow bg-white">
        <h1>Sign Up</h1>
        <form method="post" action="signup" autocomplete="off">
            <div class="mb-3">
                <label for="signup-name" class="form-label">Name</label>
                <input type="text" name="name" class="form-control" id="signup-name" placeholder="name" required>
            </div>
            <div class="mb-3">
                <label for="signup-email" class="form-label">E-mail</label>
                <input type="email" name="email" class="form-control" id="signup-email" placeholder="email" required>
            </div>
            <div class="mb-3">
                <label for="ajax-login" class="form-label">Login</label>
                <input type="text" name="login" class="form-control" placeholder="login" id="ajax-login" autocomplete="off" required>
                <div id="ajax-login-response"></div>
            </div>
            <div class="mb-3">
                <label for="signup-password" class="form-label">Password</label>
                <input type="password" name="password" class="form-control" id="signup-password" placeholder="password" required>
            </div>
            <input type="submit" value="SignUp" id="ajax-button" class="ui-button" disabled />
        </form>
    </div>
</#macro>
</html>

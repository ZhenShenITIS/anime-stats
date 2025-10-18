<!DOCTYPE html>
<html lang="en">
<#include "base.ftl">

<#macro title>Sign Up</#macro>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

<script>
    $(document).on("change", "#ajax-email", function (){
        let email = $(this).val();
        $.get("/ajax/validate/email/registration", {email :  email}, function (response){
            if (response === "true") {
                $("#ajax-email-response").text("Email свободен").css("color","#16a34a")
                $("#ajax-button").prop("disabled", false)
            } else {
                $("#ajax-email-response").text("Email занят").css("color","#e11d48")
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
                <label for="ajax-email" class="form-label">E-mail</label>
                <input type="email" name="email" class="form-control" id="ajax-email" placeholder="email" required autocomplete="off">
                <div id="ajax-email-response"></div>
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

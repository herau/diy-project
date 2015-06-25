<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title></title>

    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.css">

    <link rel="stylesheet" href="../css/signin.css">
</head>
<body>
    <div class="container">
        <form class="form-signin" action="/login" method="post">
        <#if RequestParameters.logout??>
            <p class="alert alert-info"><span class="glyphicon glyphicon-log-out"></span> You have been logged out.</p>
        </#if>
        <#if RequestParameters.error??>
            <p class="alert alert-warning">
                <span class="glyphicon glyphicon-alert"></span> Invalid username and/or password, please try again
            </p>
        </#if>
            <!--<h2 class="form-signin-heading">Connectez vous à la section</h2>-->
            <label for="username" class="sr-only">Matricule</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Matricule"
                   pattern="[0-9]*" required autofocus/>
            <label for="password" class="sr-only">Mot de passe</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Mot de passe"
                   required/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <!-- TODO Future Remember me-->
            <!--<div class="checkbox">-->
            <!--<label>-->
            <!--<input type="checkbox" value="remember-me"> Remember me-->
            <!--</label>-->
            <!--</div>-->
            <div class="btn-link">
                <label>
                    <a href="#">Mot de passe oublié</a>
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>
        </form>
    </div>
</body>
</html>
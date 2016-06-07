<!DOCTYPE html>
<html lang="">
  <head>
      <title>DIY - Welcome</title>
      <base href="/">

      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="Do It Yourself">

      <link rel="icon" href="data:;base64,iVBORw0KGgo=">

  <#if user??>
      <script type="application/javascript">var user =${user}</script>
  </#if>

  </head>

  <body>
    <app>
        Loading...
    </app>

    <!-- polyfill files -->
    <script src="dist/polyfills.bundle.js"></script>
    <!-- Angular 2 files -->
    <script src="dist/vendor.bundle.js"></script>
    <!-- App scripts -->
    <script src="dist/main.bundle.js"></script>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  </body>
</html>

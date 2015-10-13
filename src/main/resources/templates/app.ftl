<!DOCTYPE html>
<html lang="">
<head>
    <title>DIY - Welcome</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Do It Yourself">

    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    <!-- base url -->
    <base href="/">
    <!-- styles -->
    <style>
    </style>
    <!--
        Angular 2
        ES6 browser shim
    -->
    <script src="/lib/es6-shim.js"></script>
<#if user??>
    <script type="application/javascript">var user =${user}</script>
</#if>
</head>
<body>
<app>
    Loading...
</app>
<!-- Commmon files to be cached -->
<script src="/build/common.js"></script>
<!-- Angular 2 files -->
<script src="/build/angular2.js"></script>
<!-- Semantic UI files -->
<script src="/build/semantic.js"></script>
<!-- App script -->
<script src="/build/app.js"></script>
</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Latest</title>
    <meta charset="UTF-8">
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<#list codes as code>
    <div>
        <span id="load_date">${code.getDate()}</span>
        <pre id="code_snippet">${code.getCode()}</pre>
    </div>
</#list>
</body>
</html>
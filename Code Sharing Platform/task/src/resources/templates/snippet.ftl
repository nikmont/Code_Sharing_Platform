<!DOCTYPE html>
<html lang="en">
<head>
    <title>Code</title>
    <meta charset="UTF-8">
    <link href="/css/main.css" rel="stylesheet">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <span id="load_date">${code.getDate()}</span>
    <br>
    <#if code.isViewsRestrict()> <!--если есть ограничения-->
    <span id="views_restriction">${code.getViews()} more views allowed</span>
    <br>
    </#if>
    <#if code.isTimeRestrict()> <!--если есть ограничения-->
    <span id="time_restriction">The code will be available for ${code.getTime()} seconds</span>
    <br>
    </#if>
    <pre id="code_snippet"><code>${code.getCode()}</code></pre>
</body>
</html>
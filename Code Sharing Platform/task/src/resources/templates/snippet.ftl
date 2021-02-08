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
    <pre id="code_snippet"><code>${code.getCode()}</code></pre>
</body>
</html>
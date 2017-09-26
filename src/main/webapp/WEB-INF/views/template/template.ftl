<#assign base=request.contextPath>
<!DOCTYPE html>
<html lang="zh">
<base id="base" href="${base}">
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${base}/dist/js/bootstrap.js" rel="stylesheet">
<link href="${base}/dist/js/jquery-2.2.3.min.js" rel="stylesheet">

<script>
    $(function () {
        var base = document.getElementById("base").href;
        _send = function(async, url, value, success, error) {
            $.ajax({
                async : async,
                url : base + '/' + url,
                contentType : "application/x-www-form-urlencoded; charset=utf-8",
                data : value,
                dataType : 'json',
                type : 'post',
                success : function(data) {
                    success(data);
                },
                error : function(data) {
                    error(data);
                }
            });
        })
    }

</script>
<body>
    AAMAKAKM
</body>
</html>
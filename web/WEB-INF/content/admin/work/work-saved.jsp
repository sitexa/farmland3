<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        if (window.opener) {
            window.opener.location.reload();
        }
        var time = 3;
        onload = function() {
            setTimeout(function() {
                _close()
            }, 1000);
            document.getElementById("close").value = "关闭(" + time + "秒后...)";
        }
        function _close() {
            time--;
            if (time <= 0) window.close();
            setTimeout(function() {
                _close()
            }, 1000);
            document.getElementById("close").value = "关闭(" + time + "秒后...)";
        }
    </script>
</head>
<body>
<div style="margin: 20px auto 0 auto; text-align: center;">
    <font color="red" size="20"> ${msg} </font> <br/>
    <input type="button" id="close" onclick="window.close()"/>
</div>
</body>
</html>
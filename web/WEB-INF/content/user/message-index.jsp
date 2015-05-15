<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Comet demo</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript">
        var url = "/ChatServlet.svt";
        var comet = {
            connection   : false,
            iframediv    : false,

            initialize: function() {
                if (navigator.appVersion.indexOf("MSIE") != -1) {

                    // For IE browsers
                    comet.connection = new ActiveXObject("htmlfile");
                    comet.connection.open();
                    comet.connection.write("<html>");
                    comet.connection.write("<script>document.domain = '" + document.domain + "'");
                    comet.connection.write("</html>");
                    comet.connection.close();
                    comet.iframediv = comet.connection.createElement("div");
                    comet.connection.appendChild(comet.iframediv);
                    comet.connection.parentWindow.comet = comet;
                    comet.iframediv.innerHTML = "<iframe id='comet_iframe' src='" + url + "'></iframe>";
                } else if (navigator.appVersion.indexOf("KHTML") != -1) {
                    // for KHTML browsers
                    comet.connection = document.createElement('iframe');
                    comet.connection.setAttribute('id', 'comet_iframe');
                    comet.connection.setAttribute('src', url);
                    with (comet.connection.style) {
                        position = "absolute";
                        left = top = "-100px";
                        height = width = "1px";
                        visibility = "hidden";
                    }
                    document.body.appendChild(comet.connection);
                } else {

                    // For other browser (Firefox...)
                    comet.connection = document.createElement('iframe');
                    comet.connection.setAttribute('id', 'comet_iframe');
                    with (comet.connection.style) {
                        left = top = "-100px";
                        height = width = "1px";
                        visibility = "hidden";
                        display = 'none';
                    }
                    comet.iframediv = document.createElement('iframe');
                    comet.iframediv.setAttribute('src', url);
                    comet.connection.appendChild(comet.iframediv);
                    document.body.appendChild(comet.connection);
                }
            },
            // this function will be called from backend.php
            receiverMessage: function (time) {
                $('#content').html($('#content').html() + "<br />" + time);
            },
            addReceiver: function(userId, userName) {
                var n = $("#receiver option[value='" + userId + "']").length;
                if (n == 0) {
                    $("#receiver").append("<option value='" + userId + "'>" + userName + "</option>");
                }
            },
            removeReceiver: function(userId) {
                var o = $("#receiver option[value='" + userId + "']");
                o.remove();
            },
            onUnload: function() {
                if (comet.connection) {
                    comet.connection = false; // release the iframe to prevent problems with IE when reloading the page
                }
            }
        }

        onload = function() {
            comet.initialize();
            //setTimeout(function(){connent();}, 9*1000);
        }
        onunload = function() {
            comet.onUnload();
        }
        //Event.observe(window, "load",   comet.initialize);
        //Event.observe(window, "unload", comet.onUnload);

        function send() {
            var msg = $("#msg").val();
            var receiver = $("#receiver").val();
            $.get(
                    "http://www1.farmlander.com/SendServlet.svt?t=" + Math.random(),
            {msg:msg,receiver:receiver},
                    function (data) {
                    }
                    );
        }

        function connent() {
            $.get(
                    "http://www1.farmlander.com/ChatServlet?t=" + Math.random(),
            {},
                    function (data) {
                    }
                    );
        }
    </script>
</head>
<body>
<select id="receiver">
    <option value="ALL">所有人</option>
    <s:iterator value="#request.receivers" status="its">
        <option value="<s:property value='userId'/>"><s:property value='userName'/></option>
    </s:iterator>
</select>
<input type="text" id="msg"/>
<input type="button" value="提交" onclick="send()"/>

<div id="content">The server time will be shown here</div>
</body>
</html> 
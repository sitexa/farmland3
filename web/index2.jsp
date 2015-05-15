<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>花木兰ICSA农场</title>
    <meta http-equiv="refresh" content="0;url=fml">
    <meta name="verify-v1" content="k259ptqdEx4rpDBG1K1KzYoCS/of2lbHViWkT8YuzFg=">
    <script src="Scripts/swfobject_modified.js" type="text/javascript"></script>
    <style type="text/css">
        html, body {
            padding: 0;
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 12px;
            color: #414141;
            line-height: 1.2em;
        }

        td {
            font-family: '微软雅黑';
            color: green;
            font-size:12px;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div style="margin:0 auto; width:900px;height:680px; position:relative;">
    <div style="margin:0;z-index:1;background-color:blue;">
        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="900" height="680" id="FlashID"
                title="花木兰ICSA农场">
            <param name="movie" value="images/index.swf"/>
            <param name="quality" value="high"/>
            <param name="wmode" value="opaque"/>
            <param name="swfversion" value="6.0.65.0"/>
            <!-- 此 param 标签提示使用 Flash Player 6.0 r65 和更高版本的用户下载最新版本的 Flash Player。如果您不想让用户看到该提示，请将其删除。 -->
            <param name="expressinstall" value="Scripts/expressInstall.swf"/>
            <!-- 下一个对象标签用于非 IE 浏览器。所以使用 IECC 将其从 IE 隐藏。 -->
            <!--[if !IE]>-->
            <object type="application/x-shockwave-flash" data="images/index.swf" width="900" height="680">
                <!--<![endif]-->
                <param name="quality" value="high"/>
                <param name="wmode" value="opaque"/>
                <param name="swfversion" value="6.0.65.0"/>
                <param name="expressinstall" value="Scripts/expressInstall.swf"/>
                <!-- 浏览器将以下替代内容显示给使用 Flash Player 6.0 和更低版本的用户。 -->
                <div>
                    <h4>此页面上的内容需要较新版本的 Adobe Flash Player。</h4>

                    <p><a href="http://www.adobe.com/go/getflashplayer"><img
                            src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
                            alt="获取 Adobe Flash Player" width="112" height="33"/></a></p>
                </div>
                <!--[if !IE]>-->
            </object>
            <!--<![endif]-->
        </object>
    </div>
    <div style="width:220px; height:120px;z-index: 10; position:absolute; right:40px; bottom:40px;">
        <s:form action="%{#request.contextPath}/logon!login" theme="simple" style="margin:0;padding:0;">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td nowrap>用户名:</td>
                    <td align="left"><s:textfield name="user.username" cssStyle="width:130px;"/></td>
                </tr>
                <tr>
                    <td nowrap>密电码:</td>
                    <td align="left"><s:password cssStyle="font-family: Arial, Helvetica, sans-serif;width:130px;"
                                                 name="user.password"/></td>
                </tr>
                <tr>
                    <td nowrap>识别码:</td>
                    <td align="left"><s:textfield name="code" cssStyle="width:60px;"/><img
                            src="<%=request.getContextPath()%>/image!codeImage" alt="识别码"/>
                    </td>
                </tr>
                <tr>
                    <td nowrap>记住我:<s:checkbox name="remember" value="true"/></td>
                    <td><s:submit value="登录"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">忘记密码了?<a href="<%=request.getContextPath()%>/logon!findpwd"
                                                            style="color:#0066CC">找回密码</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">还没有注册?<a href="<%=request.getContextPath()%>/user/user/new"
                                                            style="color:#0066CC">立即注册</a>
                    </td>
                </tr>
            </table>
        </s:form>
    </div>
    <div style="width:100px;height:30px;font-family:'微软雅黑';font-size:16px;color:white;z-index: 10; position:absolute; right:1px; top:20px;">
        <a href="/fml" style="color:white;text-decoration:underline;">进入内页>></a>
    </div>
</div>
<script type="text/javascript">
    <!--
    swfobject.registerObject("FlashID");
    //-->
</script>
</body>
</html>
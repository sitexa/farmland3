<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/pic.js"></script>
    <script type="text/javascript">
        function showPic(id) {
            which = id;
            applyeffect();
            document.getElementById("photoslider").src = photos[which];
            document.getElementById("photodescription").innerHTML = descriptions[which];
            playeffect();
            keeptrack();
        }
    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="buyfarm-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="t">
                <div class="t-t">农场资料</div>
            </div>
            <div class="box">
                <div class="title">农场资料</div>
                <s:if test="pictures.size()>0">
                    <div class="box">
                        <div class="box">
                            <table border="0">
                                <tr>
                                    <s:iterator value="pictures" status="its">
                                        <s:if test="#its.index<6">
                                            <script>
                                                photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/l/${land.landId}/${picUrl}";
                                                titles[${its.index}] = "${picTitle}";
                                                descriptions[${its.index}] = "${description}";
                                            </script>
                                            <td>
                                                <img src="<%=request.getContextPath()%>/image?type=land&picId=${picId}" alt="点击看大图"
                                                     onclick="showPic(${its.index})"/>
                                            </td>
                                        </s:if>
                                    </s:iterator>
                                    <script>
                                        var preloadedimages = new Array();
                                        for (i = 0; i < photos.length; i++) {
                                            preloadedimages[i] = new Image();
                                            preloadedimages[i].src = photos[i];
                                        }
                                    </script>
                                </tr>
                            </table>
                        </div>
                        <div class="content">
                            <script>
                                if (photos.length > 0) {
                                    document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward()" style="filter:revealTrans(duration=2,transition=23);width:100%" border=0>');
                                    document.write('<div id="photodescription" style="text-align:center">' + descriptions[0] + '</div>');
                                }
                            </script>
                        </div>
                    </div>
                </s:if>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="buyfarm-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
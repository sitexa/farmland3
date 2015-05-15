<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%--
  User: xnpeng
  Date: 2009-4-2
  Time: 21:11:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
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
            <s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="title">会员照片</div>
                <div class="content">
                    <div id="box" style="width:100%;overflow-x:scroll;">
                        <table>
                            <tr>
                                <s:iterator value="pictures" status="its">
                                    <script>
                                        photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/m/${member.memberId}/${picUrl}";
                                        titles[${its.index}] = "${picTitle}";
                                        descriptions[${its.index}] = "${description}";
                                    </script>
                                    <td>
                                        <img src="<%=request.getContextPath()%>/image?type=m&picId=${picId}" alt="点击看大图"
                                             onclick="showPic(${its.index})"/>
                                    </td>
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
                    <div class="box">
                        <script>
                            if (photos.length > 0)
                                document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward();" style="filter:revealTrans(duration=2,transition=23);width:100%;" border=0>');
                        </script>
                        <script>
                            if (photos.length > 0)
                                document.write('<div id="photodescription">' + descriptions[0] + '</div>');
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="control-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
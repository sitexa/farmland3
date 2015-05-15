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
    <link href="<%=request.getContextPath()%>/buy/buy.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
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
            <div class="box">
                <div class="t2">
                    <div class="t2-t">
                        农场资料
                    </div>
                </div>
                <div class="content">
                    <div style="float:right;">
                        <s:include value="../map.jsp"/>
                    </div>
                    地　区　：<s:if test="land.site.parent!=null"><s:property value="land.site.parent.name"/> > </s:if><s:property value="land.site.name"/><br/>
                    农场主　：<s:property value="land.lord.realname"/><br/>
                    地　址　：<s:property value="land.address"/><br/>
                    类　型　：<s:property value="land.landType"/><br/>
                </div>
            </div>
            <span class="blank6"></span>

            <div class="box">
                <div class="t2">
                    <div class="t2-t">
                        农场图片
                    </div>
                </div>
                <s:if test="pictures.size()>0">
                    <div class="box" style="width:98%;overflow-x:scroll">
                        <table border="0">
                            <tr>
                                <s:iterator value="pictures" status="its">
                                    <script>
                                        photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/l/${land.landId}/${picUrl}";
                                        titles[${its.index}] = "${picTitle}";
                                        descriptions[${its.index}] = "${description}";
                                    </script>
                                    <td>
                                        <img src="<%=request.getContextPath()%>/image?type=land&picId=${picId}" alt="点击看大图"
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
                    <div class="content">
                        <script>
                            if (photos.length > 0) {
                                document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward()" style="filter:revealTrans(duration=2,transition=23);width:100%" border=0>');
                                document.write('<div id="photodescription" style="text-align:center">' + descriptions[0] + '</div>');
                            }
                        </script>
                    </div>
                </s:if>
            </div>
            <div class="box">
                <div class="content">
                    ${land.description}
                    <s:if test="land.landId==1001039">
                        <img src="/images/roadmap.jpg" alt="map" width="100%">
                    </s:if>
                </div>
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
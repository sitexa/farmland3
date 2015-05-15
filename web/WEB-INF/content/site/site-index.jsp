<%--
  User: xnpeng
  Date: 2009-5-2
  Time: 7:32:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="verify-v1" content="k259ptqdEx4rpDBG1K1KzYoCS/of2lbHViWkT8YuzFg=" />
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
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
            <s:include value="site-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="title1">社区资料</div>
                <div class="content">
                    <div style="float:right"><s:include value="../map.jsp"/></div>
					社区编号:<s:property value="site.siteId"/><br/>
					社区名称:<span style="font-weight:bold"><s:property value="site.name"/></span><br/>
					社区类型:<s:property value="site.type.typeName"/><br/>
					社区地址:<s:property value="site.address"/><br/>
					社区简介:<s:property value="site.introduction"/><br/>
					创建时间:<s:date name="site.createDate" format="yy年M月d日"/><br/>
					社区长官:<s:property value="site.governor.realname"/><br/>
					纬度:<s:property value="site.latitude"/><br/>
					经度:<s:property value="site.longitude"/><br/>
					缩放比例:<s:property value="site.zoom"/><br/>
					社区状态:<s:property value="site.status"/><br/>
					社区位置:
                    <s:if test="site.parent.parent.parent!=null">
	                    <a href="<%=request.getContextPath()%>/site/site/${site.parent.parent.parent.siteId}">
	                        <s:property value="site.parent.parent.parent.name"/>
	                    </a> >
                    </s:if>
                    <s:if test="site.parent.parent!=null">
                        <a href="<%=request.getContextPath()%>/site/site/${site.parent.parent.siteId}">
                            <s:property value="site.parent.parent.name"/>
                        </a> >
                    </s:if>
                    <s:if test="site.parent!=null">
                        <a href="<%=request.getContextPath()%>/site/site/${site.parent.siteId}">
                            <s:property value="site.parent.name"/>
                        </a> >
                    </s:if>
                    <s:property value="site.name"/>
                    <s:if test="site.children.size>0"> > </s:if>
                    <s:iterator value="site.children">
                        <a href="<%=request.getContextPath()%>/site/site/${siteId}"><s:property value="name"/></a>&nbsp;&nbsp;
                    </s:iterator>
                    <br/>
                    <s:iterator value="properties">
                        <s:property value="propName"/>:<s:property value="propValue"/><br/>
                    </s:iterator>
                </div>
                <div class="clear"></div>
            </div>
            <div class="box">
                <div class="title1">社区图片</div>
                <div class="content">
                    <s:if test="pictures.size()>0">
                        <div class="box" style="width:100%;overflow-x:scroll;">
                            <table>
                                <tr>
                                    <s:iterator value="pictures" status="its">
                                        <script>
                                            photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/s/${site.siteId}/${picUrl}";
                                            titles[${its.index}] = "${picTitle}";
                                            descriptions[${its.index}] = "${description}";
                                        </script>
                                        <td>
                                            <img src="<%=request.getContextPath()%>/image?type=s&picId=${picId}" alt="点击看大图" onclick="showPic(${its.index})"/>
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
                                if (photos.length > 0) {
                                    document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward()" style="filter:revealTrans(duration=2,transition=23);width:100%" border=0>');
                                }
                            </script>
                            <script>
                                if (photos.length > 0)
                                    document.write('<div id="photodescription">' + descriptions[0] + '</div>');
                            </script>
                        </div>
                    </s:if>
                </div>
                <div class="clear"></div>
            </div>
            <s:actionerror/>
            <s:actionmessage/>
        </div>
       	<div id="rightbar" class="rightcol" >
            <s:include value="site-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
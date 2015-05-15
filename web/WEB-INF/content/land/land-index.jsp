<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="verify-v1" content="k259ptqdEx4rpDBG1K1KzYoCS/of2lbHViWkT8YuzFg=">
    <s:i18n name="keywords">
        <meta name="keywords" content="<s:text name='keywords'/>">
    </s:i18n>
    <title>
        <s:text name="root.title">
            <s:param>农场</s:param>
        </s:text>
    </title>
    <script src="<%=request.getContextPath()%>/script/jquery.js" type="text/javascript"></script>
    <link href="<%=request.getContextPath()%>/land/land.css" rel="stylesheet" type="text/css"/>
    <script src="<%=request.getContextPath()%>/land/land.js" type="text/javascript"></script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="centerbar">
            <div class="map">
                <div class="mapbox"><s:include value="landmap.jsp"/></div>
                <div class="mapinfo">
                    <div class="sitecrumb">
                        <s:if test="site.parent.parent.parent!=null">
                            <a href="<%=request.getContextPath()%>/land/land?siteId=${site.parent.parent.parent.siteId}">
                                <s:property value="site.parent.parent.parent.name"/>
                            </a> >
                        </s:if>
                        <s:if test="site.parent.parent!=null">
                            <a href="<%=request.getContextPath()%>/land/land?siteId=${site.parent.parent.siteId}">
                                <s:property value="site.parent.parent.name"/>
                            </a> >
                        </s:if>
                        <s:if test="site.parent!=null">
                            <a href="<%=request.getContextPath()%>/land/land?siteId=${site.parent.siteId}">
                                <s:property value="site.parent.name"/>
                            </a> >
                        </s:if>
                        <s:property value="site.name"/>
                        <s:if test="site.children.size>0"> > </s:if>
                        <s:iterator value="site.children">
                            <a href="<%=request.getContextPath()%>/land/land?siteId=${siteId}"><s:property value="name"/></a>&nbsp;&nbsp;
                        </s:iterator>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
            <s:iterator value="%{lands}" status="its">
                <div class="box">
                    <div class="t">
                        <div class="t-t">
                            <a href="<%=request.getContextPath()%>/land/land/${landId}">${landName}</a>
                        </div>
                    </div>
                    <div class="content">
                        <s:if test="landPictures.size()>0">
                            <s:iterator value="landPictures" status="its">
                                <s:if test="#its.index<1">
                                    <a href="<%=request.getContextPath()%>/land/land/${landId}">
                                        <img src="<%=request.getContextPath()%>/image?type=land&picId=${picId}" alt="" style="float:left;margin:4px;"/>
                                    </a>
                                </s:if>
                            </s:iterator>
                        </s:if>
                        <s:else>
                            <a href="<%=request.getContextPath()%>/land/land/${landId}">
                                <img src="<%=request.getContextPath()%>/images/noimage.jpg" alt="" style="float:left;margin:4px;"/>
                            </a>
                        </s:else>
                        <a href="<%=request.getContextPath()%>/site/site/${site.siteId}">${site.state.name}-${site.name}</a>
                            ${landLord} [<a href="<%=request.getContextPath()%>/user/member/${lord.memberId}">${lord.realname}</a>]
                        <br>
                            ${description}
                        <div class="clear"></div>
                    </div>
                </div>
            </s:iterator>
            <div class="box">
                <s:include value="../paging.jsp"/>
                <form id="pageFrm" action="<%=request.getContextPath()%>/land/land/"
                      method="get" style="display:none;">
                    <s:hidden id="page.current" name="page.current" value="1"/>
                </form>
            </div>
            <div class="clear"></div>
        </div>
        <div id="rightbar">
            <s:include value="land-right.jsp"/>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
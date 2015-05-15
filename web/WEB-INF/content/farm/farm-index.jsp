<%--
  User: Administrator
  Date: 11-1-20
  Time: 下午4:53
--%>
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
            <s:param>农庄</s:param>
        </s:text>
    </title>
    <script src="<%=request.getContextPath()%>/script/jquery.js" type="text/javascript"></script>
    <link href="<%=request.getContextPath()%>/farm/farm.css" rel="stylesheet" type="text/css"/>
    <script src="<%=request.getContextPath()%>/farm/farm.js" type="text/javascript"></script>
    <script type="text/javascript">
        function buyfarm(farmId){
            alert(farmId);
        }
    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="centerbar">
            <s:iterator value="farms" status="its">
                <div class="box" style="width:340px;float:left;margin:2px">
                    <div class="t">
                        <div class="t-t">
                            <a href="<%=request.getContextPath()%>/farm/farm/${farmId}">${farmName}</a>
                        </div>
                    </div>
                    <div class="content">
                        <s:if test="farmPictures.size()>0">
                            <s:iterator value="farmPictures" status="its">
                                <s:if test="#its.index<1">
                                    <a href="<%=request.getContextPath()%>/farm/farm/${farmId}">
                                        <img src="<%=request.getContextPath()%>/image?type=farm&picId=${picId}" alt="" style="float:left;margin:4px;"/>
                                    </a>
                                </s:if>
                            </s:iterator>
                        </s:if>
                        <s:else>
                            <a href="<%=request.getContextPath()%>/farm/farm/${farmId}">
                                <img src="<%=request.getContextPath()%>/images/noimage.jpg" alt="" style="float:left;margin:4px;"/>
                            </a>
                        </s:else>
                        农场名：<a href="<%=request.getContextPath()%>/land/land/${land.landId}">${land.landName}</a><br>
                        面&nbsp;&nbsp;&nbsp;积：${acreage}M<sup>2</sup><br>
                        农庄主：
                        <s:if test="member==null">
                            <img src="<%=request.getContextPath()%>/land/img/buy1.jpg" onclick="buyfarm(${farmId});" alt="购买农庄"/>
                        </s:if>
                        <s:else>
                            <a href="<%=request.getContextPath()%>/user/member/${member.memberId}">${member.realname}</a><br>
                        </s:else>
                    </div>
                    <div class="clear"></div>
                </div>
            </s:iterator>
            <div class="clear"></div>
            <div class="box">
                <s:include value="../paging.jsp"/>
                <form id="pageFrm" action="<%=request.getContextPath()%>/farm/farm/"
                      method="get" style="display:none;">
                    <s:hidden name="land.landId"/>
                    <s:hidden id="page.current" name="page.current" value="1"/>
                </form>
            </div>
            <div class="clear"></div>
        </div>
        <div id="rightbar">
            <s:include value="farm-right.jsp"/>

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
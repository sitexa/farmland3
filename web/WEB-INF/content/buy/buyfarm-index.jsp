<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>购买农庄</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/buy/buy.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/buy/buy.js"></script>
    <script type="text/javascript">
        $(function() {
            $(".img-text-list li").each(function() {
                $(this).hover(function() {
                    $(this).css("background", "#F4F4F4")
                }, function() {
                    $(this).css("background", "")
                })
            })
        })
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
            <div class="t2">
                <div class="t2-t">
                    农场列表
                </div>
            </div>
            <div class="box">
                <div class="img-text-list">
                    <ul>
                        <s:iterator value="lands" status="its">
                            <li>
                                <div class="tit"><a
                                        href="<%=request.getContextPath()%>/buy/land/${landId}/farm">${landName}</a>
                                </div>
                                <span class="blank2"></span>
                                <div class="body">
                                    <div class="left">
                                        <div class="img-box">
                                            <a href="<%=request.getContextPath()%>/buy/land/${landId}">
                                                <img src="<%=request.getContextPath()%>/image?type=land&picId=<s:property value='landPictures.iterator().next().picId'/>"/>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="content">
                                        <font class="bold">农场类型：</font>${landType}<br/>
                                        <font class="bold">农场地址：</font>${site.name} ${address}<br/>
                                        <font class="bold">农 场 主：</font><a
                                            href="<%=request.getContextPath()%>/user/member/${lord.memberId}"
                                            class="link2">${lord.realname}</a><br/>
                                            ${description}
                                    </div>
                                </div>
                            </li>
                        </s:iterator>
                    </ul>
                    <div class="clear"></div>
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
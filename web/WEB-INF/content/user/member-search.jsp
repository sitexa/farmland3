<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/farm/css/farm.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("frmSearch").submit();
        }
        function search() {
            document.getElementById("user.username").value = document.getElementById("username").value;
            document.getElementById("frmFarm").submit();
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
                <div class="title">
                    <div class="t-text">
                        <div class="tag-message"></div>
                        搜索会员
                    </div>
                </div>
                <div class="content">
                    <div class="input_text" style="margin-top:10px">
                        <s:form id="frmFarm" theme="simple" action="%{#request.contextPath}/user/member!search"
                                method="post">
                            <s:hidden id="page.current" name="page.current" value="1"/>
                            <s:hidden id="user.username" name="user.username"/>
                            <input type="text" id="username" value="${user.username}" class="map_searchText"/>
                            <input type="button" class="map_searchButton" value="搜索会员 " onclick="search()"/>
                        </s:form>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>

            <div class="box">
                <div class="title">
                    <div class="t-text">
                        <div class="tag-message"></div>
                        会员列表
                    </div>
                </div>
                <div class="content">
                    <div class="list">
                        <ul>
                            <s:iterator value="members" id="user">
                                <li>
                                    <a href="<%=request.getContextPath()%>/user/member/${userId}">
                                        <s:property value="username"/> </a>　
                                    <s:date name="RegisterDate" format="yyyy年M月d日 H时m分"/>
                                </li>
                            </s:iterator>
                        </ul>
                    </div>
                </div>
            </div>

            <div id="paging">
                <s:if test="page.total!=0">
                    共有${page.total}页结果:
                    <s:if test="page.current>6">
                        <a href="#" onclick="dos(1);">1</a>
                    </s:if>
                    <s:if test="page.current>7">
                        ...
                    </s:if>
                    <s:bean name="org.apache.struts2.util.Counter" id="counter">
                        <s:param name="first" value="page.current-5"/>
                        <s:param name="last" value="page.current+5"/>
                        <s:iterator status="its">
                            <s:if test="current>1 && current<=page.total+1">
                                <s:if test="#its.index==5">
                                    <s:property/>
                                </s:if>
                                <s:else>
                                    <a href="#" onclick="dos('<s:property/>');"> <s:property/></a>
                                </s:else>
                            </s:if>
                        </s:iterator>
                    </s:bean>
                    <s:if test="page.total>page.current+6">
                        ...
                    </s:if>
                    <s:if test="page.total>page.current+5">
                        <a href="#" onclick="dos('<s:property value="page.total"/>');"><s:property
                                value="page.total"/></a>
                    </s:if>
                </s:if>
            </div>
            <s:form id="frmSearch" theme="simple" action="%{#request.contextPath}/user/member!search" method="get"
                    cssStyle="display: none">
                <s:hidden id="page.current" name="page.current" value="1"/>
            </s:form>
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
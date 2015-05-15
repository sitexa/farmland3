<%--
  User: xnpeng
  Date: 2009-5-4
  Time: 7:48:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <s:text name="root.title">
            <s:param>${me.username}</s:param>
        </s:text>
    </title>
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("frmSearch").submit();
        }
    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../header.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar"><s:include value="user-menu.jsp"/></div>
        <div id="centerbar">
            <div class="box">
                <div class="title">用户管理</div>
                <div class="content">
                    <div id="search-div">
                        <s:form theme="simple" id="frmSearch"
                                action="%{#request.contextPath}/admin/user/user-admin!search" onsubmit="dos(1)">
                            <s:hidden id="page.current" name="page.current" value="1"/>
                            <s:textfield id="searchWord" name="searchWord"/>
                            <s:submit value="搜索" cssClass="btn-flat"/>
                        </s:form>
                    </div>
                    <div id="paging">
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
                    </div>
                    <div class="box">
                        <table>
                            <tr>
                                <th>用户号</th>
                                <th>用户名</th>
                                <th>电子邮件</th>
                                <th>确认状态</th>
                                <th>注册日期</th>
                                <th>登录日期</th>
                                <th>登录地址</th>
                                <th>登录次数</th>
                                <th>社区</th>
                                <th>操作</th>
                            </tr>
                            <s:if test="userList.size>0">
                                <s:iterator value="userList" status="its">
                                    <tr class='<s:if test="#its.odd">odd</s:if>'>
                                        <td><s:property value="userId"/></td>
                                        <td><s:property value="username"/></td>
                                        <td><s:property value="email"/></td>
                                        <td><s:property value="emailConfirmed"/></td>
                                        <td><s:date name="registerDate" format="yyyy-MM-dd"/></td>
                                        <td><s:date name="loginDate" format="yyyy-MM-dd"/></td>
                                        <td><s:property value="loginAddress"/></td>
                                        <td><s:property value="loginTimes"/></td>
                                        <td>
                                            <s:if test="member!=null&&member.site!=null">
                                                <a href="<%=request.getContextPath()%>/site/site/${member.site.siteId}"
                                                   target="_default">
                                                    <s:property value="member.site.name"/>
                                                </a>
                                            </s:if>
                                        </td>
                                        <td nowrap>
                                            <a href="<%=request.getContextPath()%>/admin/user/user-admin/${userId}">查看</a>|
                                            <a href="<%=request.getContextPath()%>/admin/user/user-admin/${userId}/edit">编辑</a>|
                                            <a href="<%=request.getContextPath()%>/admin/user/user-admin/${userId}/delete">删除</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../footer.jsp"/>
    </div>
</div>
</body>
</html>
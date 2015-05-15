<%--
  User: xnpeng
  Date: 2009-5-5
  Time: 10:44:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/site.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function doDelete(id) {
            if (!confirm("确定删除?")) return;
            var f = document.getElementById("frmMembers");
            f.action = "<%=request.getContextPath()%>/site/site-member/${site.siteId}/destroy";
            f.siteMemberId.value = id;
            f.submit();
        }
        function doSave(id) {
            var f = document.getElementById("frmMembers");
            f.action = "<%=request.getContextPath()%>/site/site-member/${site.siteId}/update";
            f.siteMemberId.value = id;
            f.submit();
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
                <div class="title1">管理社区会员</div>
                <div class="content">
                    <s:form theme="simple" id="frmMembers" name="frmMembers" action="%{#request.contextPath}/site/site-member/%{site.siteId}">
                        <s:hidden name="_method"/>
                        <s:hidden id="siteMemberId" name="siteMemberId"/>
                        <table class="table1">
                            <thead>
								<tr>
		                            <th>姓名</th>
		                            <th>加入时间</th>
		                            <th>类型</th>
		                            <th>备注</th>
		                            <th>启用/禁用</th>
		                            <th>操作</th>
								</tr>
                            </thead>
							<tbody>
                            <s:iterator value="siteMembers">
                                <tr>
                                    <s:hidden name="siteMembers(%{id}).id" value="%{id}"/>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/user/member/${member.memberId}/show">
                                            <s:property value="%{member.realname}"/></a>
                                    </td>
                                    <td>
                                        <s:date name="joinDate" format="yy年M月d日"/>
                                    </td>
                                    <td>
                                        <s:property value="%{typeName}"/>
                                    </td>
                                    <td>
                                        <s:textfield name="siteMembers(%{id}).remark" value="%{remark}"/>
                                    </td>
                                    <td>
                                        <s:select list="statusMap" listKey="id" listValue="name" name="siteMembers(%{id}).status" value="%{status}"/>
                                    </td>
                                    <td>
                                        <a href="#" onclick="doDelete('${id}');">删除</a> <a href="#" onclick="doSave('${id}');">保存</a>
                                    </td>
                                </tr>
                            </s:iterator>
							</tbody>
                        </table>
                    </s:form>
                </div>
            </div>
            <div class="box">
                <div class="title1">增加新会员</div>
                <div class="content">
                    <s:form theme="simple" id="frmMember" name="frmMember" action="%{request.contextPath}/site/site-member/%{site.siteId}">
                   	会员编号:<s:textfield name="siteMember.member.memberId"/><br/>
                   	 备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<s:textfield name="siteMember.remark" cssStyle="width:200px"/><br/><br/>
                        <s:submit cssClass="formbutton" method="create" value="提交"/>
                    </s:form>
                </div>
            </div>
            <s:actionmessage/>
            <s:actionerror/>
        </div>
        <div id="rightbar" class="rightcol" >
            <s:include value="site-right.jsp"/>
        </div>
    </div>
    <div class="clear"></div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
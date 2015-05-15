<%--
  User: xnpeng
  Date: 2009-5-4
  Time: 14:29:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <sx:head/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <s:text name="root.title">
            <s:param>${member.realname}</s:param>
        </s:text>
    </title>
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function selectSite(v) {
            var url = "<%=request.getContextPath()%>/site/selector";
            var feature = "dialogWidth:380px;dialogHeight:250px;Status:0;resizable:0;help:0";
            var result = window.showModalDialog(url, null, feature);
            if (result != null && result != "") {
                var arr = result.split(";");
                document.getElementById(v).value = arr[0];
                document.getElementById("siteName").value = arr[1];
            }
        }
    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../admin-header.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="user-menu.jsp"/>
        </div>
        <div id="centerbar">
            <div class="box">
                <div class="title">编辑信息</div>
                <div class="content">
                    <s:form theme="simple" id="frmMember"
                            action="%{#request.contextPath}/admin/user/member-admin/%{member.memberId}/update"
                            method="post">
                        <s:hidden name="user.userId"/>
                        <s:hidden id="memberId" name="member.memberId"/>
                        真实姓名:<s:textfield name="member.realname"/><br/>
                        妮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:<s:textfield name="member.nickname"/><br/>
                        性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:
                        <s:radio name="member.gender" value="%{member.gender}" list="#{'1':'男','2':'女','0':'保密'}"/><br/>
                        手机号码:<s:textfield name="member.mobilephone"/><br/>
                        固定电话:<s:textfield name="member.telephone"/><br/>
                        出生日期:<sx:datetimepicker name="member.birthday" value="%{member.birthday}"
                                                displayFormat="yyyy-MM-dd"/><br/>
                        会员类型:<s:select list="memberTypeList" listKey="typeId" listValue="name"
                                       name="member.type.typeId"/><br/>
                        社区位置:
                        <s:if test="member.site.parent.parent.parent.parent!=null">
                            <a href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.parent.parent.siteId}"
                               target="_blank">
                                <s:property value="member.site.parent.parent.parent.parent.name"/>
                            </a> >
                        </s:if>
                        <s:if test="member.site.parent.parent.parent!=null">
                            <a href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.parent.siteId}"
                               target="_blank">
                                <s:property value="member.site.parent.parent.parent.name"/>
                            </a> >
                        </s:if>
                        <s:if test="member.site.parent.parent!=null">
                            <a href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.siteId}"
                               target="_blank">
                                <s:property value="member.site.parent.parent.name"/>
                            </a> >
                        </s:if>
                        <s:if test="member.site.parent!=null">
                            <a href="<%=request.getContextPath()%>/site/site/${member.site.parent.siteId}"
                               target="_blank">
                                <s:property value="member.site.parent.name"/>
                            </a> >
                        </s:if>
                        <s:hidden id="siteId" readonly="true" name="member.site.siteId" cssStyle="width:60px"/>
                        <br>社区名称:<s:textfield id="siteName" readonly="true" name="member.site.name"/>
                        <a id="selector" onclick="selectSite('siteId');">
                            <span style="color:blue;text-decoration:underline;">选择社区</span>
                        </a>
                        <br>更新时间:<s:date name="member.updateDate" format="yy年M月d日H时m分"/>
                        <br>会员介绍(500字以内):<br><s:textarea name="member.introduction" cols="45" rows="6"/>
                        <br>设置明星:<s:select list="starBag" listKey="id" listValue="name" name="member.starTag"/><br/>
                        <br>状态审核:<s:textfield name="member.status" size="2"/><br>
                        <br/><s:submit method="update" value="保存修改"/>
                    </s:form>
                </div>
            </div>
            <s:actionmessage cssClass="message"/>
            <s:actionerror cssClass="error"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../footer.jsp"/>
    </div>
</div>
</body>
</html>
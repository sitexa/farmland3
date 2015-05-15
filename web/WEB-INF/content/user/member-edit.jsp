<%--
  User: xnpeng
  Date: 2009-4-4
  Time: 22:14:49
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <sx:head/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
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
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="title2">修改会员资料</div>
                <div class="content">
                    <div class="box" style="width:35%;float:right;">
                        <div class="content">
                            	为什么要填写会员资料?
                            <ul>
                                <li>
                                   	注册会员是免费的.
                                </li>
                                <li>
                                   	所有功能都是基于会员的.会员才有"社的家","我的家",和"发布信息"的权限.
                                </li>
                                <li>
                                  	保证用户的真实性.社的家不允许虚拟用户发表文章,参与讨论.
                                </li>
                                <li>
                                   	社的家是一个实名社区,所有用户都是您身边的人,有您的邻居,您的同事,您的同学.所以请填写真实资料.
                                   	网站不会公开您的电话和邮件等重要信息.
                                   	社区管理员就在您的身边,他/她可能以适当的方式核实您的资料,删除虚假资料注册的用户.
                                </li>
                            </ul>
                        </div>
                    </div>
                    <s:form theme="simple" id="frmMember"
                            action="%{#request.contextPath}/user/member/%{member.memberId}/update" method="post">
                        <s:hidden name="user.userId"/>
                        <s:hidden id="memberId" name="member.memberId"/>
						<s:hidden name="member.type.typeId"/>
                       	真实姓名：<s:textfield name="member.realname"/><span style="color:red;font-size:18px"> *</span><br/>
                       	妮　　称：<s:textfield name="member.nickname"/><br/>
                       	性　　别：<s:radio name="member.gender" value="%{member.gender}" list="#{'1':'男','2':'女','0':'保密'}"/><br/>
                       	手机号码：<s:textfield name="member.mobilephone"/><br/>
                       	固定电话：<s:textfield name="member.telephone"/><br/>
                       	出生日期：<sx:datetimepicker name="member.birthday" value="%{member.birthday}" displayFormat="yyyy-MM-dd"/><br/>
                       	会员类型：<s:property value="member.type.name"/><br/>
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
                        <br>社区名称:
                        <s:if test="member.site!=null">
                            <s:property value="member.site.name"/>
                        </s:if>
                        <s:if test="member.site==null">
                            <s:textfield id="siteName" readonly="true" name="member.site.name"/>
                            <a id="selector" onclick="selectSite('siteId');"><span style="color:red;">*</span>
                                <span style="color:blue;text-decoration:underline;">选择社区</span>
                            </a>

                            <div class="box" style="background:#ffc">
                                <div class="content">
                                    您选择的社区必须是乡镇及以下级别的社区,选定以后,将不能改变.如果没有合适的社区,您可以创建自己的社区.
                                </div>
                            </div>
                        </s:if>
                        <br>会员介绍(500字以内):<s:textarea name="member.introduction" cols="45" rows="6"/>
                        <br><s:submit cssClass="formbutton" method="update" value="保存修改"/>
                    </s:form>
                </div>
            </div>
            <s:actionmessage cssClass="message"/>
            <s:actionerror cssClass="error"/>
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
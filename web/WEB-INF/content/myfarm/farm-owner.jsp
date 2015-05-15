<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <style type="text/css">
        table {
            width: 100%;
            background: #ccccff;
            border: 0;
            cellpadding: 0;
            cellspacing: 0;
            text-align: center;
        }

        th {
            background: #ccccff
        }

        td {
            background: #ffffff;
        }
    </style>
    <script type="text/javascript">
        function doDelete(id) {
            if (!confirm("确定删除?")) return;
            var f = document.getElementById("frmOwners");
            f.action = "<%=request.getContextPath()%>/myfarm/farm-owner/${farm.farmId}/destroy";
            f.ownerId.value = id;
            f.submit();
        }
        function doSave(id) {
            var f = document.getElementById("frmOwners");
            f.action = "<%=request.getContextPath()%>/myfarm/farm-owner/${farm.farmId}/update";
            f.ownerId.value = id;
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
            <s:include value="farm-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="t">
                    <div class="t-t">
                        管理农庄主
                    </div>
                </div>
                <div class="content">
                    <div class="content">
                        <s:form theme="simple" id="frmOwners" name="frmOwners" action="%{#request.contextPath}/myfarm/farm-owner/%{farm.farmId}">
                            <s:hidden name="_method"/>
                            <s:hidden id="ownerId" name="owner.ownerId"/>
                            <table>
                                <thead>
                                <th>姓名</th>
                                <th>加入时间</th>
                                <th>退出时间</th>
                                <th>备注</th>
                                <th>操作</th>
                                </thead>
                                <s:iterator value="owners">
                                    <tr>
                                        <s:hidden name="owners(%{ownerId}).ownerId" value="%{ownerId}"/>
                                        <td>
                                            <a href="<%=request.getContextPath()%>/user/member/${member.memberId}/show">
                                                <s:property value="%{member.realname}"/></a>
                                        </td>
                                        <td>
                                            <s:date name="joinDate" format="yy年M月d日"/>
                                        </td>
                                        <td>
                                            <s:date name="quitDate" format="yy年M月d日"/>
                                        </td>
                                        <td>
                                            <s:property value="%{remark}"/>
                                        </td>
                                        <td>
                                            <a href="#" onclick="doDelete('${ownerId}');">删除</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </table>
                        </s:form>
                    </div>
                    <div class="box">
                        <div class="t">
                            <div class="t-t">
                                增加新庄主
                            </div>
                        </div>
                        <div class="content">
                            <s:form theme="simple" id="frmOwner" name="frmOwner" action="%{request.contextPath}/myfarm/farm-owner/%{farm.farmId}">
                                会员编号:<s:textfield name="owner.member.memberId"/><br/>
                                备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<s:textfield name="owner.remark" cssStyle="width:200px"/><br/><br/>
                                <s:submit cssClass="formbutton" method="create" value="提交"/>
                            </s:form>
                        </div>
                    </div>
                    <s:actionmessage/>
                    <s:actionerror/>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="farm-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
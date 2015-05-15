<%--
  User: xnpeng
  Date: 2009-5-3
  Time: 23:47:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>农庄管理</title>
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
    <script type="text/javascript">
        $("tbody tr:odd").each(function() {
            $(this).addClass("odd")
        });

    </script>
</head>
<body>
<div id="container">

    <div id="header">
        <s:include value="../../h.jsp"/>
    </div>

    <div id="mainwrapper">
        <div id="leftbar">
            <s:include value="work-menu.jsp"/>
        </div>
        <div id="centerbar">
            <div class="box">
                <a href="#">${farm.farmName}</a> 【<a href="#">${farm.member.realname}</a>】
            </div>
            <table id="cropses" cellpadding="2" cellspacing="2" width="100%">
                <thead>
                <tr style="background-color:#bbb;">
                    <th>作物名称</th>
                    <th>作物面积</th>
                    <th>种植时间</th>
                    <th>作物状态</th>
                </tr>
                <thead>
                <tbody>
                <s:if test="cropses.size>0">
                    <s:iterator value="cropses" status="its">
                        <tr id="${seed.seedId}">
                            <td>
                                <a href="#">${seed.seedName}</a>
                            </td>
                            <td>${seedNumber}</td>
                            <td><s:date name="startDate" format="yyyy/MM/dd"/></td>
                            <td>
                                <s:textfield id="cropsRemark" name="crops.remark" size="50"/>
                                <input type="button" value="save" onclick="saveRemark();">
                            </td>
                        </tr>
                    </s:iterator>
                </s:if>
                </tbody>
            </table>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../../f.jsp"/>
    </div>
</div>
</body>
</html>
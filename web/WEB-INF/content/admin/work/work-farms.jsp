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
    <style type="text/css">
        tr {
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        $(function() {
            $("tbody tr").each(function() {
                $(this).click(function() {
                    self.location = "<%=request.getContextPath()%>/admin/work/work!tasks?farmId=" + this.id;
                })
            })

            $("tbody tr:odd").each(function() {
                $(this).addClass("odd")
            })
            $("tbody tr").hover(
                    function() {
                        $(this).addClass("over");
                    },
                    function() {
                        $(this).removeClass("over");
                    }
                    )
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
            <table id="farms" cellpadding="2" cellspacing="2" width="100%">
                <thead>
                <th width="100">农庄编号</th>
                <th width="230">农庄名称</th>
                <th width="150">农庄主</th>
                <th width="100">农庄面积</th>
                <th width="150">起租时间</th>
                </thead>
                <tbody>
                <s:if test="farms.size>0">
                    <s:iterator value="farms" status="its">
                        <tr id="${farmId}">
                            <td>${farmNo}</td>
                            <td>${farmName}</td>
                            <td>${member.realname}</td>
                            <td>${acreage}</td>
                            <td><s:date name="rentDate" format="yyyy/MM/dd"/></td>
                        </tr>
                    </s:iterator>
                </s:if>
                </tbody>
            </table>
            <div>
                <s:include value="../../paging.jsp"/>
                <script type="text/javascript">
                    function dos(page) {
                        document.getElementById("page.current").value = parseInt(page);
                        document.getElementById("pageFrm").submit();
                    }
                </script>
                <s:form id="pageFrm" theme="simple" action="%{#request.contextPath}/admin/work/work!farms"
                        method="get" cssStyle="display: none">
                    <s:hidden name="landId" value="%{#request.land.landId}"/>
                    <s:hidden id="page.current" name="page.current" value="1"/>
                </s:form>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../../f.jsp"/>
    </div>
</div>
</body>
</html>
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
                    self.location = "<%=request.getContextPath()%>/admin/work/work!farms?landId=" + this.id;
                })
            });

            $("tbody tr:odd").each(function() {
                $(this).addClass("odd")
            });
            $("tbody tr").hover(
                    function() {
                        $(this).addClass("over");
                    },
                    function() {
                        $(this).removeClass("over");
                    }
                    )
        })
        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("pageFarm").submit();
        }
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
                <table id="lands" cellpadding="2" cellspacing="2" width="100%">
                    <thead>
                    <th nowrap>农场名称</th>
                    <th nowrap>农场类型</th>
                    <th nowrap>农场地址</th>
                    <th nowrap>开始时间</th>
                    </thead>
                    <tbody>
                    <s:if test="lands.size>0">
                        <s:iterator value="lands" status="its">
                            <tr id="${landId}">
                                <td nowrap>${landName}</td>
                                <td nowrap>${landType}</td>
                                <td nowrap>${address}</td>
                                <td nowrap><s:date name="startDate" format="yyyy-MM-dd"/></td>
                            </tr>
                        </s:iterator>
                    </s:if>
                    </tbody>
                </table>
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
            <s:form id="pageFarm" theme="simple" action="%{#request.contextPath}/admin/work/work" method="get"
                    cssStyle="display: none">
                <s:hidden id="page.current" name="page.current" value="1"/>
            </s:form>
            <div style="clear:both"></div>
        </div>
        <div id="rightbar"></div>
        <div style="clear:both"></div>
    </div>
    <div id="footer">
        <s:include value="../../f.jsp"/>
    </div>
</div>
</body>
</html>
<%--
  User: xnpeng
  Date: 2009-5-9
  Time: 10:59:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/site.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("frmSearch").submit();
        }
        function moveSite() {
            getIdList();
            if (document.frmPost.toSite.value == "")return;
            if (confirm("确定移动社区?")) {
                var f = document.getElementById("frmPost");
                f.type.value = "moveSite";
                f.submit();
            }
        }

        function moveCate() {
            getIdList();
            if (confirm("确定移动栏目?")) {
                var f = document.getElementById("frmPost");
                f.type.value = "moveCate";
                f.submit();
            }
        }

        function doDelete() {
            getIdList();
            if (document.frmPost.idList.value == "")return;
            if (confirm("确定删除?")) {
                var f = document.getElementById("frmPost");
                f.action = "<%=request.getContextPath()%>/site/site-post/${site.siteId}/destroy";
                f.submit();
            }
        }
        function doTop() {
            getIdList();
            if (document.frmPost.idList.value == "")return;
            var f = document.getElementById("frmPost");
            f.type.value = "top";
            f.submit();
        }
        function doElite() {
            getIdList();
            if (document.frmPost.idList.value == "")return;
            var f = document.getElementById("frmPost");
            f.type.value = "elite";
            f.submit();
        }
        function doBan() {
            getIdList();
            if (document.frmPost.idList.value == "")return;
            var f = document.getElementById("frmPost");
            f.type.value = "ban";
            f.submit();
        }
        function doPublish() {
            getIdList();
            if (document.frmPost.idList.value == "")return;
            var f = document.getElementById("frmPost");
            f.type.value = "publish";
            f.submit();
        }
        function selectAll() {
            var row = document.frmPost.cbid;
            for (var i = 0; i < row.length; i++) {
                row[i].checked = true;
            }
        }
        function unSelectAll() {
            var row = document.frmPost.cbid;
            for (var i = 0; i < row.length; i++) {
                row[i].checked = false;
            }
        }
        function reverse() {
            var row = document.frmPost.cbid;
            for (var i = 0; i < row.length; i++) {
                row[i].checked = !row[i].checked;
            }
        }
        function getIdList() {
            var aid = "";
            var row = document.frmPost.cbid;
            for (var i = 0; i < row.length; i++) {
                if (row[i].checked) {
                    aid += row[i].value + ",";
                }
            }
            document.frmPost.idList.value = aid;
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
        <div id="centerbar" class="centercol" style="width:800px">
            <div class="box">
                <div class="title1">社区文章管理</div>
                <div class="content">
                <s:form id="frmPost" name="frmPost" action="%{#request.contextPath}/site/site-post/%{site.siteId}/update" theme="simple">
                    <s:hidden id="idList" name="idList"/>
                    <s:hidden id="type" name="type"/>
                    <table class="table1">
						<thead>
	                        <tr>
	                            <th>选择</th>
	                            <th>标题</th>
	                            <th>作者</th>
	                            <th>栏目</th>
	                            <th>时间</th>
	                            <th>点击</th>
	                            <th>审核</th>
	                            <th>审核人</th>
	                            <th>顶|精|状态</th>
	                        </tr>
						</thead>
						<tbody>
                            <s:iterator value="posts" status="its">
                                <tr class="<s:if test="#its.even">even</s:if><s:else>odd</s:else>">
                                    <td>
                                        <input type="checkbox" id="cbid" name="cbid" value="${id}"/>
                                    </td>
                                    <td width="40%">
                                        <a href="<%=request.getContextPath()%>/post/${category.cateId}/${id}/show" target="_blank">
                                            <s:property value="name"/>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/user/member/${creator.memberId}/show" target="_blank">
                                            <s:property value="creator.realname"/>
                                        </a>
                                    </td>
                                    <td>
                                        <s:property value="category.name"/>
                                    </td>
                                    <td>
                                        <s:date name="createDate" format="MM.dd hh:mm"/>
                                    </td>
                                    <td>
                                        <s:property value="vwCnt"/>
                                    </td>
                                    <td>
                                        <s:property value="audit"/>
                                    </td>
                                    <td>
                                        <s:property value="auditor.realname"/>
                                    </td>
                                    <td>
                                        <s:property value="topTag"/> | <s:property value="eliteTag"/> | <s:property value="statusName"/>
                                    </td>
                                </tr>
                            </s:iterator>
                            <tr>
                                <td colspan="10">
                                    <a href="#" onclick="selectAll();">全选</a> |
                                    <a href="#" onclick="unSelectAll();">全不选</a> |
                                    <a href="#" onclick="reverse();">反选</a> ||
                                    <a href="#" onclick="doTop();">置顶/反置</a> |
                                    <a href="#" onclick="doElite();">置精/反置</a> |
                                    <a href="#" onclick="doBan();">禁发</a> |
                                    <a href="#" onclick="doPublish();">解禁</a> |
                                    <a href="#" onclick="doDelete();">删除</a> ||
                                    <a href="#" onclick="moveCate();">移动栏目</a>
                                    <s:select list="categories" listKey="cateId" listValue="name" id="toCate" name="toCate"/> |
                                    <a href="#" onclick="moveSite();">移动社区</a>
                                    <input type="text" id="toSite" name="toSite" style="width:75px;height:18px">
                                </td>
                            </tr>
						</tbody>
                    </table>
					</s:form>
                    <div id="paging">
                        <form id="frmSearch" name="frmSearch" style="padding:0;margin:0"
                              action="<%=request.getContextPath()%>/site/site-post/${site.siteId}/edit">
                            <s:hidden id="page.current" name="page.current" value=""/>
                        </form>
                        <s:if test="page.total!=0">
                            共有${page.total}页结果:
                            <s:if test="page.current>6">
                                <a href="#" onclick="dose(1);">1</a>
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
                                <a href="#" onclick="dos('<s:property value="page.total"/>');"><s:property value="page.total"/></a>
                            </s:if>
                        </s:if>
                    </div>
                </div>
            </div>
        </div>
        <s:actionmessage/>
        <s:actionerror/>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
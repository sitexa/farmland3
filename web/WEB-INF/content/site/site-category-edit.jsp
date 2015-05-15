<%--
  User: xnpeng
  Date: 2009-5-5
  Time: 8:03:25
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
        function deleteCategory(cateId) {
            var f = document.getElementById("frmCategoryList");
            f.cateId.value = cateId;
            f._method.value = "delete";
            f.submit();
        }
        function saveCategory(cateId) {
            var f = document.getElementById("frmCategoryList");
            f.cateId.value = cateId;
            f._method.value = "update";
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
                <div class="title1">编辑社区栏目</div>
                <div class="content">
                    <s:form theme="simple" id="frmCategoryList" name="frmCategoryList"
                            action="%{#request.contextPath}/site/site-category/%{site.siteId}/update">
                        <s:hidden id="_method" name="_method"/>
                        <s:hidden id="cateId" name="cateId"/>
                        <table class="table1">
							<thead>
	                            <tr>
	                                <th>名称</th>
	                                <th>描述</th>
	                                <th>上级</th>
	                                <th>顺序</th>
	                                <th>启用</th>
	                                <th>操作</th>
	                            </tr>
							</thead>
							<tbody>
                            <s:iterator value="siteCategories">
                                <s:hidden name="siteCategories(%{cateId}).site.siteId"/>
                                <s:hidden name="siteCategories(%{cateId}).parent.cateId"/>
                                <s:hidden name="siteCategories(%{cateId}).type"/>
                                <s:hidden name="siteCategories(%{cateId}).cateId" value="%{cateId}"/>
                                <tr>
                                    <td>
                                        <s:textfield name="siteCategories(%{cateId}).name" value="%{name}" cssStyle="width:80px"/>
                                    </td>
                                    <td>
                                        <s:textfield name="siteCategories(%{cateId}).description" value="%{description}" cssStyle="width:200px"/>
                                    </td>
                                    <td>
                                        <s:textfield name="siteCategories(%{cateId}).parent.name" value="%{parent.name}" readonly="true" cssStyle="width:80px"/>
                                    </td>
                                    <td>
                                        <s:textfield name="siteCategories(%{cateId}).ord" value="%{ord}" cssStyle="width:20px"/>
                                    </td>
                                    <td>
                                        <s:textfield name="siteCategories(%{cateId}).status" value="%{status}" cssStyle="width:20px"/>
                                    </td>
                                    <td>
                                        <a href="#" onclick="saveCategory('%{cateId}');">保存</a> <a href="#" onclick="deleteCategory('%{cateId}');">删除</a>
                                    </td>
                                </tr>
                            </s:iterator>
							</tbody>
                        </table>
                        <s:submit cssClass="formbutton" method="update" value="保存修改"/>
                    </s:form>
                </div>
            </div>
            <div class="box">
                <div class="title1">增加新栏目</div>
                <div class="content">
                    <s:form theme="simple" id="frmCategory" name="frmCategory" action="%{request.contextPath}/site/site-category/%{site.siteId}/create">
			                        上级栏目:<s:select list="categories" listKey="cateId" listValue="name" name="category.parent.cateId"/><br/>
			                        栏目名称:<s:textfield name="category.name"/><br/>
			                        栏目描述:<s:textfield name="category.description" cssStyle="width:400px"/><br/>
			                        栏目顺序:<s:textfield name="category.ord" cssStyle="width:20px"/><br/><br/>
                        <s:submit cssClass="formbutton" method="create" value="提交"/>
                    </s:form>
                </div>
            </div>
            <s:actionerror/>
        </div>
        <div id="rightbar" class="rightcol" >
            <s:include value="site-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
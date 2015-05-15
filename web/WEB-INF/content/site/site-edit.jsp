<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
        <s:text name="root.title">
            <s:param>${site.name}</s:param>
        </s:text>
    </title>
    <link href="<%=request.getContextPath()%>/style/site.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function selectSite(id, name) {
            var url = "<%=request.getContextPath()%>/site/selector";
            var feature = "dialogWidth:380px;dialogHeight:250px;Status:0;resizable:0;help:0";
            var result = window.showModalDialog(url, null, feature);
            if (result != null && result != "") {
                var arr = result.split(";");
                document.getElementById(id).value = arr[0];
                document.getElementById(name).value = arr[1];
            }
        }
        function openMap() {
            var sUrl = "<%=request.getContextPath()%>/map";
            window.open(sUrl, "", 'width=800,height=600');
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
                <div class="title1">修改社区资料</div>
	            <div class="content">
	                <s:form theme="simple" id="frmBase" method="post" action="%{#request.contextPath}/site/site/%{site.siteId}">
                    	社区编号：<s:textfield id="siteId" readonly="true" name="site.siteId"/><br/>
                    	社区名称：<s:textfield name="site.name"/><br/>
                    	社区类型：<s:select name="site.type.typeId" list="siteTypeList" listKey="typeId" listValue="typeName"/><br/>
                    	上级编号：<s:textfield id="parentId" readonly="true" name="site.parent.siteId"/><br/>
                    	上级名称：<s:textfield id="parentName" readonly="true" name="site.parent.name"/>
	                    	<a href="#" onclick="selectSite('parentId','parentName');">点击选择</a><br/>
                    	纬　　度：<s:textfield name="site.latitude"/> <a href="#" onclick="openMap();">点击查地图</a><br/>
                    	经　　度：<s:textfield name="site.longitude"/><br/>
                    	缩放比例：<s:textfield name="site.zoom"/><br/>
						管  理  员：<s:select list="members" listKey="member.memberId" headerKey="-1" headerValue="请选择" listValue="member.realname" name="site.governor.memberId"/><br/>
                    	社区地址：<s:textfield name="site.address" cssStyle="width:300px;"/><br/>
                    	简要介绍(500字以内)：<br/><s:textarea name="site.introduction" cols="50" rows="4"/><br/>
                    	启用状态：<s:checkbox name="site.status" fieldValue="true" label="状态"/>(启用/停用)<br/>
                    	<s:submit cssClass="formbutton" method="update" value="保存修改"/>
	                </s:form>
	            </div>
	        </div>
	        <s:actionerror/>
	        <s:actionmessage/>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
        花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/site.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function selectSite(v) {
            var url = "<%=request.getContextPath()%>/site/selector";
            var feature = "dialogWidth:380px;dialogHeight:250px;Status:0;resizable:0;help:0";
            var result = window.showModalDialog(url, null, feature);
            if (result != null && result != "") {
                var arr = result.split(";");
                document.getElementById(v).value = arr[0];
                document.getElementById("parentName").value = arr[1];
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
	    <div id="leftbar">
	    	<s:include value="site-menu.jsp"/>
		</div>
	    <div id="centerbar">
	        <s:if test="profile!=null">
	            <div class="box">
	                <div class="title">
	                    <ul>
	                        <li class="title_left9"></li>
	                        <li class=title_text>创建社区</li>
	                        <li class="title_right"></li>
	                    </ul>
	                    <div class="clear"></div>
	                </div>
	                <div class="content">
	                    <s:form id="frmSite" theme="simple" action="%{#request.contextPath}/site/site">
	                        <s:hidden id="parentId" name="site.parent.siteId" value=""/>
	                        社区名称:<s:textfield name="site.name" value=""/><br/>
	                        <%--社区类型:<s:select disabled="true" name="site.type.typeId" list="siteTypeList" listKey="typeId" listValue="typeName"/><br/>--%>
	                        上级社区:<s:textfield id="parentName" readonly="true" name="site.parent.name" value=""/>
	                        <a href="#" onclick="selectSite('parentId','parentName');">点击选择</a><br/>
	                        纬&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度:<s:textfield name="site.latitude" value=""/> <a href="#" onclick="openMap();">点击查地图</a><br/>
	                        经&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度:<s:textfield name="site.longitude" value=""/><br/>
	                        缩放比例:<s:textfield name="site.zoom" value=""/><br/>
	                        管&nbsp;&nbsp;理&nbsp;员:<s:property value="profile.realname"/><br/>
	                        社区地址:<s:textfield name="site.address" cssStyle="width:300px;" value=""/><br/>
	                        简要介绍(500字以内):<br/><s:textarea name="site.introduction" value="" cols="50" rows="4"/><br/>
	                        <s:submit cssClass="formbutton" value="提交" method="create"/>
	                    </s:form>
	                </div>
	            </div>
	        </s:if>
	        <s:actionerror cssClass="error"/>
	        <s:actionmessage/>
	    </div>
	    <div id="rightbar"><s:include value="site-right.jsp"/></div>
	</div>
	<div class="clear"></div>
	<div id="footer">
	    <s:include value="../f.jsp"/>
	</div>
</div>
</body>
</html>
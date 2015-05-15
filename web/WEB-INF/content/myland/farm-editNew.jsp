<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场--新建农庄</title>
    <link href="<%=request.getContextPath()%>/myland/land.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript">
        tinyMCE.init({
            mode : "textareas",
            theme : "advanced",
            plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",

            theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,formatselect,fontselect,fontsizeselect,|,cleanup,code,fullscreen",
            theme_advanced_buttons2 : "",
            theme_advanced_buttons3 : "",
            theme_advanced_toolbar_location : "top",
            theme_advanced_toolbar_align : "left",
            theme_advanced_statusbar_location : "bottom",
            theme_advanced_resizing : true,

            content_css : "css/content.css",

            template_external_list_url : "lists/template_list.js",
            external_link_list_url : "lists/link_list.js",
            external_image_list_url : "lists/image_list.js",
            media_external_list_url : "lists/media_list.js",

            template_replace_values : {
                username : "Some User",
                staffid : "991234"
            }
        });
        
        function testForm(){
        	var msg = "";
        	var farmNo = $.trim($("#farmNo").val());
        	if(farmNo==""){
        		msg += "农庄编号不能为空，请您输入农庄编号;";
        	}
        	var farmName = $.trim($("#farmName").val());
        	if(farmName==""){
        		msg += "\n农庄名称不能为空，请您输入农庄名称。";
        	}   
        	if(msg!=""){
        		alert(msg);
        		return false;
        	}else{
        		return true;
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
            <s:include value="land-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
				<div class="t">
					<div class="t-t">
						新建农庄
					</div>
				</div>
                <div class="content">
                    <s:form action="%{#request.contextPath}/myland/farm/" id="operFrm" method="post">
                        <s:hidden name="land.landId"/>
                        <table>
                            <tr>
                                <td>农庄编号</td>
                                <td><s:textfield id="farmNo" name="farm.farmNo"/></td>
                            </tr>
                            <tr>
                                <td>农庄名称</td>
                                <td><s:textfield id="farmName" name="farm.farmName"/></td>
                            </tr>
                            <tr>
                                <td>农庄面积</td>
                                <td><s:textfield id="acreage" name="farm.acreage"/></td>
                            </tr>
                            <tr>
                                <td>农庄坐标</td>
                                <td><s:textfield name="farm.coordination"/></td>
                            </tr>
                            <tr>
                                <td>农庄租金</td>
                                <td><s:textfield name="farm.rentPrice"/></td>
                            </tr>
                            <tr>
                                <td>耕种状态</td>
                                <td><s:textfield name="farm.PloughStatus"/></td>
                            </tr>
                            <tr>
                                <td>备 注</td>
                                <td><s:textarea name="farm.remark" cssStyle="width:500px" rows="5"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><s:submit cssClass="formbutton" value="创 建" method="create" onclick="return testForm()"/></td>
                            </tr>
                        </table>
                    </s:form>
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
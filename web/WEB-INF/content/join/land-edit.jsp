<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <sx:head/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript">
        tinyMCE.init({
            mode : "textareas",
            theme : "simple",
            plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",

            theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
            theme_advanced_buttons2 : "search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,image,cleanup,help,code,|,preview,|,forecolor,backcolor",
            theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,fullscreen",
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

        function getLatLng() {
            var url = "<%=request.getContextPath()%>/land/mapper";
            var siteId = document.getElementById("siteId").value;
            var landId = document.getElementById("landId").value;
            url = url + "?siteId=" + siteId + "&landId=" + landId;
            var feature = "dialogWidth:500px;dialogHeight:400px;Status:0;resizable:0;help:0";
            var result = window.showModalDialog(url, null, feature);
            if (result != null && result != "") {
                var arr = result.split(";");
                document.getElementById("latitude").value = arr[0];
                document.getElementById("longitude").value = arr[1];
            }
        }

        function testForm() {
            var landName = $.trim($("#landName").val());
            if (landName == "") {
                alert("农场名称不能为空，请您输入农场名称。");
                return false;
            }
            var address = $.trim($("#address").val());
            if (address == "") {
                alert("农场地址不能为空，请您输入农场地址。");
                return false;
            }

            return true;
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
            <div class="t">
                <div class="t-t">
                    修改农场资料
                </div>
            </div>
            <div class="box">
                <s:form name="%{#request.contextPath}/join/land/%{land.landId}" theme="simple" method="post">
                    <s:hidden id="landId" name="land.landId"/>
                    <s:hidden name="land.lord.memberId"/>
                    <table border="0">
                        <tr>
                            <td>农场名称</td>
                            <td><s:textfield id="landName" name="land.landName" size="40"/></td>
                        </tr>
                        <tr>
                            <td>公司名称</td>
                            <td><s:textfield id="landLord" name="land.landLord" size="60"/></td>
                        </tr>
                        <tr>
                            <td>农场地址</td>
                            <td><s:textfield id="address" name="land.address" size="40"/></td>
                        </tr>
                        <tr>
                            <td>农场类型</td>
                            <td><s:select list="landTypes" name="land.landType"/></td>
                        </tr>
                        <tr>
                            <td>所在社区</td>
                            <td><s:hidden id="siteId" readonly="true" name="land.site.siteId" cssStyle="width:60px"/>
                                <s:textfield id="siteName" readonly="true" name="land.site.name"/>
                                 <input type="button" value="选择社区" onclick="selectSite('siteId');"/>
                            </td>
                        </tr>
                        <tr>
                            <td>纬度-经度</td>
                            <td>
                                <s:textfield id="latitude" name="land.latitude" size="15"/>-<s:textfield id="longitude" name="land.longitude" size="15"/>
                                <input type="button" value="地图定位" onclick="getLatLng();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>开始日期</td>
                            <td><sx:datetimepicker name="land.startDate" value="%{land.startDate}" displayFormat="yyyy-MM-dd"/></td>
                        </tr>
                        <tr>
                            <td>结束日期</td>
                            <td><sx:datetimepicker name="land.endDate" value="%{land.endDate}" displayFormat="yyyy-MM-dd"/></td>
                        </tr>
                        <tr>
                            <td>农场简介</td>
                            <td><s:textarea name="land.description" cssStyle="width:500px" rows="5"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><s:submit value="保存" method="update" onclick="return testForm()"/></td>
                        </tr>
                    </table>
                </s:form>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="land-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
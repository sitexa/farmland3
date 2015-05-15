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
    <link href="<%=request.getContextPath()%>/myland/land.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
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
            var siteName = $.trim($("#siteName").val());
            if (siteName == "") {
                alert("所在社区不能为空，请您选择所在社区。");
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
            <div class="box">
                <div class="t">
                    <div class="t-t">
                        创建农场
                    </div>
                </div>
                <s:form name="%{#request.contextPath}/myland/land" theme="simple" method="post">
                    <s:hidden id="landId" name="land.landId"/>
                    <s:hidden name="lord.memberId"/>
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
                            <td><sx:datetimepicker name="land.startDate" displayFormat="yyyy-MM-dd"/></td>
                        </tr>
                        <tr>
                            <td>结束日期</td>
                            <td><sx:datetimepicker name="land.endDate" displayFormat="yyyy-MM-dd"/></td>
                        </tr>
                        <tr>
                            <td>农场简介</td>
                            <td><s:textarea name="land.description" cssStyle="width:500px" rows="5"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><s:submit value="保存" method="create" onclick="return testForm()"/></td>
                        </tr>
                    </table>
                </s:form>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <div class="info" style="background-color:#fff;">
                <div class="t">
                    <div class="t-t">加盟条件</div>
                </div>
                <ul>
                    <li>土地确属于农业用地</li>
                    <li>土地没有被污染</li>
                    <li>能够证明对于特定土地的使用权，例如，土地租赁合同、承包经营书等</li>
                    <li>熟悉并接受花木兰ICSA理念与经营操作规则</li>
                </ul>
                <div class="t">
                    <div class="t-t">加盟步骤</div>
                </div>
                <ul>
                    <li>提出加盟申请</li>
                    <li>花木兰业务员对土地进行评估</li>
                    <li>签订花木兰ICSA农场加盟协议和农场管理员协议：明确农场作物、农场租金、农场服务项目、农场管理员等</li>
                    <li>在花木兰网站公开发布</li>
                </ul>
                <div class="t">
                    <div class="t-t">加盟义务</div>
                </div>
                <ul>
                    <li>接受花木兰经营操作规则</li>
                    <li>确保作物安全</li>
                    <li>加盟方承担土地整理、日常经营的费用支出</li>
                    <li>花木兰网站负责承租过程、农庄经营等过程的所有网络服务</li>
                </ul>
                <div class="t">
                    <div class="t-t">加盟利益分享</div>
                </div>
                <ul>
                    <li>花木兰收取品牌经营、技术输出、网络维护等费用</li>
                    <li>加盟农场获得租金收益</li>
                    <li>加盟方有权获得农庄经营过程中的其他收益：包括农事活动劳务收入等</li>
                </ul>
                <div class="t">
                    <div class="t-t">加盟风险</div>
                </div>
                <ul>
                    <li>加盟方要承担土地无法租出的市场风险：如果加盟方的土地没有在花木兰网站上发租成功，花木兰不承担加盟方前期的所有投入</li>
                    <li>加盟方加盟花木兰ICSA项目后，土地发租成功，土地原有作物的损失由加盟方负责</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
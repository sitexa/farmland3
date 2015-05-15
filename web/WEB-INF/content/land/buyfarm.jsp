<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>
        <s:text name="root.title">
            <s:param>
                购买农庄
            </s:param>
        </s:text>
    </title>
    <link href="<%=request.getContextPath()%>/land/land.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script src='<%=request.getContextPath()%>/dwr/interface/JRentModeService.js'></script>
    <script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
    <script type="text/javascript">
        function testFarm() {
            var password = $.trim($("#password").val());
            var acreage = $.trim($("#farmAcreage").val());
            var farm_name = $.trim($("#farmName").val());
            if (password == "") {
                alert("请您输入密码。");
                return false;
            }
            if (!isInt(acreage)) {
                alert("农庄面积不能为空。");
                return false;
            } else {
                var farm_acreage = parseInt(acreage);
                if (farm_acreage < 50) {
                    alert("农庄面积不能小于50平方。");
                    return false;
                }
            }

            if (farm_name == "") {
                alert("农庄名称不能为空。");
                return false;
            }
            return true;
        }

        function calcValue() {
            var mode_id = $("#modeId").find("option:selected").val();
            JRentModeService.getPriceById(mode_id, function(data) {
                if (typeof(data) != "undefined") {
                    var farm_acreage = $.trim($("#farmAcreage").val());
                    var rent_price = parseInt(farm_acreage) * parseFloat(data) * 12 * 10;
                    $("#rentPrice").val(rent_price);
                } else {
                    $("#rentPrice").val(0);
                }
            });
        }

    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="centerbar">
            <div class="box">
                <div class="t">
                    <div class="t-t">
                        购买农庄
                    </div>
                </div>
                <div class="content" style="width:50%;float:right;background-color:#ffffcc">
                    <ul>
                        <s:iterator value="rentModes">
                            <li style="list-style:circle;list-style-position: inside;">${modeName}:${description}</li>
                        </s:iterator>
                    </ul>
                </div>
                <div class="content" style="padding:5px">
                    <form id="buyFarm" action="<%=request.getContextPath()%>/land/buyfarm" method="post">
                        所在农场：<a href="<%=request.getContextPath()%>/land/land/${land.landId}" target="_blank"><s:property value="land.landName"/></a> <br>
                        <s:hidden name="farm.land.landId" value="%{land.landId}"/>
                        农庄名称：<s:textfield id="farmName" name="farm.farmName"/><br/>
                        农庄面积：<s:textfield id="farmAcreage" name="farm.acreage" onblur="calcValue();"/> 平方米<br/>
                        租种模式：<s:select id="modeId" list="rentModes" listKey="modeId" listValue="modeName" name="farm.rentMode" headerKey="2" onchange="calcValue();"/> <br/>
                        农庄价格：<s:textfield id="rentPrice" name="farm.rentPrice" value="" readonly="true"/> ￥花币<br/>
                        支付密码：<input type="password" id="password" name="password" value=""/><br/>
                        <input type="submit" value="购 买" onclick="return testFarm()"/>
                        <s:actionerror cssStyle="color:red"/>
                    </form>
                </div>
            </div>
        </div>
        <div id="rightbar">
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
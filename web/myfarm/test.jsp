<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/work/work.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src='<%=request.getContextPath()%>/dwr/interface/JCropService.js'></script>
    <script type="text/javascript" src='<%=request.getContextPath()%>/dwr/interface/JSeedService.js'></script>
    <script type="text/javascript" src='<%=request.getContextPath()%>/dwr/engine.js'></script>
    <script type="text/javascript">
        function opname() {
            var landId = "1000445";
            var farmId = "1000451";

            var on_val = $("#op_name").find("option:selected").text();
            if (on_val == "播种") {
                JSeedService.getJSONSeedByLand(landId, set_crops_list);
            } else {
                JCropService.getJSONSeedByFarm(farmId, set_crops_list);
            }
        }

        function set_crops_list(data) {

            $("#seed_name").empty();
            if (typeof (data) != "undefined") {
                for (var p in data) {
                    var seed_name = data[p];
                    $("#seed_name").append("<option value='" + p + "'>" + seed_name + "</option>")
                }
            }
        }

        function seedname() {

        }

        function paymode() {
            var cb = $("#cb_paymode").attr("checked");
            if (cb) {
                $("#pay_amount").removeAttr("readonly");
            } else {
                $("#pay_amount").val("");
                $("#pay_amount").attr("readonly", "true");
            }
        }
    </script>
</head>
<body>
<div id="container">
    <div id="mainwrapper">
        <div id="leftbar" style="width:16%;">
        </div>
        <div id="centerbar" style="width:54%;">
            <div class="box">
                发布指令<br>

                操作：
                <select id="op_name" onchange="return opname();">
                    <option title="-1" value="11">请选择</option>
                    <option title="播种" value="1">播种</option>
                    <option title="管理" value="2">管理</option>
                    <option title="收割" value="3">收割</option>
                </select>
                品种:
                <select id="seed_name" onchange="return seedname();">
                    <option title="萝卜" value="1">萝卜</option>
                    <option title="白菜" value="2">白菜</option>
                    <option title="南瓜" value="3">南瓜</option>
                </select>
            </div>

            <div class="box">
                品种区域<br>
            </div>

            <div class="box">

            </div>
        </div>
        <div id="rightbar">

        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
    </div>
</div>
</body>
</html>
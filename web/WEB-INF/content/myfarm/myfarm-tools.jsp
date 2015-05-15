<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>${faction.actionName }</title>
    <link href="<%=request.getContextPath()%>/myfarm/myfarm-tools.css" rel="stylesheet" type="text/css"/>
    <script src='<%=request.getContextPath()%>/dwr/interface/JCropService.js'></script>
    <script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
    <script src='<%=request.getContextPath()%>/dwr/util.js'></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script>
        onload = function() {
            $("#farming_quantity").val("");
        }
        $("#operFrm").ready(function() {
            $("#tool_seedId").change(function() {
                var seedId = $(this).val();
                var farmId = $("#farmId").val();
                $("#seedImg").attr("src", "<%=request.getContextPath()%>/image?type=seed&picId=" + seedId);
                JCropService.getJSONByFarmAndSeed(farmId, seedId, show_crops_status);
            })
        });

        function show_crops_status(data) {
            if (data != "") {
                if (data.result == "1") {
                    $("#seed-price").html(data.price);
                    if (typeof(count) == "function") count();
                    $("#seed-remark").html(data.description);
                    $("#farming_quantity").val(data.seedNumber);
                } else {
                    $("#info").html('');
                    //alert(data.msg);
                }
            }
        }
        function logsDisplay(style) {
            $("#logs").css("display", style);
        }
    </script>
</head>
<body>
<div class="window">
<div class="header">${faction.actionName }</div>
<div class="body">

<s:form action="%{#request.contextPath}/myfarm/myfarm" id="operFrm" method="post">
<s:hidden name="farm.farmId" id="farmId"/>
<s:hidden name="faction.factionId"/>
<s:hidden name="faction.actionId"/>

<%--product begin--%>
<%--耕地--%>
<s:if test="faction.actionId==1001555||faction.actionId==1001540">
    <div class="box">
        <div class="left">
            <img src="<%=request.getContextPath()%>/image?type=faction&picId=${faction.factionId }"/>
        </div>
        <div class="right">
            <div class="description">${faction.contents}</div>
        </div>
    </div>
    <div class="box">
        <ul>
            <li>
                <div class="left">面积:</div>
                <div class="right"><s:textfield id="farming_quantity" name="farming.quantity" readonly="true" onchange="count()"/>平方米(该服务最低按10平方米收费)</div>
            </li>
            <li>
                <div class="left">说明/日志:</div>
                <div class="right">
                    <s:textarea name="farming.contents" cols="60" rows="4"/><br>
                    委托农场操作:<s:checkbox name="farming.paymode" value="false" id="cb_paymode" onclick="return paymode();"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit value="提交" method="operate" onclick="return testForm()"/>
                </div>
            </li>
        </ul>
    </div>
    <div id="paybox" class="box" style="display:none">
        <span class="prices-box">操作/服务价格:<label class="prices" id="service-price">${faction.expense}</label>（花币）</span> <br>
        <span class="prices-box">总价:<label class="prices" id="total-fee"></label>（花币）</span> <br>
        支付密码：<s:password id="creditPassword" name="creditPassword"/>
        <div class="notice">
            　　请慎重操作，此项服务一但支付，将不可逆转！如有疑问，请联系客服。
        </div>
    </div>

    <script type="text/javascript">
        function paymode() {
            var cb = $("#cb_paymode").attr("checked");
            if (cb) {
                $("#paybox").css("display", "block");
            } else {
                $("#paybox").css("display", "none");
            }
        }
        function testForm() {
            var password = $("#creditPassword").val();
            if ($("#cb_paymode").attr("checked") && isEmpty(password)) {
                alert("请您输入密码！");
                return false;
            }
            return true;
        }
        function count() {
            var count = 0;
            var service_price = $.trim($("#service-price").html());
            var quantity = $.trim($("#farming_quantity").val());
            if (isInt(service_price)) count += Number(service_price);
            if (isInt(seed_price) && isInt(quantity)) count += (Number(seed_price) * Number(quantity));
            $("#total-fee").html(count);
        }
    </script>
</s:if>
<%--播种--%>
<s:elseif test="faction.actionId==1001572">
    <div id="logs" class="logs-box">
        <div class="logs-header"><a href="#" class="close" onclick="logsDisplay('none')">关闭</a>${faction.actionName }日志</div>
        <div class="logs-content">
            <s:iterator value="farmings" status="its">
                <s:date format="yyyy-MM-dd" name="startTime"/>
                ${farmer.realname}
                ${seed.seedName}
                ${faction.actionName}
                ${contents}<br/>
            </s:iterator>
        </div>
    </div>
    <div class="box">
        <div class="left">
            <img src="<%=request.getContextPath()%>/image?type=faction&picId=${faction.factionId }"/>
        </div>
        <div class="right">
            <label class="link"><a href="#" onclick="logsDisplay('block')">${faction.actionName }日志</a></label>

            <div class="description">${faction.contents} </div>
        </div>
    </div>
    <div class="box">
        <div class="left">
            <s:select id="tool_seedId" list="seeds" listKey="seedId" listValue="seedName" name="farming.seed.seedId" headerKey="-1" headerValue=""/>
            <div class="box-img"><img id="seedImg"/></div>
        </div>
        <div class="right">
            <div id="seed-remark" class="description"></div>
        </div>
    </div>
    <div class="box">
        <ul>
            <li>
                <div class="left">面积:</div>
                <div class="right"><s:textfield id="farming_quantity" name="farming.quantity" onchange="count()"/>平方米(该服务最低按10平方米收费)</div>
            </li>
            <li>
                <div class="left">说明/日志:</div>
                <div class="right">
                    <s:textarea name="farming.contents" cols="60" rows="4"/><br>
                    委托农场操作:<s:checkbox name="farming.paymode" value="false" id="cb_paymode" onclick="return paymode();"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit value="提交" method="operate" onclick="return testForm()"/>
                </div>
            </li>
        </ul>
    </div>
    <div id="paybox" class="box" style="display:none">
        <span class="prices-box">操作/服务价格:<label class="prices" id="service-price">${faction.expense}</label>（花币）</span> <br>
        <span class="prices-box">种子/材料价格:<label class="prices" id="seed-price"></label>（花币）</span> <br>
        <span class="prices-box">总价:<label class="prices" id="total-fee"></label>（花币）</span> <br>
        支付密码：<s:password id="creditPassword" name="creditPassword"/>
        <div class="notice">
            　　请慎重操作，此项服务一但支付，将不可逆转！如有疑问，请联系客服。
        </div>
    </div>
    <div class="bottomer"></div>

    <script type="text/javascript">
        function paymode() {
            var cb = $("#cb_paymode").attr("checked");
            if (cb) {
                $("#paybox").css("display", "block");
            } else {
                $("#paybox").css("display", "none");
            }
        }
        function testForm() {
            var seed = $.trim($("#tool_seedId").val());
            if (isEmpty(seed) || seed == "-1") {
                alert("请您选择要播种的种子。");
                return false;
            }
            var quantity = $.trim($("#farming_quantity").val());
            if (!isInt(quantity)) {
                alert("您输入的面积格式有误，请您重新输入。\n注意：面积为整数。");
                return false;
            }
            var password = $("#creditPassword").val();
            if ($("#cb_paymode").attr("checked") && isEmpty(password)) {
                alert("请您输入密码！");
                return false;
            }
            return true;
        }
        function count() {
            var count = 0;
            var service_price = $.trim($("#service-price").html());
            var seed_price = $.trim($("#seed-price").html());
            var quantity = $.trim($("#farming_quantity").val());
            if (isInt(service_price)) count += Number(service_price);
            if (isInt(seed_price) && isInt(quantity)) count += (Number(seed_price) * Number(quantity));
            $("#total-fee").html(count);
        }
    </script>
</s:elseif>
<%--采摘--%>
<s:elseif test="faction.actionId==1001554">
    <div class="box">
        <div class="left">
            <img src="<%=request.getContextPath()%>/image?type=faction&picId=${faction.factionId }"/>
        </div>
        <div class="right">
            <div class="description">${faction.contents} </div>
        </div>
    </div>
    <div class="box">
        <div class="left">
            <s:select id="tool_seedId" list="farmSeeds" listKey="seedId" listValue="seedName" name="farming.seed.seedId" headerKey="-1" headerValue=""/>
            <div class="box-img"><img id="seedImg"/></div>
        </div>
        <div class="right">
            <div id="seed-remark" class="description"></div>
        </div>
    </div>
    <div class="box">
        <ul>
            <li>
                <div class="left">面积:</div>
                <div class="right"><s:textfield id="farming_quantity" name="farming.quantity" onchange="count()"/>平方米(该服务最底按10平方米收费)</div>
            </li>
            <li>
                <div class="left">说明/日志:</div>
                <div class="right">
                    <s:textarea name="farming.contents" cols="60" rows="4"/><br>
                    委托农场操作:<s:checkbox name="farming.paymode" value="false" id="cb_paymode" onclick="return paymode();"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit value="提交" method="operate" onclick="return testForm()"/>
                </div>
            </li>
        </ul>
    </div>
    <div id="paybox" class="box" style="display:none">
        <span class="prices-box">操作/服务:<label class="prices" id="service-price">${faction.expense}</label>（花币）</span><br>
        <span class="prices-box">种子/材料:<label class="prices" id="seed-price"></label>（花币）</span><br>
        <span class="prices-box">总价:<label class="prices" id="total-fee"></label>（花币）</span><br>
        支付密码：<s:password id="creditPassword" name="creditPassword"/>
        <div class="notice">
            　　请慎重操作，此项服务一但支付，将不可逆转！如有疑问，请联系客服。
        </div>
    </div>
    <div class="bottomer"></div>

    <script type="text/javascript">
        function paymode() {
            var cb = $("#cb_paymode").attr("checked");
            if (cb) {
                $("#paybox").css("display", "block");
            } else {
                $("#paybox").css("display", "none");
            }

        }
        function testForm() {
            var seed = $.trim($("#tool_seedId").val());
            if (isEmpty(seed) || seed == "-1") {
                alert("请您选择要采摘的农作物。");
                return false;
            }
            var quantity = $.trim($("#farming_quantity").val());
            if (!isInt(quantity)) {
                alert("您输入的面积格式有误，请您重新输入。\n注意：面积为整数。");
                return false;
            }
            var password = $("#creditPassword").val();
            if ($("#cb_paymode").attr("checked") && isEmpty(password)) {
                alert("请您输入密码！");
                return false;
            }
            return true;
        }
        function count() {
            var count = 0;
            var service_price = $.trim($("#service-price").html());
            var seed_price = $.trim($("#seed-price").html());
            var quantity = $.trim($("#farming_quantity").val());
            if (isInt(service_price)) count += Number(service_price);
            if (isInt(seed_price) && isInt(quantity)) count += (Number(seed_price) * Number(quantity));
            $("#total-fee").html(count);
        }
    </script>
</s:elseif>
<%--其他服务--%>
<s:else>
    <div class="box" style="border-bottom:1px dashed #ACFF59">
        <div class="left">
            <img src="<%=request.getContextPath()%>/image?type=faction&picId=${faction.factionId }"/>
        </div>
        <div class="right">
            <div class="description">${faction.contents} </div>
        </div>
    </div>
    <div class="box" style="border-bottom:1px dashed #ACFF59">
        <div class="left">
            <s:select id="tool_seedId" list="farmSeeds" listKey="seedId" listValue="seedName" name="farming.seed.seedId" headerKey="-1" headerValue=""/>
            <div class="box-img"><img id="seedImg"/></div>
        </div>
        <div class="right">
            <div id="seed-remark" class="description"></div>
        </div>
    </div>
    <div class="box">
        <ul>
            <li>
                <div class="left">面积:</div>
                <div class="right"><s:textfield id="farming_quantity" name="farming.quantity" readonly="true" onchange="count()"/>平方米(该服务最底按10平方米收费)</div>
            </li>
            <li>
                <div class="left">说明/日志:</div>
                <div class="right"><s:textarea name="farming.contents" cols="60" rows="4"/><br>
                    委托农场操作:<s:checkbox name="farming.paymode" value="false" id="cb_paymode" onclick="return paymode();"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit value="提交" method="operate" onclick="return testForm()"/>
                </div>
            </li>
        </ul>
    </div>
    <div id="paybox" class="box" style="display:none">
        <span class="prices-box">操作/服务:<label class="prices" id="service-price">${faction.expense}</label>（花币）</span><br>
        <span class="prices-box">种子/材料:<label class="prices" id="seed-price"></label>（花币）</span><br>
        <span class="prices-box">总价:<label class="prices" id="total-fee"></label>（花币）</span><br>
        支付密码：<s:password id="creditPassword" name="creditPassword"/>
        <div class="notice">
            　　请慎重操作，此项服务一但支付，将不可逆转！如有疑问，请联系客服。
        </div>
    </div>
    <div class="bottomer"></div>

    <script type="text/javascript">
        function paymode() {
            var cb = $("#cb_paymode").attr("checked");
            if (cb) {
                $("#paybox").css("display", "block");
            } else {
                $("#paybox").css("display", "none");
            }

        }
        function testForm() {
            var seed = $.trim($("#tool_seedId").val());
            if (isEmpty(seed) || seed == "-1") {
                alert("请您选择要操作的农作物。");
                return false;
            }
            //var expense = $.trim($("#faction_expense").val());
            //if(!isInt(expense)){	alert("服务价格有误，请您刷新页面或与管理员联系！"); return false;		}
            var cb = $("#cb_paymode").attr("checked");
            if (cb) {
                var password = $("#creditPassword").val();
                if (isEmpty(password)) {
                    alert("请您输入密码！");
                    return false;
                }
            }
            return true;
        }
        function count() {
            var count = 0;
            var service_price = $.trim($("#service-price").html());
            var seed_price = $.trim($("#seed-price").html());
            var quantity = $.trim($("#farming_quantity").val());
            if (isInt(service_price)) count += Number(service_price);
            if (isInt(seed_price) && isInt(quantity)) count += (Number(seed_price) * Number(quantity));
            $("#total-fee").html(count);
        }
    </script>
</s:else>
<%--product end--%>
</s:form>
</div>
</div>
</body>
</html>
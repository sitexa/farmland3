<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript">
        function testForm() {
            var pay_amt = $("#pay_amount").val();
            var amt = parseInt(pay_amt);
            if (isNaN(amt) || amt < 0) {
                alert("费用错误!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div>
    <fieldset>
        <legend>回复庄主</legend>
        <s:form id="frmFarm" action="%{#request.contextPath}/admin/work/work!saveReply" method="post">
            <s:hidden name="farming.farmingId"/>
            <table cellspacing=1px cellpadding=1px>
                <tbody>
                <tr>
                    <td class="controlbg" nowrap>农庄名称</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/work/work/${farm.farmId}" target="_blank">
                            <s:property value="farm.farmName"/>
                        </a>
                    </td>
                    <td class="controlbg">下令人</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/user/member/${farming.farmer.memberId}" target="_blank">
                            <s:property value="farming.farmer.realname"/>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td class="controlbg">操作名</td>
                    <td><s:property value="farming.faction.actionName"/></td>
                    <td class="controlbg">对象</td>
                    <td>
                        <s:if test="farming.farmPlan!=null"><s:property value="farming.farmPlan.planName"/></s:if>
                        <s:elseif test="farming.seed!=null"><s:property value="farming.seed.seedName"/></s:elseif>
                    </td>
                </tr>
                <tr>
                    <td class="controlbg">面积</td>
                    <td>
                        <s:textfield id="quantity" name="farming.quantity" maxlength="6" size="6"/>
                        M<sup>2</sup>
                    </td>
                    <td class="controlbg">委托</td>
                    <td><s:textfield id="pay_amount" name="farming.amount" maxlength="6" size="6"/>花币</td>
                </tr>
                <tr>
                    <td class="controlbg">指令日期</td>
                    <td><s:date name="farming.startTime" format="M月d日H时"/></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td class="controlbg">说明</td>
                    <td colspan="3"><s:property value="farming.contents"/></td>
                </tr>
                <tr>
                    <td class="controlbg">回复</td>
                    <td colspan="3"><s:textarea name="farming.remark" value="" cols="60" rows="4"> </s:textarea></td>
                </tr>
                <tr>
                    <td class="controlbg"></td>
                    <td colspan="3">
                        <s:checkbox name="farming.state" title="完成"/> 已经完成操作。
                        <s:submit value="保存" cssClass="button" onclick="return testForm()"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </s:form>
    </fieldset>
</div>
</body>
</html>
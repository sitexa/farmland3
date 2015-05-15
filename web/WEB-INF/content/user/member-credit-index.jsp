<%--
  User: xnpeng
  Date: 2009-12-2
  Time: 11:10:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript">

        $(function() {
            $("tbody tr:odd").each(function() {
                $(this).addClass("odd");
            })
        });

        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("frmSearch").submit();
        }
        function testForm() {
            var val = $("#goodPrice").val();
            var reg = new RegExp(/^[1-9]\d*$/);
            val = $.trim(val);
            if (val == "") {
                alert("请输入金额！");
                return false;
            }
            if (!reg.test(val)) {
                alert("您输入的金额格式有误，请重新输入(金额为整数)。");
                $("#goodPrice").select();
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
            <s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol" style="width:800px">
            <div class="box">
                <div class="title2">农庄银号-【${member.realname}】</div>
                <div class="content" style="border:1px solid gray;margin:4px;padding:4px;line-height:1.9em;">
                    <div style="font-size: 20px; font-weight:bold;">
                        帐户余额： <span style="color:red;"><s:property value="mc.credit"/></span>
                        花木兰币(花币)
                    </div>
                    <form method="post" action="<%=request.getContextPath()%>/user/member-credit!alipay"
                          style="padding:10px;">
                        花木兰网站以花木兰币结算, 所有的标价都按花木兰币标出.10￥花木兰币 = 1￥人民币.<br>
                        <s:hidden name="member.memberId"/>
                        <%--<input type="radio" name="serviceType" value="1" disabled/>支付宝担保交易--%>
                        充值方式：<input type="radio" name="serviceType" value="2" checked="checked"/>网银支付即时到帐
                        <input type="radio" name="serviceType" value="3" <s:if test="perm!='l'">disabled</s:if>/>银行转帐[***]
                        <input type="radio" name="serviceType" value="4" <s:if test="perm!='l'">disabled</s:if>/>现金充值<br>
                        充值金额：<input type="text" id="goodPrice" name="goodPrice" value="" size="8" maxlength="5"/>￥人民币
                        <span style="color: red">(10￥花木兰币= 1￥人民币)</span>
                        <input class="submit" type="submit" name="v_action" value="帐号充值" onclick="return testForm()"
                               style="font:normal bold 11pt arial"/>
                    </form>
                </div>
            </div>
            <div class="box">
                <div class="title2">收支明细</div>
                <div class="content">
                    <table width="100%">
                        <thead>
                        <tr style="background-color:#bbb">
                            <th nowrap width="120">发生时间</th>
                            <th nowrap width="30">+/-</th>
                            <th nowrap width="40">金额</th>
                            <th nowrap width="80">经手人</th>
                            <th nowrap width="80">对象</th>
                            <th>摘要</th>
                            <th>备注</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="mclogs" status="its">
                            <tr>
                                <td><s:date name="eventDate" format="yyyy-MM-dd HH:mm"/></td>
                                <td><s:if test="incDec==1">＋</s:if><s:else>－</s:else></td>
                                <td><s:property value="amount"/></td>
                                <td><s:property value="member.realname"/></td>
                                <td><s:property value="member2.realname"/></td>
                                <td><s:property value="contents"/></td>
                                <td><s:if test="orderNo!=null">(<s:property value="orderNo"/>)</s:if></td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                    <div id="paging">
                        <s:if test="page.total!=0">
                            共有${page.total}页结果:
                            <s:if test="page.current>6">
                                <a href="#" onclick="dos(1);">1</a>
                            </s:if>
                            <s:if test="page.current>7">
                                ...
                            </s:if>
                            <s:bean name="org.apache.struts2.util.Counter" id="counter">
                                <s:param name="first" value="page.current-5"/>
                                <s:param name="last" value="page.current+5"/>
                                <s:iterator status="its">
                                    <s:if test="current>1 && current<=page.total+1">
                                        <s:if test="#its.index==5">
                                            <s:property/>
                                        </s:if>
                                        <s:else>
                                            <a href="#" onclick="dos('<s:property/>');"> <s:property/></a>
                                        </s:else>
                                    </s:if>
                                </s:iterator>
                            </s:bean>
                            <s:if test="page.total>page.current+6">
                                ...
                            </s:if>
                            <s:if test="page.total>page.current+5">
                                <a href="#" onclick="dos('<s:property value="page.total"/>');"><s:property
                                        value="page.total"/></a>
                            </s:if>
                        </s:if>
                    </div>
                    <s:form id="frmSearch" style="display:none;"
                            action="%{#request.contextPath}/user/member-credit/%{member.memberId}/show"
                            method="get">
                        <s:hidden id="page.current" name="page.current" value="1"/>
                    </s:form>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="control-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
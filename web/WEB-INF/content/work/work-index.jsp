<%@ page import="com.sitexa.framework.config.AppConfig" %>
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/myfarm/myfarm.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/pic.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/work/work.js"></script>
    <script src='<%=request.getContextPath()%>/dwr/interface/JCropService.js'></script>
    <script src='<%=request.getContextPath()%>/dwr/interface/JSeedService.js'></script>
    <script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/script/slide.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/slide.js"></script>
    <script type="text/javascript">

        var url = "<%=request.getContextPath()%>";
        var imgurl = "<%=AppConfig.getProperty("imgurl")%>";
        $(function() {
            $("tbody tr:odd").each(function() {
                $(this).addClass("odd")
            })
            $("tbody tr:even").each(function() {
                $(this).addClass("even")
            })
            turnImg(1000,2000);
        });

        function showPicture(picUrl) {
            var path = imgurl + picUrl;
            window.open(path, "", 'left=100,top=50,width=600,height=450,location=no,resizable=no');
        }

        function uploadPicture() {
            var dialogLeft = (screen.width - 650) / 2;
            var dialogTop = (screen.height - 550) / 2;
            var farmId = $("#farmId").val();
            var path = url + "/work/work!upload?farm.farmId=" + farmId;
            window.open(path, "", 'left=' + dialogLeft + ',top=' + dialogTop + ',width=550,height=100,location=yes,resizable=no');
        }

        function upSeedList() {
            var landId = $("#landId").val();
            var farmId = $("#farmId").val();
            var cb = $("#cb_paymode").attr("checked");
            var on_val = $("#op_name").find("option:selected").val();
            if (on_val == "plant") {
                JSeedService.getJSONSeedByLand(landId, set_crops_list);
                if (cb) {
                    $("#pay_amount").val("300");
                } else {
                    $("#pay_amount").val("");
                }
            } else {
                JCropService.getJSONSeedByFarm(farmId, set_crops_list);
                if (on_val == "manage") {
                    if (cb) {
                        $("#pay_amount").val("150");
                    } else {
                        $("#pay_amount").val("");
                    }
                } else if (on_val == "delete") {
                    if (cb) {
                        $("#pay_amount").val("100");
                    } else {
                        $("#pay_amount").val("");
                    }
                }
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

        function paymode() {
            var cb = $("#cb_paymode").attr("checked");
            var oper_id = $("#op_name").val();
            if (cb) {
                $("#pay_amount").removeAttr("readonly");
                if (oper_id == "plant") {
                    $("#pay_amount").val("300");
                } else if (oper_id == "manage") {
                    $("#pay_amount").val("150");
                } else if (oper_id == "delete") {
                    $("#pay_amount").val("100");
                }
            } else {
                $("#pay_amount").val("");
                $("#pay_amount").attr("readonly", "true");
            }
        }

        function check_qty() {
            var qty = $("#farming_qty").val();
            var qty_v = parseInt(qty);
            if (isNaN(qty_v) || qty_v < 0) {
                alert("面积错误。");
                $("#farming_qty").val("0");
                return false;
            } else
                return true;
        }

        function check_form() {
            var oper_id = $("#op_name").val();
            var seed_id = $("#seed_name").val();
            var pay_amt = $("#pay_amount").val();
            var pay_mode = $("#cb_paymode").attr("checked");
            if (oper_id == "-1") {
                alert("请选择操作!");
                return false;
            }
            if (seed_id == "-1") {
                alert("请选择作物!");
                return false;
            }
            if (pay_mode) {
                var amt = parseInt(pay_amt);
                if (isNaN(amt) || amt < 0) {
                    alert("费用错误!");
                    return false;
                }
            }
            return true;
        }

        function showLog(farmId, seedId) {
            self.location = "<%=request.getContextPath()%>/work/work/" + farmId + "?seedId=" + seedId;
        }

        function showhelp() {
            $("#helpdiv").css("display", "block");
        }

        function hidehelp() {
            $("#helpdiv").css("display", "none");
        }

        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("pageFarm").submit();
        }
    </script>
</head>
<body>
<div id="container">
<div id="header">
    <s:include value="../h.jsp"/>
</div>
<div id="mainwrapper">
<div class="banner">
    <div class="farminfo">
        <div style="width:90px;float:left;padding-left:120px;">
            <s:if test="farm.member.pictures.size>0">
                <s:iterator value="farm.member.pictures" status="its">
                    <s:if test="#its.index<1">
                        <img src="<%=request.getContextPath()%>/image?type=m&picId=${picId}"
                             title="${picTitle}"
                             style="width:76px;height:76px;margin:4px;"/>
                    </s:if>
                </s:iterator>
            </s:if>
            <s:else>
                <img src="<%=request.getContextPath()%>/myfarm/img/member-pic.gif"
                     title="member-pic"
                     style="width:76px;height:76px;margin:4px;"/>
            </s:else>
        </div>
        <div style="width:250px;float:left;">
            <span id="farm_farmName">${farm.farmName}【${farm.acreage }M<sup>2</sup>】</span>
            【<a href="<%=request.getContextPath()%>/myfarm/farm/${farm.farmId}/edit">管理农庄</a>】
            <br>
            所属农场：
            <a href="<%=request.getContextPath()%>/buy/land/${farm.land.landId}">
                ${farm.land.landName}
            </a> <br>
            农庄主：
            <a href="<%=request.getContextPath()%>/user/member/${farm.member.memberId}">${farm.member.realname}</a>
            <s:if test="farm.owners.size>0">
                <s:iterator value="farm.owners" status="its">
                    <a href="<%=request.getContextPath()%>/user/member/${member.memberId}">${member.realname}</a>
                </s:iterator><br>
            </s:if>
            <s:if test="farms.size>1">
                其他农庄：
                <s:iterator value="farms" status="its">
                    <s:if test="farmId!=farm.farmId">
                        <a href="<%=request.getContextPath()%>/work/work/${farmId}">
                                ${farmName}(${acreage }M<sup>2</sup>)
                        </a>
                    </s:if>
                </s:iterator><br>
            </s:if>
            花币：<span style="color:red">${credit.credit}</span>￥
        </div>
    </div>
    <div class="ann">
        <s:if test="newNotices.size>0">
            <a href="<%=request.getContextPath()%>/play/play?postType.typeId=110" target="blank">
                <img src="<%=request.getContextPath()%>/images/ann.gif" alt="场长公告" title="场长公告"/>
            </a>
        </s:if>
        <s:else>
            <img src="<%=request.getContextPath()%>/images/ann.png" alt="场长公告" title="场长公告"/>
        </s:else>
    </div>
    <div class="ann">
        <s:if test="newPicks.size>0">
            <a href="<%=request.getContextPath()%>/post/farm-post/${farm.farmId}" target="blank">
                <img src="<%=request.getContextPath()%>/images/ann2.gif" alt="好友请求" title="好友请求"/>
            </a>
        </s:if>
        <s:else>
            <img src="<%=request.getContextPath()%>/images/ann2.png" alt="好友请求" title="好友请求"/>
        </s:else>
    </div>
    <div class="clear"></div>
    <div class="pnn">
        <s:if test="farm.slogan!=null">${farm.slogan}</s:if>
    </div>
</div>

<div id="leftbar" style="width:200px;">
    <div class="dx">
        <div class="t">
            <div class="t-t">
                <div class="t-m">
                    <a href="<%=request.getContextPath()%>/post/farm-post/?farmId=${farm.farmId}"
                       target="_blank">更多</a>
                </div>
                农事感言
            </div>
        </div>

        <div class="list">
            <ul>
                <s:iterator value="newPosts">
                    <li>
                        <a href="<%=request.getContextPath()%>/post/farm-post/${id}" target="_blank">${name} </a>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
    <s:if test="friendFarms.size>0">
        <div class="dx">
            <div class="t">
                <div class="t-t">
                    好友农庄
                </div>
            </div>
            <div id="friendFarms" class="list">
                <ul>
                    <s:iterator value="friendFarms">
                        <li>
                            <a href="<%=request.getContextPath()%>/work/work/${farmId}"
                               target="_blank">${farmName}</a>
                        </li>
                    </s:iterator>
                </ul>
            </div>
        </div>
    </s:if>
    <s:if test="neFarms.size>0">
        <div class="dx">
            <div class="t">
                <div class="t-t">
                    周边农庄
                </div>
            </div>
            <div id="neFarms" class="list">
                <ul>
                    <s:iterator value="neFarms" status="its">
                        <s:if test="#its.index<5">
                            <li>
                                <a href="<%=request.getContextPath()%>/work/work/${farmId}"
                                   target="_blank">${farmName}</a>
                            </li>
                        </s:if>
                    </s:iterator>
                </ul>
            </div>
        </div>
    </s:if>
    <s:if test="visitors.size>0">
        <div class="dx">
            <div class="t">
                <div class="t-t">
                    最近访客
                </div>
            </div>
            <div id="newMembers" class="list">
                <ul>
                    <s:iterator value="visitors" status="its">
                        <li>
                            <a href="<%=request.getContextPath()%>/user/member/${memberId}"
                               target="_blank">${realname}</a>
                        </li>
                    </s:iterator>
                </ul>
            </div>
        </div>
    </s:if>
</div>

<div id="centerbar" style="width:493px;margin:0 6px;">
    <div class="crops">
        <ul>
            <s:if test="cropses.size>0">
                <s:iterator value="cropses" status="its">
                    <li id="${cropsId}" style="width:110px;height:140px;overflow:hidden;">
                        <img src="<%=request.getContextPath()%>/work/work!getSeedImage?seedId=${seed.seedId}"
                             title="点击查看日志" alt="点击查看日志"
                             onclick="return showLog(${farm.farmId},${seed.seedId});"
                             style="width:100px;height:80px;border:1px solid green"/>

                        <div style="color:green">${seed.seedName}:${seedNumber}M<sup>2</sup></div>
                        <div style="color:#cc6600;width:90px;" title="${remark}">${remark}</div>
                    </li>
                </s:iterator>
            </s:if>
            <s:if test="16-cropses.size>0">
                <s:bean name="org.apache.struts2.util.Counter" id="counter">
                    <s:param name="first" value="1"/>
                    <s:param name="last" value="16-cropses.size"/>
                    <s:iterator>
                        <li style="width:110px;height:140px;overflow:hidden;">
                            <img src="<%=request.getContextPath()%>/images/kongdi.png" title=""/>

                            <div style="color:#cc6600;width:90px;" title="">赶紧来播种哦！</div>
                        </li>
                    </s:iterator>
                </s:bean>
            </s:if>
        </ul>
    </div>
</div>

<div id="rightbar" style="width:260px;">
    <div class="box">
        <div class="t">
            <div class="t-t">
                网上种菜(<span style="color:red" onmouseover="showhelp();"
                           onmouseout="hidehelp();">?</span>)
            </div>
        </div>
        <div id="helpdiv" style="background-color:#ffffcc;border:1px solid #eee;font-size:12px;display:none">
            1，播种:300花币/10平米；<br/>2，管理:150花币/60平米；<br/>3，收割:100花币/60平米；
            <br/>4，特殊情形，请填写说明，跟农场服务人员商定价格，在费用框中写明费用；
            <br/>5，自助操作，只记录日志，不收取费用。
        </div>
        <s:form action="%{#request.contextPath}/work/work" id="operFrm" method="post"
                cssStyle="margin:0;padding:0;">
            <s:hidden name="farm.farmId" id="farmId"/>
            <s:hidden name="farm.land.landId" id="landId"/>
            操作：<s:select list="factions" id="op_name" name="faction.actionId"
                         listKey="actionId" listValue="actionName" headerKey="-1" headerValue="请选择"
                         onchange="return upSeedList();"/>
            品种:
            <select id="seed_name" name="seed.seedId">
                <option title="-1" value="-1">----</option>
            </select><br>
            面积：<input class="text" type="text" id="farming_qty" name="farming.quantity" value="10" size="5" maxlength="5" onblur="return check_qty();"/>(平米)
            委托农场：<input class="text" type="checkbox" id="cb_paymode" name="farming.paymode" checked onclick="return paymode();"/><br/>

            费用：<input class="text" type="text" id="pay_amount" name="farming.amount" size="5" maxlength="5" readonly/>(花币)

            <textarea name="farming.contents" style="float:left;width:240px;height:40px;"></textarea>
            <s:submit value="提交" method="operation" onclick="return check_form();"
                      cssStyle="float:left;margin:10px 0 0 10px;"/>
        </s:form>
        <div class="clear"></div>
    </div>
    <div class="box">
        <div class="t">
            <div class="t-t">
                操作日志<s:if test="seed!=null">【${seed.seedName}】</s:if>
            </div>
        </div>
        <div class="log">
            <table width="240" cellspacing="0" cellpadding="0" border="0">
                <s:iterator value="farmings" status="its">
                    <tr class='<s:if test="#its.odd">odd</s:if>'>
                        <td width="60"><s:date format="M月d日" name="startTime"/></td>
                        <td style="padding:5px 0">
                            <s:if test="paymode==1">【委托】</s:if>
                            <s:if test="farmer.memberId!=farm.member.memberId">
                                <a href="<%=request.getContextPath()%>/user/member/${farmer.memberId}"
                                   target="_blank">${farmer.realname}</a>
                            </s:if>
                                ${faction.actionName}
                                ${seed.seedName}
                            <s:if test="quantity==0||quantity==">${quantity}m<sup>2</sup></s:if>
                                ${contents}
                            <s:if test="paymode==1">[${amount}花币]</s:if>
                            <s:if test="paymode==1&&state!=1"><span style="color:#996600">【未完成】</span></s:if>
                            <s:if test="remark!=null||remark!=''">
                                <span style="color:green">${remark}</span>
                            </s:if>
                        </td>
                    </tr>
                </s:iterator>
            </table>
        </div>
        <div id="paging" style="border:1px solid #bbb;margin-bottom:0;">
            <s:if test="page.total>=0">
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
        <s:form id="pageFarm" theme="simple" action="%{#request.contextPath}/work/work/%{farm.farmId}"
                method="get" cssStyle="display: none">
            <s:hidden name="seedId" value="%{seed.seedId}"/>
            <s:hidden id="page.current" name="page.current" value="1"/>
            <s:submit method="show"/>
        </s:form>
    </div>
</div>

<div class="clear"></div>
<div class="t2">
    <div class="t2-t">
        农庄图片
        <input type="button" value="上传图片" onclick="uploadPicture();" style="background-color:#ff9900;"/>
    </div>
    <div class="slideBox">
        <ul>
            <s:iterator value="farm.farmPictures">
                <li>
                    <a href="<%=AppConfig.getProperty("imgurl")%>/f/${farm.farmId}/${picUrl}" target="blank">
                        <img src="<%=request.getContextPath()%>/image?type=farm&picId=${picId}"
                             title="${farm.farmName}" style="border:1px solid green;"/>
                    </a>
                </li>
            </s:iterator>
        </ul>
    </div>
</div>
<div class="clear"></div>
</div>
<div id="footer" style="margin-top:85px;">
    <s:include value="../f.jsp"/>
</div>
</div>
</body>
</html>
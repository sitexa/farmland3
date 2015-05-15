<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/join/join.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/pic.js"></script>
    <script type="text/javascript">
        function showPic(id) {
            which = id;
            applyeffect();
            document.getElementById("photoslider").src = photos[which];
            document.getElementById("photodescription").innerHTML = descriptions[which];
            playeffect();
            keeptrack();
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
            <s:include value="joinland-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="t2">
                    <div class="t2-t">
                        加盟花木兰农场
                    </div>
                </div>
                <s:if test="step==1">
                    <div class="stepOn"><a href="<%=request.getContextPath()%>/user/user/new">第1步<BR>注册用户</a></div>
                </s:if>
                <s:else>
                    <div class="stepOff">第1步<BR>注册用户</div>
                </s:else>
                <s:if test="step==2">
                    <div class="stepOn"><a
                            href="<%=request.getContextPath()%>/user/member/${member.memberId}/applyLanderPage">第2步<BR>变农场主</a>
                    </div>
                </s:if>
                <s:else>
                    <div class="stepOff">第2步<BR>变农场主</div>
                </s:else>
                <s:if test="step==3">
                    <div class="stepOn"><a href="<%=request.getContextPath()%>/join/land/new">第3步<BR>创建农场</a></div>
                </s:if>
                <s:else>
                    <div class="stepOff">第3步<BR>创建农场</div>
                </s:else>
                <div class="step">第4步<BR>划分农庄</div>
                <div class="step">第5步<BR>审核发布</div>
            </div>
            <div class="clear"></div>
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
        <div id="rightbar" class="rightcol">
            <s:include value="joinland-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
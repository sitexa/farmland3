<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/market/css/menu.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/market/js/market.js"></script>
    <script language="javascript">
        var url = '<%=request.getContextPath()%>';
        var imgPath = '<%=AppConfig.getProperty("imgurl")%>';
        onload = function() {
            $("#nav .nav_off").get(${itemType}-1).className = "nav_on";
            var menuChildren = $("#menu_con div").get(${itemType}-1);
            $(menuChildren).css("display", "block")
                    		.find("li:not(.menu_line2)").eq(${cateId}-1).children().attr("class", "a2");
            $("#submit").click(function() { return testFrom() });
        }
    </script>
</head>
<body>
<div id="container">
<div id="header">
    <s:include value="../h.jsp"/>
</div>
<div id="mainwrapper">
<div id="leftbar" style="width:68%;">
    <div class="box">
        <div id="siteBox" class="content" style="font-weight:bold">
            <s:if test="site.type.typeId==2">
                <s:iterator value="site.children">
					<span class="a-bar">
                    	<a href="<%=request.getContextPath()%>/market/market?siteId=${siteId}"><s:property
                            value="name"/></a>
					</span>
                </s:iterator>
            </s:if>
            <s:if test="site.type.typeId==3">
                <s:iterator value="site.parent.children">
                    <s:if test="site.siteId==siteId"><span class="a-bar" style="color:#F00"><s:property value="name"/></span></s:if>
                    <s:else>
						<span class="a-bar">
                        <a href="<%=request.getContextPath()%>/market/market?siteId=${siteId}"><s:property
                                value="name"/></a>
						</span>
                    </s:else>
                </s:iterator>
            </s:if>
        </div>
		<div class="clear"></div>
        <!--Begin menu  -->
        <div class="content">
            <div id="menu_out">
                <div id="menu_in">
                    <div id="menu">
                        <ul id="nav">
                            <li>
                                <a class="nav_off" id="mynav0"
                                   href="<%=request.getContextPath()%>/market/market?siteId=${site.siteId}&itemType=1"><span>供 应</span></a>
                            </li>
                            <li>
                                <a class="nav_off" id="mynav1"
                                   href="<%=request.getContextPath()%>/market/market?siteId=${site.siteId}&itemType=2"><span>需 求</span></a>
                            </li>
                        </ul>
                        <div id="menu_con">
                            <div id="qh_con0" style="DISPLAY: none">
                                <ul>
                                    <li>
                                        <a href="<%=request.getContextPath()%>/market/market?siteId=${site.siteId}&itemType=1&cateId=1"><span>生产服务</span></a>
                                    </li>
                                    <li class="menu_line2"></li>
                                    <li>
                                        <a href="<%=request.getContextPath()%>/market/market?siteId=${site.siteId}&itemType=1&cateId=2"><span>生产资料</span></a>
                                    </li>
                                    <li class="menu_line2"></li>
                                    <li>
                                        <a href="<%=request.getContextPath()%>/market/market?siteId=${site.siteId}&itemType=1&cateId=3"><span>农产品</span></a>
                                    </li>
                                </ul>
                            </div>
                            <div id=qh_con1 style="DISPLAY: none">
                                <ul>
                                    <li>
                                        <a href="<%=request.getContextPath()%>/market/market?siteId=${site.siteId}&itemType=2&cateId=1"><span>生产服务</span></a>
                                    </li>
                                    <li class="menu_line2"></li>
                                    <li>
                                        <a href="<%=request.getContextPath()%>/market/market?siteId=${site.siteId}&itemType=2&cateId=2"><span>生产资料</span></a>
                                    </li>
                                    <li class="menu_line2"></li>
                                    <li>
                                        <a href="<%=request.getContextPath()%>/market/market?siteId=${site.siteId}&itemType=2&cateId=3"><span>农产品</span></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--End menu  -->
        <!--Begin list & page  -->
        <div class="content">
            <div id="info" class="list">
                <ul>
                    <s:iterator value="markets">
                        <li>
                            <a href="<%=request.getContextPath()%>/market/market/${itemId }"><s:property value="itemTitle"/> </a> (图)
                            [<a href="<%=request.getContextPath()%>/farm/farm/${farm.farmId}"><s:property
                                value="farm.farmName"/></a>/<a
                                href="<%=request.getContextPath()%>/user/member/${member.memberId }"><s:property
                                value="member.realname"/></a>]
                            <s:date format="M月d日" name="createDate"/>
                            <s:if test="member.memberId==profile.memberId">
                                <a href="javascript:edit(${itemId})">编辑</a>
                                <a href="javascript:del(${itemId})">删除</a>
                            </s:if>
                        </li>
                    </s:iterator>
                </ul>
            </div>
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
            <form id="frmSearch" style="display:none;" method="get" action="<%=request.getContextPath()%>/market/market"
                  onsubmit="dos(1);">
                <input type="hidden" id="siteId" name="siteId" value="${site.siteId}"/>
                <input type="hidden" id="itemType" name="itemType" value="${itemType}"/>
                <input type="hidden" id="cateId" name="cateId" value="${cateId}"/>
                <s:hidden id="page.current" name="page.current"/>
                <s:submit cssClass="formbutton" method="index"/>
            </form>
        </div>
        <!--End list & page -->
        <!--Begin 发布 -->
        <s:if test="%{#request.isFarmer}">
			<div class="title">
				<div class="t-text">
					<div class="tag-notepad"></div>
					发布信息
				</div>
			</div>
            <div class="content">
                <div class="box" style="border:1px solid #666;">
                    <s:form id="frmMarket" theme="simple" method="post" style="padding:10px"
                            action="%{#request.contextPath}/market/market?siteId=%{site.siteId}&itemType=%{#request.itemType}&cateId=%{#request.cateId}">
                        项目标题：<s:textfield id="itemTitle" name="market.itemTitle" cssStyle="width:250px"/><br/>
                        <s:textarea id="contents" name="market.contents" cols="60" rows="4"/><br/>

                        <div id="imgBox">
                            <div id="imgBoxTitle" style="display:none">图片列表</div>
                        </div>
                        <s:submit id="submit" value="发布" method="create"/>
                    </s:form>
                    <div>
                        <div class="divTitle"><span>上传图片</span></div>
                        <div class="clear"></div>
                        标题：<input type='text' size='8' id='title' value=''/>
                        说明：<input type='text' size='10' id='description' value=''/>
                        <input id="upload" type="file" size="15" name="upload" class="input" contenteditable="false"/>
                        <button class="formbutton" id="buttonUpload" onclick="return addImg();">添 加</button>
                    </div>
                </div>
            </div>
        </s:if>
        <!--End 发布 -->
		<span class="blank6"></span>
        <div class="box">
			<div class="title">
				<div class="t-text">
					<div class="tag-message"></div>
					在淘宝搜索种苗
				</div>
			</div>
            <div class="content">
                <script type='text/javascript'>
                    alimama_pid = 'mm_14862528_0_0';
                    alimama_type = 'g';
                    alimama_tks = {};
                    alimama_tks.style_i = 1;
                    alimama_tks.lg_i = 1;
                    alimama_tks.w_i = 572;
                    alimama_tks.h_i = 69;
                    alimama_tks.btn_i = 1;
                    alimama_tks.txt_s = '';
                    alimama_tks.hot_i = 1;
                    alimama_tks.hc_c = '#999999';
                    alimama_tks.c_i = 1;
                    alimama_tks.cid_i = 0;
                </script>
                <script type='text/javascript' src='http://a.alimama.cn/inf.js'></script>
            </div>
        </div>
    </div>
</div>
<div id="rightbar" style="width:29%">
	<div class="box margin-top">
        <div class="textBox">
			<div class="t-title">最新种苗</div>
			<div class="t-body">
	            <table cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style="width:100%;border: 1px solid #E6E6E6;">
	                <tr>
	                    <td rowspan="2" align="center">
	                        <div style="margin:5px auto; width: 80px;height:80px;">
	                            <a target="_blank"
	                               href="http://s.click.taobao.com/t_1?i=qzi%2FQbxabpclCA%3D%3D&p=mm_14862528_0_0&n=12"
	                               style="width: 80px; margin:0px;padding:0px;height: 80px; overflow:hidden;">
	                                <img style="margin:0px;border:none;"
	                                     src="http://image.taobao.com/bao/uploaded/http://img08.taobaocdn.com/bao/uploaded/i8/T1APXoXm8bXXackVTb_092705.jpg_sum.jpg"/>
	                            </a>
	                        </div>
	                        <div class="clearing"></div>
	                    </td>
	                    <td colspan="2"><a target="_blank"
	                                       href="http://s.click.taobao.com/t_1?i=qzi%2FQbxabpclCA%3D%3D&p=mm_14862528_0_0&n=12"
	                                       style="height:40px;width:180px;margin:5px;line-height:20px;color:#0000FF">低价出售
	                        金针菜苗 黄花菜苗 蔬菜种苗</a></td>
	                </tr>
	                <tr>
	                    <td nowrap="nowrap"><span
	                            style="font-weight:600;margin:5px;line-height:30px;color:#CC0000;">5.0元</span>&nbsp;</td>
	                    <td nowrap="nowrap" width="100px">
	                        <a target="_blank"
	                           href="http://s.click.taobao.com/t_1?i=qzi%2FQbxabpclCA%3D%3D&p=mm_14862528_0_0&n=12">
	                            <img name=""
	                                 style="margin:0px; pandding:0px;line-height:24px;vertical-align: text-bottom;border:none;"
	                                 src="http://img.alimama.cn/images/tbk/cps/fgetccode_btn.gif"/>
	                        </a>
	                    </td>
	                </tr>
	            </table>
			</div>
        </div>
        <div class="textBox">
			<div class="t-title">淘宝蔬菜种子店</div>
			<div class="t-body">
	        	<iframe frameborder="0" marginheight="0" marginwidth="0" border="0" id="alimamaifrm" name="alimamaifrm"
	                scrolling="no" height="142px" width="100%"
	                src="http://z.alimama.com/cpscode.php?pid=mm_14862528_0_0&w=100%&h=142&uid=10844094&m=41&t=1000&bgc=FFFFFF&bdc=E6E6E6&tc=0000FF&sc=1047"></iframe>
			</div>
	    </div>
	</div>
</div>
<div class="clear"></div>
</div>
<div id="footer">
    <s:include value="../f.jsp"/>
</div>
</div>
</body>
</html>
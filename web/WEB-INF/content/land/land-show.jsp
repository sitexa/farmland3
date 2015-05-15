<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>
        <s:text name="root.title">
            <s:param>${land.landName}</s:param>
        </s:text>
    </title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/ext/ext-all-debug.js"></script>
    <link href="<%=request.getContextPath()%>/land/land.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/pic.js"></script>
    <script type="text/javascript">
        Ext.BLANK_IMAGE_URL = '<%=request.getContextPath()%>/ext/resources/images/default/s.gif';
        Ext.onReady(function() {
            var tabs1 = new Ext.TabPanel({
                renderTo:'landtab',
                width:'100%',
                activeTab:${tabIndex},
                frame:false,
                plain:true,
                defaults:{autoHeight:true},
                autoLoad:false,
                items:[
                    {
                        title:'简要介绍',
                        contentEl:'baseInfo'
                    },
                    {
                        title:'精彩图片',
                        contentEl:'pictures'
                    },
                    {
                        title:'服务项目',
                        contentEl:'factions'
                    },
                    {
                        title:'种子种苗',
                        contentEl:'seeds'
                    },
                    {
                        title:'农庄列表',
                        contentEl:'farms'
                    }
                ]
            });
        });
        function showPic(id) {
            which = id;
            applyeffect();
            document.getElementById("photoslider").src = photos[which];
            document.getElementById("photodescription").innerHTML = descriptions[which];
            playeffect();
            keeptrack();
        }
        function buyfarm(landId) {
            var url = "<%=request.getContextPath()%>/land/buyfarm?landId=" + landId;
            window.open(url);
        }
        function buyfarm2(farmId) {
            var url = "<%=request.getContextPath()%>/land/buyfarm/" + farmId;
            window.open(url);
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
                <div class="t4">
                    <div class="t4-t">
                        ${land.landName}
                    </div>
                </div>
                <div class="content">
                    <div style="float:right;">
                        <s:include value="map.jsp"/>
                    </div>
                    <div style="position:relative;top:1px;right:1px;float:right">
                        <s:if test="land.status==1">
                            <img src="<%=request.getContextPath()%>/land/img/buy1.jpg" onclick="buyfarm(${land.landId});" alt="购买农庄"/>
                        </s:if>
                        <s:else>
                            <img src="<%=request.getContextPath()%>/land/img/buy2.jpg" alt="购买农庄"/>
                        </s:else>
                    </div>
                    地　区　：<s:if test="land.site.city!=null"><s:property value="land.site.city.name"/> > </s:if><s:property value="land.site.name"/><br/>
                    农场主　：<s:property value="land.lord.realname"/><br/>
                    地　址　：<s:property value="land.address"/><br/>
                    类　型　：<s:property value="land.landType"/><br/>
                    ${land.description}
                    <div class="clear"></div>
                </div>
            </div>
            <div id="landtab" class="box">
                <div id="baseInfo" class="x-hide-display">
                    <div class="box" style="border:0">
                        ${land.description}
                    </div>
                </div>
                <div id="pictures" class="x-hide-display">
                    <div class="box" style="border:0">
                        <s:if test="pictures.size()>0">
                            <div class="box" style="width:98%;overflow-x:scroll;border:0">
                                <table border="0">
                                    <tr>
                                        <s:iterator value="pictures" status="its">
                                            <script>
                                                photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/l/${land.landId}/${picUrl}";
                                                titles[${its.index}] = "${picTitle}";
                                                descriptions[${its.index}] = "${description}";
                                            </script>
                                            <td>
                                                <img src="<%=request.getContextPath()%>/image?type=land&picId=${picId}" alt="点击看大图"
                                                     onclick="showPic(${its.index})"/>
                                            </td>
                                        </s:iterator>
                                        <script>
                                            var preloadedimages = new Array();
                                            for (i = 0; i < photos.length; i++) {
                                                preloadedimages[i] = new Image();
                                                preloadedimages[i].src = photos[i];
                                            }
                                        </script>
                                    </tr>
                                </table>
                            </div>
                            <div class="content">
                                <script>
                                    if (photos.length > 0) {
                                        document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward()" style="filter:revealTrans(duration=2,transition=23);width:100%" border=0>');
                                        document.write('<div id="photodescription" style="text-align:center">' + descriptions[0] + '</div>');
                                    }
                                </script>
                            </div>
                        </s:if>
                    </div>
                </div>
                <div id="factions" class="x-hide-display">
                    <div class="box" style="border:0">
                        <s:iterator value="factions" status="its">
                            <div style="border-bottom:1px solid #ccf;margin:5px;padding:4px;">
                                <a href="<%=request.getContextPath()%>/land/faction/${factionId}">
                                    <img src="<%=request.getContextPath()%>/image?type=service&picId=${actionId}" style="float:left;margin:4px;"/>
                                </a>
                                <a href="<%=request.getContextPath()%>/land/faction/${factionId}"><b>${actionName}</b></a><br>
                                <b>服务价格：</b>￥${expense}<br/>
                                <b>服务说明：</b>${contents}<br/>
                            </div>
                            <div class="clear"></div>
                        </s:iterator>
                        <s:include value="paging1.jsp"/>
                        <form id="pageFrm1" action="<%=request.getContextPath()%>/land/land/${land.landId}"
                              method="get" style="display:none;">
                            <s:hidden id="page1.current" name="page1.current" value="1"/>
                            <s:hidden id="tabIndex" name="tabIndex" value="2"/>
                        </form>
                    </div>
                </div>
                <div id="seeds" class="x-hide-display">
                    <div class="box" style="border:0">
                        <s:iterator value="seeds" status="its">
                            <div style="border-bottom:1px solid #ccf;margin:5px;padding:4px;">
                                <a href="<%=request.getContextPath()%>/land/seed/${seedId}">
                                    <img src="<%=request.getContextPath()%>/image?type=seed&picId=${seedId}" style="float:left;margin:4px;"/>
                                </a>
                                <a href="<%=request.getContextPath()%>/land/seed/${seedId}"><b>${seedName}</b></a>
                                <b>价格：</b>${price}<br/>
                                <b>说明：</b>${description}<br/>

                                <div class="clear"></div>
                            </div>
                        </s:iterator>
                    </div>
                    <s:include value="paging2.jsp"/>
                    <form id="pageFrm2" action="<%=request.getContextPath()%>/land/land/${land.landId}"
                          method="get" style="display:none;">
                        <s:hidden id="page2.current" name="page2.current" value="1"/>
                        <s:hidden id="tabIndex" name="tabIndex" value="3"/>
                    </form>
                </div>
                <div id="farms" class="x-hide-display">
                    <div class="box">
                        <table cellpadding="2" cellspacing="2" width="100%">
                            <thead style="background-color:#ccccff">
                            <th>农庄编号</th>
                            <th>农庄名称</th>
                            <th>农庄面积</th>
                            <th>农庄主</th>
                            </thead>
                            <tbody>
                            <s:iterator value="farms">
                                <tr id="${farmId}">
                                    <s:if test="member!=null">
                                        <td>
                                            <a href="<%=request.getContextPath()%>/farm/farm/${farmId}">${farmId}</a>
                                        </td>
                                        <td>
                                            <a href="<%=request.getContextPath()%>/work/work/${farmId}">${farmName}</a>
                                        </td>
                                        <td>${acreage}m<sup>2</sup></td>
                                        <td>
                                            <a href="<%=request.getContextPath()%>/user/member/${member.memberId}">${member.realname}</a>
                                        </td>
                                        <td></td>
                                    </s:if>
                                    <s:else>
                                        <td>
                                            <a href="<%=request.getContextPath()%>/farm/farm/${farmId}">${farmId}</a>
                                        </td>
                                        <td>${farmName}</td>
                                        <td>${acreage}m<sup>2</sup></td>
                                        <td>
                                            <img src="<%=request.getContextPath()%>/land/img/buy1.jpg" onclick="buyfarm2(${farmId});" alt="购买农庄"/>
                                        </td>
                                    </s:else>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <s:include value="paging3.jsp"/>
                    <form id="pageFrm3" action="<%=request.getContextPath()%>/land/land/${land.landId}"
                          method="get" style="display:none;">
                        <s:hidden id="page3.current" name="page3.current" value="1"/>
                        <s:hidden id="tabIndex" name="tabIndex" value="4"/>
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
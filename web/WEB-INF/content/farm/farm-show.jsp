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
            <s:param>${farm.farmName}</s:param>
        </s:text>
    </title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/ext/ext-all-debug.js"></script>
    <script src="<%=request.getContextPath()%>/script/jquery.js" type="text/javascript"></script>
    <link href="<%=request.getContextPath()%>/farm/farm.css" rel="stylesheet" type="text/css"/>
    <script src="<%=request.getContextPath()%>/farm/farm.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/script/pic.js" type="text/javascript"></script>
    <script type="text/javascript">
        Ext.BLANK_IMAGE_URL = '<%=request.getContextPath()%>/ext/resources/images/default/s.gif';
        Ext.onReady(function() {
            var tabs1 = new Ext.TabPanel({
                renderTo:'farmtab',
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
                        title:'农庄作物',
                        contentEl:'crops'
                    },
                    {
                        title:'操作记录',
                        contentEl:'logs'
                    },
                    {
                        title:'农庄文章',
                        contentEl:'posts'
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
                        农庄资料-${farm.farmName}
                    </div>
                </div>
                <div class="content">
                    农庄编号:${farm.farmId}<br>
                    农庄名称:${farm.farmName}<br>
                    农场名称:${farm.land.landName}<br>
                    社区名称:${farm.site.name}<br>
                    农庄面积:${farm.acreage}平方米<br>
                    大庄主:${farm.member.realname}<br>
                    <s:if test="farm.owners.size>0">
                        二庄主:
                        <s:iterator value="farm.owners" status="its">
                            ${member.realname}
                        </s:iterator>
                        <br>
                    </s:if>
                    租赁时间:<s:date name="farm.rentDate" format="yyyy-MM-dd"/>
                    -- <s:date name="farm.endDate" format="yyyy-MM-dd"/>
                    <br>
                    标语牌:${farm.slogan}<br>
                </div>
            </div>
            <div class="clear"></div>
            <div id="farmtab" class="box">
                <div id="baseInfo" class="x-hide-display">
                    <div class="box" style="border:0">
                        农庄编号:${farm.farmId}<br>
                        农庄名称:${farm.farmName}<br>
                        农场名称:${farm.land.landName}<br>
                        社区名称:${farm.site.name}<br>
                        农庄面积:${farm.acreage}平方米<br>
                        大庄主:${farm.member.realname}<br>
                        <s:if test="farm.owners.size>0">
                            二庄主:
                            <s:iterator value="farm.owners" status="its">
                                ${member.realname}
                            </s:iterator>
                            <br>
                        </s:if>
                        租赁时间:<s:date name="farm.rentDate" format="yyyy-MM-dd"/>
                        -- <s:date name="farm.endDate" format="yyyy-MM-dd"/>
                        <br>
                        标语牌:${farm.slogan}<br>
                    </div>
                </div>
                <div id="pictures" class="x-hide-display">
                    <div class="box">
                        <s:if test="farm.farmPictures.size()>0">
                            <div class="box" style="width:98%;overflow-x:scroll">
                                <table>
                                    <tr>
                                        <s:iterator value="farm.farmPictures" status="its">
                                            <script>
                                                photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/f/${farm.farmId}/${picUrl}";
                                                titles[${its.index}] = "${picTitle}";
                                                descriptions[${its.index}] = "${description}";
                                            </script>
                                            <td>
                                                <img src="<%=request.getContextPath()%>/image?type=farm&picId=${picId}" alt="点击看大图"
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
                <div id="crops" class="x-hide-display">
                    <table width="100%">
                        <tr>
                            <th>名称</th>
                            <th>数量</th>
                            <th>生命力</th>
                            <th>成熟度</th>
                            <th>种植日期</th>
                            <th>状态</th>
                        </tr>
                        <s:iterator value="cropses" status="its">
                            <tr class='<s:if test="#its.odd">odd</s:if>'>
                                <td>${seed.seedName}</td>
                                <td>${seedNumber}</td>
                                <td>${vitalPower}</td>
                                <td>${maturity}</td>
                                <td>${startDate}</td>
                                <td>${remark}</td>
                            </tr>
                        </s:iterator>
                    </table>
                    <s:include value="paging1.jsp"/>
                    <form id="pageFrm1" action="<%=request.getContextPath()%>/farm/farm/${farm.farmId}"
                          method="get" style="display:none;">
                        <s:hidden id="page1.current" name="page1.current" value="1"/>
                        <s:hidden id="tabIndex" name="tabIndex" value="2"/>
                    </form>
                </div>
                <div id="logs" class="x-hide-display">
                    <div class="box" style="border:0">
                        <table width="100%">
                            <tr>
                                <th>名称</th>
                                <th>内容</th>
                                <th>时间</th>
                                <th>数量</th>
                                <th>费用</th>
                                <th>模式</th>
                                <th>状态</th>
                            </tr>
                            <s:iterator value="farmings" status="its">
                                <tr class='<s:if test="#its.odd">odd</s:if>'>
                                    <td>${faction.actionName}</td>
                                    <td>${contents}</td>
                                    <td>${startTime}</td>
                                    <td>${quantity}</td>
                                    <td>${amount}</td>
                                    <td>${paymode}</td>
                                    <td>${state}</td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <s:include value="paging2.jsp"/>
                    <form id="pageFrm2" action="<%=request.getContextPath()%>/farm/farm/${farm.farmId}"
                          method="get" style="display:none;">
                        <s:hidden id="page2.current" name="page2.current" value="1"/>
                        <s:hidden id="tabIndex" name="tabIndex" value="3"/>
                    </form>
                </div>
                <div id="posts" class="x-hide-display">
                    <div class="list">
                        <ul>
                            <s:iterator value="posts" status="its">
                                <li class='<s:if test="#its.odd">odd</s:if>'>
                                    <span style="float:right"><s:date format="M月dd日HH时" name="createDate"/></span>
                                    <a href="<%=request.getContextPath()%>/post/csa/${id}">${name}</a>
                                    <a href="<%=request.getContextPath()%>/user/member/${creator.memberId}">${creator.realname}</a>
                                </li>
                            </s:iterator>
                        </ul>
                    </div>
                    <s:include value="paging3.jsp"/>
                    <form id="pageFrm3" action="<%=request.getContextPath()%>/farm/farm/${farm.farmId}"
                          method="get" style="display:none;">
                        <s:hidden id="page3.current" name="page3.current" value="1"/>
                        <s:hidden id="tabIndex" name="tabIndex" value="4"/>
                    </form>
                </div>
            </div>
        </div>
        <div id="rightbar">
            <s:include value="farm-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
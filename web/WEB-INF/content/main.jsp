<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>
        <s:text name="root.title">
            <s:param>
                首页
            </s:param>
        </s:text>
    </title>
    <link rel="shortcut icon" href="/images/favicon.ico"/>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/style/remind.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/swissarmy.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/script/slide.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/slide.js"></script>
    <script type="text/javascript">
        var slides = [];
        slides.width = 372;
        slides.height = 230;

        <s:iterator value="newPictures" status="its">
        slides[${its.index}] = ['<%=AppConfig.getProperty("imgurl")%>/p/${post.id}/${picUrl}',
            null,
            '/post/post/${post.id}',
            '_new'];
        </s:iterator>

        turnImg(1000, 2000);

        function initialize() {
            $("#remind").appendTo("#container");
            var p = getPageSize();
            window.onscroll = function() {
                $("#remind").css("top", p.windowHeight - 185 + getScrollTop());
            };
            window.onscroll();
            remind();
            setInterval('remind()', 60 * 1000);
        }

        function remind() {
            $.get(
                    "<%=request.getContextPath()%>/user/leave-message!getNewLeaveMessageCount?d=" + Math.random(),
            {},
                 function (count) {
                     if (count) {
                         if (count > 0) {
                             var link = "<a href='<%=request.getContextPath()%>/user/leave-message'>您有新的留言(" + count + ")。</a><br />";
                             $("#remind div.linkbox").html(link);
                             $("#remind").show();
                         } else {
                             $("#remind").hide();
                         }
                     } else {
                         $("#remind").hide();
                     }
                 }
                    );
        }

    </script>
</head>
<body onload="initialize()">
<div id="container">
<div id="header">
    <s:include value="h.jsp"/>
</div>
<div id="mainwrapper">
<div id="leftbar">
    <s:include value="csa.html"/>
</div>
<div class="map">
    <div class="mapbox"><s:include value="landmap.jsp"/></div>
    <div class="mapinfo">
        <div class="t">
            <div class="t-t">
                <div class="t-m"><a href="<%=request.getContextPath()%>/land/land">更多</a></div>
                您身边的CSA农场
            </div>
        </div>
        <div class="sitecrumb" style="border:1px gray solid;padding:2px;margin:1px;background-color:#ffffcc">
            <s:if test="site.parent.parent.parent!=null">
                <a href="<%=request.getContextPath()%>/main/${site.parent.parent.parent.siteId}">
                    <s:property value="site.parent.parent.parent.name"/>
                </a> >
            </s:if>
            <s:if test="site.parent.parent!=null">
                <a href="<%=request.getContextPath()%>/main/${site.parent.parent.siteId}">
                    <s:property value="site.parent.parent.name"/>
                </a> >
            </s:if>
            <s:if test="site.parent!=null">
                <a href="<%=request.getContextPath()%>/main/${site.parent.siteId}">
                    <s:property value="site.parent.name"/>
                </a> >
            </s:if>
            <s:property value="site.name"/>
            <s:if test="site.children.size>0"> > </s:if>
            <s:iterator value="site.children">
                <a href="<%=request.getContextPath()%>/main/${siteId}"><s:property value="name"/></a>&nbsp;&nbsp;
            </s:iterator>
        </div>
        <div class="sitecrumb">
            <s:iterator value="lands" status="its">
                <s:if test="#its.index<30">
                    <a href="<%=request.getContextPath()%>/land/land/${landId}"><s:property value="landName"/></a> &nbsp;&nbsp;&nbsp;
                </s:if>
            </s:iterator>
        </div>
        There is an important concept woven into the CSA model that takes the arrangement beyond the usual commercial transaction. That is the notion of
        shared risk. When originally conceived, the CSA was set up differently than it is now. A group of people pooled their money, bought a farm, hired a
        farmer, and each took a share of whatever the farm produced for the year. If the farm had a tomato bonanza, everyone put some up for winter. If a
        plague of locusts ate all the greens, people ate cheese sandwiches. Very few such CSAs exist today, and for most farmers, the CSA is just one of the
        ways their produce is marketed. They may also go to the farmers market, do some wholesale, sell to restaurants, etc. Still, the idea that "we're in
        this together" remains. On some farms it is stronger than others, and CSA members may be asked to sign a policy form indicating that they agree to
        accept without complaint whatever the farm can produce.
    </div>
    <div class="clear"></div>
</div>
<div id="centerbar">
    <div class="box">
        <div class="t3">
            <div class="t3-t">
                精采图片
            </div>
        </div>
        <div class="content">
            <div>
                <script type="text/javascript">
                    new inter_slide(slides);
                </script>
            </div>
        </div>
    </div>
    <div class="box">
        <div class="t">
            <div class="t-t">
                <div class="t-m"><a href="<%=request.getContextPath()%>/play/play">更多</a></div>
                农场新闻
            </div>
        </div>
        <div class="content">
            <div id="news" class="list">
                <ul>
                    <s:iterator value="news">
                        <li>
                            <div class="right">[<s:date name="createDate" format="yyyy-MM-dd"/>]</div>
                            <a href="<%=request.getContextPath()%>/play/play/${id}">${name}</a>
                        </li>
                    </s:iterator>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="box">
        <div class="t">
            <div class="t-t">
                <div class="t-m"><a href="<%=request.getContextPath()%>/post/farm-post">更多</a></div>
                农庄风采
            </div>
        </div>
        <div class="content" style="padding:2px 0">
            <div id="farmposts" class="list">
                <ul>
                    <s:iterator value="farmPosts">
                        <li>
                            <div class="right">
                                <a href="<%=request.getContextPath()%>/work/work/${farm.farmId}">${farm.farmName }</a>
                            </div>
                            <a href="<%=request.getContextPath()%>/post/farm-post/${id}"
                               title="[<s:date name="createDate" format="yyyy-MM-dd"/>]">${post.name} </a>
                        </li>
                    </s:iterator>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<div id="rightbar">
    <div class="box" style="height:258px;overflow-y:hidden;">
        <div class="t">
            <div class="t-t">
                明星农场
            </div>
        </div>
        <div class="content" style="padding:2px">
            <a href="<%=request.getContextPath()%>/land/land/${starLand.landId}"><img src="<%=request.getContextPath()%>/image?type=land&picId=<s:property value='starLand.landPictures.iterator().next().picId'/>" alt=""
                 title="" width="80" height="80" style="float:left;padding-right:4px"/></a>
            <a href="<%=request.getContextPath()%>/land/land/${starLand.landId}">${starLand.landName}</a>
            [${starLand.site.state.name} ${starLand.site.city.name}]<br>${starLand.description}
            <div class="clear"></div>
        </div>
    </div>
    <div class="box">
        <div class="t">
            <div class="t-t">
                农场通告
            </div>
        </div>
        <div class="content" style="padding:2px 0">
            <div id="notices" class="list">
                <ul>
                    <s:iterator value="notices" status="its">
                        <li>
                            <div class="right">${creator.realname }</div>
                            <a href="<%=request.getContextPath()%>/play/play/${id}"
                               title="[<s:date name="createDate" format="yyyy-MM-dd"/>]">
                               <s:if test="id==6070"><span style="color:red;font-size:16px;">${name}</span> </s:if>
                               <s:else>${name}</s:else>
                            </a>
                        </li>
                    </s:iterator>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="mod-box">
        <div class="box" style="margin-bottom:6px;+*margin-bottom:6px;_margin-bottom:2px;">
            <div class="t4">
                <div class="t4-t">
                    <div class="t-m"><a href="<%=request.getContextPath()%>/user/member!search">更多</a></div>
                    新注册会员
                </div>
            </div>
            <div class="content">
                <div id="members" class="list">
                    <ul>
                        <s:iterator value="newMembers">
                            <li>
                                <div class="right"><s:if test="site!=null">
                                    [${site.state.name}-${site.name}]
                                </s:if></div>
                                <a href="<%=request.getContextPath()%>/user/member/${memberId}">${realname}</a>
                            </li>
                        </s:iterator>
                    </ul>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
        <div class="box" style="margin-bottom:6px;+*margin-bottom:6px;_margin-bottom:2px;">
            <div class="t4">
                <div class="t4-t">
                    <div class="t-m"><a href="<%=request.getContextPath()%>/farm/farm">更多</a></div>
                    新入驻农庄
                </div>
            </div>
            <div class="content">
                <div id="farms" class="list">
                    <ul>
                        <s:iterator value="newFarms">
                            <li>
                                <div class="right">[${site.state.name}-${site.name}]</div>
                                <a href="<%=request.getContextPath()%>/work/work/${farmId}">${farmName}</a>
                            </li>
                        </s:iterator>
                    </ul>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="clear"></div>
<div class="box">
    <div class="t2">
        <div class="t2-t">
            人气农庄
        </div>
    </div>
    <div class="slideBox">
        <ul>
            <s:iterator value="hotFarms">
                <li>
                    <a href="<%=request.getContextPath()%>/work/work/${farm.farmId}">
                        <img src="<%=request.getContextPath()%>/image?type=farm&picId=${picId}"
                             title="${farm.farmName}" style="border:1px solid white;"/>
                    </a>
                    <a href="#">${farm.farmName}</a>
                </li>
            </s:iterator>
        </ul>
    </div>
</div>
</div>
</div>
<div class="clear"></div>
<div id="footer">
    <s:include value="f.jsp"/>
</div>
<div id="remind" class="dialog">
    <div class="header">
        <span class="h">系统提示</span>
        <label id="x" onclick="$('#remind').css('display','none')"></label>
    </div>
    <div class="contents">
        <div class="linkbox"></div>
    </div>
</div>
</body>
</html>
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
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/style/remind.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/swissarmy.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/script/slide.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/slide.js"></script>
    <script type="text/javascript">
        var slides = [];
        slides.width = 350;
        slides.height = 215;

        <s:iterator value="newPictures" status="its">
        slides[${its.index}] = ['<%=AppConfig.getProperty("imgurl")%>/p/${post.id}/${picUrl}',
            null,
            '/post/post/${post.id}',
            '_new'];
        </s:iterator>

        onload = function() {
            $.formatLink("#activities a", 43);
            $.formatLink("#activities a,#markets a,#posts a,#farmposts a,#news a", 38);
            $.formatLink("#notices a,#members a,#farms a,#lands a", 33);

            $("#remind").appendTo("#container");
            var p = getPageSize();
            window.onscroll = function() {
                $("#remind").css("top", p.windowHeight - 185 + getScrollTop());
            };
            window.onscroll();
            remind();
            setInterval('remind()', 60 * 1000);
            turnImg(1000, 2000);
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
<body>
<div id="container">
    <div id="header">
        <s:include value="h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar">
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
                <div class="content" style="padding:2px 0px">
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
        <div id="centerbar">
            <div class="box" style="height:267px; +*height:267px; _height:257px;">
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
                        社区活动
                    </div>
                </div>
                <div class="content">
                    <div id="activities" class="list">
                        <ul>
                            <s:iterator value="activities">
                                <li>
                                    <div class="right">${creator.realname }</div>
                                    <a href="<%=request.getContextPath()%>/play/play/${id}"
                                       title="[<s:date name="createDate" format="yyyy-MM-dd"/>]">${name} </a>
                                </li>
                            </s:iterator>
                        </ul>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
        <div id="rightbar">
            <div class="box">
                <div class="t">
                    <div class="t-t">
                        <div class="t-m"></div>
                        农场通告
                    </div>
                </div>
                <div class="content" style="padding:2px 0px">
                    <div id="notices" class="list">
                        <ul>
                            <s:iterator value="notices">
                                <li>
                                    <div class="right">${creator.realname }</div>
                                    <a href="<%=request.getContextPath()%>/play/play/${id}"
                                       title="[<s:date name="createDate" format="yyyy-MM-dd"/>]">${name} </a>
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
                            <div class="t-m"><a href="<%=request.getContextPath()%>/buy/farm!farms">更多</a></div>
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
        <div class="edge">&nbsp;</div>
        <div class="box" style="margin-left:6px;margin-right:6px;">
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
    <div id="footer">
        <s:include value="f.jsp"/>
    </div>
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
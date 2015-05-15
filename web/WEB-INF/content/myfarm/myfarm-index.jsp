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
    <link href="<%=request.getContextPath()%>/myfarm/myfarm.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/myfarm/myfarm.js"></script>
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
    <script type="text/javascript">
        var url = "<%=request.getContextPath()%>";
        var imgurl = "<%=AppConfig.getProperty("imgurl")%>";

        function load_tool_box(id) {
            var dialogLeft = (screen.width - 650) / 2;
            var dialogTop = (screen.height - 550) / 2;
            var farmId = $("#farm_farmId").val();
            if (id == "shop") {
                var path = url + "/myfarm/shop?farmId=" + farmId;
                window.open(path, "", 'left=' + dialogLeft + ',top=' + dialogTop + ',width=612,height=502,location=no,resizable=no,scrollbars=no');
            } else if (id == "store") {
                var path = url + "/myfarm/store?farmId=" + farmId;
                window.open(path, "", 'left=' + dialogLeft + ',top=' + dialogTop + ',width=612,height=502,location=no,resizable=no,scrollbars=no');
            } else if (id == "farmings") {
                var path = url + "/myfarm/farming!window?farmId=" + farmId;
                window.open(path, "", 'left=' + dialogLeft + ',top=' + dialogTop + ',width=612,height=502,location=no,resizable=no,scrollbars=no');
            } else if (id == "post") {
                var path = url + "/post/farm-post/new?farmId=" + farmId;
                window.open(path);
            } else if (id == "picture") {
                var path = url + "/myfarm/myfarm!upload?farm.farmId=" + farmId;
                window.open(path, "", 'left=' + dialogLeft + ',top=' + dialogTop + ',width=550,height=100,location=yes,resizable=no');
            } else {
                var path = url + "/myfarm/myfarm!tools";
                path += "?factionId=" + id;
                path += "&farmId=" + farmId;
                window.open(path, "", 'left=' + dialogLeft + ',top=' + dialogTop + ',width=612,height=502,location=no,resizable=no,scrollbars=no');
            }
        }

        function load_crops_box(id) {
            if (id == "0") {
                $("#crops-status").css("display", "block");
                $("#crops-content").css("display", "none");
            } else {
                $("#crops-status").css("display", "none");
                $("#crops-content").css("display", "block");
                $("#crops-panel li.in").removeClass("in");
                $("#crops-panel li#" + id).addClass("in");
                var farmId = $("#farm_farmId").val();
                var surl = "<%=request.getContextPath()%>/myfarm/myfarm!status";
                var parm = {"cropsId":id,"farmId":farmId};

                $("#crops-content").load(surl, parm, null);
            }
        }


        onload = function() {
            $("#tool-panel").ready(function() {
                $("#tool-panel ul li").each(function() {
                    $(this).click(function() {
                        $(".#tool-panel li.in").removeClass("in");
                        $(this).addClass("in");
                        var factionId = $(this).attr("id");
                        load_tool_box(factionId);
                    });
                });
            });

            $("#crops-panel").ready(function() {
                $("#crops-panel ul li").each(function() {
                    $(this).click(function() {
                        $(".#crops-panel li.in").removeClass("in");
                        $(this).addClass("in");
                        var cropsId = $(this).attr("id");
                        load_crops_box(cropsId);
                    });
                });
            });

            var factionId = $("#factionId").val();
            if (factionId != "" && factionId != "undefined") {
                load_tool_box(factionId);
            }
            var cropsId = $("#crops_cropsId").val();
            if (cropsId != "" && cropsId != "undefined") {
                load_crops_box(cropsId);
            }

            $("#tool-panel ul img,#crops-panel ul img").each(function() {
                $.title(this, -35, -10)
            });

            $.formatLink("#newPosts a", 22);
            $.formatLink("#newActions a,#newNotices a,#newPlans a,#friendFarms a,#neFarms a,#newMembers a", 22);
        }
    </script>
</head>
<body>
<div id="container">
<div id="header">
    <s:include value="../h.jsp"/>
</div>
<div id="mainwrapper" style="background-color:#fff;">
<s:hidden name="farm.farmId" id="farm_farmId"/>
<s:hidden name="crops.cropsId" id="crops_cropsId"/>
<s:hidden name="seed.seedId" id="seed_seedId"/>
<s:hidden name="faction.factionId" id="factionId"/>
<div class="member" style="margin-left:22px">
    <table border='0' cellpadding="0" cellspacing="0">
        <tr>
            <td width="90">
                <s:if test="farm.member.pictures.size>0">
                    <s:iterator value="farm.member.pictures" status="its">
                        <s:if test="#its.index<1">
                            <img src="<%=request.getContextPath()%>/image?type=m&picId=${picId}" title="${picTitle}" width="88px" height="88px"/>
                        </s:if>
                    </s:iterator>
                </s:if>
                <s:else>
                    <img src="<%=request.getContextPath()%>/myfarm/img/member-pic.gif" title="member-pic" width="88px" height="88px"/>
                </s:else>
            </td>
            <td>
                <table border='0' cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <span class="text" id="farm_farmName">${farm.farmName}(${farm.acreage }m<sup>2</sup>)</span>
                            [<a href="<%=request.getContextPath()%>/myfarm/farm/${farm.farmId}">管理农庄</a>]
                        </td>
                    </tr>
                    <tr>
                        <td>所属农场：
                            <a href="<%=request.getContextPath()%>/buy/land/${farm.land.landId}">
                                ${farm.land.landName}
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>农庄主：
                            <a href="<%=request.getContextPath()%>/user/member/${farm.member.memberId}">${farm.member.realname}</a>
                            <s:if test="farm.owners.size>0">
                                <s:iterator value="farm.owners" status="its">
                                    <a href="<%=request.getContextPath()%>/user/member/${member.memberId}">${member.realname}</a>&nbsp;&nbsp;
                                </s:iterator>
                            </s:if>
                        </td>
                    </tr>
                    <tr>
                        <td>其他农庄：
                            <s:iterator value="farms" status="its">
                                <s:if test="farmId!=farm.farmId">
                                    <a href="<%=request.getContextPath()%>/myfarm/myfarm/${farmId}">
                                        <span class="link">${farmName}(${acreage }m<sup>2</sup>)</span>
                                    </a>&nbsp;&nbsp;
                                </s:if>
                            </s:iterator>
                        </td>
                    </tr>
                </table>
                <div id="slogan">
                    <s:if test="farm.slogan==null||farm.slogan==''">哥种的不是菜，是相思豆！</s:if>
                    <s:else>${farm.slogan}</s:else>
                </div>
            </td>
        </tr>
    </table>
    <div class="clear"></div>
</div>
<div class="clear"></div>

<div id="centerbar" style="width:68%">
    <div class="farming">
        <div class="tool-box" style="margin-left:10px; + *margin-left:3px;">
            <div class="tabpanel" id="tool-panel">
                <div class="tab">
                    <ul>
                        <li id="shop">
                            <img src="<%=request.getContextPath()%>/myfarm/img/shop.jpg" alt="商店" title="商店" width="42" height="42"/>
                        </li>
                        <li id="store">
                            <img src="<%=request.getContextPath()%>/myfarm/img/store.jpg" alt="仓库" title="仓库" width="42" height="42"/>
                        </li>
                        <li id="farmings">
                            <img src="<%=request.getContextPath()%>/myfarm/img/schedule.png" alt="指令" title="指令" width="42" height="42"/>
                        </li>
                        <li id="post">
                            <img src="<%=request.getContextPath()%>/myfarm/img/write.png" alt="写日志" title="写日志" width="42" height="42"/>
                        </li>
                        <li id="picture">
                            <img src="<%=request.getContextPath()%>/myfarm/img/upload.jpg" alt="上传图片" title="上传图片" width="42" height="42"/>
                        </li>
                        <s:iterator value="factions">
                            <li id="${factionId}">
                                <img src="<%=request.getContextPath()%>/image?type=faction&picId=${factionId}"
                                     title="${actionName}" width="42" height="42"/>
                            </li>
                        </s:iterator>
                    </ul>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="box" style="float:left;margin-left:10px">
            <div class="crops-box">
                <div class="tabpanel" id="crops-panel">
                    <div class="tab">
                        <ul>
                            <li id="0">
                                <img src="<%=request.getContextPath()%>/myfarm/img/status.jpg"
                                     title="作物状态" width="42" height="42"/>
                            </li>
                            <s:iterator value="cropses" status="its">
                                <li id="${cropsId}">
                                    <img src="<%=request.getContextPath()%>/myfarm/myfarm!getSeedImage?seedId=${seed.seedId}"
                                         title="${seed.seedName}" width="42" height="42"/>
                                </li>
                            </s:iterator>
                        </ul>
                        <div class="clear"></div>
                    </div>
                    <div id="crops-content" class="panel  hidden"></div>
                    <div id="crops-status" class="panel">
                        <table border='0' cellpadding="0" cellspacing="0">
                            <s:if test="cropses.size<1">
                                <tr>
                                    <td>
                                        锄禾日当午，汗滴禾下土。谁知盘中餐，粒粒皆辛苦。
                                    </td>
                                </tr>
                            </s:if>
                            <s:iterator value="cropses" status="its">
                                <tr>
                                    <td>${seed.seedName}</td>
                                    <td>
                                        数量:${seedNumber};
                                        心情:${vitalPower};
                                        成熟度:${maturity};
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
            <div class="farming-log-box">
                <div class="farming-log-title">
                    耕种日志
                </div>
                <div class="farming-log-list">
                    <ul>
                        <s:iterator value="farmings" status="its">
                            <li>
                                <s:date format="yyyy-MM-dd" name="startTime"/>
                                    ${farmer.realname}
                                    ${seed.seedName}
                                    ${faction.actionName}
                                    ${contents}
								<s:if test="paymode==1">
	                                <s:if test="state==1">
                                		<委托>
                                	</s:if>
                                	<s:else>
                                	</s:else>
								</s:if>

                            </li>
                        </s:iterator>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="clear"></div>
    </div>

    <%--
        <div class="farm-pic-box">
            <div class="farm-title">
                <div class="f-title">农庄照片</div>
                <div class="f-more"><a href="<%=request.getContextPath()%>/myfarm/farm/${farm.farmId}">管理图片</a></div>
            </div>
            <div class="clear"></div>
            <s:if test="farm.farmPictures.size()>0">
                <div class="box">
                    <div class="box" style="width:98%;overflow-x:scroll">
                        <table>
                            <tr>
                                <s:iterator value="farm.farmPictures" status="its">
                                    <script>
                                        photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/uploads/f/${farm.farmId}/${picUrl}";
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
                </div>
            </s:if>
        </div>
    --%>
    <div class="clear"></div>
</div>

<div id="rightbar" style="width:29%">
    <div class="farm">
        <div class="farm-title">
            <div class="f-title">${farm.farmName}</div>
            <div class="f-more"><a href="<%=request.getContextPath()%>/post/farm-post/?farmId=${farm.farmId}">更多</a></div>
        </div>
        <div style="width: 280px; margin: 0; padding: 0; overflow-y: auto; overflow-x: hidden;">
            <div class="farm-info">
                <s:if test="newPosts.size>0">
                    <div class="box">
                        <h5><label class="tag1"></label>农庄灌水</h5>

                        <div id="newPosts" class="list">
                            <ul>
                                <s:iterator value="newPosts">
                                    <li>
                                        <div class="right">(${creator.realname})[<s:date name="createDate" format="MM-dd"/>]</div>
                                        <a href="<%=request.getContextPath()%>/post/farm-post/${id}">${name} </a>
                                    </li>
                                </s:iterator>
                            </ul>
                        </div>
                    </div>
                </s:if>
                <s:if test="newActions.size>0">
                    <div class="box">
                        <h5>农庄活动</h5>

                        <div id="newActions" class="list">
                            <ul>
                                <s:iterator value="newActions">
                                    <li>
                                        <div class="right">[<s:date name="createDate" format="yyyy-MM-dd"/>]</div>
                                        <a href="<%=request.getContextPath()%>/post/farm-post/${id}">${name} </a>
                                    </li>
                                </s:iterator>
                            </ul>
                        </div>
                    </div>
                </s:if>
                <s:if test="newNotices.size>0">
                    <div class="box">
                        <h5>农庄通知</h5>

                        <div id="newNotices" class="list">
                            <ul>
                                <s:iterator value="newNotices">
                                    <li>
                                        <div class="right">[<s:date name="createDate" format="yyyy-MM-dd"/>]</div>
                                        <a href="<%=request.getContextPath()%>/post/farm-post/${id}">${name}</a>
                                    </li>
                                </s:iterator>
                            </ul>
                        </div>
                    </div>
                </s:if>
                <s:if test="newPlans.size>0">
                    <div class="box">
                        <h5>生产计划</h5>

                        <div id="newPlans" class="list">
                            <ul>
                                <s:iterator value="newPlans">
                                    <li>
                                        <div class="right">[<s:date name="createDate" format="yyyy-MM-dd"/>]</div>
                                        <a href="<%=request.getContextPath()%>/post/farm-post/${id}">${name}</a>
                                    </li>
                                </s:iterator>
                            </ul>
                        </div>
                    </div>
                </s:if>
                <s:if test="friendFarms.size>0">
                    <div class="box">
                        <h5><label class="tag5"></label>好友农庄</h5>

                        <div id="friendFarms" class="list">
                            <ul>
                                <s:iterator value="friendFarms">
                                    <li>
                                        <div class="right">[${member.realname}]</div>
                                        <a href="<%=request.getContextPath()%>/myfarm/myfarm/${farmId}" target="_blank">${farmName}</a>
                                    </li>
                                </s:iterator>
                            </ul>
                        </div>
                    </div>
                </s:if>
                <s:if test="neFarms.size>0">
                    <div class="box">
                        <h5><label class="tag6"></label>周边农庄</h5>

                        <div id="neFarms" class="list">
                            <ul>
                                <s:iterator value="neFarms" status="its">
                                    <s:if test="#its.index<5">
                                        <li>
                                            <div class="right">[${member.realname}]</div>
                                            <a href="<%=request.getContextPath()%>/myfarm/myfarm/${farmId}">${farmName}</a>
                                        </li>
                                    </s:if>
                                </s:iterator>
                            </ul>
                        </div>
                    </div>
                </s:if>
                <s:if test="visitors.size>0">
                    <div class="box">
                        <h5><label class="tag7"></label>最近访客</h5>

                        <div id="newMembers" class="list">
                            <ul>
                                <s:iterator value="visitors" status="its">
                                    <li>
                                        <div class="right"><s:if test="site!=null"> [${site.name}]</s:if></div>
                                        <a href="<%=request.getContextPath()%>/user/member/${memberId}"
                                           target="_blank">${realname}</a>
                                    </li>
                                </s:iterator>
                            </ul>
                        </div>
                    </div>
                </s:if>
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
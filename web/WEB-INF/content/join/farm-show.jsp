<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场--查看农庄资料</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/join/join.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
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
            <s:include value="farm-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
				<div class="t">
					<div class="t-t">
						查看农庄资料
					</div>
				</div>
                <div class="content">
                    <dl>
                        <dt>农庄编号</dt>
                        <dd>${farm.farmId}</dd>
                    </dl>
                    <dl>
                        <dt>农庄名称</dt>
                        <dd>${farm.farmName}</dd>
                    </dl>
                    <dl>
                        <dt>农场名称</dt>
                        <dd>${farm.land.landName}</dd>
                    </dl>
                    <dl>
                        <dt>社区名称</dt>
                        <dd>${farm.site.name}</dd>
                    </dl>
                    <dl>
                        <dt>农庄面积</dt>
                        <dd>${farm.acreage}平方米</dd>
                    </dl>
                    <dl>
                        <dt>大庄主</dt>
                        <dd>${farm.member.realname}</dd>
                    </dl>
                    <s:if test="farm.owners.size>0">
                        <dl>
                            <dt>二庄主</dt>
                            <s:iterator value="farm.owners" status="its">
                                <dd>${member.realname}</dd>
                            </s:iterator>
                        </dl>
                    </s:if>
                    <dl>
                        <dt>租赁时间</dt>
                        <dd>
                            <s:date name="farm.rentDate" format="yyyy-MM-dd"/>
							至 <s:date name="farm.endDate" format="yyyy-MM-dd"/>
                        </dd>
                    </dl>
                    <dl>
                        <dt>标语牌</dt>
                        <dd>${farm.slogan}</dd>
                    </dl>
                </div>
            </div>
            <div class="clear"></div>
			<span class="blank6"></span>
            <div class="box">
				<div class="title">
					<div class="t-text">
						<div class="tag-flowerpot"></div>
						农庄照片
					</div>
				</div>
                <s:if test="farm.farmPictures.size()>0">
                    <div class="box">
                        <div class="box" style="overflow-x:scroll">
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
                                    document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward()" style="filter:revealTrans(duration=2,transition=23);width:100%;" border=0>');
                                    document.write('<div id="photodescription" style="text-align:center">' + descriptions[0] + '</div>');
                                }
                            </script>
                        </div>
                    </div>
                </s:if>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
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
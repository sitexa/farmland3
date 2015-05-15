<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/buy/buy.css" rel="stylesheet" type="text/css"/>
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
            <s:include value="buyfarm-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
				<div class="t">
					<div class="t-t">
						农庄信息
					</div>
				</div>
                <div id="content" class="content">
                    <s:hidden name="farm.farmId"/>
					农庄名称：<s:property value="farm.farmName"/><br/>
					农 庄 主：<s:property value="farm.member.realname"/><br/>
					所属农场：<s:property value="farm.land.landName"/><br/>
					<!--农 场 主：<s:property value="farm.land.lord.realname"/><br/>-->
					区域名称：<s:property value="farm.site.name"/><br/>
					农庄范围：<s:property value="farm.coordination"/><br/>
					农庄面积：<s:property value="farm.acreage"/><br/>
					<a href="<%=request.getContextPath()%>/buy/farm/${farm.farmId }/buyFarm"><strong>购买农庄</strong></a>
                </div>
            </div>
            <div class="box">
				<div class="title">
					<div class="t-text">
						<div class="tag-flowerpot"></div>
						农庄照片
					</div>
				</div>
                <div class="content">
	                <s:if test="farm.farmPictures.size>0>0">
	                    <div class="box">
	                        <table border="0">
	                            <tr>
	                                <s:iterator value="farm.farmPictures" status="its">
	                                    <s:if test="#its.index<6">
	                                        <script>
	                                            photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/f/${farm.farmId}/${picUrl}";
	                                            titles[${its.index}] = "${picTitle}";
	                                            descriptions[${its.index}] = "${description}";
	                                        </script>
	                                        <td>
	                                            <img src="<%=request.getContextPath()%>/image?type=farm&picId=${picId}" alt="点击看大图"
	                                                 onclick="showPic(${its.index})"/>
	                                        </td>
	                                    </s:if>
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
            </div>
			<div class="box">
					<strong>备　　注：</strong><br/>
                    <div style="height: 100px;border: 1px #E9F3DC dotted;overflow-y:auto">
                        　　 			${farm.remark}
                    </div>
			</div>
        </div>
        <div id="rightbar" class="rightcol">
			<div class="title1">用户信息</div>
            <div class="commonBox">
                <div class="c-body">
                    <s:if test="farm.member!=null">
			                        真实姓名：${farm.member.realname}<br/>
			                        用户妮称：${farm.member.nickname}<br/>
			                        性　　别：${farm.member.genderName}<br/>
                        <s:if test="farm.member.memberId==profile.memberId">
			                            手机号码：${farm.member.mobilephone}<br/>
			                            固定电话：${farm.member.telephone}<br/>
                        </s:if>
			                        出生日期：<s:date name="farm.member.birthday" format="M月d日"/><br/>
			                        会员类型：${farm.member.type.name}<br/>
                    </s:if>
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
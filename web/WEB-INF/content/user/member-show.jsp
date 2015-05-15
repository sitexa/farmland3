<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%--
  User: xnpeng
  Date: 2009-4-2
  Time: 21:11:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="verify-v1" content="k259ptqdEx4rpDBG1K1KzYoCS/of2lbHViWkT8YuzFg=" />
    <s:i18n name="keywords">
        <meta name="keywords" content="<s:text name='keywords'/>" />
    </s:i18n>
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/pic.js"></script>
    <script type="text/javascript">
        function applyFriend() {
            if (document.getElementById("addFriend").style.display == "block") {
                document.getElementById("addFriend").style.display = "none";
            }
            else {
                document.getElementById("addFriend").style.display = "block";
            }
        }
    </script>
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
           	<s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
				<div class="title2">基本信息</div>
                <div class="content" style="width:40%;float:left;">
		                    用户号:${user.userId}<br/>
		                    用户名:${user.username}<br/>
                    <s:if test="user.userId==me.userId">
                       	电子邮件:${user.email}<br/>
                    </s:if>
		                    邮件确认:${user.emailConfirmed}<br/>
		                    用户状态:${user.status}<br/>
		                    注册时间:<s:date name="user.registerDate" format="yy年M月d日H时"/><br/>
		                    生效时间:<s:date name="user.validDate" format="yy年M月d日H时"/><br/>
		                    过期时间:<s:date name="user.expiryDate" format="yy年M月d日H时"/><br/>
                </div>
                <div class="content">
		                    真实姓名:${member.realname}<br/>
		                    用户妮称:${member.nickname}<br/>
		                    性别:${member.genderName}<br/>
                    <s:if test="member.memberId==profile.memberId">
			                        手机号码:${member.mobilephone}<br/>
			                        固定电话:${member.telephone}<br/>
                    </s:if>
		                    出生日期:<s:date name="member.birthday" format="M月d日"/><br/>
		                    会员类型:${member.type.name}<br/>
		                    所在社区:
                    <s:if test="member.site.parent.parent.parent.parent.parent!=null">
                        <a class="a1" href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.parent.parent.parent.siteId}"> ${member.site.parent.parent.parent.parent.parent.name} </a> >
                    </s:if>
                    <s:if test="member.site.parent.parent.parent.parent!=null">
                        <a class="a1" href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.parent.parent.siteId}"> ${member.site.parent.parent.parent.parent.name} </a> >
                    </s:if>
                    <s:if test="member.site.parent.parent.parent!=null">
                        <a class="a1" href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.parent.siteId}"> ${member.site.parent.parent.parent.name} </a> >
                    </s:if>
                    <s:if test="member.site.parent.parent!=null">
                        <a class="a1" href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.siteId}"> ${member.site.parent.parent.name} </a> >
                    </s:if>
                    <s:if test="member.site.parent!=null">
                        <a class="a1" href="<%=request.getContextPath()%>/site/site/${member.site.parent.siteId}"> ${member.site.parent.name} </a> >
                    </s:if>
                    <s:if test="member.site!=null">
                        <a class="a1" href="<%=request.getContextPath()%>/site/site/${member.site.siteId}"> ${member.site.name}</a><br/>
                    </s:if>
                </div>
				会员介绍:${member.introduction}<br/>
                <div class="content">
                    <s:iterator value="properties">
                        <s:if test="member.memberId==profile.memberId || publish==true ">
                            ${propName}:${propValue}<br/>
                        </s:if>
                    </s:iterator>
                </div>
                <s:if test="member.memberId!=profile.memberId">
                    <div style="padding-left:10px;text-decoration:underline;color:red;">
                        <a id="add" onclick="applyFriend();">加为好友</a>
                    </div>
                    <div style="margin-top:5px; padding-left:10px;text-decoration:underline;color:red;">
                        <a href="<%=request.getContextPath()%>/user/leave-message!send?sid=${member.memberId }">给好友留言</a>
                    </div>
                    <div id="addFriend" class="content" style="display:none;">
                        <s:form id="frmFriend" name="frmFriend"
                                action="%{#request.contextPath}/user/member/%{member.memberId}/applyFriend"
                                theme="simple">
                            给对方的话:<br/>
                            <s:textarea cols="40" rows="4" name="remark"/><br/><br/>
                            <s:submit cssClass="formbutton" value="发送请求"/>
                        </s:form>
                        <s:actionmessage/>
                        <s:actionerror/>
                    </div>
                </s:if>
            </div>
            <div class="box" style="clear:left">
				<div class="title2">会员照片</div>
                <div class="content">
                    <s:if test="pictures.size>0">
                        <div class="box" style="width:100%;overflow-x:scroll;">
                            <table>
                                <tr>
                                    <s:iterator value="pictures" status="its">
                                        <script>
                                            photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/m/${member.memberId}/${picUrl}";
                                            titles[${its.index}] = "${picTitle}";
                                            descriptions[${its.index}] = "${description}";
                                        </script>
                                        <td>
                                            <img src="<%=request.getContextPath()%>/image?type=m&picId=${picId}"
                                                 alt="点击看大图" onclick="showPic(${its.index})"/>
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
                        <div class="box">
                            <script>
                                if (photos.length > 0)
                                    document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward();" style="filter:revealTrans(duration=2,transition=23);width:100%" border=0>');
                            </script>
                            <script>
                                if (photos.length > 0)
                                    document.write('<div id="photodescription">' + descriptions[0] + '</div>');
                            </script>
                        </div>
                    </s:if>
                </div>
            </div>
        </div>

        <div id="rightbar" class="rightcol">
           	<s:include value="control-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
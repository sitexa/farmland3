<%--
  User: xnpeng
  Date: 2009-5-4
  Time: 14:28:18
--%>
<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <s:text name="root.title">
            <s:param>${member.realname}</s:param>
        </s:text>
    </title>
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
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
        <s:include value="../admin-header.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol"><s:include value="user-menu.jsp"/></div>
        <div id="centerbar">
            <div class="box">
                <div class="title">基本信息</div>
                <div class="content" style="width:30%;float:left;">
                    用户号:${user.userId}<br/>
                    用户名:${user.username}<br/>
                    电子邮件:${user.email}<br/>
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
                    手机号码:${member.mobilephone}<br/>
                    固定电话:${member.telephone}<br/>
                    出生日期:<s:date name="member.birthday" format="yy年M月d日"/><br/>
                    资料更新:<s:date name="member.updateDate" format="yy年M月d日"/><br/>
                    会员类型:${member.type.name}<br/>
                    所在社区:
                    <s:if test="member.site.parent.parent.parent.parent.parent!=null">
                        <a href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.parent.parent.parent.siteId}"
                           target="_blank"> ${member.site.parent.parent.parent.parent.parent.name} </a> >
                    </s:if>
                    <s:if test="member.site.parent.parent.parent.parent!=null">
                        <a href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.parent.parent.siteId}"
                           target="_blank"> ${member.site.parent.parent.parent.parent.name} </a> >
                    </s:if>
                    <s:if test="member.site.parent.parent.parent!=null">
                        <a href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.parent.siteId}"
                           target="_blank"> ${member.site.parent.parent.parent.name} </a> >
                    </s:if>
                    <s:if test="member.site.parent.parent!=null">
                        <a href="<%=request.getContextPath()%>/site/site/${member.site.parent.parent.siteId}"
                           target="_blank"> ${member.site.parent.parent.name} </a> >
                    </s:if>
                    <s:if test="member.site.parent!=null">
                        <a href="<%=request.getContextPath()%>/site/site/${member.site.parent.siteId}"
                           target="_blank"> ${member.site.parent.name} </a> >
                    </s:if>
                    <s:if test="member.site!=null">
                        <a href="<%=request.getContextPath()%>/site/site/${member.site.siteId}"
                           target="_blank">${member.site.name}</a><br/>
                    </s:if>
                    会员介绍:${member.introduction}<br/>
                    是否明星:${member.starTag}<br/>
                </div>
                <div class="content">
                    <s:iterator value="properties">
                        <s:if test="member.memberId==profile.memberId || publish==true ">
                            ${propName}:${propValue}<br/>
                        </s:if>
                    </s:iterator>
                </div>
            </div>
            <div class="box">
                <div class="title">会员照片</div>
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
                                    document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward();" style="filter:revealTrans(duration=2,transition=23);width:100%;" border=0>');
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
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../footer.jsp"/>
    </div>
</div>
</body>
</html>
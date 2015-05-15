<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%--
  User: xnpeng
  Date: 2009-4-2
  Time: 21:11:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="verify-v1" content="k259ptqdEx4rpDBG1K1KzYoCS/of2lbHViWkT8YuzFg=">
    <s:i18n name="keywords">
        <meta name="keywords" content="<s:text name='keywords'/>">
    </s:i18n>
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
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
            <s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box" style="width:50%;float:left;">
                <div class="title">用户信息</div>
                <div class="content">
                    用户号:${user.userId}<br/>
                    用户名:${user.username}<br/>
                    电子邮件:${user.email}<br/>
                    邮件确认:${user.emailConfirmed}<br/>
                    用户状态:${user.status}<br/>
                    注册时间:<s:date name="user.registerDate" format="yy年M月d日H时"/><br/>
                    生效时间:<s:date name="user.validDate" format="yy年M月d日H时"/><br/>
                    过期时间:<s:date name="user.expiryDate" format="yy年M月d日H时"/><br/>
                </div>
            </div>
            <div class="box">
                <div class="title">会员信息</div>
                <div class="content">
                    真实姓名:${member.realname}<br/>
                    用户妮称:${member.nickname}<br/>
                    性别:${member.gender}<br/>
                    手机号码:${member.mobilephone}<br/>
                    固定电话:${member.telephone}<br/>
                    出生日期:<s:date name="member.birthdate" format="M月d日"/><br/>
                    会员类型:${member.type.name}<br/>
                    所在社区:${member.site.name}<br/>
                </div>
            </div>
            <div class="box">
                <div class="title">会员属性</div>
                <div class="content">
                    <table class="borderAll">
                        <s:iterator value="properties">
                            ${propName}:${propValue}<br/>
                        </s:iterator>
                    </table>
                </div>
            </div>
            <div class="box">
                <div class="title">会员照片</div>
                <div class="content">
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
                                        <img src="<%=request.getContextPath()%>/image?type=m&picId=${picId}" alt="点击看大图"
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
                    <div class="box">
                        <script>
                            if (photos.length > 0)
                                document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward();" style="filter:revealTrans(duration=2,transition=23)" border=0>');
                        </script>
                        <script>
                            if (photos.length > 0)
                                document.write('<div id="photodescription">' + descriptions[0] + '</div>');
                        </script>
                    </div>
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
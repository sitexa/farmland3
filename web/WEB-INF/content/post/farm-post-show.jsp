<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/pic.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript">
        tinyMCE.init({
            mode : "textareas",
            theme : "simple",
            plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",

            theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
            theme_advanced_buttons2 : "search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,image,cleanup,help,code,|,preview,|,forecolor,backcolor",
            theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,fullscreen",
            theme_advanced_toolbar_location : "top",
            theme_advanced_toolbar_align : "left",
            theme_advanced_statusbar_location : "bottom",
            theme_advanced_resizing : true,

            content_css : "css/content.css",

            template_external_list_url : "lists/template_list.js",
            external_link_list_url : "lists/link_list.js",
            external_image_list_url : "lists/image_list.js",
            media_external_list_url : "lists/media_list.js",

            template_replace_values : {
                username : "Some User",
                staffid : "991234"
            }
        });
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
    <div id="mainwrapper" >
        <div id="leftbar" class="leftcol">
            <s:include value="farm-post-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div style="background-color:#eee;font-weight:bold;padding-left:4px;">
                    ${post.name}
                </div>
                <div class="infobox">
                    <a href="<%=request.getContextPath()%>/user/member/${post.creator.memberId}">${post.creator.realname}</a>
                    <s:date format="M月d日H时m分" name="post.createDate"/>
                    <s:if test="post.creator.memberId==profile.memberId||site.governor.memberId==profile.memberId">
                        <a href="<%=request.getContextPath()%>/post/farm-post/${post.id}/edit">编辑</a>
                        <a href="<%=request.getContextPath()%>/post/farm-post/${post.id}/destroy">删除</a>
                    </s:if>
                </div>
            </div>
            <s:if test="pictures.size()>0">
                <div class="box">
                    <div class="box" style="width:100%;overflow-x:scroll">
                        <table border="0">
                            <tr>
                                <s:iterator value="pictures" status="its">
                                    <script>
                                        photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/p/${post.id}/${picUrl}";
                                        titles[${its.index}] = "${picTitle}";
                                        descriptions[${its.index}] = "${description}";
                                    </script>
                                    <td>
                                        <img src="<%=request.getContextPath()%>/image?type=p&picId=${picId}" alt="点击看大图"
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
                                document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward()" style="filter:revealTrans(duration=2,transition=23);" border=0>');
                                document.write('<div id="photodescription">' + descriptions[0] + '</div>');
                            }
                        </script>
                    </div>
                </div>
            </s:if>
            <div class="content">
                ${post.content}
                <s:if test="post.parent!=null">
                    <div class="mainpost">
                        主帖:<a href="%{post.parent.id}">${post.parent.name}</a>
                    </div>
                </s:if>
            </div>
            <s:if test="post.children.size>0">
                <div class="box">
                    <div style="background-color:#eee;font-weight:bold;padding-left:4px;">
                        网友评论
                    </div>
                    <s:iterator value="post.children">
                        <div class="box">
                            <div class="infobox">
                                <a href="<%=request.getContextPath()%>/user/member/${creator.memberId}">${creator.realname}</a>
                                <s:date format="M月d日H时m分" name="createDate"/>
                                <s:if test="creator.memberId==profile.memberId||post.creator.memberId==profile.memberId||site.governor.memberId==profile.memberId">
                                    <a href="<%=request.getContextPath()%>/post/farm-post/${id}/destroy">删除</a>
                                </s:if>
                            </div>
                            <div class="comment">${content}</div>
                        </div>
                    </s:iterator>
                </div>
            </s:if>
            <s:if test="profile!=null">
                <div class="box">
                     <div style="background-color:#eee;font-weight:bold;padding-left:4px;">
                        发表评论
                    </div>
                    <div class="content">
                        <s:form theme="simple" action="%{#request.contextPath}/post/farm-post/%{post.id}/comment">
                            <s:textarea rows="5" cssStyle="width:95%;" name="comment"/><br/>
                            <s:submit cssClass="formbutton" method="comment" value=" 提 交 "/>
                        </s:form>
                    </div>
                </div>
            </s:if>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="farm-post-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
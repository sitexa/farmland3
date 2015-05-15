<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%--
  User: xnpeng
  Date: 2009-12-3
  Time: 20:49:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript">
        tinyMCE.init({
            mode : "textareas",
            theme : "advanced",
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
        function showPicture(picUrl) {
            var sUrl = "<%=AppConfig.getProperty("imgurl")%>";
            sUrl = sUrl + "/p/" + document.getElementById("postId").value;
            sUrl = sUrl + "/" + picUrl;
            window.open(sUrl);
        }

        function deletePicture(picId) {
            alert(picId);
            var f = document.getElementById("frmPost");
            f.action = "<%=request.getContextPath()%>/post!deletePicture?picId=" + picId;
            f.submit();
        }

        function doCheck() {
            if (document.getElementById("uploadframe").style.display == "block") {
                document.getElementById("uploadframe").style.display = "none";
            } else {
                document.getElementById("uploadframe").style.display = "block";
            }
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
            <s:include value="land-post-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
		        <div class="t">
		            <div class="t-t">
	                	编辑信息 
		            </div>
		        </div>
                <div class="content">
                    <s:form theme="simple" id="frmPost" name="frmPost"
                            action="%{#request.contextPath}/post/land-post/%{post.id}/update">
                        <s:hidden id="_method" name="_method"/>
                        <s:hidden name="post.id" id="postId"/>
                        标题:<s:textfield name="post.name" size="40" style="width:90%"/><br/>
                        <s:textarea name="post.content" rows="15" cols="80" style="width: 96%"/>
                        <input type="checkbox" id="insertpic" name="insertpic" onclick="doCheck();"/> 查看图片
                        <iframe id="uploadframe" name="uploadframe"
                                src="<%=request.getContextPath()%>/upload/${post.id}"
                                style="width:98%;height:240px;display:none;overflow-x:scroll" frameborder="0"
                                scrolling="no">
                            upload picture
                        </iframe>
                        <br/><s:submit cssClass="formbutton" method="update" value="提交"/>
                    </s:form>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div id="rightbar" class="rightcol">
           <s:include value="land-post-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
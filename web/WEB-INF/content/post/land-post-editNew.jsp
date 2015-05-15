<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript">
        tinyMCE.init({
            mode : "textareas",
            theme : "advanced",
            plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",

            theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
            theme_advanced_buttons2 : "search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,cleanup,help,code,|,preview,|,forecolor,backcolor",
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
        function doChange(src) {
            window.location.href = "<%=request.getContextPath()%>/publish?cateId=" + src.value;
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
                        发布信息
                    </div>
                </div>
                <div class="content">
                    <s:form theme="simple" id="frmPost" name="frmPost" action="%{#request.contextPath}/post/land-post">
                        <s:hidden id="_method" name="_method"/>
                        <s:hidden name="land.landId"/>
                        农场:<s:property value="land.landName"/>
                        栏目:<s:select list="postTypes" listKey="typeId" listValue="name" name="postType.typeId"/><br/>
                        标题:<s:textfield name="post.name" size="50"/><br/>
                        <s:textarea name="post.content" rows="15" cols="80" cssStyle="width: 96%"/>
                        <input type="checkbox" id="insertpic" name="insertpic" onclick="doCheck();"/>上传图片
                        <s:submit cssClass="button" value="提交" method="create"/>
                        <iframe id="uploadframe" name="uploadframe" src="<%=request.getContextPath()%>/upload"
                                style="width:90%;height:210px;display:none;"
                                frameborder="0" scrolling="no">
                            upload picture
                        </iframe>
                    </s:form>
                </div>
            </div>
            <s:actionmessage/>
            <s:actionerror/>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
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
        <div id="centerbar" class="centercol">
            <div class="box">
                <h3>发布信息</h3>

                <div class="content">
                    <s:form theme="simple" id="frmPost" name="frmPost" action="%{#request.contextPath}/post/post">
                        <s:hidden id="_method" name="_method"/>
                        农庄:<s:property value="site.name"/><br/>
                        栏目:<s:property value="category.name"/><br/>
                        标题:<s:textfield name="post.name" size="50"/><br/>
                        <s:textarea name="post.content" rows="15" cols="80" style="width: 96%"/>
                        <input type="checkbox" id="insertpic" name="insertpic" onclick="doCheck();"/>上传图片
                        <iframe id="uploadframe" name="uploadframe" src="<%=request.getContextPath()%>/upload"
                                style="width:90%;height:210px;display:none;"
                                frameborder="0" scrolling="no">
                            upload picture
                        </iframe>
                        <br/><s:submit cssClass="formbutton" value="提交"/>
                    </s:form>
                </div>
            </div>
            <s:actionmessage/>
            <s:actionerror/>
        </div>
        <div id="rightbar" class="rightcol">
            right
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
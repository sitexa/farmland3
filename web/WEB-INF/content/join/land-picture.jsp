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
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript">
        var rawmaxsize = 2048000;
        var humanmaxsize = '2 MB';
        var url = "<%=request.getContextPath()%>";
        var imgurl = "<%=AppConfig.getProperty("imgurl")%>";

        function addtr() {
        	document.getElementById("picDiv").style.display = 'block';
        }

        function deltr(rowId) {
        	document.getElementById("picDiv").style.display = 'none';
        }

        function showPicture(picUrl) {
            var sUrl = imgurl ;
            sUrl = sUrl + "/l/" + document.getElementById("landId").value;
            sUrl = sUrl + "/" + picUrl;
            window.open(sUrl);
        }

        function deletePicture(picId) {
            if (confirm("确认删除?")) {
                var f = document.getElementById("frmPicture");
                f._method.value = "delete";
                f.picId.value = picId;
                f.submit();
            }
        }
        function disableurlfield(el) {
            if (document.getElementById && document.all) {
                document.previewimg.src = 'file:///' + el.form.upload.value;
                previewuploaded(el);
            }
        }

        function previewuploaded(el) {
            var imgtest = /(.gif)|(.jpg)|(.png)|(.bmp)/gi;
            if (imgtest.test(el.value)) {
                document.getElementById("previewarea").innerHTML =
                '<img src="' + 'file:///' + el.form.upload.value + '" style="width:100%;"/>';
                document.getElementById("previewarea").style.display = "none";
            }
        }

        function checktypesize(el) {
            if (document.getElementById("frmPicture").upload.value == "")return true;
            return IsValidImageType(document.getElementById("frmPicture").upload.value);
        }

        function IsValidImageSize() {
            if ((document.previewimg.fileSize * 0) != 0) {
                return true;
            }
            if (navigator.userAgent.indexOf("Mac") != -1)
                return true;
            if (document.previewimg.fileSize == -1) {
                return true;
            }

            if ((document.previewimg.fileSize > 0) && (document.previewimg.fileSize < rawmaxsize)) {
                return true;
            }
            alert('最大照片不能超过: ' + humanmaxsize);
            return false;
        }
        function IsValidImageType(filename) {
            if (filename == "") {
                return false;
            }
            var reg = /(gif|png|jpe?g?|bmp)$/i;
            if (!reg.test(filename)) {
                alert('只接受JPEG, PNG, GIF,BMP格式图片!');
                return false;
            }
            return IsValidImageSize();
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
            <s:include value="land-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
			<div class="t">
				<div class="t-t">
					农场图片管理
				</div>
			</div>
            <div class="box">
                <div class="content">
                    <s:form theme="simple" id="frmPicture"
                            action="%{#request.contextPath}/join/land-picture/%{land.landId}"
                            onsubmit="return checktypesize(this);" enctype="multipart/form-data">
                        <s:hidden name="_method"/>
                        <s:hidden name="picId"/>
                        <s:hidden id="landId" name="landId" value="%{land.landId}"/>
                        <table>
                            <s:iterator value="pictures">
                                <tr>
                                    <td>
                                        <s:hidden name="pictures(%{picId}).land.landId" value="%{land.landId}"/>
                                        <s:hidden name="pictures(%{picId}).picId" value="%{picId}"/>
                                        <img title="点击看大图" src="<%=request.getContextPath()%>/image?type=land&picId=${picId}"
                                             onclick="showPicture('<s:property value="%{picUrl}"/>');"/>
                                    </td>
                                    <td>
                                        <table>
                                            <tr>
                                                <td>标题</td>
                                                <td>
                                                    <s:textfield size="20" name="pictures(%{picId}).title"
                                                                 value="%{title}"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>说明</td>
                                                <td>
                                                    <s:textfield size="60" name="pictures(%{picId}).description"
                                                                 value="%{description}"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" align="right">
                                                    <input class="formbutton" type=button value=" 删除 "
                                                           onClick="deletePicture('<s:property value="%{picId}"/>');">
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                        <div id="picDiv" style="display:none">
                            <table class="borderAll">
                                <tbody id="ptable">
                                <tr id="row0">
                                    <td><input type="text" name="title" size="6" value="标题"></td>
                                    <td><input type="text" name="description" size="10" value="说明"></td>
                                    <td><s:file name="upload" label="File" size="10" value="路径"
                                                onchange="disableurlfield(this)"/></td>
                                    <td><input type=button value="删除" onClick="deltr('row0');"/></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        (图片最大不能超过1M)<input class="formbutton" type="button" value="增加图片" onClick="addtr();" />
                        <s:submit cssClass="formbutton" method="update" value="保存&上传"/>
                    </s:form>
                </div>
                <div class="content">
                    <div id='previewarea' style="display: none">&nbsp;</div>
                    <div id='previewdiv' style="display: none">
                        <img src="" alt="" border=0 name="previewimg"/>
                    </div>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="land-right.jsp"/>
            <s:actionerror cssClass="error"/><s:actionmessage cssClass="message"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
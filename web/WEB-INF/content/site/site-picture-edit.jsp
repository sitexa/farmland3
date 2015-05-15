<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%--
  User: xnpeng
  Date: 2009-4-7
  Time: 11:45:33
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/site.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var rawmaxsize = 1024000;
        var humanmaxsize = '1 MB';
        var url = "<%=request.getContextPath()%>";
        var imgurl = "<%=AppConfig.getProperty("imgurl")%>";

        function addtr() {
            if (document.getElementById("picDiv").style.display == 'none') {
                document.getElementById("picDiv").style.display = 'block';
                return;
            }
            var tableb = document.getElementById("ptable");
            var maxtr = tableb.childNodes.length;
            if (maxtr > 0)return;
            var oldtr = tableb.childNodes[maxtr - 1];
            var td0 = oldtr.childNodes[0];
            var td1 = oldtr.childNodes[1];
            var td2 = oldtr.childNodes[2];

            maxtr++;

            var newtr = document.createElement("tr");
            newtr.setAttribute("id", "row" + maxtr);

            var newtd0 = document.createElement("td");
            var newtd1 = document.createElement("td");
            var newtd2 = document.createElement("td");
            var newtd3 = document.createElement("td");

            newtd0.innerHTML = td0.innerHTML;
            newtd1.innerHTML = td1.innerHTML;
            newtd2.innerHTML = td2.innerHTML;
            newtd3.innerHTML = "<input type=button value=\"删除\" onClick=\"deltr('row" + maxtr + "')\"/>";

            newtr.appendChild(newtd0);
            newtr.appendChild(newtd1);
            newtr.appendChild(newtd2);
            newtr.appendChild(newtd3);

            tableb.appendChild(newtr);

        }

        function deltr(rowId) {
            var tableb = document.getElementById("ptable");
            var row = document.getElementById(rowId);
            tableb.removeChild(row);
            document.getElementById("previewarea").style.display = "none";
        }

        function showPicture(picUrl) {
            var sUrl = imgurl ;
            sUrl = sUrl + "/s/" + document.getElementById("siteId").value;
            sUrl = sUrl + "/" + picUrl;
            window.open(sUrl);
        }

        function deletePicture(picId) {
            var f = document.getElementById("frmPicture");
            f._method.value = "delete";
            f.picId.value = picId;
            f.submit();
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
                '<img src="' + 'file:///' + el.form.upload.value + '"style="width:100%;"/>';
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
            <s:include value="site-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="title1">管理社区图片</div>
                <div class="content">
                    <s:form theme="simple" id="frmPicture" action="%{#request.contextPath}/site/site-picture/%{site.siteId}"
                            onsubmit="return checktypesize(this);"
                            enctype="multipart/form-data">
                        <s:hidden name="_method"/>
                        <s:hidden name="picId"/>
                        <s:hidden id="siteId" name="siteId" value="%{site.siteId}"/>
                        <table class="borderAll">
                            <s:iterator value="pictures">
                                <tr>
                                    <s:hidden name="pictures(%{picId}).site.siteId" value="%{site.siteId}"/>
                                    <s:hidden name="pictures(%{picId}).picId" value="%{picId}"/>
                                    <td>
                                        <img alt="点击看大图" src="<%=request.getContextPath()%>/image?type=s&picId=${picId}"
                                             onclick="showPicture('<s:property value="%{picUrl}"/>');"/>
                                    </td>
                                    <td>
                                        <table>
                                            <tr>
                                                <td>标题</td>
                                                <td>
                                                    <s:textfield name="pictures(%{picId}).picTitle" value="%{picTitle}" size="10"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>说明</td>
                                                <td>
                                                    <s:textfield size="30" name="pictures(%{picId}).description" value="%{description}"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" align="right">
                                                    <input type=button value="删除" onClick="deletePicture('<s:property value="%{picId}"/>');">
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
                                    <td><input type="text" name="picTitle" value="标题" size="6"></td>
                                    <td><input type="text" name="description" value="说明" size="10"></td>
                                    <td><s:file name="upload" label="File" size="10" onchange="disableurlfield(this)"/></td>
                                    <td><input type=button value="删除" onClick="deltr('row0');"></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        (图片最大不能超过1M)<input class="formbutton" type=button value="增加图片" onClick="addtr();">
                        <s:submit cssClass="formbutton" method="update" value="保存/上传"/>
                    </s:form>
                </div>
                <DIV id='previewarea'>&nbsp;</DIV>
                <DIV id='previewdiv' style="DISPLAY: none">
                    <IMG src="" alt="" border=0 name=previewimg>
                </DIV>
            </div>
            <s:actionmessage/>
            <s:actionerror/>
        </div>
        <div id="rightbar" class="rightcol" >
        	<s:include value="site-right.jsp"/>
		</div>
    </div>
    <div class="clear"></div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
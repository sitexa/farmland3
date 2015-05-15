<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
    if (window.parent.frames["uploadframe"] == null) {
        window.location.href = "http://www.farmlander.com";
    }

    var rawmaxsize = 2048000;
    var humanmaxsize = '2MB';

    function deletePicture(picId) {
        var f = document.getElementById("frmPicture");
        f.action = '<%=request.getContextPath()%>/upload!delete?picId=' + picId;
        f.submit();
    }

    //onchange 事件自动上传。----暂不用。
    function uploadPicture(el) {
        document.previewimg.src = 'file:///' + el.form.upload.value;
        if (checktypesize(el)) {
            document.getElementById("frmPicture").action = '<%=request.getContextPath()%>/upload!update';
            document.getElementById("frmPicture").submit();
        }
    }

    //提交时进行大小和类型检查
    function checktypesize(el) {
        if (document.getElementById("frmPicture").upload.value == "")return true;
        return IsValidImageType(document.getElementById("frmPicture").upload.value);
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
        return true;
//        return IsValidImageSize();
    }

    function IsValidImageSize() {
        if (navigator.userAgent.indexOf("Mac") != -1)
            return true;

        if (document.previewimg.fileSize == -1) {
            return true;
        }

        if ((document.previewimg.fileSize > 0) && (document.previewimg.fileSize < rawmaxsize)) {
            return true;
        }

        alert('照片最大不能超过: ' + humanmaxsize);
        return false;
    }
</script>
<div class="content">
    <s:form theme="simple" id="frmPicture" action="%{#request.contextPath}/upload!update" enctype="multipart/form-data" onsubmit="return checktypesize(this);">
        <div class="box" style="display:block;width:98%;border:0;">
            <s:hidden name="post.id"/>
            上传图片(小于2M):<br>
            <input type="text" size="10" name="picTitle" value="标题">
            <input type="text" size="36" name="description" value="说明"><br>
            <s:file name="upload" size="40"/>
            <s:submit method="update" value="上传" cssStyle=""/>
        </div>
    </s:form>
</div>
<div class="box" style="width:100%;overflow-x:scroll">
    <table>
        <tr>
            <s:iterator value="pictures">
                <td align="center">
                    <s:url id="url" action="%{#request.contextPath}upload!image">
                        <s:param name="picId" value="%{picId}"/>
                    </s:url>
                    <img alt="" src="<s:property value='#url'/>"/><br/>
                    <a href="#" onClick="deletePicture('<s:property value="%{picId}"/>');">删除</a>
                </td>
            </s:iterator>
        </tr>
    </table>
</div>
<DIV id='previewdiv' style="DISPLAY: none">
    <IMG src="" alt="" border=0 name=previewimg>
</DIV>

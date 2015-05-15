<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>花木兰ICSA农场</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript">
        function testForm() {
            var imgPath = $("#upload").val();
            if (imgPath == "") {
                alert("请您选择图片。");
                return false;
            }
            if (!isImg(imgPath)) {
                alert("您选择的图片文件格式不正确\n只能上传图片扩展名为(.gif .jpg .bmp .png .jpeg .pjpeg)的图片\n请重新选择。");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div>
    <fieldset class="farm-pic-form">
        <legend>上传图片</legend>
        <form id="frmFarm" action="<%=request.getContextPath()%>/work/work/${farm.farmId }/savePicture" method="post" enctype="multipart/form-data">
            <input type="text" id="title" name="title" value="标题"/>
            <input type="text" id="description" name="description" value="说明"/>
            <input id="upload" type="file" size="15" name="upload" class="input"/>
            <input type="submit" value="上传图片" onclick="return testForm()"/>
        </form>
    </fieldset>
</div>
</body>
</html>
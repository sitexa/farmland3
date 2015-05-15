<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript">
		function addImg()
		{ 	
			$.ajaxFileUpload({ 
				url:"<%=request.getContextPath()%>/upfile!index", 
				secureuri:false, 
				fileElementId:'upload', 
				data:{}, 
				dataType: 'text', 
				success: function (fileName, status)
		    				{ 	
								if(fileName!=null){
		    						document.getElementById("img").src = '<%=AppConfig.getProperty("imgurl")%>/temp/'+fileName;
	    							$("#imgName").val(fileName);
		    					}
		    				}, 
				error: function (data, status, e)
							{ 
								alert("添加失败，请重新添加。");
							} 
			});
			return false;
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
            <s:include value="seed-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
			<div class="box">
				<div class="t">
					<div class="t-t">
						新建种子
					</div>
				</div>
                <div class="contents">
					<s:form id="frmFaction" action="%{#request.contextPath}/join/seed" method="post">
						<s:hidden name="seed.seedId"/>
						<s:hidden name="page.current"/>
						<input type="hidden" name="landId" value="${land.landId }" />
						<input type="hidden" id="imgName" name="imgName"/>
						<table>
							<tr>
								<td>
									<span class="blank6"></span>
									所属农场：<s:property value="land.landName"/>
									<span class="blank6"></span>
									种子名称：<s:textfield name="seed.seedName"></s:textfield>
									<span class="blank6"></span>
									种子价格：<s:textfield name="seed.price"></s:textfield>
									<span class="blank6"></span>
									种植说明：<br />
								</td>
								<td style="border-left:1px solid #CCCCCC; padding-left:10px">
									<div style="width:100px;height:75px;overflow:hidden;border:1px solid #CCCCCC; background:#F4F4F4">
										<img id="img" width="100" height="75" src="<%=request.getContextPath()%>/image?type=seed&picId=${seed.seedId}"/>
									</div>
									<input type="file" id="upload" name="upload"/><input type="button" value="上 传" onclick="addImg()"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<s:textarea name="seed.description" cols="60" rows="4"></s:textarea>
								</td>
							</tr>
							<tr>
                                <td>状态：<s:textfield id="status" name="seed.status" size="2" maxLength="2"/></td>
								<td>
									<s:submit value=" 保 存 " method="update" cssClass="formbutton"/>
								</td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="seed-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.sitexa.framework.config.AppConfig" %>
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
		$(function(){

		})

		function addImg(){ 	
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
            <s:include value="faction-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
			<div class="t">
				<div class="t-t">
					修改农场服务
				</div>
			</div>
            <div class="box">
                <div class="box">
                    <s:form id="frmFaction" action="%{#request.contextPath}/join/faction" method="post">
						<s:hidden name="faction.factionId"></s:hidden>
						<s:hidden name="faction.land.landId"></s:hidden>
						<input type="hidden" id="imgName" name="imgName"/>
						
                        <table border="0" width="100%">
							<tr>	
								<td width="15%"></td>
								<td width="30%"></td>
								<td width="30%"></td>
								<td width="25%"></td>
							</tr>
                            <tr>
                                <td></td>
								<td></td>
                                <td colspan="2" rowspan="4" align="center" style="border-left:1px solid #CCCCCC">
									<div style="width:100px;height:75px;overflow:hidden;border:1px solid #CCCCCC; background:#F4F4F4">
										<img id="img" width="100" height="75" src="<%=request.getContextPath()%>/image?type=faction&picId=${faction.factionId }"/>
									</div>
									<input type="file" id="upload" name="upload"/><input type="button" value="上 传" onclick="addImg()"/>
								</td>
							</tr>
                            <tr>
                                <td class="odd">服务名称：</td>
                                <td><s:textfield id="actionName" name="faction.actionName"/></td>
                            </tr>
                            <tr>
                                <td class="odd">服务价格：</td>
                                <td><s:textfield id="expense" name="faction.expense"/></td>
                            </tr>
                            <tr>
                                <td class="odd">使用材料：</td>
                                <td><s:textfield id="materials" name="faction.materials"/></td>
                            </tr>
                            <tr>
                                <td colspan="4">
									服务说明：
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <s:textarea id="contents" name="faction.contents" rows="5" style="width:560px"/>
                                </td>
                            </tr>
                            <tr>
                                <td>状态</td>
                                <td><s:textfield id="status" name="faction.status" size="2" maxLength="1"/> </td>
                                <td colspan="2">
                                    <s:submit value=" 保 存 " method="update" cssClass="formbutton"/>
                                </td>
                            </tr>
                        </table>
                    </s:form>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="faction-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
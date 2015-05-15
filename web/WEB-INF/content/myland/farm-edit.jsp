<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/myland/land.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/pic.js"></script>
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
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="land-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
				<div class="t">
					<div class="t-t">
						修改农场资料
					</div>
				</div>
                <div class="content">
                    <s:form action="%{#request.contextPath}/myland/farm" id="operFrm" method="post">
                        <s:hidden name="farm.land.landId"/>
                        <table>
                            <tr>
                                <td>农庄ID</td>
                                <td><s:textfield name="farm.farmId" readonly="true"/></td>
                            </tr>
                            <tr>
                                <td>农庄编号</td>
                                <td><s:textfield name="farm.farmNo"/></td>
                            </tr>
                            <tr>
                                <td>农庄名称</td>
                                <td><s:textfield name="farm.farmName"/></td>
                            </tr>
                            <tr>
                                <td>农庄面积</td>
                                <td><s:textfield name="farm.acreage"/></td>
                            </tr>
                            <tr>
                                <td>农庄坐标</td>
                                <td><s:textfield name="farm.coordination"/></td>
                            </tr>
                            <tr>
                                <td>农庄租金</td>
                                <td><s:textfield name="farm.rentPrice"/></td>
                            </tr>
                            <tr>
                                <td>耕种状态</td>
                                <td><s:textfield name="farm.PloughStatus"/></td>
                            </tr>
                            <tr>
                                <td>备 注</td>
                                <td><s:textfield name="farm.remark" size="60"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><s:submit value="保存" method="update" cssClass="formbutton"/></td>
                            </tr>
                        </table>
                    </s:form>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="farm-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
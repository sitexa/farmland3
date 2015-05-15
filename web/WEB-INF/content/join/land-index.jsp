<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/join/join.css" rel="stylesheet" type="text/css"/>
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
            <s:include value="joinland-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="t2">
                <div class="t2-t">
                    农场管理
                </div>
            </div>
            <div class="box">
                <table border="0" cellpadding="2" cellspacing="4" width="100%">
                    <tr>
                        <th>编号</th>
                        <th>农场名称</th>
                        <th>农场主</th>
                        <th>公司名称</th>
                        <th>操作</th>
                    </tr>
                    <s:iterator value="lands" status="its">
                        <tr class='<s:if test="#its.even">even</s:if><s:else>odd</s:else>'>
                            <td>${landId} <s:if test="status==1"><em style="color:red">●</em></s:if>
                            </td>
                            <td>${landName}</td>
                            <td>${lord.realname}</td>
                            <td>${landLord}</td>
                            <td>
                                <a href="<%=request.getContextPath()%>/join/land/${landId}">预览</a>
                                <a href="<%=request.getContextPath()%>/join/land/${landId}/edit">修改</a>
                                <a href="<%=request.getContextPath()%>/join/land/${landId}/destroy">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="joinland-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
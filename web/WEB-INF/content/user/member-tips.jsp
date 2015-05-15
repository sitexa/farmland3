<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div class="clear"></div>
    <div id="mainwrapper" style="padding-top:10px;">
        <div id="leftbar" class="leftcol">
            <s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
		        <div class="title">
		            <div class="t-text">
		                <div class="tag-flowerpot"></div>
	                	操作提示
		            </div>
		        </div>
                <div class="content" style="text-align:center;font-size:18px;">
					<s:if test="#request.tipType==1">
						<br />
						您已提交农场主申请，或您已慢农场主，无需再次申请。<br />
						<span class="blank6"></span>
						<a href="<%=request.getContextPath()%>/join/joinland">返回加盟农场</a>
					</s:if>
					<s:if test="#request.tipType==2">
						<br />
						申请农场主成功，请等待审核...<br />
						<span class="blank6"></span>
						<a href="<%=request.getContextPath()%>/join/joinland">返回加盟农场</a>						
					</s:if>
                    <div class="clear"></div>
                </div>
                <div>
                    <s:actionmessage/>
                    <s:actionerror/>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div id="rightbar" class="rightcol">
			<s:include value="control-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
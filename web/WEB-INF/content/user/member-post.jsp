<%--
  User: xnpeng
  Date: 2009-5-6
  Time: 22:17:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("frmPost").submit();
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
       		<s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
				<div class="title2">文章列表</div>
				<div class="content">
					<div class="list">
						<ul>
	                    <s:iterator value="posts">
							<li>
		                        <a href="<%=request.getContextPath()%>/site/site/${site.siteId}/show"><s:property value="site.name"/></a>
		                        <s:property value="category.name"/>
		                        <a href="<%=request.getContextPath()%>/post/post/${id}/show"><s:property value="name"/> </a>
		                      	  点击次数:<s:property value="vwCnt"/>
							</li>
	                    </s:iterator>
						</ul>
					</div>
                </div>
            </div>
            <div id="paging">
                <s:if test="page.total!=0">
                    	共有${page.total}页结果:
                    <s:if test="page.current>6">
                        <a href="#" onclick="dos(1);">1</a>
                    </s:if>
                    <s:if test="page.current>7">
                        ...
                    </s:if>
                    <s:bean name="org.apache.struts2.util.Counter" id="counter">
                        <s:param name="first" value="page.current-5"/>
                        <s:param name="last" value="page.current+5"/>
                        <s:iterator status="its">
                            <s:if test="current>1 && current<=page.total+1">
                                <s:if test="#its.index==5">
                                    <s:property/>
                                </s:if>
                                <s:else>
                                    <a href="#" onclick="dos('<s:property/>');"> <s:property/></a>
                                </s:else>
                            </s:if>
                        </s:iterator>
                    </s:bean>
                    <s:if test="page.total>page.current+6">
                        ...
                    </s:if>
                    <s:if test="page.total>page.current+5">
                        <a href="#" onclick="dos('<s:property value="page.total"/>');"><s:property
                                value="page.total"/></a>
                    </s:if>
                </s:if>
            </div>
            <form id="frmPost" style="display:none;" action="<%=request.getContextPath()%>/user/member-post/${member.memberId }"
                  onsubmit="dos(1);" method="get">
                <s:hidden id="page.current" name="page.current" value="1"/>
                <s:submit cssClass="formbutton" method="index"/>
            </form>
        </div>
        <div id="rightbar" class="rightcol">
           	<s:include value="control-right.jsp"/>
        </div>
    </div>
	<div class="clear"></div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
<%--
  User: xnpeng
  Date: 2009-12-3
  Time: 11:15:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript">
        onload = function(){
        	$(".list .li-body").hoverCss("border", "dashed #DCECC6 1px", "dashed #FFF 1px");
        	$.formatLink("#posts a", 70);
        	//rightbar
        	$.formatLink("#hotPosts a", 28);
        	$.formatLink("#newFarms a", 20);
        }
        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("frmSearch").submit();
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
            <s:include value="csa-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
				<div class="t">
					<div class="t-t">
						玩转农场
					</div>
				</div>
				<div class="content">
	                <div id="posts" class="list">
	                    <ul>
	                        <s:iterator value="posts">
	                            <li>
									<div class="li-body">
										<div class="detail"><s:date format="M月dd日HH时" name="createDate"/></div>
										<a href="<%=request.getContextPath()%>/post/csa/${id}">${name}</a>
	                                </div>
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
            <form id="frmSearch" style="display:none;" action="<%=request.getContextPath()%>/post/csa"
                  onsubmit="dos(1);" method="get">
                <s:hidden id="page.current" name="page.current" value="1"/>
                <s:textfield name="words"/><s:submit cssClass="formbutton" method="index"/>
            </form>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="csa-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
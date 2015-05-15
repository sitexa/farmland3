<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript">
        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("frmSearch").submit();
        }
        function submit(){
        	$("#word").val($("#word1").val());
        	document.getElementById("page.current").value = 1;
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
            <s:include value="buyfarm-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="title">
                    <div class="t-text">
                        <div class="tag-flowerpot"></div>
                        	搜索农庄
                    </div>
                </div>
				<span class="blank6"></span>
				<div class="search-box">
					<input type="text" class="text" id="word1" value="${word }"/>
					<div class="button" onclick="submit()"></div>
				</div>
				<span class="blank6"></span>
			</div>
            <div class="box">
                <div class="title">
                    <div class="t-text">
                        <div class="tag-flowerpot"></div>
                        农庄列表
                    </div>
                </div>
                <div class="content">
                    <div class="list">
                        <s:if test="farms.size>0">
                            <ul>
                                <s:iterator value="farms">
                                    <li id="${farmId}">
                                        <a class="link2" href="<%=request.getContextPath()%>/myfarm/myfarm/${farmId}">
                                                ${farmNo}
                                            <s:if test="farmNo!=farmName">(<font style="font-weight:bold">${farmName}</font>)</s:if>
                                        </a>&nbsp;
                                       	农庄面积:${acreage}平方米&nbsp;
                                   	 	<s:if test="member!=null">农庄主:<a class="link2" href="<%=request.getContextPath()%>/user/member/${member.memberId}">${member.realname}</a></s:if>
										<label class="detail"><s:date name="rentDate" format="yyyy年MM月dd日"/></label>
                                    </li>
                                </s:iterator>
                            </ul>
                        </s:if>
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
                                <a href="#" onclick="dos('<s:property value="page.total"/>');"><s:property value="page.total"/></a>
                            </s:if>
                        </s:if>
                    </div>
                    <form id="frmSearch" style="display:none;" action="<%=request.getContextPath()%>/buy/farm!searchFarms" onsubmit="dos(1);"
                          method="get">
                        <s:hidden id="page.current" name="page.current" value="1"/>
						<input type="hidden" id="word" name="word" value="${word }" />
                        <s:submit cssClass="formbutton"/>
                    </form>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="buyfarm-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
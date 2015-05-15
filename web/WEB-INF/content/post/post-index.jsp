<%--
  User: xnpeng
  Date: 2009-4-14
  Time: 20:49:07
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="verify-v1" content="k259ptqdEx4rpDBG1K1KzYoCS/of2lbHViWkT8YuzFg=">
    <title>
        花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
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
            <s:include value="post-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <s:iterator value="%{posts}" status="its">
                <div class="box">
                    <div class="box" style="border:0;float:right;margin-top:5px;">
                        <s:date format="M月dd日HH时" name="createDate"/>
                    </div>
                    <div class="title">
                        <a href="<%=request.getContextPath()%>/post/post/${id}">${name}</a>
                    </div>
                    <div class="subject">
                        <s:if test="pictures.size()>0">
                            <s:iterator value="pictures" status="its">
                                <s:if test="#its.index<1">
                                    <img src="<%=request.getContextPath()%>/image?type=p&picId=${picId}" alt=""/>
                                </s:if>
                            </s:iterator>
                        </s:if>
                            ${subject}...
                        <a href="<%=request.getContextPath()%>/post/post/${id}">详细内容</a>
                    </div>
                    <div class="c"></div>
                </div>
            </s:iterator>
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
            <div class="box">
                <div class="ctitle">快速发布</div>
                <div class="content">
                    <s:form theme="simple" id="frmPost" action="%{request.contextPath}/post/post/">
                        <s:hidden name="site.siteId"/>
                        <s:hidden name="category.cateId"/>
                        社区:<s:property value="site.name"/><br>
                        栏目:<s:property value="category.name"/><br/>
                        标题:<s:textfield name="post.name" cssStyle="width:80%;"/><br/>
                        <s:textarea rows="5" cssStyle="width:90%;" name="post.content"/><br/>
                        <s:submit cssClass="formbutton" value="提交"/>
                    </s:form>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="post-right.jsp"/>
        </div>
        <form id="frmSearch" style="display:none;" action="<%=request.getContextPath()%>/post/post" onsubmit="dos(1);"
              method="get">
            <s:hidden id="page.current" name="page.current" value="1"/>
            <s:textfield name="words"/><s:submit cssClass="formbutton" method="index"/>
        </form>
        <s:actionmessage/>
        <s:actionerror/>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
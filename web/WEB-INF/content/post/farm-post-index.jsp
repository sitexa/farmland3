<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="verify-v1" content="k259ptqdEx4rpDBG1K1KzYoCS/of2lbHViWkT8YuzFg=">
    <s:i18n name="keywords">
        <meta name="keywords" content="<s:text name='keywords'/>">
    </s:i18n>
    <title>花木兰ICSA农场</title>
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
            <s:include value="farm-post-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box" style="margin-bottom:5px;">
                <a href="<%=request.getContextPath()%>/post/farm-post/?farmId=">农庄风采</a>
                <s:if test="farm!=null">
                    > <a href="<%=request.getContextPath()%>/post/farm-post/?farmId=${farm.farmId}">${farm.farmName}</a>
                </s:if>
                <s:if test="postType!=null">
                    > <a href="<%=request.getContextPath()%>/post/farm-post/?typeId=${postType.typeId}">${postType.name}</a>
                </s:if>
            </div>
            <div class="content">
                <div class="post-list">
                    <s:iterator value="%{posts}" status="its">
                        <div class="box">
                            <div class="right"><s:date format="M月dd日HH时" name="createDate"/></div>
                            <div style="font-size:14px;font-weight:bold;background-color:#eee"><a href="<%=request.getContextPath()%>/post/farm-post/${id}">${name}</a>
                            </div>
                        </div>
                        <div class="content">
                            <s:if test="pictures.size()>0">
                                <img src="<%=request.getContextPath()%>/image?type=p&picId=<s:property value='pictures.iterator().next().picId'/>"
                                     style="float:left"/>
                            </s:if>　　
                            <s:if test="subject.length()<=50">
                                <s:property value="subject"/>
                            </s:if>
                            <s:else>
                                <s:property value="subject.substring(0,100)"/>…
                            </s:else>
                            <a class="detail" href="<%=request.getContextPath()%>/post/farm-post/${id}">[详细内容]</a>
                        </div>
                        <div class="clear"></div>
                    </s:iterator>
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
                            <a href="#" onclick="dos('<s:property value=" page.total"/>');"><s:property
                                    value="page.total"/></a>
                        </s:if>
                    </s:if>
                    <form id="frmSearch" style="display:none;"
                          action="<%=request.getContextPath()%>/post/farm-post"
                          method="get">
                        <s:hidden id="page.current" name="page.current" value="1"/>
                        <s:hidden name="postType.typeId"/>
                        <s:submit cssClass="formbutton" method="index"/>
                    </form>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="farm-post-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
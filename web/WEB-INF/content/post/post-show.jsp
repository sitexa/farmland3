<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%--
  User: xnpeng
  Date: 2009-4-10
  Time: 14:06:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/pic.js"></script>
    <script type="text/javascript">
        function showPic(id) {
            which = id;
            applyeffect();
            document.getElementById("photoslider").src = photos[which];
            document.getElementById("phototitle").innerHTML = titles[which];
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
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="box" style="border:0;">
					<div class="t">
						<div class="t-t">
							${post.name}
						</div>
					</div>
                    <div class="infobox">
                        <a href="<%=request.getContextPath()%>/user/member/${post.creator.memberId}">${post.creator.realname}</a>
                        (<a href="<%=request.getContextPath()%>/site/site/${post.creator.site.siteId}">${post.creator.site.name}</a>)
                        ${post.category.name}
                        <s:date format="M月d日H时m分" name="post.createDate"/>
                        阅读${post.vwCnt}次
                        <s:if test="post.creator.memberId==profile.memberId||haveRight">
                            <a href="<%=request.getContextPath()%>/post/${category.cateId}/${id}/edit">编辑</a>
                            <a href="<%=request.getContextPath()%>/post/${category.cateId}/${id}/destroy">删除</a>
                        </s:if>
                    </div>
                </div>
                <div class="content">
                    <p>${post.content}</p>
                    <s:if test="post.parent!=null">
                        <div class="mainpost">
                            主帖:<a
                                href="<%=request.getContextPath()%>/post/${post.parent.category.cateId}/${post.parent.id}">${post.parent.name}</a>
                        </div>
                    </s:if>
                </div>
                <s:if test="pictures.size()>0">
                    <div class="box" style="width:100%;overflow-x:scroll;">
                        <table>
                            <tr>
                                <s:iterator value="pictures" status="its">
                                    <script>
                                        photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/p/${post.id}/${picUrl}";
                                        titles[${its.index}] = "${picTitle}";
                                        descriptions[${its.index}] = "${description}";
                                    </script>
                                    <td>
                                        <img src="<%=request.getContextPath()%>/image?type=p&picId=${picId}" alt="点击看大图"
                                             onclick="showPic(${its.index})"/>
                                    </td>
                                </s:iterator>
                                <script>
                                    var preloadedimages = new Array();
                                    for (i = 0; i < photos.length; i++) {
                                        preloadedimages[i] = new Image();
                                        preloadedimages[i].src = photos[i];
                                    }
                                </script>
                            </tr>
                        </table>
                    </div>
                    <div class="box">
                        <script>
                            if (photos.length > 0)
                                document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward()" style="filter:revealTrans(duration=2,transition=23)" border=0>');
                        </script>
                        <script>
                            if (photos.length > 0)
                                document.write('<div id="phototitle">' + titles[0] + '</div>');
                        </script>
                        <script>
                            if (photos.length > 0)
                                document.write('<div id="photodescription">' + descriptions[0] + '</div>');
                        </script>
                    </div>
                </s:if>
                <s:if test="post.children.size>0">
                    <div class="box">
						<div class="title">
							<div class="t-text">
								<div class="tag-flowerpot"></div>
								网友评论
							</div>
						</div>
                        <s:iterator value="post.children">
                            <div class="comment">
                                <a href="<%=request.getContextPath()%>/user/member/${creator.memberId}">${creator.realname}</a>
                                <s:date format="M月d日H时m分" name="createDate"/>
                                <s:if test="creator.memberId==profile.memberId||post.creator.memberId==profile.memberId||site.governor.memberId==profile.memberId">
                                    <a href="<%=request.getContextPath()%>/post/post/${id}/destroy">删除</a>
                                </s:if>
                            </div>
                            <div class="content"> ${content} </div>
                        </s:iterator>
                    </div>
                </s:if>
            </div>
            <div class="box">
				<div class="t">
					<div class="t-t">
						评 论
					</div>
				</div>
                <div class="content">
                    <s:form theme="simple" action="%{#request.contextPath}/post/post/%{post.id}/comment">
                        <s:hidden name="parent.id" value="%{post.id}"/>
                        <s:hidden name="category.cateId" value="%{post.category.cateId}"/>
                        <s:textarea rows="5" cssStyle="width:90%;" name="comment"/><br/>
                        <s:submit cssClass="formbutton" value="提交"/>
                    </s:form>
                </div>
            </div>
            <s:actionmessage/>
            <s:actionerror/>
        </div>
        <div id="rightbar" class="rightcol"><s:include value="post-right.jsp"/></div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
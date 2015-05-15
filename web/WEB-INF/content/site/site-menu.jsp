<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box">
	<div class="menu">
	    <div class="menubar">社区档案</div>
	    <div class="menuitem">
	        <ul>
<!-- 
	            <li>
	                <a href="<%=request.getContextPath()%>/main/${site.siteId}">社区首页</a>
	            </li>
 -->
	            <li>
	                <a href="<%=request.getContextPath()%>/site/site/${site.siteId}/show">社区资料</a>
	            </li>
	            <li>
	                <a href="<%=request.getContextPath()%>/site/site-member/${site.siteId}">社区会员</a>
	            </li>
	        </ul>
	    </div>
	</div>
	<s:if test="haveRight">
    <div class="menu">
        <div class="menubar">社区管理</div>
        <div class="menuitem">
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/site/site-member/${site.siteId}/edit">社区会员管理</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/site/site-post/${site.siteId}/edit">社区文章管理</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="menu">
        <div class="menubar">社区档案维护</div>
        <div class="menuitem">
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/site/site/${site.siteId}/edit">修改基本信息</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/site/site-property/${site.siteId}/edit">修改社区属性</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/site/site-picture/${site.siteId}/edit">社区图片管理</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/site/site-category/${site.siteId}/edit">修改社区栏目</a>
                </li>
            </ul>
        </div>
    </div>
	</s:if>
</div>


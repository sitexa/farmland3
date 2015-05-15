<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box margin-top">
	<s:if test="hotPosts.size>0">
	<div class="textBox">
	    <div class="t-title">热门文章</div>
	    <div class="list">
	        <ul>
	            <s:iterator value="hotPosts">
	                <li><a href="<%=request.getContextPath()%>/post/csa/${id}" target="blank">
	                        ${name} [<s:date name="createDate" format="yyyy-MM-dd"/>] </a>
	                </li>
	            </s:iterator>
	        </ul>
	    </div>
	</div>
	</s:if>
	<s:if test="hotPictures.size>0">	
	<div class="textBox">
        <div class="t-title">热门图片</div>
	    <div class="list">
	        <ul>
	            <s:iterator value="hotPictures" status="its">
	                <li style="float:left;margin-left:15px;padding-left:5px;">
	                    <a href="<%=request.getContextPath()%>/post/csa/${post.id}" target="blank">
	                        <img src="<%=request.getContextPath()%>/image?type=p&picId=${picId}" alt="pic"></a>
	                </li>
	            </s:iterator>
	        </ul>
	    </div>
	</div>
	</s:if>
	<s:if test="newMembers.size>0">
	<div class="textBox">
        <div class="t-title">新加盟会员</div>
	    <div class="list">
	        <ul>
	            <s:iterator value="newMembers" status="its">
	                <li>
	                    <a href="<%=request.getContextPath()%>/user/member/${memberId}" target="blank">
	                            ${realname}[${site.name}] </a>
	                </li>
	            </s:iterator>
	        </ul>
	        <div class="clear"></div>
	    </div>
	</div>
	</s:if>
	<s:if test="newFarms.size>0">
	<div class="commonBox">
        <div class="c-title">新加盟农庄</div>
	    <div class="list">
	        <ul>
	            <s:iterator value="newFarms">
	                <li>
	                    <a href="<%=request.getContextPath()%>/land/farm/${farmId}" target="blank">
	                            ${farmName} [${site.name}]</a>
	                </li>
	            </s:iterator>
	        </ul>
	        <div class="clear"></div>
	    </div>
	</div>
	</s:if>
</div>
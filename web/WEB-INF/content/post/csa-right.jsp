<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box margin-top">
	<s:if test="hotPosts.size>0">
	<div class="textBox">
	    <div class="t-title">热门文章</div>
		<div class="t-body">
		    <div id="hotPosts" class="list">
		        <ul>
		            <s:iterator value="hotPosts">
		                <li><a href="<%=request.getContextPath()%>/post/csa/${id}" target="blank" title="[<s:date name="createDate" format="yyyy-MM-dd"/>]：">
		                        ${name}</a>
		                </li>
		            </s:iterator>
		        </ul>
		        <div class="clear"></div>
		    </div>
		</div>
	</div>
	</s:if>
	<s:if test="hotPictures.size>0">
	<div class="textBox">
	    <div class="t-title">热门图片</div>
		<div class="t-body">
			<ul>
			    <s:iterator value="hotPictures" status="its">
			        <li style="float:left;padding-left:13px;">
			            <a href="<%=request.getContextPath()%>/post/csa/${post.id}" target="blank">
			                <img src="<%=request.getContextPath()%>/image?type=p&picId=${picId}" title="${picTitle }" width="75" height="57">
						</a>
			        </li>
			    </s:iterator>
			</ul>
			<div class="clear"></div>
		</div>
	</div>
	</s:if>
	<s:if test="newMembers.size>0">
	<div class="textBox">
		<div class="t-title">新加盟会员</div>
		<div class="t-body">
		    <div class="list">
		        <ul>
		            <s:iterator value="newMembers" status="its">
		                <li><div class="detail">[${site.name}]</div>
		                    <a href="<%=request.getContextPath()%>/user/member/${memberId}" target="blank">${realname}</a> 
		                </li>
		            </s:iterator>
		        </ul>
		        <div class="clear"></div>
		    </div>
		</div>
	</div>
	</s:if>
	<s:if test="newFarms.size>0">
	<div class="textBox">
		<div class="t-title">新加盟农庄</div>
		<div class="t-body">
		    <div id="newFarms" class="list">
		        <ul>
		            <s:iterator value="newFarms">
		                <li><div class="detail">[${land.landName}]</div>
							<a href="<%=request.getContextPath()%>/land/farm/${farmId}" target="blank" title=""> ${farmName} </a>
		                </li>
		            </s:iterator>
		        </ul>
		        <div class="clear"></div>
		    </div>
		</div>
	</div>
	</s:if>
</div>

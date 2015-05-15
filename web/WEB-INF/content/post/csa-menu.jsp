<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box margin-top">
	<div class="menu">
	    <div class="menubar">栏目名称</div>
	    <div class="menuitem">
	        <ul>
	            <s:iterator value="postTypes">
					<s:if test="csaType.typeId==typeId"><li class="select"></s:if><s:else><li></s:else>
	                    <a href="<%=request.getContextPath()%>/post/csa?csaType.typeId=${typeId}">${name}</a>
	                </li>
	            </s:iterator>
	            <s:if test="#request.newTag==1"><li class="select"></s:if><s:else><li></s:else>
	                <a href="<%=request.getContextPath()%>/post/csa/new">发表文章</a>
	            </li>
	        </ul>
	    </div>
	</div>
	<s:actionerror cssClass="error"/>
	<s:actionmessage cssClass="message"/>
</div>


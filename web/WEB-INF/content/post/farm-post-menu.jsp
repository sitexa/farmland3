<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box">
    <div class=menu>
        <div class="menubar">农庄风采</div>
        <div class="menuitem">
            <ul>
                <s:iterator value="postTypes">
                    <li>
                        <a href="<%=request.getContextPath()%>/post/farm-post?typeId=${typeId}">${name}</a>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
    <div style="background-color:#bbb;padding:4px;">
        <a href="<%=request.getContextPath()%>/post/farm-post/new?farmId=${farm.farmId}">发表文章</a>
    </div>
</div>

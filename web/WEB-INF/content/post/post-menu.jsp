<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box">
    <div class="menu">
        <div class="menubar">
            社区栏目
        </div>
        <div class="menuitem">
            <ul>
                <s:iterator value="categories">
                    <li>
                        <a href="<%=request.getContextPath()%>/post/post?cateId=${cateId}">${name}</a>
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
</div>


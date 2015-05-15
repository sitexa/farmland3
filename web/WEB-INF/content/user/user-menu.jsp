<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box">
    <div class="title">用户档案</div>
    <div class="menu">
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/user/member/${member.memberId}">会员信息</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/user/member-farm/${member.memberId}">会员农庄</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/user/member-post/${member.memberId}">会员文章</a>
            </li>
        </ul>
    </div>
</div>
<s:if test="profile.memberId==member.memberId">
    <div class="box">
        <div class="title">资料管理</div>
        <div class="menu">
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/user/user/${member.memberId}/password">修改登录密码</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/user/member/${member.memberId}/edit">修改会员信息</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/user/member-property/${member.memberId}/edit">修改会员属性</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/user/member-picture/${member.memberId}/edit">会员图片管理</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/user/member-credit">农庄银号管理</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="box">
        <div class="title">朋友管理</div>
        <div class="menu">
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/user/member-site/${member.memberId}/edit">友好社区</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/user/friend/${member.memberId}/edit">亲朋好友</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/user/visitor/${member.memberId}">最近来访</a>
                </li>
            </ul>
        </div>
    </div>
</s:if>



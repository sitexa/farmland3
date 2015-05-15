<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box">
<s:if test="site!=null">
    <div class="textBox">
        <div class="t-title">${site.name}</div>
        <div class="t-body">
            <span class="subtitle">地址:</span>${site.address}<br />
            <s:iterator value="site.properties">
                <span class="subtitle">${propName}</span>:${propValue}<br />
            </s:iterator>
            <s:iterator value="site.pictures" status="its">
                <s:if test="#its.index<1">
                    <img src="<%=request.getContextPath()%>/image?type=s&picId=${picId}" alt=""/>
                </s:if>
            </s:iterator>
        </div>
    </div>
    <div class="textBox">
        <div class="t-title">社区管理员</div>
        <div class="t-body">
            <s:if test="site.governor!=null">
                <s:property value="site.type.governor"/>:
                <a href="<%=request.getContextPath()%>/user/member/${site.governor.memberId}">
                    <s:property value="site.governor.realname"/>
                </a><br/>
                <s:iterator value="site.governor.properties">
                    <s:property value="propName"/>:<s:property value="propValue"/><br/>
                </s:iterator>
                <s:iterator value="site.governor.pictures" status="its">
                    <s:if test="#its.index<1">
                        <img src="<%=request.getContextPath()%>/image?type=m&picId=${picId}" alt=""/>
                    </s:if>
                </s:iterator>
            </s:if>
        </div>
    </div>
    <s:if test="site.governor==null&&profile!=null">
        <s:if test="home==null||(home!=null&&home.governor==null)||(home.governor!=null&&profile.memberId!=home.governor.memberId)">
            <div class="textBox">
                <div class="t-body">
		                    社区管理员(<span style="color:red"><s:property value="site.type.governor"/></span>)
		                    招募中...如果你有责任感,热心公益,那么你可以竞争该社区管理员岗位.<a href="#">报名参选.</a>
                </div>
            </div>
        </s:if>
    </s:if>
</s:if>
<s:if test="me==null">
    <div class="textBox">
        <div class="t-title">登录社的家</div>
        <div class="t-body">
            <s:form action="%{#request.contextPath}/logon!login" theme="simple" cssStyle="margin:4px">
                用户名:<s:textfield cssStyle="width:80px;" name="user.username"/><br/>
                密电码:<s:password cssStyle="width:80px;" name="user.password"/><br/>
                识别码:<s:textfield cssStyle="width:40px;" name="code"/>
                <img src="<%=request.getContextPath()%>/image!codeImage" alt="识别码"/><br/>
                记住我:<s:checkbox name="remember" value="true"/>
                <s:submit value="登录"/><br/>
                <a href="<%=request.getContextPath()%>/login!findpwd">找回密码</a>
                <a href="<%=request.getContextPath()%>/user/user/new">立即注册</a>
            </s:form>
        </div>
    </div>
</s:if>
<s:if test="me!=null">
    <s:if test="profile!=null">
        <s:if test="home==null">
            <div class="textBox" style="background:#ffc">
                <div class="t-body">
		                    还没有家? 您可以通过<a href="<%=request.getContextPath()%>/user/member/${profile.memberId}/edit">修改会员资料</a>,
		                    加入到一个社区中去,比如居住的小区,工作的园区,或者家乡的小区.或者可以马上<a href="<%=request.getContextPath()%>/site/site/new">建立一个</a>
		                    社区.
                </div>
            </div>
        </s:if>
    </s:if>
</s:if>
<div class="textBox" style="background:#ffc">
    <div class="t-body">
        <a href="<%=request.getContextPath()%>/site/site/new"><strong>建立新社区</strong></a><br />
        	社区必须是现实中存在的,比如居住的小区,工作的园区,或者家乡的小区.
    </div>
</div>
</div>
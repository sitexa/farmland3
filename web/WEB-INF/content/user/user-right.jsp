<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="site!=null">
    <div class="box">
        <div class="title"><a href="<%=request.getContextPath()%>/site/site/${site.siteId}">${site.name}</a></div>
        <div class="content">
            <span class="subtitle">地址:</span>${site.address}</br>
            <span class="subtitle">管理员:</span>${site.governor.realname}</br>
            <s:iterator value="site.properties">
                <span class="subtitle">${propName}</span>:${propValue}<br/>
            </s:iterator>
            <s:iterator value="site.pictures" status="its">
                <s:if test="#its.index<1">
                    <img src="<%=request.getContextPath()%>/image?type=s&picId=${picId}" alt=""/>
                </s:if>
            </s:iterator>
        </div>
    </div>
    <div class="box">
        <div class="title">社区管理员</div>
        <s:if test="site.governor!=null">
            <div class="content">
                <s:property value="site.type.governor"/>:
                <a href="<%=request.getContextPath()%>/user/member/${site.governor.memberId}">${site.governor.realname}</a><br/>
                <s:iterator value="site.governor.properties">
                    <span class="subtitle">${propName}</span>:${propValue}<br/>
                </s:iterator>
                <s:iterator value="site.governor.pictures" status="its">
                    <s:if test="#its.index<1">
                        <img src="<%=request.getContextPath()%>/image?type=m&picId=${picId}" alt=""/>
                    </s:if>
                </s:iterator>
            </div>
        </s:if>
    </div>
    <s:if test="site.governor==null&&profile!=null">
        <s:if test="home==null||(home!=null&&home.governor==null)||(home.governor!=null&&profile.memberId!=home.governor.memberId)">
            <div class="box" style="background:#ffc">
                <div class="content">
                    社区管理员(<span style="color:red"><s:property value="site.type.governor"/></span>)
                    招募中...如果你有责任感,热心公益,那么你可以竞争该社区管理员岗位.<a href="#">报名参选.</a>
                </div>
            </div>
        </s:if>
    </s:if>
</s:if>
<s:if test="me==null">
    <div class="box" style="background:#ffc">
        <div class="content">
            您还没有登录!<br><a href="<%=request.getContextPath()%>/login">请登录社的家</a>
        </div>
    </div>
</s:if>
<s:if test="me!=null">
    <s:if test="profile==null">
        <div class="box" style="background:#ffc">
            <div class="content">
                还没有家?花上<span style="color:blue;">10秒钟</span>,填写完用户资料后,您就有了社的家和我的家,就有权发表文章.
                马上<a href="<%=request.getContextPath()%>/user/member/new">填写用户资料</a>.
            </div>
        </div>
    </s:if>
    <s:if test="profile!=null">
        <s:if test="home==null">
            <div class="box" style="background:#ffc">
                <div class="content">
                    还没有家? 您可以通过<a href="<%=request.getContextPath()%>/user/member/${profile.memberId}/edit">修改会员资料</a>,
                    加入到一个社区中去,比如居住的小区,工作的园区,或者家乡的小区.或者可以马上<a href="<%=request.getContextPath()%>/site/site/new">建立一个</a>
                    社区.
                </div>
            </div>
        </s:if>
        <s:if test="home!=null&&home.governor!=null&&home.governor.memberId!=profile.memberId">
            <div class="box" style="background:#ffc">
                <div class="content">
                    还没有拥有社区?何不马上<a href="<%=request.getContextPath()%>/site/site/new">建立一个</a>?
                    社区必须是现实中存在的,比如居住的小区,工作的园区,或者家乡的小区.
                </div>
            </div>
        </s:if>
    </s:if>
</s:if>


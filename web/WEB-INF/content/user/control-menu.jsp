<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="box">
	<s:if test="me==null">
		<div class="box">
			您还没登录
		</div>
	</s:if>
	<s:else>
		<div class="menu">
			<div class="menubar">用户档案</div>
			<div class="menuitem">
				<ul>
					<li>
						<a href="<%=request.getContextPath()%>/user/member/${member.memberId}">会员信息</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/user/member-post/${member.memberId}">会员文章</a>
					</li>
                    <li>
                        <a href="<%=request.getContextPath()%>/user/member-credit/${member.memberId}">农庄银号</a>
                    </li>
				</ul>
			</div>
		</div>
	</s:else>
	<s:if test="profile.memberId==member.memberId">
		<div class="menu">
			<div class="menubar">
				资料管理
			</div>
			<div class="menuitem">
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
				</ul>
			</div>
		</div>
		<div class="menu">
			<div class="menubar">
				朋友管理
			</div>
			<div class="menuitem">
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
					<li>
						<a href="<%=request.getContextPath()%>/user/leave-message">好友留言</a>
					</li>
				</ul>
			</div>
		</div>
	</s:if>
	
	<s:if test="member.farms.size>0">
		<div class="menu">
			<div class="menubar">
				会员农庄
			</div>
			<div class="menuitem">
				<ul>
					<s:iterator value="member.farms">
						<li>
							<a href="<%=request.getContextPath()%>/work/work/${farmId}">${farmName}</a>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</s:if>

</div>
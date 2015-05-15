<%--
  User: xnpeng
  Date: 2009-5-6
  Time: 21:49:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function doSave(id) {
            var f = document.getElementById("frmFriends");
            f.fid.value = id;
            f.op.value = "save";
            f.submit();
        }
        function doDelete(id) {
            if (confirm("确实删除?")) {
                var f = document.getElementById("frmFriends");
                f.fid.value = id;
                f.op.value = "delete";
                f.submit();
            }
        }
        function doAccept(id) {
            var f = document.getElementById("frmFellows");
            f.fid2.value = id;
            f.op2.value = "accept";
            f.submit();
        }
        function doDeny(id) {
            if (confirm("确实删除?")) {
                var f = document.getElementById("frmFellows");
                f.fid2.value = id;
                f.op2.value = "deny";
                f.submit();
            }
        }
    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="title2">
                        亲朋好友2
                </div>
                <div class="content">
                    <s:if test="friends.size>0">
                        <s:form name="frmFriends" id="frmFriends" theme="simple" action="%{#request.contextPath}/user/friend/%{member.memberId}/update">
                            <s:hidden id="op" name="op"/>
                            <s:hidden id="fid" name="fid"/>
							<div class="list">
								<ul>
	                            <s:iterator value="friends">
									<li>
	                                <s:hidden name="friends(%{id}).id" value="%{id}"/>
	                                <a href="<%=request.getContextPath()%>/user/member/${fellow.memberId}/show">
	                                    <s:property value="fellow.realname"/>
	                                </a>
	                                <s:property value="fellow.genderName"/>
	                                <a href="<%=request.getContextPath()%>/site/site/${fellow.site.siteId}/show">
	                                    <s:property value="fellow.site.name"/>
	                                </a>
	                                <a href="#" onclick="doDelete(${id});">删除</a>
									</li>
	                            </s:iterator>
								</ul>
							</div>
                        </s:form>
                    </s:if>
                    <s:else>
                        暂无好友.
                    </s:else>
                </div>
            </div>
			<s:if test="fellows.size>0">
            <div class="box">
                <div class="title">
                    <div class="t-text">
                        <div class="tag-flowerpot"></div>
                        受邀列表
                    </div>
                </div>
                <div class="content">
                   <s:form name="frmFellows" id="frmFellows" theme="simple"
                           action="%{#request.contextPath}/user/friend/%{member.memberId}/update2">
                       <s:hidden id="op2" name="op2"/>
                       <s:hidden id="fid2" name="fid2"/>
                       <s:iterator value="fellows">
                           <s:hidden name="fellows(%{id}).id" value="%{id}"/>
                           <a href="<%=request.getContextPath()%>/user/member/${member.memberId}/show">
                               <s:property value="member.realname"/>
                           </a>
                           <s:property value="member.genderName"/>
                           <a href="<%=request.getContextPath()%>/site/site/${member.site.siteId}/show">
                               <s:property value="fellow.site.name"/>
                           </a>
                           <s:property value="remark"/>
                           <a href="#" onclick="doAccept(${id});">接受</a> |
                           <a href="#" onclick="doDeny(${id});">拒绝</a> <br/>
                       </s:iterator>
                   </s:form>
                </div>
            </div>
			</s:if>
            <div class="box">
                <div class="title">
                    <div class="t-text">
                        <div class="tag-flowerpot"></div>
                        邀请加盟
                    </div>
                </div>
                <div class="content">
                    <s:form name="frmFriend" id="frmFriend"
                            action="%{#request.contextPath}/user/friend/%{member.memberId}/create" theme="simple"
                            method="post">
                        会员编号:<s:textfield name="friend.fellow.memberId"/><br/>
                        给他的话:<br>
                        <s:textarea name="friend.remark" cols="40" rows="4"/><br/><br/>
                        <s:submit cssClass="formbutton" value="提交申请"/>
                    </s:form>
                </div>
            </div>
            <s:actionmessage cssClass="message"/>
            <s:actionerror cssClass="error"/>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="control-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>
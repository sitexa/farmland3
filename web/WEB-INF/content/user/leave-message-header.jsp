<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
	$("#menubar").ready(function(){
		selectMenu(${menu});
	})
</script>
<div id="menubar">
	<ul>
		<li class="current"><a title="收件箱" href="<%=request.getContextPath()%>/user/leave-message!inBox">收件箱</a> </li>
		<li><a title="发件箱" href="<%=request.getContextPath()%>/user/leave-message!outBox">发件箱</a> </li>
		<li><a title="留言" href="<%=request.getContextPath()%>/user/leave-message!send">留言</a> </li>
	</ul>
	<div class="clear"></div>
</div>

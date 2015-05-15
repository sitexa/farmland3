<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>购买种子/种苗</title>
	<link href="<%=request.getContextPath()%>/myfarm/myfarm-tools.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/myfarm/myfarm-shop.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
	<script type="text/javascript">
		function buySeed(){
			var pwd = $.trim($("#pwd").val());
			if(pwd==""){
				alert("请您输入密码。");
				return;
			}
			var val = new Object();
			val.number = document.getElementById("number").value;
			val.pwd = document.getElementById("pwd").value;
			window.returnValue = val;
			window.close();
		}
		function checkNumber(){
			var $number = $("#number");
			if(!ispositiveInteger($number.val())){
				$number.val(1);
			}
		}
	</script>
</head>
<body>
	<div class="window">
		<div class="header">购买种子</div>
		<div class="body">
			<div style="width:500px;padding:0 47px;text-align:center">
				<div class="seedName">${seed.seedName}</div>
				<div class="left">
					<img src="<%=request.getContextPath()%>/image?type=seed&picId=${seed.seedId}" />
					<div class="info">
						<font color="#BA2636">花币</font>&nbsp;<font style="font-weight:bold;color:#FF8000">${seed.price }</font>
					</div>	
					<form id="buySeed" action="<%=request.getContextPath()%>/myfarm/shop!buySeed" style="margin:0;padding:0">
						<input type="hidden" name="seedId" value="${seed.seedId}" />
						<input type="text" id="number" value="1" style="width:30px;text-align:center;" onchange="checkNumber()" /><br />
					</form>
					输入购买数量			
				</div>
				<div class="right">
					<div class="description">${seed.description}</div>
				</div>
				<div class="line"></div>
				<br />
				<font color="#BA2636">请输入支付密码：</font><input type="password" id="pwd" name="pwd"/>
				<br /><br />
				<input type="button" value="购 买" onclick="buySeed()"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="取 消" onclick="window.close()"/>
				<br /><br />
			</div>
		</div>
	</div>
</body>
</html>
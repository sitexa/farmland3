<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>购买种子/种苗</title>
	<link href="<%=request.getContextPath()%>/myfarm/myfarm-tools.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/myfarm/myfarm-shop.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="window">
		<div class="header">种子信息</div>
		<div class="body">
			<div style="width:500px;padding:0 47px;text-align:center">
				<div class="seedName">${seed.seedName}</div>
				<div class="left">
					<img src="<%=request.getContextPath()%>/image?type=seed&picId=${seed.seedId}" />
					<div class="info">
						<font color="#BA2636">数量</font>&nbsp;<font style="font-weight:bold;color:#FF8000">${good[2]}</font><br /><font style="font-size:12px; color:#BA2636">(单位：平方米)</font>
					</div>	
				</div>
				<div class="right">
					<div class="description">${seed.description}</div>
				</div>
				<div class="line"></div>
				<br /><br />
				<input type="button" value=" 关 闭 " onclick="window.close()"/>
				<br /><br />
			</div>
		</div>
	</div>
</body>
</html>
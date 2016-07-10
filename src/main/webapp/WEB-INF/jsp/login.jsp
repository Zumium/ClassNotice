<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录</title>
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/login.css">
</head>
<body>
	<div class="wrapper">
		<img id="loginLogo" src="/resources/image/login.png">
		<form role="form" action="/login" method="post">
		<h5><c:out value="${param.loginError}"/></h5>
			<div class="form-group">
			<label class="form-label">用户名</label>
				<input name="id" type="text" class="form-control" placeholder="请输入用户名"/><br />
			<label class="form-label">密码</label>
				<input name="password" type="password" class="form-control" placeholder="请输入密码"/><br />
			</div>
			<button class="btn btn-info" name="submit" id="btn_submit" type="submit">登录</button>
		</form>
	</div>
</body>
</html>

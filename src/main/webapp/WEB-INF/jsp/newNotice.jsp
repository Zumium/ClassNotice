<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="type/html; charset=utf-8">
	<title>发布通知</title>
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/announce.css">
</head>
<body>
	<aside class="left">
		<img id="headsculpture" src="${selfPortrait}">
		<ul class="list-group">
			<a href="/">
				<li class="list-group-item">
					<span class="badge">${unreadCount}</span>未读通知
				</li>
			</a>
			<a href="/read">
				<li class="list-group-item">
					<span class="badge">${readCount}</span>已读通知
				</li>
			</a>
			<a href="/star">
				<li class="list-group-item">
					<span class="badge">${starCount}</span>标星通知
				</li>
			</a>
			<a href="readStatus">
				<li class="list-group-item">
					<span class="badge">${sentCount}</span>通知阅读情况
				</li>
			</a>
			<a href="/publishNotice">
				<li class="list-group-item list-active">
					发布通知
				</li>
			</a>
		</ul>
	</aside>
	<aside class="right">
		<div class="paper">
			<form action="/notice" method="post" id="noticeform">
				<label class="label_paper">主题</label>
				<input class="subject" name="title"/>	<br />
				<label class="label_paper">通知接收者</label>
				<input type="radio" name="receiversGroup" value="all" checked/>全体同学
				<input type="radio" name="receiversGroup" value="committee"/>班委  <br>
			</form>
				<label class="label_paper">正文</label>				
				<textarea class="text" form="noticeform" name="content" rows="30"></textarea>
				<button class="btn btn-info submit" form="noticeform" type="submit">提交</button>
		</div>
	</aside>

	<script src="/resources/js/jquery.min.js"></script>
 	<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>

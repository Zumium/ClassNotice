<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="type/html; charset=utf-8">
	<title>${pageTitle}</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="/resources/css/unread_detail.css">
</head>
<body>
	<div class="left">
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
		</ul>
	</div>
	<div class="right">
		<div class="paper">
			<h2>${noticeItem.notice.title}</h2>
			<h4>${noticeItem.senderBanner}<time>${noticeItem.notice.publishTime}</time></h4>
			<article>${noticeItem.notice.content}</article>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</body>
</html>

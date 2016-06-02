<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="type/html; charset=utf-8">
	<title>${pageTitle}</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="/resources/css/unread.css">
</head>
<body>
	<div class="left">
		<img id="headsculpture" src="${selfPortrait}">
		<ul class="list-group">
			<li class="list-group-item list-active">
				<span class="badge">${unreadCount}</span>未读通知
			</li>
			<li class="list-group-item">
				<span class="badge">${readCount}</span>已读通知
			</li>
			<li class="list-group-item">
				<span class="badge">${starCount}</span>标星通知
			</li>
		</ul>
	</div>
	<div class="right">
		<ul class="media-list">
		<c:forEach items="${listItems}" var="eachItem">
			<a href="">	
			<li class="media">
				<img class="media-object" src="${eachItem.senderPortrait}">
				<div class="media-body">
					<h4 class="media-heading">${eachItem.notice.title}
						<time>${eachItem.notice.publishTime}</time>
					</h4>
					<div>${eachItem.senderBanner}</div>
				</div>
			</li>
			</a>
		</c:forEach>
		</ul>
	</div>

	<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</body>
</html>

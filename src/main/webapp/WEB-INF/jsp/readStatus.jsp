<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="type/html; charset=utf-8">
	<title>通知阅读情况</title>
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/noticeRead.css">
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
			<a href="/readStatus">
				<li class="list-group-item list-active">
					<span class="badge">${sentCount}</span>通知阅读情况
				</li>
			</a>
			<a href="/publishNotice">
				<li class="list-group-item">
					发布通知
				</li>
			</a>
		</ul>
	</div>
	<div class="right">
		<ul class="media-list">
			<c:forEach items="${readStatuses}" var="eachStatus">
			<a href="${eachStatus.noticePath}">	
			<li class="media">
				<div class="media-body">
					<h4 class="media-heading">
						${eachStatus.notice.title}
						<time>${eachStatus.notice.publishTime}</time>
						<p class="readnum">阅读人数：<span>${eachStatus.readCount}人/${eachStatus.receiversCount}人</span></p>
					</h4>
				</div>
			</li>
			</a>
			</c:forEach>
		</ul>
	</div>

	<script src="/resources/js/jquery.min.js"></script>
 	<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>

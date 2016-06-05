<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="type/html; charset=utf-8">
	<title>${pageTitle}</title>
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/read_detail.css">
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
					<span id="starCountDisplayer" class="badge">${starCount}</span>标星通知
				</li>
			</a>
			<c:if test="${isAdmin==true}">
			<a href="/readStatus">
				<li class="list-group-item">
					<span class="badge">${sentCount}</span>通知阅读情况
				</li>
			</a>
			<a href="/publishNotice">
				<li class="list-group-item">
					发布通知
				</li>
			</a>
			</c:if>
		</ul>
	</div>
	<div class="right">
		<div class="paper">
			<h2>${noticeItem.notice.title}</h2>
			<div class="block_star">
				<input id="chkbox_star" class="input_check" type="checkbox" <c:if test="${noticeItem.star==true}"><c:out value="checked"/></c:if> />
				<label for="chkbox_star" class="img_star" >
				</label>
			</div>
			<h4>${noticeItem.senderBanner}<time>${noticeItem.notice.publishTime}</time></h4>
			<article>
			${noticeItem.notice.content}
			</article>
		</div>
	</div>

	<script src="/resources/js/jquery.min.js"></script>
 	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/js/readPage.js"></script>
</body>
</html>

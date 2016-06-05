<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="type/html; charset=utf-8">
	<title>${pageTitle}</title>
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/read.css">
</head>
<body>
	<div class="left">
		<img id="headsculpture" src="${selfPortrait}">
		<ul class="list-group">
			<a href="/">
				<li class="list-group-item <c:if test="${pageIndex==0}"><c:out value="list-active"/></c:if>">
					<span class="badge">${unreadCount}</span>未读通知
				</li>
			</a>
			<a href="/read">
				<li class="list-group-item <c:if test="${pageIndex==1}"><c:out value="list-active"/></c:if>">
					<span class="badge">${readCount}</span>已读通知
				</li>
			</a>
			<a href="/star">
				<li class="list-group-item <c:if test="${pageIndex==2}"><c:out value="list-active"/></c:if>">
					<span class="badge">${starCount}</span>标星通知
				</li>
			</a>
			<c:if test="${isAdmin==true}">
			<a href="/readStatus">
				<li class="list-group-item <c:if test="${pageIndex==3}"><c:out value="list-active"/></c:if>">
					<span class="badge">${sentCount}</span>通知阅读情况
				</li>
			</a>
			<a href="/publishNotice">
				<li class="list-group-item <c:if test="${pageIndex==4}"><c:out value="list-active"/></c:if>">
					发布通知
				</li>
			</a>
			</c:if>
		</ul>
	</div>
	<div class="right">
		<ul class="media-list">
			<c:forEach items="${listItems}" var="eachItem">
			<a href="${eachItem.noticePath}">	
			<li class="media">
				<img class="media-object" src="${eachItem.senderPortrait}">
				<div class="media-body">
					<h4 class="media-heading">
						<c:choose>
						<c:when test="${eachItem.star==true && pageIndex!=0}">
							<img class="star"src="/resources/image/star.png">
						</c:when>
						<c:when test="${eachItem.star==false && pageIndex!=0}">
							<img class="star"src="/resources/image/unstar.png">
						</c:when>
						</c:choose>
						${eachItem.notice.title}
						<time>${eachItem.notice.publishTime}</time>
					</h4>
					<div>${eachItem.senderBanner}</div>
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

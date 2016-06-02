<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello World</title>
    </head>
    <body>
	    <h2>${message}</h2>
	    <img src="${portraitUrl}">
	    <h2>${usermessage}</h2>
	    <h2>You have ${totalCount} notices totally</h2>
	    <h2>You have ${unreadCount} unread notices</h2>

	    <c:forEach items="${unreadNotice}" var="eachUnread">
		<p>Title: <c:out value="${eachUnread.title}"/></p>
		<p>Content: <c:out value="${eachUnread.content}"/></p>
		<p>Publish Time: <c:out value="${eachUnread.publishTime}"/></p>
	    </c:forEach>
	    <a href="/logout">Logout</a>
    </body>
</html>

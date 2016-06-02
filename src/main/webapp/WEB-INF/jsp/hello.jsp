<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello World</title>
    </head>
    <body>
	    <h2>${message}</h2>
	    <h2>${usermessage}</h2>
	    <h2>You have ${unreadCount} unread notices</h2>
	    <h2>You have ${totalCount} notices</h2>
	    <p>Title: ${unreadNotice[0].title}</p>
	    <p>Content: ${unreadNotice[0].content}</p>
	    <p>Publish Time: ${unreadNotice[0].publishTime}</p>
	    <a href="/logout">Logout</a>
    </body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
	    <form action="/login" method="post">
	    	<div>
			Username:<br>
			<input type="text" name="id"><br>
			Password:<br>
			<input type="password" name="password"><br>
			<input type="submit" value="Submit">
		</div>
	    </form>
    </body>
</html>

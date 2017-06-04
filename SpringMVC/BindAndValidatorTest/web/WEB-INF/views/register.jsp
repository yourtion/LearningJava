<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>register page</title>
</head>
<body>
<div id="global">
    <form:form modelAttribute="user" method="post" action="register">
        <fieldset>
            <legend>register</legend>
            <p>
                <label>name:</label>
                <form:input path="username" />
            </p>
            <p>
                <label>password:</label>
                <form:password path="password" />
            </p>
            <p>
                <label>age:</label>
                <form:input path="age" />
            </p>
            <p>
                <label>phone:</label>
                <form:input path="phone" />
            </p>
            <p>
                <label>email:</label>
                <form:input path="email" />
            </p>
            <p id="buttons">
                <input id="submit" type="submit" value="register"> <input
                    id="reset" type="reset" value="reset">
            </p>
        </fieldset>
    </form:form>
</div>
</body>
</html>
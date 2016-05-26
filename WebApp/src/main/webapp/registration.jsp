<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="content/style.css">
<title>Web App</title>
</head>
<body>
<h1>Registration</h1>
<div id="options"> 
<button id="first"><a href="index.jsp">Main</a></button>
<button id="authorization"><a href="authorization.jsp">Log in</a></button>
</div>
<div id="form">
    <div id="errors">
        <center>${errors} <br/></center>
        </div>
    <form name="sign_up" action="/Account/register" method="Post">
        <p id="name">
            <input type="text" name="name" placeholder="Name" value="${user.getName()}" required />
        </p>
        <p id="surname">
            <input type="text" name="surname" placeholder="Surname" value="${user.getSurname()}" required />
        </p>
        <p id="password">
            <input type="password" name="password" placeholder="Password" required />
        </p>
        <p id="password2">
            <input type="password" name="password2" placeholder="Confirm password" required />
        </p>
        <p id="mail">
            <input id="email" type="email" name="email" placeholder="E-mail" value="${user.getEmail()}" required />
        </p>
        <p id="login">
            <input id="log_in" type="text" name="login" placeholder="Login name" value="${user.getLogin()}" required />
        </p>
        <p id="gender">
            Gender*:
        <p><input type="radio" name="gender" value="male" checked required />Male</p>
        <p><input type="radio" name="gender" value="female" required />Female</p>
        </p>
                <p id="button">
                    <input id="submit" type="submit" name="submit" value="Sign up">
                </p>
            </form>
        </div>
</body>
</html>
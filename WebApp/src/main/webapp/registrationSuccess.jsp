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
<h1>Registration success</h1>
<div id="options">
    <button id="first"><a href="index.jsp">Main</a></button>
    <button id="registration"><a href="authorization.jsp">Log In</a></button>
</div>
<div id="name">
    <c:if test="${not empty name}">
        <center><h2> Congratulations, ${name} ${surname}! You are a new registered user!</h2></center>
    </c:if>
</div>
</body>
</html>

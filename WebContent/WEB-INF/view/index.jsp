<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Spring - managed by GIT</title>
</head>
<style>
	a {
		text-decoration: none;
	}
	
</style>
<body>
	<div align="center">
		<div style="width: 980px;">
			<div>
				<h1>Spring Project</h1>
				<small>- ${ment } -</small>
			</div>
			<hr/>
			<div align="right" style="padding-right: 20px;">
				<a href="/login"><span>Sign in</span></a> <span>or</span> 
				<a href="/join"><span>Sign up</span></a>
			</div>
			<hr/>
		</div>
	</div>
</body>
</html>
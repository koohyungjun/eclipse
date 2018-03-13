<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Spring - managed by GIT</title>
</head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>
	<div align="center" class="container">
		<div style="width: 980px;">
			<div class="jumbotron">
				<h1>Spring Project</h1>
				<small>- ${ment } -</small>
			</div>
			<hr />
			<div align="right" style="padding-right: 20px;">
				<a href="/login"><span>Sign in</span></a> <span>or</span> <a
					href="/join"><span>Sign up</span></a>
			</div>
			<hr />
		</div>
	</div>
</body>
</html>
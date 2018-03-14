<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>login in to Spring</title>
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
<style>
	label {
		margin-bottom: 7px;
	}
	input {
		margin-bottom: 15px;
	}
</style>
<body>
	<div align="center" class="container">
		<form action="/session" method="post" >
			<div>
				<h1>Sign in to SPRING</h1>
			</div>
			<c:if test="${!empty err }">
			<div class="alert alert-danger">${err }</div>
			</c:if>
			<div class="">
				<label for="login_field" > Username or email address </label> <input
					type="text" name="idmail" id="login_field" class="form-control"
					tabindex="1" autofocus="autofocus"> <label for="password">
					Password <a class="label-link" href="/password_reset">Forgot
						password?</a>
				</label> <input type="password" name="password" id="password"
					class="form-control">
				<button type="submit" name="commit" value="Sign in"
					class="btn btn-primary btn-block" style="margin-top: 20px;">Sign
					in</button>
			</div>
		</form>
	</div>
</body>
</html>
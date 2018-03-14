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
		<div class="jumbotron">
			<h1>Spring Project</h1>
			<small>- ${ment } -</small>
		</div>
		<div class="alert alert-warning alert-dismissible" id="warn"
			style="display: none">
			<a href="javascript:location.reload();" class="close"
				data-dismiss="alert" aria-label="close">&times;</a> <strong>경고!</strong>
			다른 윈도우 혹은 탭에서 상태가 변경되었습니다.
		</div>
		<hr />
		<div align="right" style="padding-right: 20px;">
			<a href="/login"><span>Sign in</span></a> <span>or</span> <a
				href="/join"><span>Sign up</span></a>
		</div>
		<hr />
	</div>
	<div>
		<div class="alert alert-info">
			<b>현재접속자수 : </b><span id="cnt"></span> <br /> <strong>서버알림
				: </strong><span id="info">-</span>
		</div>
	</div>

	<!-- ${pageContext.request.serverName } -->
	<script>
		var ws = new WebSocket("ws://${pageContext.request.serverName}/alert");
		ws.onmessage = function(rst) {
			console.log(rst);
			$("#warn").show();
			// var obj = JSON.parse(rst);
		}

		var ws = new WebSocket("ws://${pageContext.request.serverName}/handle");
		// 연결이 됬을때. 
		ws.onopen = function() {
			console.log("opened ");
			console.log(this);
		}
		// 메시지가 들어올때.  
		ws.onmessage = function(resp) {
			console.log(resp);
			var obj = JSON.parse(resp.data); // {"cnt" : ??, "info" :""}
			$("#cnt").html(obj.cnt);
			$("#info").html(obj.info);
		}
		// 연결이 끊길때. 
		ws.onclose = function() {
			window.alert("연결이 해제되었습니다.");
		}
	</script>
</body>
</html>
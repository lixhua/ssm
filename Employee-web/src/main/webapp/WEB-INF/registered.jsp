<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>注册</title>
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/font-awesome.min.css" />
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.11.1.min.js" ></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js" ></script>
		<script type="text/javascript">
			$(function(){
				$("#submitBtn").click(function(){
					var username = $("#username").val();
					var password = $("#password").val();
					$.ajax({
						url:"${pageContext.servletContext.contextPath}/sys_user",
						type:"post",
						data:{username:username,password:password},
						dataType:"json",
						success:function(_data){
							if(_data.flag){
								location.href="login";
							}else{
								$("#enameAlertLabel").show();
								$("#enameErrorMsg").html("用户名已存在,请重新注册.");
							}
						}
					})
					return false;
				});
			})
		</script>
	</head>
	<body>
		<div class="container" style="margin-top: 50px">
			<div class="row">
				<div class="col-xs-6 col-xs-push-3">
					<div class="panel-success">
						<div class="panel-heading">
							<h3 class="panel-title">用户注册</h3>
						</div>
						<div class="panel-body">
							<div id="enameAlertLabel" class="alert alert-warning" style="display:none;">
								<span id="enameErrorMsg"></span>
							</div>
							<form class="form">
								<div class="form-group">
									<label>用户名:</label>
									<input type="text" name="username" id="username" class="form-control">
								</div>
								<div class="form-group">
									<label>密　码:</label>
									<input type="password" name="password" id="password" class="form-control">
								</div>
								<button type="button" id="submitBtn" class="btn btn-primary btn-block">注册</button>
							</form>
						</div>
						<div class="panel-footer">
							XX 版权所有
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
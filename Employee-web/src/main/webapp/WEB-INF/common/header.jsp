<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>企业员工信息管理系统</title>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-table.min.css" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-datetimepicker.min.css" />
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.11.1.min.js" ></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js" ></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap-table.min.js" ></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap-table-zh-CN.min.js" ></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<style type="text/css">
		.menu{
			width: 190px;
			position: absolute;
			top:50px;
		}
		.menu:before{
			content: "";
			background-color: #e3e3e3;
			position: fixed;
			top:0;
			bottom:0;
			width:190px;
		}
		.menu .last, .menu .first{
			border-radius: 0;
		}
		.content{
			margin-left: 190px;
			padding: 0 10px 0 10px;
		}
		.mgl8{
			margin-left: 8px
		}
		.mgb15{
			margin-bottom: 15px;
		}
		.table th{
			text-align: center;
		}
		.reltop5{
			position: relative;
			top: 5px;
		}
		.font-align-right{
			text-align: right;
		}
	</style>
	</head>
<body>
	<nav class="navbar navbar-default navbar-static-top" style="margin-bottom:0;">
		<div class="container-fluid">
			<div class="navbar-hrader">
				<a class="navbar-brand" href="${pageContext.servletContext.contextPath}/WEB-INF/login">企业员工信息管理系统</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="${pageContext.servletContext.contextPath}/dropOut">
						<i class="fa fa-user-circle-o"></i>
						退出登录
					</a>
				</li>
			</ul>
			<p class="navbar-text navbar-right">欢迎您,${sessionScope.userInfo.nicky_name }</p>
		</div>
	</nav>
	<div id="importDialog" style="float: right;">
		<form action="import" method=post enctype="multipart/form-data">
			<input type="file" id="file" class="" name="profile">
			<input type="submit" value="导入" class="btn btn-default">
		</form>
		<p>${sessionScope.importMsg.msg }</p>
	</div>
	<div class="menu">
		<div class="list-group nav nav-tabs nav-justified">
			<div class="nav nav-tabs nav-justified">
				<a class="list-group-item first" href="#Employee" data-toggle="tab">员工管理</a>
				<a class="list-group-item last" href="#Attendance"  data-toggle="tab">出勤信息</a>
				<a class="list-group-item last" href="#Wage"  data-toggle="tab">工资卡</a>
				<a class="list-group-item" href="#Department" data-toggle="tab">部门管理</a>
				<a class="list-group-item" href="#User" data-toggle="tab">员工信息</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="container-fluid">
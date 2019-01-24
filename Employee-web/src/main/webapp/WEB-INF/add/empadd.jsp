<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
    function eadd(){
		var addName = /^[\u4e00-\u9fa5]{2,6}$/;
		var addSal = /^[1-9]{1}([0-9]{1,5}.[0-9]{1,2}|[0-9]*)$/;
		var addMgr = /^[0-9]*$/;
		    
		var aEnameErrorMsg = $("#aEnameErrorMsg");
		var aEjobErrorMsg = $("#aEjobErrorMsg");
		var aEmgrErrorMsg = $("#aEmgrErrorMsg");
		var aEsalErrorMsg = $("#aEsalErrorMsg");
		var aEcommErrorMsg = $("#aEcommErrorMsg");
		var aEhiredateErrorMsg = $("#aEhiredateErrorMsg");
		
		var aEname = $("#aEname").val();
		var aEjob = $("#aEjob").val();
		var aEmgr = $("#aEmgr").val();
		var aEsal = $("#aEsal").val();
		var aEcomm = $("#aEcomm").val();
		var aEhiredate = $("#aEhiredate").val();
		var aEdeptno = $("#aEdeptno").val();
		
		var flag = true;
		
		if(!addName.test(aEname)){
			aEnameErrorMsg.html("员工姓名不正确。");
			flag = false;
		}
		if(!addName.test(aEjob)){
			aEjobErrorMsg.html("工种不正确。");
			flag = false;
		}
		if(!addMgr.test(aEmgr)){
			aEmgrErrorMsg.html("上级领导格式不正确。");
			flag = false;
		}
		if(!addSal.test(aEsal)){
			aEsalErrorMsg.html("工资不正确。");
			flag = false;
		}
		if(!addSal.test(aEcomm)){
			aEcommErrorMsg.html("奖金不正确。");
			flag = false;
		}
		if(!aEhiredate){
			aEhiredateErrorMsg.html("日期不能为空。");
			flag = false;
		}
		
		$("#aEname").focus(function(){
			aEnameErrorMsg.html("");
		});
		$("#aEjob").focus(function(){
			aEjobErrorMsg.html("");
		});
		$("#aEmgr").focus(function(){
			aEmgrErrorMsg.html("");
		});
		$("#aEsal").focus(function(){
			aEsalErrorMsg.html("");
		});
		$("#aEcomm").focus(function(){
			aEcommErrorMsg.html("");
		});
		$("#aEhiredate").focus(function(){
			aEhiredateErrorMsg.html("");
		});
		if(flag){
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/emp/add",
				type:"post",
				datatype:"json",
				data:{ename:aEname, job:aEjob, mgr:aEmgr, sal:aEsal, comm:aEcomm, hiredate:aEhiredate, deptno:aEdeptno},
				success:function(_data){
					if(_data.flag){
						$("#aEddModal").modal("hide");
						$("#eContentTableEmp").bootstrapTable("refresh");
					}else{
						alert("添加失败，请联系管理员。");
					}
				}
			});
		}
	}
</script>
<div class="modal fade" id="aEddModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">添加员工</h4>
			</div>
			<div class="modal-boby">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">员工姓名:</label>
						<div class="col-xs-6">
							<input type="text" id="aEname" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="aEnameErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">工　&nbsp;&nbsp;&nbsp;种:</label>
						<div class="col-xs-6">
							<input type="text" id="aEjob" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="aEjobErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">上级领导:</label>
						<div class="col-xs-6">
							<input type="text" id="aEmgr" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="aEmgrErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">工　&nbsp;&nbsp;&nbsp;资:</label>
						<div class="col-xs-6">
							<input type="text" id="aEsal" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="aEsalErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">奖　&nbsp;&nbsp;&nbsp;金:</label>
						<div class="col-xs-6">
							<input type="text" id="aEcomm" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="aEcommErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">入职日期</label>
						<div class="col-xs-6">
							<input type="text" id="aEhiredate" readonly="readonly" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="aEhiredateErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">部门名称:</label>
						<div class="col-xs-6">
							<select id="aEdeptno" class="form-control" >
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal">关闭</button>
				<button class="btn btn-success" onclick="return eadd()">提交</button>
			</div>
		</div>
	</div>
</div>
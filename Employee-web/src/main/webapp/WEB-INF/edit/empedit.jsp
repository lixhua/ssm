<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function eEdit(){
		var editName = /^[\u4e00-\u9fa5]{2,6}$/;
		var editSal = /^[1-9]{1}([0-9]{1,5}.[0-9]{1,2}|[0-9]*)$/;
		var editMgr = /^[0-9]*$/;
		
		var eEnameErrorMsg = $("#eEnameErrorMsg");
		var eEjobErrorMsg = $("#eEjobErrorMsg");
		var eEmgrErrorMsg = $("#eEmgrErrorMsg");
		var eEsalErrorMsg = $("#eEsalErrorMsg");
		var eEcommErrorMsg = $("#eEcommErrorMsg");
		var eEhiredateErrorMsg = $("#eEhiredateErrorMsg");
		
		var eEmpno = $("#eEmpno").val();
		var eEname = $("#eEname").val();
		var eEjob = $("#eEjob").val();
		var eEmgr = $("#eEmgr").val();
		var eEsal = $("#eEsal").val();
		var eEcomm = $("#eEcomm").val();
		var eEhiredate = $("#eEhiredate").val();
		var eEdeptno = $("#eEdeptno").val();
		
		var flag = true;
		
		if(!editName.test(eEname)){
			eEnameErrorMsg.html("员工姓名不正确。");
			flag = false;
		}
		if(!editName.test(eEjob)){
			eEjobErrorMsg.html("工种不正确。");
			flag = false;
		}
		if(!editMgr.test(eEmgr)){
			eEmgrErrorMsg.html("上级领导格式不正确。");
			flag = false;
		}
		if(!editSal.test(eEsal)){
			eEsalErrorMsg.html("工资不正确。");
			flag = false;
		}
		if(!editSal.test(eEcomm)){
			eEcommErrorMsg.html("奖金不正确。");
			flag = false;
		}
		if(!eEhiredate){
			eEhiredateErrorMsg.html("日期不能为空。");
			flag = false;
		}
		
		$("#eEname").focus(function(){
			eEnameErrorMsg.html("");
		});
		$("#eEjob").focus(function(){
			eEjobErrorMsg.html("");
		});
		$("#eEmgr").focus(function(){
			eEmgrErrorMsg.html("");
		});
		$("#eEsal").focus(function(){
			aEsalErrorMsg.html("");
		});
		$("#eEcomm").focus(function(){
			eEcommErrorMsg.html("");
		});
		$("#eEhiredate").focus(function(){
			eEhiredateErrorMsg.html("");
		});
		
		if(flag){
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/emp/" + eEmpno,
				type:"post",
				datatype:"json",
				data:{empno: eEmpno, ename: eEname, job: eEjob, mgr: eEmgr, sal: eEsal, comm: eEcomm, hiredate: eEhiredate, deptno: eEdeptno,_method:"PUT"},
				success:function(_data){
					if(_data.flag){
						$("#eEditModal").modal("hide");
						$("#eContentTableEmp").bootstrapTable("refresh");
					}else{
						alert("添加失败，请联系管理员。");
					}
				}
			});
		}
	}
</script>
<div class="modal fade" id="eEditModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">编辑员工</h4>
			</div>
			<div class="modal-boby">
				<form class="form-horizontal">
					<input type="hidden" id="eEmpno">
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">员工姓名:</label>
						<div class="col-xs-6">
							<input type="text" id="eEname" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="eEnameErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">工　&nbsp;&nbsp;&nbsp;种:</label>
						<div class="col-xs-6">
							<input type="text" id="eEjob" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="eEjobErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">上级领导:</label>
						<div class="col-xs-6">
							<input type="text" id="eEmgr" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="eEmgrErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">工　&nbsp;&nbsp;&nbsp;资:</label>
						<div class="col-xs-6">
							<input type="text" id="eEsal" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="eEsalErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">奖　&nbsp;&nbsp;&nbsp;金:</label>
						<div class="col-xs-6">
							<input type="text" id="eEcomm" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="eEcommErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">入职日期</label>
						<div class="col-xs-6">
							<input type="text" id="eEhiredate" readonly="readonly" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="eEhiredateErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">部门名称:</label>
						<div class="col-xs-6">
							<select id="eEdeptno" class="form-control" >
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal">关闭</button>
				<button class="btn btn-success" onclick="return eEdit()">提交</button>
			</div>
		</div>
	</div>
</div>
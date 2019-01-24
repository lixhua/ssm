<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
    function dadd(){
		var adddeptno = /^[0-9]{2}$/;
		var addname = /^[\u4e00-\u9fa5]{2,6}|[a-zA-Z]{3,12}$/;
		  
		var aDdeptnoErrorMsg = $("#aDdeptnoErrorMsg");
		var aDdNameErrorMsg = $("#aDdNameErrorMsg");
		var aDLocErrorMsg = $("#aDLocErrorMsg");
		  
		var aDdeptno = $("#aDdeptno").val();
		var aDdname = $("#aDdname").val();
		var aDLoc = $("#aDLoc").val();
		
		var flag = true;
		
		if(!addname.test(aDdname)){
			aDdNameErrorMsg.html("部门名字不正确,请输入2-6位汉子或者3-12位英文.");
			flag = false;
		}
		if(!adddeptno.test(aDdeptno)){
			aDdeptnoErrorMsg.html("部门编号不正确,请输入整数.");
			flag = false;
		}
		
		$("#aDdname").focus(function(){
			aDdNameErrorMsg.html("");
		});
		$("#aDdeptno").focus(function(){
			aDdeptnoErrorMsg.html("");
		});
		
		if(flag){
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/dept/add",
				type:"post",
				datatype:"json",
				data:{deptno: aDdeptno, dname: aDdname, loc: aDLoc},
				success:function(_data){
					if(_data.flag){
						$("#aDddModal").modal("hide");
						$("#dContentTableDept").bootstrapTable("refresh");
					}else{
						alert("添加失败，请联系管理员。");
					}
				}
			});
		}
	}
</script>
<div class="modal fade" id="aDddModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">添加部门</h4>
			</div>
			<div class="modal-boby">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">部门编号:</label>
						<div class="col-xs-6">
							<input type="text" id="aDdeptno" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="aDdeptnoErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">部门名字:</label>
						<div class="col-xs-6">
							<input type="text" id="aDdname" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="aDdNameErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">部门地址:</label>
						<div class="col-xs-6">
							<input type="text" id="aDLoc" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="aDLocErrorMsg"></span>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal">关闭</button>
				<button class="btn btn-success" onclick="return dadd()">提交</button>
			</div>
		</div>
	</div>
</div>
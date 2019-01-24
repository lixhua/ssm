<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function dEdit(){
		var editdeptno = /^[0-9]{2}$/;
		var editname = /^[\u4e00-\u9fa5]{2,6}|[a-zA-Z]{3,12}$/;
		  
		var eDdeptnoErrorMsg = $("#eDdeptnoErrorMsg");
		var eDdNameErrorMsg = $("#eDdNameErrorMsg");
		var eDLocErrorMsg = $("#eDLocErrorMsg");
		  
		var eDdeptno = $("#eDdeptno").val();
		var eDdname = $("#eDdname").val();
		var eDLoc = $("#eDLoc").val();
		
		var flag = true;
		
		if(!editdeptno.test(eDdeptno)){
			eDdeptnoErrorMsg.html("部门名字不正确。");
			flag = false;
		}
		if(!editname.test(eDdname)){
			eDdNameErrorMsg.html("工种不正确。");
			flag = false;
		}
		
		$("#eDdeptno").focus(function(){
			eDdeptnoErrorMsg.html("");
		});
		$("#eDdname").focus(function(){
			eDdNameErrorMsg.html("");
		});
		
		if(flag){
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/dept/" + eDdeptno,
				type:"post",
				datatype:"json",
				data:{dname: eDdname, loc: eDLoc, _method:"PUT"},
				success:function(_data){
					if(_data.flag){
						$("#eDditModal").modal("hide");
						$("#dContentTableDept").bootstrapTable("refresh");
					}else{
						alert("添加失败，请联系管理员。");
					}
				}
			});
		}
	}
</script>
<div class="modal fade" id="eDditModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">编辑部门</h4>
			</div>
			<div class="modal-boby">
				<form class="form-horizontal">
					<input type="hidden" id="eDdeptno">
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">部门名字:</label>
						<div class="col-xs-6">
							<input type="text" id="eDdname" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="eDdNameErrorMsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">部门地址:</label>
						<div class="col-xs-6">
							<input type="text" id="eDLoc" class="form-control" >
						</div>
						<div class="col-xs-4 reltop5">
							<span id="eDLocErrorMsg"></span>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal">关闭</button>
				<button class="btn btn-success" onclick="return dEdit()">提交</button>
			</div>
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	function empUpload(){
		var empFile = $("#empFile").val();
		$.ajax({
			url:"${pageContext.servletContext.contextPath}/emp/toSave",
			type:"post",
			datatype:"json",
		});
	}
</script>
<div class="modal fade" id="empImportModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">请选择上传的文件</h4>
			</div>
			<div class="modal-boby">
				<form class="form-horizontal" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-xs-2 reltop5 font-align-right">上传Excel文件:</label>
						<div class="col-xs-6">
							<input type="file" id="empFile" class="form-control" name="file">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal">关闭</button>
				<button class="btn btn-success" onclick="return empUpload()">上传</button>
			</div>
		</div>
	</div>
</div>
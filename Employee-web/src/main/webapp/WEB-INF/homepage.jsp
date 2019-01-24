<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp" %>
<script type="text/javascript">
	$(function(){
		//选择日期的插件
		$("#ubeginDate,#uendDate, #beginDate,#endDate,#aEhiredate,#eEhiredate, #AbeginDate,#AendDate").datetimepicker({
			format:"yyyy-mm-dd",
			minView:2,
			maxView:4,
			language:"zh-CN",
			weekStart:1,
			todayBtn:true,
			autoclose:true
		});
		$("#aEhiredate,#eEhiredate").datetimepicker().on("show",function(event){
			event.preventDefault();
			event.stopPropagation();
		});
		
		
		// 部门查询并展示出来
		var dept = $("#deptno,#aEdeptno,#eEdeptno");
		$.ajax({
			url:"${pageContext.servletContext.contextPath}/dept",
			datatype:"json",
			type:"get",
			success:function(_data){
				for(var i = 0; i < _data.length; i++){
					dept.append("<option value='" + _data[i].deptno +"'>" + _data[i].dname + "</option>");
				}
			}
		});
		
		
		// emp表 的ID 获取到的的val值
		var ename = $("#ename").val();
		var job = $("#job").val();
		var beginDate = $("#beginDate").val();
		var endDate = $("#endDate").val();
		var deptno = $("#deptno").val();
		
		
		// emp表的 搜索与内容清除
		var eSearchBtn = $("#eSearchBtn");
		eSearchBtn.click(function(){
			eContentTableEmp.bootstrapTable("selectPage",1);
			return false;
		})
		var eClearBtn = $("#eClearBtn");
		eClearBtn.click(function(){
			$("#ename").val("");
			$("#job").val("");
			$("#beginDate").val("");
			$("#endDate").val("");
			$("#deptno").val("-1");
			eContentTableEmp.bootstrapTable("refresh");
			return false;
		});
		
		
		// user表的 搜索与内容清除
		var searchBtn = $("#searchBtn");
		searchBtn.click(function(){
			contentTable.bootstrapTable("selectPage",1);
			return false;
		})
		var clearBtn = $("#clearBtn");
		clearBtn.click(function(){
			$("#name").val("");
			$("#gender").val("-1");
			$("#ubeginDate").val("");
			$("#uendDate").val("");
			$("#company").val("-1");
			contentTable.bootstrapTable("refresh");
			return false;
		});


        // attendance表的 搜索与内容清除
        var aSearchBtn = $("#aSearchBtn");
        aSearchBtn.click(function(){
            aContentTableAttendance.bootstrapTable("selectPage",1);
            return false;
        })
        var aClearBtn = $("#aClearBtn");
        aClearBtn.click(function(){
            $("#Aname").val("");
            $("#Anormal").val("");
            $("#AbeginDate").val("");
            $("#AendDate").val("");
            $("#Aleave").val("");
            $("#Aabsence").val("");
            aContentTableAttendance.bootstrapTable("refresh");
            return false;
        });


        // salaryCard表的 搜索与内容清除
        var sSearchBtn = $("#sSearchBtn");
        sSearchBtn.click(function(){
            sContentTableSalaryCard.bootstrapTable("selectPage",1);
            return false;
        })
        var sClearBtn = $("#sClearBtn");
        sClearBtn.click(function(){
            $("#swname").val("");
            $("#sbasic").val("-1");
            $("#beginRealWages").val("");
            $("#endRealWages").val("");
            $("#sbonus").val("-1");
            $("#sfine").val("-1");
            sContentTableSalaryCard.bootstrapTable("refresh");
            return false;
        });


		// 显示编辑 模态窗
		$("#eEditModal").on("show.bs.modal",function(){
			var eContentTableEmp = $("#eContentTableEmp");
			var selects = eContentTableEmp.bootstrapTable("getSelections");
			var empno = selects[0].empno;
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/emp/" + empno,
				type:"get",
				datatype:"json",
				success:function(_data){
					console.log(_data);
					$("#eEmpno").val(_data.empno);
					$("#eEname").val(_data.ename);
					$("#eEjob").val(_data.job);
					$("#eEmgr").val(_data.mgr);
					$("#eEsal").val(_data.sal);
					$("#eEcomm").val(_data.comm);
					$("#eEhiredate").val(_data.hiredate);
					$("#eEdeptno").val(_data.dept.deptno);
				}
			});
		});
		$("#eDditModal").on("show.bs.modal",function(){
			var dContentTableDept = $("#dContentTableDept");
			var selects = dContentTableDept.bootstrapTable("getSelections");
			var deptno = selects[0].deptno;
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/dept/" + deptno,
				type:"get",
				datatype:"json",
				success:function(_data){
					$("#eDdeptno").val(_data.deptno);
					$("#eDdname").val(_data.dname);
					$("#eDLoc").val(_data.loc);
				}
			});
		});
		
		
		// 显示添加 模态窗
		$("#aEddModal").on("show.bs.modal",function(){
			var eContentTableEmp = $("#eContentTableEmp");
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/emp",
				datatype:"json",
				success:function(_data){
					if(_data.id == null){
						$("#aEmpno").val(_data.empno);
						$("#aEname").val(_data.ename);
						$("#aEjob").val(_data.job);
						$("#aEmgr").val(_data.mgr);
						$("#aEsal").val(_data.sal);
						$("#aEcomm").val(_data.comm);
						$("#aEhiredate").val(_data.hiredate);
						$("#aEdeptno").val(_data.deptno);
					}
				}
			});
		});
		$("#aDddModal").on("show.bs.modal",function(){
			var dContentTableDept = $("#dContentTableDept");
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/dept",
				type:"get",
				datatype:"json",
				success:function(_data){
					if(_data.id == null){
						$("#aDdeptno").val(_data.deptno);
						$("#aDdname").val(_data.dname);
						$("#aDLoc").val(_data.loc);
					}
				}
			});
		});
		
		//table插件  查询并展示emp表数据
		var eContentTableEmp = $("#eContentTableEmp").bootstrapTable({
			url:"${pageContext.servletContext.contextPath}/emp",
			type: "get",
			pagination:true,
			sidePagination:"server",
			toolbar:"#eToolbar",
			queryParams:function(params) {
				console.log(params);
				var ename = $("#ename").val();
				var job = $("#job").val();
				var beginDate = $("#beginDate").val();
				var endDate = $("#endDate").val();
				var deptno = $("#deptno").val();
				    
				params.ename = ename;
				params.job = job;
				params.beginDate = beginDate;
				params.endDate = endDate;
				params.deptno = deptno;
				return params;
			},
			pageList:[5,10,20,25],
			columns:[
				{"field":"emp", checkbox:true},
				{"field":"empno", "title":"工号"},
				{"field":"ename", "title":"姓名"},
				{"field":"job", "title":"工种"},
				{"field":"mgr", "title":"上级领导"},
				{"field":"hiredate", "title":"入职日期"},
				{"field":"sal", "title":"工资"},
				{"field":"comm", "title":"奖金"},
				{"field":"dept.dname", "title":"部门名字"}
			]
		});
		
		//table插件  查询并展示dept表数据
		var dContentTableDept = $("#dContentTableDept").bootstrapTable({
			url:"${pageContext.servletContext.contextPath}/dept/all",
			pagination:true,
			sidePagination:"server",
			toolbar:"#dToolbar",
			pageList:[5,10,20,25],
			columns:[
				{"field":"dept", checkbox:true},
				{"field":"deptno", "title":"部门编号"},
				{"field":"dname", "title":"姓名"},
				{"field":"loc", "title":"办公地址"},
				{"field":"peoples", "title":"部门人数"},
			]
		});
		
		//table插件  查询并展示user表数据
		var contentTable = $("#contentTable").bootstrapTable({
			url:"${pageContext.servletContext.contextPath}/user",
			type:"get",
			pagination:true,
			sidePagination:"server",
			toolbar:"#toolbar",
			queryParams:function(params) {
				console.log(params);
				var name = $("#name").val();
				var gender = $("#gender").val();
				var ubeginDate = $("#ubeginDate").val();
				var uendDate = $("#uendDate").val();
				
				params.name = name;
				params.gender = gender;
				params.ubeginDate = ubeginDate;
				params.uendDate = uendDate;
				return params;
			},
			pageList:[5,10,20,25],
			columns:[
				{"field":"user", checkbox:true},
				{"field":"id", "visible":false},
				{"field":"name", "title":"姓名"},
				{"field":"gender", "title":"性别", formatter: function(value) {
					return value == "F" ? "女" : "男";
				}},
				{"field":"email", "title":"邮件"},
				{"field":"birthday", "title":"生日"},
				{"field":"createtime", "title":"创建日期"},
				{"field":"updatetime", "title":"修改日期"},
			]
		});

		//table插件  查询并展示Attendance表数据
        var aContentTableAttendance = $("#aContentTableAttendance").bootstrapTable({
            url:"${pageContext.servletContext.contextPath}/attendance",
            type: "get",
            pagination:true,
            sidePagination:"server",
            toolbar:"#AToolbar",
            queryParams:function(params) {
                console.log(params);
                var Aname = $("#Aname").val();
                var Anormal = $("#Anormal").val();
                var AbeginDate = $("#AbeginDate").val();
                var AendDate = $("#AendDate").val();
                var Aleave = $("#Aleave").val();
                var Aabsence = $("#Aabsence").val();

                params.aname = Aname;
                params.normal = Anormal;
                params.beginDate = AbeginDate;
                params.endDate = AendDate;
                params.aleave = Aleave;
                params.absence = Aabsence;
                return params;
            },
            pageList:[5,10,20,25],
            columns:[
                {"field":"aname", "title":"姓名"},
                {"field":"normal", "title":"正常出勤"},
                {"field":"aleave", "title":"请假"},
                {"field":"late", "title":"迟到"},
                {"field":"leave_early", "title":"早退"},
                {"field":"absence", "title":"缺席"},
                {"field":"createTime", "title":"创建时间"},
            ]
        });


        //table插件  查询并展示salaryCard表数据
        var sContentTableSalaryCard = $("#sContentTableSalaryCard").bootstrapTable({
            url:"${pageContext.servletContext.contextPath}/salaryCard",
            type: "get",
            pagination:true,
            sidePagination:"server",
            toolbar:"#SToolbar",
            queryParams:function(params) {
                console.log(params);
                var swname = $("#swname").val();
                var sbasic = $("#sbasic").val();
                var beginRealWages = $("#beginRealWages").val();
                var endRealWages = $("#endRealWages").val();
                var sbonus = $("#sbonus").val();
                var sfine = $("#sfine").val();

                params.swname = swname;
                params.sbasic = sbasic;
                params.beginRealWages = beginRealWages;
                params.endRealWages = endRealWages;
                params.sbonus = sbonus;
                params.sfine = sfine;
                return params;
            },
            pageList:[5,10,20,25],
            columns:[
                {"field":"wname", "title":"姓名"},
                {"field":"basic", "title":"基本工资"},
                {"field":"bonus", "title":"奖金"},
                {"field":"fine", "title":"罚款"},
                {"field":"wage_payable", "title":"应发工资"},
                {"field":"real_wages", "title":"实发工资"},
                {"field":"ceateTime", "title":"创建时间"},
            ]
        });
	});

	
	//删除
	function deleteEmp(){
		var eContentTableEmp = $("#eContentTableEmp");
		var selects = eContentTableEmp.bootstrapTable("getSelections");
		if(selects.length < 1){
			alert("请选择一条数据进行删除。");
		}else{
			if(confirm("您确定要删除数据吗？")){
				var empnos = [];
				for(var i = 0; i < selects.length; i++){
					empnos.push(selects[i].empno);
				}
				empnos = empnos.join(",");
				$.ajax({
					url:"${pageContext.servletContext.contextPath}/emp",
					type:"post",
					datatype:"json",
					data:{_method:"DELETE",empnos:empnos},
					success:function(_data){
						if(_data.flag){
							eContentTableEmp.bootstrapTable("refresh");
						}else{
							alert(_data.msg);
						}
					}
				});
			}
		}
	}
	function deleteDept(){
		var dContentTableDept = $("#dContentTableDept");
		var selects = dContentTableDept.bootstrapTable("getSelections");
		if(selects.length < 1){
			alert("请选择数据进行删除。");
		}else{
			if(confirm("您确定要删除数据吗？")){
				var deptnos = [];
				for(var i = 0; i < selects.length; i++){
					deptnos.push(selects[i].deptno);
				}
				deptnos = deptnos.join(",");
				$.ajax({
					url:"${pageContext.servletContext.contextPath}/dept",
					type:"post",
					datatype:"json",
					data:{_method: "DELETE", deptnos: deptnos},
					success:function(_data){
						if(_data.flag){
							dContentTableDept.bootstrapTable("refresh");
						}else{
							alert(_data.msg);
						}
					}
				});
			}
		}
	}
	function deleteUser(){
		var contentTable = $("#contentTable");
		var selects = contentTable.bootstrapTable("getSelections");
		if(selects.length < 1){
			alert("请选择数据进行删除。");
		}else{
			if(confirm("您确定要删除数据吗？")){
				var ids = [];
				for(var i = 0; i < selects.length; i++){
					ids.push(selects[i].id);
				}
				ids = ids.join(",");
				$.ajax({
					url:"${pageContext.servletContext.contextPath}/user",
					type:"post",
					datatype:"json",
					data:{_method:"DELETE",ids:ids},
					success:function(_data){
						if(_data.flag){
							contentTable.bootstrapTable("refresh");
						}else{
							alert(_data.msg);
						}
					}
				});
			}
		}
	}
	
	
	// 编辑
	function editEmp(){
		var eContentTableEmp = $("#eContentTableEmp");
		var selects = eContentTableEmp.bootstrapTable("getSelections"); 
		if(selects.length != 1){
			alert("请选择一条数据进行编辑。");
		}else{
			$("#eEditModal").modal({
				backdrop: "static",
				show:true
			});
		}
	}
	function editDept(){
		var dContentTableDept = $("#dContentTableDept");
		var selects = dContentTableDept.bootstrapTable("getSelections"); 
		if(selects.length != 1){
			alert("请选择一条数据进行编辑。");
		}else{
			$("#eDditModal").modal({
				backdrop: "static",
				show:true
			});
		}
	}
	
	
	//添加
	function addEmp(){
		$("#aEddModal").modal({
			backdrop: "static",
			show:true
		});
	}
	function addDept(){
		$("#aDddModal").modal({
			backdrop: "static",
			show:true
		});
	}
	
	//emp表 导出
	function exportEmp(){
		var ename = $("#ename").val();
		var job = $("#job").val();
		var beginDate = $("#beginDate").val();
		var endDate = $("#endDate").val();
		var deptno = $("#deptno").val();	
	
		$.ajax({
			url:"${pageContext.servletContext.contextPath}/emp/export",
			type:"get",
			datatype:"json",
			data:{ename: ename, job:job, beginDate: beginDate, endDate: endDate, deptno: deptno},
			success:function(_data){
				if(_data.flag){
					alert(_data.msg);
				}else{
					alert(_data.msg);
				}
			}
		});
	}
    //attendance表 导出
    function exportAttendance(){
        var Aname = $("#Aname").val();
        var Anormal = $("#Anormal").val();
        var AbeginDate = $("#AbeginDate").val();
        var AendDate = $("#AendDate").val();
        var Aleave = $("#Aleave").val();
        var Aabsence = $("#Aabsence").val();

        $.ajax({
            url:"${pageContext.servletContext.contextPath}/attendance/export",
            type:"get",
            datatype:"json",
            data:{aname: Aname, anormal:Anormal, abeginDate: AbeginDate, aendDate: AendDate, aleave: Aleave, aabsence: Aabsence},
            success:function(_data){
                if(_data.flag){
                    alert(_data.msg);
                }else{
                    alert(_data.msg);
                }
            }
        });
    }
    //salaryCard表 导出
    function exportSalaryCard(){
        var swname = $("#swname").val();
        var sbasic = $("#sbasic").val();
        var beginRealWages = $("#beginRealWages").val();
        var endRealWages = $("#endRealWages").val();
        var sbonus = $("#sbonus").val();
        var sfine = $("#sfine").val();

        $.ajax({
            url:"${pageContext.servletContext.contextPath}/salaryCard/export",
            type:"get",
            datatype:"json",
            data:{swname: swname, sbasic:sbasic, beginRealWages: beginRealWages, endRealWages: endRealWages, sbonus: sbonus, sfine: sfine},
            success:function(_data){
                if(_data.flag){
                    alert(_data.msg);
                }else{
                    alert(_data.msg);
                }
            }
        });
    }

	
	//模板导出 Emp
	function templateEmp(){
		$.ajax({
			url:"${pageContext.servletContext.contextPath}/emp/template",
			type:"get",
			datatype:"json",
			success:function(_data){
				if(_data.flag){
					alert(_data.msg);
				}else{
					alert(_data.msg);
				}
			}
		});
	}

    //模板导出 Attendance
    function templateAttendance(){
        $.ajax({
            url:"${pageContext.servletContext.contextPath}/attendance/template",
            type:"get",
            datatype:"json",
            success:function(_data){
                if(_data.flag){
                    alert(_data.msg);
                }else{
                    alert(_data.msg);
                }
            }
        });
    }

    //模板导出 salaryCard
    function templateSalaryCard(){
        $.ajax({
            url:"${pageContext.servletContext.contextPath}/salaryCard/template",
            type:"get",
            datatype:"json",
            success:function(_data){
                if(_data.flag){
                    alert(_data.msg);
                }else{
                    alert(_data.msg);
                }
            }
        });
    }
	
</script>
<div class="tab-content">
	<div class="tab-pane active" id="Employee">
		<div class="page-header">
			<h3>员工管理</h3>
		</div>
		<div class="row mgb15">
			<form class="form-inline">
				<div class="form-group">
					<label>姓名: </label>
					<input type="text" style="width:120px;" id="ename" class="form-control" />
				</div>
				<div class="form-group">
					<label>职位: </label>
					<input type="text" style="width:120px;" id="job" class="form-control" />
				</div>
				<div class="form-group mgl8">
					<label>入职日期: </label>
					<input type="text" id="beginDate" class="form-control" readonly="readonly"> -
					<input type="text" id="endDate" class="form-control" readonly="readonly">
				</div>
				<div class="form-group mgl8">
					<label>部门名称: </label>
					<select id="deptno" class="form-control">
						<option value="-1">全部</option>
					</select>
				</div>
			</form>
		</div>
		<div class="row">
			<table id="eContentTableEmp" class="table table-bordered table-hover table-striped" style="text-align: center;">
			</table>
		</div>
		<div id="eToolbar">
			<button onclick="addEmp()" class="btn btn-primary">
				<i class="fa fa-plus"></i>
				添加
			</button>
			<button onclick="editEmp()" class="btn btn-success">
				<i class="fa fa-edit"></i>
				编辑
			</button>
			<button onclick="deleteEmp()" class="btn btn-danger">
				<i class="fa fa-times"></i>
				删除
			</button>
			<button onclick="exportEmp()" class="btn btn-default" style="margin-left: 100px">
				<i class="fa fa-reply-all"></i>
				批量导出
			</button>
			<button onclick="templateEmp()" class="btn btn-success" style="margin-left: 50px">
				<i class="fa fa-plus"></i>
				导入模板
			</button>
			<button id="eSearchBtn" class="btn btn-success" style="margin-left: 150px">
				<i class="fa fa-search"></i>
				搜索
			</button>
			<button id="eClearBtn" class="btn btn-danger">
				<i class="fa fa-trash-o"></i>
				清空
			</button>
		</div>
	</div>
	<div class="tab-pane" id="Attendance">
		<div class="page-header">
			<h3>出勤信息</h3>
		</div>
		<div class="row mgb15">
			<form class="form-inline">
				<div class="form-group">
					<label>姓名: </label>
					<input type="text" style="width:120px;" id="Aname" class="form-control" />
				</div>
				<div class="form-group">
					<label>正常出勤: </label>
					<input type="text" style="width:80px;" id="Anormal" class="form-control" />
				</div>
				<div class="form-group">
					<label>请假: </label>
					<input type="text" style="width:80px;" id="Aleave" class="form-control" />
				</div>
				<div class="form-group">
					<label>早退: </label>
					<input type="text" style="width:80px;" id="Aabsence" class="form-control" />
				</div>
				<div class="form-group mgl8">
					<label>创建时间: </label>
					<input type="text" id="AbeginDate" class="form-control" readonly="readonly"> -
					<input type="text" id="AendDate" class="form-control" readonly="readonly">
				</div>
			</form>
		</div>
		<div class="row">
			<table id="aContentTableAttendance" class="table table-bordered table-hover table-striped" style="text-align: center;">
			</table>
		</div>
		<div id="AToolbar">
			<button onclick="exportAttendance()" class="btn btn-default" style="margin-left: 50px">
				<i class="fa fa-reply-all"></i>
				批量导出
			</button>
			<button onclick="templateAttendance()" class="btn btn-success" style="margin-left: 50px">
				<i class="fa fa-plus"></i>
				导入模板
			</button>
			<button id="aSearchBtn" class="btn btn-success" style="margin-left: 400px">
				<i class="fa fa-search"></i>
				搜索
			</button>
			<button id="aClearBtn" class="btn btn-danger">
				<i class="fa fa-trash-o"></i>
				清空
			</button>
		</div>
	</div>
	<div class="tab-pane" id="Wage">
		<div class="page-header">
			<h3>工资卡</h3>
		</div>
		<div class="row mgb15">
			<form class="form-inline">
				<div class="form-group">
					<label>姓名: </label>
					<input type="text" style="width:120px;" id="swname" class="form-control" />
				</div>
				<div class="form-group">
					<label>基础工资: </label>
					<select id="sbasic" class="form-control">
						<option value="-1">不排序</option>
						<option value="asc">升序</option>
						<option value="desc">降序</option>
					</select>
				</div>
				<div class="form-group">
					<label>实发工资: </label>
					<input type="text" style="width:120px;" id="beginRealWages" class="form-control" /> --
					<input type="text" style="width:120px;" id="endRealWages" class="form-control" />
				</div>
				<div class="form-group">
					<label>奖金: </label>
					<select id="sbonus" class="form-control">
						<option value="-1">不排序</option>
						<option value="asc">升序</option>
						<option value="desc">降序</option>
					</select>
				</div>
				<div class="form-group">
					<label>罚款: </label>
					<select id="sfine" class="form-control">
						<option value="-1">不排序</option>
						<option value="asc">升序</option>
						<option value="desc">降序</option>
					</select>
				</div>
				<button id="sSearchBtn" class="btn btn-success" style="margin-left: 20px">
					<i class="fa fa-search"></i>
					搜索
				</button>
				<button id="sClearBtn" class="btn btn-danger">
					<i class="fa fa-trash-o"></i>
					清空
				</button>
			</form>
		</div>
		<div class="row">
			<table id="sContentTableSalaryCard" class="table table-bordered table-hover table-striped" style="text-align: center;">
			</table>
		</div>
		<div id="SToolbar">
			<button onclick="exportSalaryCard()" class="btn btn-default" style="margin-left: 50px">
				<i class="fa fa-reply-all"></i>
				批量导出
			</button>
			<button onclick="templateSalaryCard()" class="btn btn-success" style="margin-left: 50px">
				<i class="fa fa-plus"></i>
				导入模板
			</button>
		</div>
	</div>
	<div class="tab-pane" id="Department">
		<div class="page-header">
			<h3>部门管理</h3>
		</div>
		<div class="row">
			<table id="dContentTableDept" class="table table-bordered table-hover table-striped" style="text-align: center;">
			</table>
		</div>
		<div id="dToolbar">
			<button onclick="addDept()" class="btn btn-primary">
				<i class="fa fa-plus"></i>
				添加
			</button>
			<button onclick="editDept()" class="btn btn-success">
				<i class="fa fa-edit"></i>
				编辑
			</button>
			<button onclick="deleteDept()" class="btn btn-danger">
				<i class="fa fa-times"></i>
				删除
			</button>
		</div>
	</div>
	<div class="tab-pane" id="User">
		<div class="page-header">
			<h3>员工信息</h3>
		</div>
		<div class="row mgb15">
			<form class="form-inline">
				<div class="form-group">
					<label>姓名: </label>
					<input type="text" style="width:120px;" id="name" class="form-control" />
				</div>
				<div class="form-group mgl8">
					<label>性别: </label>
					<select id="gender" class="form-control">
						<option value="-1">全部</option>
						<option value="M">男</option>
						<option value="F">女</option>
					</select>
				</div>
				<div class="form-group mgl8">
					<label>生日: </label>
					<input type="text" id="ubeginDate" class="form-control" readonly="readonly"> -
					<input type="text" id="uendDate" class="form-control" readonly="readonly">
				</div>
				<button id="searchBtn" class="btn btn-success mgl8">
					<i class="fa fa-search"></i>
					搜索
				</button>
				<button id="clearBtn" class="btn btn-danger mgl8">
					<i class="fa fa-trash-o"></i>
					清空
				</button>
			</form>
		</div>
		<div class="row">
			<table id="contentTable" class="table table-bordered table-hover table-striped" style="text-align: center;">
			</table>
		</div>
		<div id="toolbar">
			<button onclick="deleteUser()" class="btn btn-danger">
				<i class="fa fa-times"></i>
				删除
			</button>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/add/empadd.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/add/deptadd.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/edit/empedit.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/edit/deptedit.jsp"></jsp:include>
	<%@ include file="common/footer.jsp"  %>
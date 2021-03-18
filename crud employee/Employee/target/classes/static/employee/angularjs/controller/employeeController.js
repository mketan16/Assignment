var module = angular.module('employeeApp.controllers', ['ui.bootstrap','ui.utils']);

employeeApp.controller('employeeController', function($scope, employeeService) {
	$scope.empList = [];
	$scope.addEmpObject = {
			deptId : 0,
			empName : null
	}
	$scope.editEmpObject = {
			id : 0,
			deptId : 0,
			empName : null
	}
	$scope.retriveAllEmp = function() {
		employeeService.retriveAllEmp().then(
				function Success(response) {
					console.log(response.data);
					$scope.empList = [];
					if (response.data.serviceStatus == "Success"){
						$scope.empList = response.data.serviceResponse;
					}
				});
	}
	$scope.deptList = [];
	$scope.retriveAllDept = function() {
		employeeService.retriveAllDept().then(
				function Success(response) {
					console.log(response.data);
					$scope.deptList = [];
					if (response.data.serviceStatus == "Success"){
						$scope.deptList = response.data.serviceResponse;
					}
				});
	}
	/*$('#empTable').on('click','#deleteBtn',  function () {
		var tr = $(this).closest('tr');
		$scope.empId = tr.children('td:eq(0)').text();
		$scope.empName = tr.children('td:eq(1)').text();
		if(!confirm("Are you sure you want to delete employee : "+$scope.empName)){
			return false;
		}
		$scope.deleteEmp($scope.empId)
	});*/
	$scope.deleteEmp = function(emp) {
		if(!confirm("Are you sure you want to delete employee : "+emp.empName)){
			return false;
		} else{
			employeeService.deleteEmp(emp.id).then(
					function Success(response) {
						console.log(response.data);
						alert(response.data.serviceResponse);
						$scope.retriveAllEmp();
					});
		}
	}
	$scope.error = 1;
	$scope.saveEmp = function() {

		if(document.getElementById("AddEmpDeptId").value == ''){
			alert('kindly select department');
			$scope.error = 0;
			return false
		}
		if($scope.addEmpObject.empName == null || $scope.addEmpObject.empName.trim() == ''){
			alert('kindly enter all mandatory fields');
			$scope.error = 0;
			return false
		}
		if($scope.error = 1){
			$scope.addEmpObject.deptId = document.getElementById("AddEmpDeptId").value;
			employeeService.saveEmp($scope.addEmpObject).then(
					function Success(response) {
						console.log(response.data);
						if (response.data.serviceStatus == "Success"){
							alert(response.data.serviceResponse);
							$scope.retriveAllDept();
							$scope.retriveAllEmp();
						}
					});
		}

	}
	$scope.editEmp = function() {

		if($scope.editEmpObject.empName == null || $scope.editEmpObject.empName.trim() == '' ||
				$scope.editEmpObject.deptId == null || $scope.editEmpObject.deptId.trim() == ''){
			alert('kindly enter all mandatory fields');
			$scope.error = 0;
			return false
		}
		if($scope.error = 1){
			employeeService.editEmp($scope.editEmpObject).then(
					function Success(response) {
						console.log(response.data);
						if (response.data.serviceStatus == "Success"){
							alert(response.data.serviceResponse);
							$scope.retriveAllDept();
							$scope.retriveAllEmp();
						}
					});
		}

	}	
	$('#openAddEmpModal').click(function() {
		$('#addEmpModal').modal('show');
		$scope.resetAddEmpForm();
	});
	$('#closeAddEmpModal').click(function() {
		$('#addEmpModal').modal('hide');
	});
	$scope.openEditEmpModal = function(emp) {
		$scope.resetEditEmpForm();
		$scope.editEmpObject = {
				id : emp.id,
				deptId : emp.deptId,
				empName : emp.empName
		}
		$('#editEmpModal').modal('show');
	}
	$('#closeEditEmpModal').click(function() {
		$('#editEmpModal').modal('hide');
	});
	$scope.resetAddEmpForm =  function() {
		$scope.addEmpObject = {
				deptId : 0,
				empName : null
		}
		document.getElementById('addEmpForm').reset();
	}
	$scope.resetEditEmpForm =  function() {
		$scope.editEmpObject = {
				id : 0,
				deptId : 0,
				empName : null
		}
		document.getElementById('editEmpForm').reset();
	}
	$scope.retriveAllDept();
	$scope.retriveAllEmp();
});
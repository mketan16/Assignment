var module = angular.module('employeeApp.controllers', ['ui.bootstrap','ui.utils']);

employeeApp.controller('departmentController', function($scope, departmentService) {
	$scope.deptList = [];
	$scope.retriveAllDept = function() {
		departmentService.retriveAllDept().then(
				function Success(response) {
					console.log(response.data);
					$scope.deptList = [];
					if (response.data.serviceStatus == "Success"){
						$scope.deptList = response.data.serviceResponse;
					}
				});
	}
	$scope.retriveAllDept();
});
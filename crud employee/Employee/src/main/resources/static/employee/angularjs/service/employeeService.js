employeeApp.service('employeeService', function($http) {

	var URL="/Employee";
	this.retriveAllEmp = function retriveAllEmp() {
		return $http({
			method : 'GET',
			url : URL+'/api/retriveAllEmp'
		})
	}
	this.retriveAllDept = function retriveAllDept() {
		return $http({
			method : 'GET',
			url : URL+'/api/retriveAllDept'
		})
	}
	this.deleteEmp = function deleteEmp(empId) {
		return $http({
			method : 'DELETE',
			url : URL+'/api/deleteEmp',
			params : {
				"empId" : empId
			}
		})
	}
	
	this.saveEmp = function saveEmp(addEmpObject) {
		return $http({
			method : 'POST',
			url : URL+'/api/saveEmp',
			data : addEmpObject
		})
	}
	this.editEmp = function editEmp(editEmpObject) {
		return $http({
			method : 'POST',
			url : URL+'/api/editEmp',
			data : editEmpObject
		})
	}
	
	
});

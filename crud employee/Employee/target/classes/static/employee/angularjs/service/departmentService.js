employeeApp.service('departmentService', function($http) {

	var URL="/Employee";
	this.retriveAllDept = function retriveAllDept() {
		return $http({
			method : 'GET',
			url : URL+'/api/retriveAllDept'
		})
	}
});

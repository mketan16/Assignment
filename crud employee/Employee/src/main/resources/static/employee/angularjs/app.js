var employeeApp = angular.module('employeeApp', ['ui.router']);
employeeApp.config(function($stateProvider, $urlRouterProvider) {


	$stateProvider
	.state('employee', {
		url: '/employee',
		templateUrl: 'employee.html',
		controller: 'employeeController'
	})
	.state('department', {
		url: '/department',
		templateUrl: 'department.html',
		controller: 'departmentController'
	})
	
	$urlRouterProvider.otherwise('/employee');

});
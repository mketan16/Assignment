var assignApp = angular.module('assignApp', ['ui.router']);
assignApp.config(function($stateProvider, $urlRouterProvider) {


	$stateProvider
	.state('event', {
		url: '/event',
		templateUrl: 'event.html',
		controller: 'eventController'
	})
	$urlRouterProvider.otherwise('/event');

});
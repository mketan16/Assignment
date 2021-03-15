assignApp.service('eventService', function($http) {

	var URL="/Assignment";
	this.retriveAllEvent = function retriveAllEvent() {
		$(".se-pre-con").show();
		return $http({
			method : 'POST',
			url : URL+'/api/retriveAllEvent'
		})
	}
	this.saveEvent = function saveEvent(event) {
		$(".se-pre-con").show();
		return $http({
			method : 'POST',
			url : URL+'/api/saveEvent',
			data : event
		})
	}
	this.deleteEvent = function deleteEvent(eventId) {
		$(".se-pre-con").show();
		return $http({
			method : 'POST',
			url : URL+'/api/deleteEvent',
			params : {
				"eventId" : eventId
			}
		})
	}
});

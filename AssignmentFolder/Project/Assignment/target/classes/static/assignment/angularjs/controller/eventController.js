var module = angular.module('assignApp.controllers', ['ui.bootstrap','ui.utils']);

assignApp.controller('eventController', function($scope, eventService) {
	$(document).ready(function() {
		var userName=localStorage.getItem('userName');
		if (userName==null|| userName==""){
			window.location.replace("/Assignment/logout");
		}
		var dateRange = 1;
		var monthRange = 1;
		$('#eventTable').DataTable({
			responsive: true
		});
		$(".startDate").datepicker({
			todayBtn:  1,
			format: 'yyyy-mm-dd',
			autoclose: true
		}).on('changeDate', function (selected) {
			var minDate = new Date(selected.date.valueOf());
			var maxDate = new Date(minDate.getFullYear(), minDate.getMonth() + monthRange, minDate.getDate() - dateRange);
			$('.endDate').datepicker('setStartDate', minDate);
			$('.endDate').datepicker('setEndDate', maxDate);

		});

		$(".endDate").datepicker({
			format: 'yyyy-mm-dd',
			autoclose: true
		})
		.on('changeDate', function (selected) {
			var maxDate = new Date(selected.date.valueOf());
			var minDate = new Date(maxDate.getFullYear(), maxDate.getMonth() - monthRange, maxDate.getDate() - dateRange);
			$('.startDate').datepicker('setStartDate', minDate);
			$('.startDate').datepicker('setEndDate', maxDate);
		});

		$('.endTime').timepicker({
			timeFormat: 'HH:mm',
			interval: 30,
		});
		$('.startTime').timepicker({
			timeFormat: 'HH:mm',
			interval: 30
		})
	} );
	$scope.eventObject={
			Id:null,
			eventName:null,
			startDate:null,
			endDate:null,
			startTime:null,
			endTime:null,
			dowType:""
	}
	$scope.successMsg = "";
	$scope.errorMsg = "";
	$scope.retriveAllEvent = function() {
		eventService.retriveAllEvent().then(
				function Success(response) {
					$(".se-pre-con").hide();
					$scope.eventList = "";
					$scope.eventList = response.data.serviceResponse;
					if (response.data.serviceStatus == "Success"){
						$('#eventTable').empty();
						var table = $("#eventTable").DataTable({
							dom: 'lrBftip',
							"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
							"pageLength": 10,
							columnDefs: [
								{
									"targets": 7,
									orderable: false,
									defaultContent: ['<button class="btn btn-primary fa fa-trash" id="deleteBtn" ></button>']
								},


								],
								data:$scope.eventList,
								'order': [[0, 'decs']],
								destroy: true
						});
					}
				});
	}

	$scope.saveEvent = function() {
		$scope.successMsg = "";
		$scope.errorMsg = "";
		$scope.eventObject.startTime = document.getElementById("startTime").value;
		var timeDiffHour = 5;
		var startTimeHour = parseInt($('#startTime').val().substring(0, 2));
		var startTimeMinutes = parseInt($('#startTime').val().substring(3, 8));
		var endTimeHour = parseInt($('#endTime').val().substring(0, 2));
		var endTimeMinutes = parseInt($('#endTime').val().substring(3, 8));
		
		if($('#startTime').val() == $('#endTime').val()){
			alert("Event should atleast 30 min");
			return false;
		}
		else if(endTimeHour < startTimeHour && $('#endTime').val() != '00:00'){
			alert("Start time should not be greater than end time");
			return false;
		} else if((endTimeHour - startTimeHour > timeDiffHour) 
				|| (endTimeHour - startTimeHour == timeDiffHour && startTimeMinutes != endTimeMinutes)){
			alert("Event time should not to be greater than 5 hours");
			return false;

		} else if(endTimeHour == startTimeHour && endTimeMinutes < startTimeMinutes){
			alert("Start time should not be greater than end time");
			return false;
		}
		else if((24 - startTimeHour > timeDiffHour) && $('#endTime').val() == '00:00'){
			alert("Event time should not to be greater than 5 hours");
			return false;
		}
		
		$scope.eventObject.endTime = document.getElementById("endTime").value;
		if($scope.eventObject.eventName == ""  || $scope.eventObject.startDate==""  || 
				$scope.eventObject.endDate == ""  || $scope.eventObject.dowType == "" ||
				$scope.eventObject.startTime == ""  || $scope.eventObject.endTime == "" ||
				$scope.eventObject.eventName == null  || $scope.eventObject.startDate== null  || 
				$scope.eventObject.startTime == null  || $scope.eventObject.endTime == null ||
				$scope.eventObject.endDate == null  || $scope.eventObject.dowType == null  ){
			$scope.errorMsg = 'Kindly add all mandatory field';
			$( ".failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
			return false;
		}
		else{
			console.log($scope.eventObject);
			eventService.saveEvent($scope.eventObject).then(
					function Success(response) {
						$(".se-pre-con").hide();
						console.log(response.data);
						if(response.data.serviceStatus == "Success"){
							document.getElementById('eventForm').reset();
							$scope.successMsg = response.data.serviceResponse;
							$( ".success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
							$scope.resetEventForm();
						}else{
							$scope.errorMsg = response.data.serviceResponse;
							$( ".failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
						}
						$scope.retriveAllEvent();
					});
		}
	}
	$scope.deleteEvent = function(eventId) {
		if(!confirm("Are you sure you want to delete event")){
			return false;
		}
		$scope.successMsg = "";
		$scope.errorMsg = "";
		eventService.deleteEvent(eventId).then(
				function Success(response) {
					$(".se-pre-con").hide();
					if(response.data.serviceStatus == "Success"){
						$scope.successMsg = response.data.serviceResponse;
						$( ".success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
					}else{
						$scope.errorMsg = response.data.serviceResponse;
						$( ".failure" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
					}
					$scope.retriveAllEvent();
				});
	}
	$scope.retriveAllEvent();
	$('#eventTable').on('click','#deleteBtn',  function () {
		var tr = $(this).closest('tr');
		$scope.eventId = tr.children('td:eq(0)').text();
		$scope.deleteEvent($scope.eventId)
	});
	$scope.resetEventForm = function() {
		$scope.eventObject={
				Id:null,
				eventName:null,
				startDate:null,
				endDate:null,
				startTime:null,
				endTime:null,
				dowType:""
		}
		$('.startDate').datepicker('setStartDate', null);
		$('.startDate').datepicker('setEndDate', null);
		$('.endDate').datepicker('setStartDate', null);
		$('.endDate').datepicker('setEndDate', null);
	}
});

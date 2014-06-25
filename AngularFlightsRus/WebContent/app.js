'use strict';
var app = angular.module('myApp', ['ngRoute', 'ngMessages']);

//configure routing
app.config(function($routeProvider) {
	$routeProvider.when("/",
			{
				templateUrl: 'FlightForm.html',
				controller: 'FlightsFormController'
			}
	)
	.when("/flightOptions",
			{
				templateUrl: 'FlightOptionsList.html',
				controller: 'flightOptionsController'
			}
	)	
	.when("/order",
			{
				templateUrl: 'FlightOrder.html',
				controller: 'flightOrderController'
			}
	)	
	.when("/confirmation",
			{
				templateUrl: 'OrderConfirmation.html',
				controller: 'OrderConfirmationController'
			}
	);	

});

//configure service for passing data between controllers
app.factory('dataService', function() {

	var flightOptions = [];

	var flightDetails = [];

	var numAdults=0;

	var numChildren=0;

	var totalCost = 0;
	
	var personalDetails = [];

	var formDetails = [];
	
	var setFormDetails = function(formDetailsIn) {
		formDetails = formDetailsIn;
	};
	
	var getFormDetails = function() {
		return formDetails;
	};
	
	var setPersonalDetails = function(personalDetailsIn) {
		personalDetails = personalDetailsIn;
		console.log('in: ' + personalDetailsIn.firstName);
	};
	
	var getPersonalDetails = function() {
		console.log('out: ' + personalDetails.firstName);
		return personalDetails;
	};
	
	var setTotalCost = function(cost) {
		totalCost = cost;
	};
	var getTotalCost = function() {
		return totalCost;
	};

	var setNumAdults = function(nadults) {
		numAdults = nadults;
	};
	var getNumAdults = function() {
		return numAdults;
	};

	var setNumChildren = function(nchild) {
		numChildren = nchild;
	};
	var getNumChildren = function() {
		return numChildren;
	};

	var addFlights = function(newObj) {
		flightOptions = newObj;

	};

	var getFlights= function() {
		return flightOptions;
	};

	var setFlightDetails = function(newObj) {
		flightDetails = newObj;
	};

	var getFlightDetails = function() {
		return flightDetails;
	};

	return {
		addFlights : addFlights,
		getFlights : getFlights,
		setFlightDetails : setFlightDetails,
		getFlightDetails : getFlightDetails,

		setNumAdults : setNumAdults,
		getNumAdults : getNumAdults,
		setNumChildren : setNumChildren,
		getNumChildren : getNumChildren,
		setTotalCost : setTotalCost,
		getTotalCost : getTotalCost,
		
		setPersonalDetails : setPersonalDetails,
		getPersonalDetails : getPersonalDetails,
		
		setFormDetails : setFormDetails,
		getFormDetails : getFormDetails
	};
});

//define controller for main form
app.controller('FlightsFormController', ['$scope', '$http', 'dataService', '$location', function($scope, $http, dataService, $location) {

	console.log("[DDT0]a dataService : " + dataService);

	$scope.dataService = dataService;

	console.log("[DDT0]b dataService : " + dataService);

	$http.get('http://localhost:8080/RESTflightsRus/flightsRusService/allroutes').
	success(function(data) {
		$scope.destinations = data;
		console.log('allRoutes: ' + data);
	});

	//ensure that we cache the form details in between pages
	if (typeof dataService.getFormDetails() == 'undefined') {
		console.log('[DTD] RESETTING formDetails');
		$scope.formDetails = {};
	}
	else {
		$scope.formDetails = dataService.getFormDetails();
	}

	//define submit function
	$scope.submit = function() {
		console.log('Form Details destination: ' + $scope.formDetails.destination.name);
		console.log('Form Details departure: ' + $scope.formDetails.departure.name);
		console.log('Form Details fromDate: ' + $scope.formDetails.fromDate);
		console.log('Form Details toDate: ' + $scope.formDetails.toDate);
		console.log('Form Details # adults: ' + $scope.formDetails.numAdults);
		console.log('Form Details # children: ' + $scope.formDetails.numChildren);

		dataService.setNumAdults($scope.formDetails.numAdults);
		dataService.setNumChildren($scope.formDetails.numChildren);
		dataService.setFormDetails($scope.formDetails);

		console.log("[DDT1] dataService : " + dataService);

		$http.get("http://localhost:8080/RESTflightsRus/flightsRusService/flightoptions/?" + "departure=" + $scope.formDetails.departure.name +
				"&destination="+$scope.formDetails.destination.name+
				"&fromDate="   +$scope.formDetails.fromDate+
				"&toDate="     +$scope.formDetails.toDate+					
				"&numAdults="     +$scope.formDetails.numAdults+					
				"&numChildren="     +$scope.formDetails.numChildren).					
				success(function(data) {
					console.log("successfully submitted form");
					console.log(data);

					console.log("[DDT2] dataService : " + dataService);
					dataService.addFlights(data);
					$location.path('/flightOptions');
				});

	};

}]);

//define controller for flightOptions
app.controller('flightOptionsController', ['$scope', 'dataService', '$location', function ($scope, dataService, $location) {
	$scope.dataService = dataService;

	$scope.flightOptions = dataService.getFlights();

	console.log("CALLED FLIGHTOPTIONS CONTROLLER");
	$scope.selectedFlight = function(flightDetails) {
		console.log('selected flight: ' + flightDetails);
		dataService.setFlightDetails(flightDetails);
		$location.path('/order');
	};

}]);


//define controller for flightOrder
app.controller('flightOrderController', ['$scope', 'dataService', '$location', function ($scope, dataService, $location) {
	$scope.dataService = dataService;

	$scope.flightDetails = dataService.getFlightDetails();
	$scope.numAdults = dataService.getNumAdults();
	$scope.numChildren = dataService.getNumChildren();

	$scope.yearSelect = [
	                     {value: 2014},
	                     {value: 2015},
	                     {value: 2016},
	                     {value: 2017}
	                     ];

	$scope.monthSelect = [
		                     {value: 'Jan'}, {value: 'Feb'},{value: 'Mar'},{value: 'Apr'},
		                     {value: 'May'}, {value: 'Jun'},{value: 'Jul'},{value: 'Aug'},
		                     {value: 'Sep'}, {value: 'Oct'},{value: 'Nov'},{value: 'Dec'},
	                     ];

	$scope.personalDetails = [];

	//define submit function
	$scope.submit = function() {
		console.log('[flightOrderController] Customer details: ' + $scope.personalDetails);
		console.log('[flightOrderController] Customer First Name: ' + $scope.personalDetails.firstName);
		dataService.setPersonalDetails($scope.personalDetails);
		$location.path('/confirmation');
	};

}]);



//define controller for flightOrder
app.controller('OrderConfirmationController', ['$scope', 'dataService', function ($scope, dataService) {

//	$scope.dataService = dataService;

	$scope.personalDetails = dataService.getPersonalDetails();
	
	var person = $scope.personalDetails;
	
	console.log('[Confirmation] Customer First Name: ' + $scope.personalDetails.firstName);
	console.log('[Confirmation] Customer First Name: ' + person.firstName);
	
}]);


//create directive to perform custom validation
var MOBILE_REGEXP = /^0781/;
app.directive('mobile', function() {
  return {
    require: 'ngModel',
    link: function(scope, elm, attrs, ctrl) {
      ctrl.$parsers.unshift(function(inputValue) {
        if (MOBILE_REGEXP.test(inputValue)) {
          // it is valid
          ctrl.$setValidity('mobile', true);
          return inputValue;
        } else {
          // it is invalid, return undefined (no model update)
          ctrl.$setValidity('mobile', false);
          return undefined;
        }
      });
    }
  };
});
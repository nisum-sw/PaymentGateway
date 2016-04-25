var app = angular.module('nAutomationApp', [ 'ngAnimate', 'ui.bootstrap' ]);
app.controller('nAutomationCtrl', function($scope, $window) {
	$scope.pagenames = [ "login", "checkout", "payment" ];
	$scope.DropDownChnaged = function() {
			$scope.selectedPage = $scope.pagenames;
	};
	
	$scope.elements = [ {
		"name" : "userTxt"
	}, {
		"name" : "passTxt"
	}, {
		"name" : "subBtn"
	}, {
		"name" : "resetBtn"
	} ];
	
//	$scope.domains = [ {
//		"name" : "safeway"
//	}, {
//		"name" : "Walmart"
//	}, {
//		"name" : "Gap"
//	}, {
//		"name" : "Target"
//	} ];
	
	
	$scope.domains = [ "safeway","Walmart","Gap","Target"];
	$scope.DropDownDomains = function() {
		$scope.selectedDomain = $scope.DropDownDomains;
};
	
});
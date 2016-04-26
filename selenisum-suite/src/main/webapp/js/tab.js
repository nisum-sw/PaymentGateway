var app = angular.module('nAutomationApp', [ 'ngAnimate', 'ui.bootstrap' ]);
app.controller('nAutomationCtrl', function($scope, $window,$location,$http) {
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

	$scope.testSuits = [];
	$scope.testSuitsPerm = [];
	$scope.testCases = [];

	var path = $location.absUrl().substr(0, $location.absUrl().lastIndexOf("/"));
	$http.get(path+'/getTestScenario?input=abc').success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.testSuitsPerm  = data;
                var tSuites = Object.keys(data);
                $scope.testSuits.push("Please Select");
                tSuites.forEach(function(suite){
                	$scope.testSuits.push(suite);
                });
              })
              .error(function(data, status) {
                alert(1);
                alert( "failure message: " + JSON.stringify(data));
          });
          
	$scope.changeSuite = function(selectedSuite){
		$scope.testCases = $scope.testSuitsPerm[selectedSuite];
	};
	
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
	
	$scope.dropDownChnaged = function () {
	      //alert(url.name);
	        var valData = $scope.selectedDName + $scope.selectedPageName + "." + $scope.selectedFileExtension;
	        alert(valData);
	        var path = $location.absUrl().substr(0, $location.absUrl().lastIndexOf("/"));
	        
	        alert(path);
	          //$scope.selectedPage=$scope.selectedPage;
	          $http.get(path+'/getElements?input='+
	              encodeURIComponent(valData)).success(function(data, status) {
	                //alert("i am" + status);
	                
	                $scope.elements = data;
	              })
	              .error(function(data, status) {
	                alert(status);
	                alert( "failure message: " + JSON.stringify(data));
	              });
	      };
	
});
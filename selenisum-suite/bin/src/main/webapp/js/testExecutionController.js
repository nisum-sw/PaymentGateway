(function () {
'use strict';
var app = angular.module('nAutomationApp');
app.controller('testExecutionCtrl',
		function($scope, $window, $location, $http) {
			$scope.pagenames = [ "login", "checkout", "payment" ];
			$scope.DropDownChnaged = function() {
				$scope.selectedPage = $scope.pagenames;
			};

			
			$scope.testSuits = [];
			$scope.testSuitsPerm = [];
			$scope.testCases = [];

			var path = $location.absUrl().substr(0,
					$location.absUrl().lastIndexOf("/"));
			$http.get(path + '/getTestScenario?input=abc').success(
					function(data, status) {
						console.log(JSON.stringify(data));
						$scope.testSuitsPerm = data;
						var tSuites = Object.keys(data);
						$scope.testSuits.push("Please Select");
						tSuites.forEach(function(suite) {
							$scope.testSuits.push(suite);
						});
					}).error(function(data, status) {
			});

			
			$scope.changeSuite = function(selectedSuite) {
				$scope.testCases = $scope.testSuitsPerm[selectedSuite];
			};

			
			$scope.domains = [ "safeway", "Walmart", "Gap", "Target" ];

			$scope.browsers = [ "Chrome", "Firefox", "Safari", "Explorer" ];

						$scope.project = {};

			
			
			$scope.executeTest = function() {
				//alert(url.name);
				var path = $location.absUrl().substr(0,
						$location.absUrl().lastIndexOf("/"))

				//$scope.selectedPage=$scope.selectedPage;
				$http.post(path + '/executeTest',
						encodeURIComponent($scope.domainName)).success(
						function(data, status) {
							//alert("i am" + status);
							console.log(data);
							$scope.report = data;
							$scope.reportHref = data;
						}).error(function(data, status) {
					alert(status);
					alert("failure message: " + JSON.stringify(data));
				});

				$scope.newWindow = function(reportHref) {
					$window.open(reportHref);
				};
			};

});

})();
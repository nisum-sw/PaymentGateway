(function () {
'use strict';
var app = angular.module('nAutomationApp');
app.controller('testExecutionCtrl',
		function($scope, $window, $location, $http) {
			$scope.pagenames = [ "login", "checkout", "payment" ];
			$scope.DropDownChnaged = function() {
				$scope.selectedPage = $scope.pagenames;
			};

			
			$scope.testSuites = {};
			$scope.testSuitsPerm = [];
			$scope.testCases = [];
			$scope.projects = [];
			$scope.project = {
					selectedSuite :"",
					selectedCase :"",
			};
			
			$http.get('./projects').success(
					function(data, status) {
						$scope.projects = data;
						createTestSuiteObject(data);
					//	console.log(JSON.stringify($scope.projects));
					}).error(function(data, status) {
			});

			function createTestSuiteObject(data){
				
				data.forEach(function(project){
					if($scope.testSuites[project.testSuite[0].testSuiteName]){
						$scope.testSuites[project.testSuite[0].testSuiteName].push(project.testSuite[0].testCases[0].testCaseName);
					}else {
						$scope.testSuites[project.testSuite[0].testSuiteName] = [];
						$scope.testSuites[project.testSuite[0].testSuiteName].push(project.testSuite[0].testCases[0].testCaseName);
					}
					
				});
				console.log(JSON.stringify($scope.testSuites));
			}
			
			$scope.changeSuite = function(suiteSeleted) {
				console.log(suiteSeleted);
				$scope.testCases = $scope.testSuites[suiteSeleted.testSuite[0].testSuiteName];
				console.log($scope.testCases);
			};

			
			$scope.domains = [ "safeway", "Walmart", "Gap", "Target" ];

			$scope.browsers = [ "Chrome", "Firefox", "Safari", "Explorer" ];

						

			
			
			$scope.executeTest = function() {
				console.log('Executing Test with input url ' + $scope.domainName);
				$http.post('./executeTest',
						encodeURIComponent($scope.domainName)).success(
						function(data, status) {
							//alert("i am" + status);
							
							$scope.report = data;
							$scope.reportHref = data;
						}).error(function(data, status) {
				
					alert("failure message: " + JSON.stringify(data));
				});

				
			};

});

})();
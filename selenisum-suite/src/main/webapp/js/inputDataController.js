(function () {
'use strict';
var app = angular.module('nAutomationApp');

app.controller('inputDataCtrl',
		function($scope, $window, $location, $http) {
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

			$scope.domains = [ "safeway", "Walmart", "Gap", "Target" ];

			$scope.browsers = [ "Chrome", "Firefox", "Safari", "Explorer" ];

			$scope.dropDownChnaged = function() {
				//alert(url.name);
				var valData = $scope.selectedDName + $scope.selectedPageName
						+ "." + $scope.selectedFileExtension;
				var path = $location.absUrl().substr(0,
						$location.absUrl().lastIndexOf("/"));

				//$scope.selectedPage=$scope.selectedPage;
				$http.get(
						path + '/getElements?input='
								+ encodeURIComponent(valData)).success(
						function(data, status) {
							//alert("i am" + status);

							$scope.elements = data;
						}).error(function(data, status) {
					console.log("failure message: " + JSON.stringify(data));
				});
			};

			$scope.project = {};

			$scope.postHttpJSON = function() {

				var postObject = {
					"testSuite" : [ {
						"testSuiteId" : "testSuiteId",
						"testSuiteName" : "testSuiteName",
						"testSuiteDesc" : "testSuiteDesc",
						"testCaseList" : [ {
							"testCaseId" : "testCaseId",
							"testCaseName" : "testCaseName",
							"testCaseDesc" : "testCaseDesc",
							"pageElement" : [ {
								"pageElementId" : "pageElementId",
								"pageElementName" : "pageElementName",
								"pageElementDesc" : "pageElementDesc"
							} ]
						} ]
					} ],
					"pageURL" : "MongoDBangu is no sql database",
					"pageName" : [ "mpn1", "database1", "NoSQL1" ],
					"brwType" : [ "mpn12", "database12", "NoSQL12" ]
				};

				var path = $location.absUrl().substr(0,
						$location.absUrl().lastIndexOf("/")) +"/projects/";
				
				$http({
					url : path,
					dataType : 'json',
					method : 'POST',
					data : postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
				}).error(function(error) {
					$scope.error = error;
				});
			};

			$scope.getAllProjectsHttpJSONByName = function() {
				$http({
					url : 'http://localhost:8080/projects/',
					dataType : 'json',
					method : 'GET',
					data : postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
				}).error(function(error) {
					$scope.error = error;
				});
			};

			$scope.getTestSuiteHttpJSONByProjects = function() {
				$http({
					url : 'http://localhost:8080/testsuites',
					dataType : 'json',
					method : 'GET',
					data : postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
				}).error(function(error) {
					$scope.error = error;
				});
			};

			$scope.getTestCasesHttpJSONByTestSuite = function() {
				$http({
					url : 'http://localhost:8080/pgElements',
					dataType : 'json',
					method : 'GET',
					data : postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
				}).error(function(error) {
					$scope.error = error;
				});
			};

			$scope.getPageElementHttpJSONByTestCases = function() {
				$http({
					url : 'http://localhost:8080/pgElements',
					dataType : 'json',
					method : 'GET',
					data : postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
				}).error(function(error) {
					$scope.error = error;
				});
			};

});



})();
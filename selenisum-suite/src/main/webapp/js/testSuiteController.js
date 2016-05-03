(function () {
'use strict';
var app = angular.module('nAutomationApp');

app.controller('testSuiteCtrl', [ '$scope', '$http', '$window', '$location',
		function($scope, $http, $window, $location) {
			
			$scope.testSuite = {
					testSuiteName : "",
					testCases : []
			};
			

			var counter = 0;
			$scope.testsuiteelemnt = [ {
				'id' : 'counter',
				'question' : 'TEST-SUITE',
				'answer' : '',
				'inline' : 'true'
			} ];

			$scope.newItem = function($event) {
				counter++;
				$scope.testsuiteelemnt.push({
					'id' : 'counter',
					'question' : 'TEST-SUITE',
					'answer' : '',
					'inline' : 'true'
				});
				$event.preventDefault();
			}

			$scope.setSuiteName = function($event) {
				$scope.hideTxtBox = false;
				//$event.preventDefault();
			}

			$scope.makeSuiteNameStatic = function($event) {
				$scope.hideTxtBox = true;
				//$event.preventDefault();
			}

			$scope.addNewTestCase = function($event) {
				
				$scope.testSuite.testCases.push({
					'testCaseName' : $scope.newTestCaseName
					
				});
				$scope.newTestCaseName = "";
			}

			$scope.removeTestCase = function(index, testCase) {
				$scope.testSuite.testCases.splice(index, 1);
			}

			$scope.saveTestSuite = function() {
					
				$http({
					url : "./testsuites",
					dataType : 'json',
					method : 'POST',
					data : $scope.testSuite,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					 $scope.successTextAlert = "Test Suite Saved Successfully !";
					    $scope.showSuccessAlert = true;
					$scope.getTestSuites();
				}).error(function(error) {
					$scope.error = error;
				});
			};
			
			$scope.saveTestSuites = [];
			$scope.getTestSuites = function() {
				
				$http({
					url : "./testsuites",
					method : 'GET',
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					console.log(JSON.stringify(response));
					$scope.savedTestSuites = response;
				}).error(function(error) {
					alert(2);
					$scope.error = error;
				});
			};
			$scope.getTestSuites();
			console.log(JSON.stringify($scope.saveTestSuites));
			
			$scope.sort = function(keyname){
		        $scope.sortKey = keyname;   //set the sortKey to the param passed
		        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
		    }		
			
			$scope.editTestSuite = function(testSuite){
				console.log(JSON.stringify(testSuite));
				$scope.testSuite = testSuite;
			};
			$scope.deleteTestSuite = function(testSuite){
				console.log(JSON.stringify(testSuite));
				$http({
					url : "./testsuites/" +testSuite.testSuiteId,
					method : 'DELETE',					
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.successTextAlert = "Test Suite : " + testSuite.testSuiteName +" Deleted Successfully !";
				    $scope.showSuccessAlert = true;
					console.log(JSON.stringify(response));
					$scope.getTestSuites();
				}).error(function(error) {
					alert(2);
					$scope.error = error;
				});
				
			};
			
			
		} ]);
})();
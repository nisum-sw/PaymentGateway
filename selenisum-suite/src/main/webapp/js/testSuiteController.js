(function () {
'use strict';
var app = angular.module('nAutomationApp');

app.controller('testSuiteCtrl', [ '$scope', '$http', '$window', '$location',
		function($scope, $http, $window, $location) {
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
				$scope.testCases.push({
					'name' : $scope.newTestCaseName,
					'selected' : false
				});
				$scope.newTestCaseName = "";
			}

			$scope.removeTestCase = function(index, testCase) {
				$scope.testCases.splice(index, 1);
			}

		} ]);
})();
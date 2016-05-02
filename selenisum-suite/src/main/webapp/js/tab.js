'use strict';
var app = angular.module('nAutomationApp', [ 'ngAnimate', 'ui.bootstrap' ]);

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

app.controller('nAutomationCtrl',
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

			$scope.reports = [];
			$http.get(path + "/report?input=s").success(function(data, status) {
				console.log(data);

				$scope.reports = data;
				//     data.forEach(function(suite){
				//$scope.reports.push(suite);
				//   });
			}).error(function(data, status) {
				console.log("failure message: " + JSON.stringify(data));
			});

			$scope.changeSuite = function(selectedSuite) {
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

			$scope.saveAlert = function() {
				console.log("foo Mukarram");
			};

			$scope.jsonData = {
				"April281030" : "foo000001"
			};

			$scope.jsonDomainName = "http://192.168.7.85:8080/projects/";

			$scope.project = {};

			$scope.saveToDatabase = function() {

				console.log("tab.js::saveToDatabase");

				console.log(" $scope.project = "
						+ JSON.stringify($scope.project));

				var config = {
					'Content-Type' : 'application/json'
				};

				var pathToMongo = "http://localhost:8080/projects"

				$http.post(pathToMongo, $scope.project).success(
						function(data, status) {
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

				$http({
					url : 'http://localhost:8080/projects/',
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

			$scope.sortType = 'name'; // set the default sort type
			$scope.sortReverse = false; // set the default sort order
			$scope.searchReport = ''; // set the default search/filter term

			$scope.today = function(d1) {
				d1 = new Date();
			};
			$scope.today();
			$scope.clear = function(d1) {
				d1 = null;
			};

			var todaysDate = new Date();

			$scope.dateOptions = {

				formatYear : 'yy',
				maxDate : new Date(2020, 5, 22),
				minDate : new Date(todaysDate.getFullYear(), todaysDate
						.getMonth() - 6, todaysDate.getDate()),
				startingDay : 1
			};

			// Disable weekend selection
			function disabled(data) {
				var date = data.date, mode = data.mode;
				return mode === 'day'
						&& (date.getDay() === 0 || date.getDay() === 6);
			}
			$scope.open1 = function() {
				$scope.popup1.opened = true;
			};

			$scope.open2 = function() {
				$scope.popup2.opened = true;
			};

			$scope.setDate = function(year, month, day) {
				$scope.dt = new Date(year, month, day);
			};

			$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy',
					'shortDate' ];
			$scope.format = $scope.formats[0];
			$scope.altInputFormats = [ 'M!/d!/yyyy' ];

			$scope.popup1 = {
				opened : false
			};

			$scope.popup2 = {
				opened : false
			};
			function getDayClass(data) {
				var date = data.date, mode = data.mode;
				if (mode === 'day') {
					var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

					for (var i = 0; i < $scope.events.length; i++) {
						var currentDay = new Date($scope.events[i].date)
								.setHours(0, 0, 0, 0);

						if (dayToCheck === currentDay) {
							return $scope.events[i].status;
						}
					}
				}

				return '';
			}

		});

function parseDate(date) {

	date = new Date(date);
	var str = date.toISOString().slice(0, 10);
	date = new Date(str);
	return date;
}

app.filter('FilterByDate', function() {
	return function(items, dateFrom, dateTo) {
		if (dateFrom == "" || dateFrom == undefined) {
			return items;
		}
		;
		if (dateTo == "" || dateTo == undefined) {
			return items;
		}
		;

		var filtered = [];

		for (var i = 0; i < items.length; i++) {
			var item = items[i];

			var r = parseDate(item.reportDate);
			var f = parseDate(dateFrom);
			var t = parseDate(dateTo);

			if (r.getTime() >= f.getTime() && r.getTime() <= t.getTime()) {
				filtered.push(item);
			}
		}

		return filtered;
	};
});
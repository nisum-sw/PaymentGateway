(function () {
'use strict';
var app = angular.module('nAutomationApp');

app.controller('inputDataCtrl',
		function($scope, $window, $location, $http) {
			$scope.pagenames = [ "ShopStores/OSSO-Login.page", "login", "checkout", "payment" ];
			$scope.domains = [ "www.safeway.com", "Walmart", "Gap", "Target" ];
			$scope.browsers = [ "Chrome", "Firefox", "Safari", "Explorer" ];

			$scope.testSuits = [];
			$scope.testSuitsPerm = [];
			$scope.testCases = [];
			$scope.selectedFileExtension ="html";
			$scope.selectedDName;
			
            $scope.elements = [];

			
			$scope.elements = [ {
				"name" : "userTxt"
			}, {
				"name" : "passTxt"
			}, {
				"name" : "subBtn"
			}, {
				"name" : "resetBtn"
			} ];

			
			$scope.DropDownDomains= function(selectedDName) {
				
				$scope.selectedDName = selectedDName;
			};
			
			
		    $scope.dropDownChanged = function (selectedPageName) {
		        $scope.selectedPageName = selectedPageName;
		        //var valData = $scope.selectedDName + $scope.selectedPageName + "." + $scope.selectedFileExtension;
		        var valData = $scope.selectedDName +  "/" + $scope.selectedPageName;

		        console.log("may 4th valData encodeURIComponent(valData)= " + encodeURIComponent(valData));
		 		        
		        
		        $http.get('./getElements?input='+
		                encodeURIComponent(valData)).success(function(data, status) {
		                  //alert("i am" + status);

		                  $scope.elements = data;
		                })
		                .error(function(data, status) {
		                  alert(status);
		                  alert( "failure message: " + JSON.stringify(data));
		                });
		        };
		        
			$scope.testcase;
			
			$scope.changeCase = function(selectedCase) {
	
				var tcase = {
						"testCaseName":selectedCase,
						"testCaseDesc": "fooCaseDecription"
				} ;
								
				console.log ( " test line 45 " + $scope.project.testSuite[0] );
				
				if(!$scope.project.testSuite[0].testCases) {
					$scope.project.testSuite[0].testCases = [];
				}

				$scope.project.testSuite[0].testCases.push(tcase);			
				
			};
			
			$scope.changeSuite = function(selectedSuite) {				

				var t = {
						"testSuiteName":selectedSuite,
						"testCases": $scope.testcase
				} ;
				
				
				$scope.project.testSuite.push(t);
				$scope.testCases = $scope.testSuitsPerm[selectedSuite];
			};
			
			var path = $location.absUrl().substr(0,
					$location.absUrl().lastIndexOf("/"));
			$http.get(path + '/getTestScenario?input=abc').success(
					function(data, status) {
						
						$scope.testSuitsPerm = data;
						var tSuites = Object.keys(data);
						$scope.testSuits.push("Please Select");
						tSuites.forEach(function(suite) {
							$scope.testSuits.push(suite);
						});
					}).error(function(data, status) {
			});

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

			/*
			$scope.project = {  
					 testSuite : [ 
					               { 
					            	 testSuiteId:"", 
					            	 testSuiteName:"", 
					            	 testSuiteDesc:"",
					            	 testCases:[
					            	              {
					            	            	  testCaseId:"",
					            	            	  testCaseName:"",
					            	            	  testCaseDesc:"",
					            	            	  testSteps:[
					            	            	               {
					            	            	            	   "testStepId": "",
					            	            	            	   "testStepName":"",
					            	            	            	   "expected":"",
					            	            	            	   "actual":"",
					            	            	            	   "status":""                                                                                                                                        
					            	            	               	}
					            	            	               ], 
		                                pageElement:[
		                                               {
		                                            	   pageElementId:"",
		                                            	   pageElementName:"",
		                                            	   pageElementDesc:""
		                                               }
		                                               ]                                  
					            	              }
					            	             ]	
					               }
					             ],     
		   pageURL: "",
		   pageName :  "",
		   brwType : "",
		   domainName : ""    
		};
			
			*/
			
            $scope.project = {
            		pageURL:"testingpageurl123",
            		testSuite:[]
                    };

			$scope.postHttpJSON = function() {

                console.log('postHttpJSON $scope.project = '+JSON.stringify($scope.project));

                $http.post('projects/', $scope.project )
                .success(function (data, status, headers)
                {
                 console.log('Success'+JSON.stringify(data));
                })
                .error(function (data, status, headers)
                {
                 console.log('Failure');
                });
                
/*				
				$http({
					url : "projects",
					dataType : 'json',
					method : 'POST',
					data : $scope.postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
                    console.log('Success'+JSON.stringify(response));
				}).error(function(error) {
					$scope.error = error;
					
                    console.log('Failure');
				});
				
				*/
                
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
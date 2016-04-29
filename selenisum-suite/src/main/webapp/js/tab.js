var app = angular.module('nAutomationApp', [ 'ngAnimate', 'ui.bootstrap' ]);

app.controller('testSuiteCtrl', ['$scope', '$http',
   function ($scope, $http) {
 	var counter=0;
     $scope.testsuiteelemnt = [ {'id':'counter', 'question' : 'TEST-SUITE', 'answer' : '','inline':'true'} ];

     $scope.newItem = function($event){
         counter++;
         $scope.testsuiteelemnt.push({'id':'counter', 'question' : 'TEST-SUITE', 'answer' : '','inline':'true'});
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
    	 $scope.testCases.push({'name':$scope.newTestCaseName, 'selected':false});
    	 $scope.newTestCaseName = "";
     }
     
     $scope.removeTestCase = function(removeSelectedTestCase) {
    	 $scope.testCases.splice(removeSelectedTestCase, 1);
     }
     
}]);


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
	
	$scope.reports =[];
	$http.get(path+"/getReports?input=s").success(function(data, status) {
        console.log(data);
        
        
        data.forEach(function(suite){
        	$scope.reports.push(suite);
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
	
	$scope.browsers = [ "Chrome","Firefox","Safari","Explorer"];
	


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

	      $scope.saveAlert= function () {
		      alert("foo Mukarram");
		  };
		      

	      $scope.jsonData =
	      {  
	    		     "Pid":"NisumJsonDataStatis P000001",
	    		    "pageURL" : "Nisum MongoDB is no sql database",
	    		    "pageName" : [ "Nisum mpn1", "database1", "muksJsonDataStatis NoSQL1"],
	    		    "brwType" : [ "Nisum mpn12", "database12", "muksJsonDataStatis NoSQL12"]   
	      };
	      
	      $scope.jsonDomainName= "http://192.168.7.85:8080/projects/";
	      
	      $scope.saveFooJsonTest = function () {
	    	  var pathToMongo="http://192.168.7.85:8080/projects/";
	            
	              //$scope.selectedPage=$scope.selectedPage;
	              $http.post(pathToMongo,
	                  encodeURIComponent($scope.jsonData)).success(function(data, status) {
	                    alert(data);
	                    $scope.report = data;
	                    $scope.reportHref = data;
	                  })
	                  .error(function(data, status) {
	                    alert(status);
	                    alert( "failure message: " + JSON.stringify(data));
	                  });
	              
	              $scope.newWindow = function (reportHref) { 
	                 $window.open(reportHref);
	              };
	          };
	          
	      $scope.executeTest = function () {
		          //alert(url.name);
		            var path = $location.absUrl().substr(0, $location.absUrl().lastIndexOf("/"))
		            
		              //$scope.selectedPage=$scope.selectedPage;
		              $http.post(path+'/executeTest',
		                  encodeURIComponent($scope.domainName)).success(function(data, status) {
		                    //alert("i am" + status);
		                    alert(data);
		                    $scope.report = data;
		                    $scope.reportHref = data;
		                  })
		                  .error(function(data, status) {
		                    alert(status);
		                    alert( "failure message: " + JSON.stringify(data));
		                  });
		              
		              $scope.newWindow = function (reportHref) { 
		                 $window.open(reportHref);
		              };
		          };
          $scope.newWindow = function (reportHref) { 
              $window.open("Results/"+reportHref);
           };

	
});
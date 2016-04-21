var app = angular.module('nAutomationApp', []);
app.controller('nAutomationCtrl', function($scope,$http,$window,$location) {
	$scope.report="";
	$scope.reportHref="#";
	$scope.pagenames = [{
		"name" : "index"
	}, {
		"name" : "sample_v3.2"
	}, {
		"name" : "transaction"
	}, {
		"name" : "lastPage"
	}];

	$scope.elements = [  ];
	
	
	$scope.dropDownChnaged = function (url) {
		//alert(url.name);
			var valData = $scope.domainName + url.name + "." + $scope.extension;
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

});
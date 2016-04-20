var app = angular.module('nAutomationApp', []);
app.controller('nAutomationCtrl', function($scope,$http) {
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
        //$scope.selectedPage=$scope.selectedPage;
        $http.post('http://localhost:8080/selenisum-suite/getElements',
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
			
			var valData ={};
        //$scope.selectedPage=$scope.selectedPage;
        $http.get('http://localhost:8080/selenisum-suite/executeTest',
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
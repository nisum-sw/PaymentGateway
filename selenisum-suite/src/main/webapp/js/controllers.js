'use strict';

/* Controllers */

var testAutomationControllers = angular.module('testAutomationControllers', []);

testAutomationControllers.controller('populateDomainNames', ['$scope', '$http',
  function ($scope, $http) {
    $http.get('config/domain-names.json').success(function(data) {
      $scope.domainNames = data;
      $scope.selectedDName = data[0].url;
    });

    $http.get('config/file-extensions.json').success(function(data) {
      $scope.fileExtensions = data;
      $scope.selectedFileExtension = data[0].ext;
    });
    
    $http.get('config/page-names.json').success(function(data) {
        $scope.pageNames = data;
        $scope.selectedPageName = "Select Page";
      });
    
  }]);


testAutomationControllers.controller('nAutomationCtrl', ['$scope', '$http', '$window', '$location',
  function($scope,$http,$window,$location) {
    $scope.report="";
    $scope.reportHref="#";

    $scope.elements = [  ];
      
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
  }]);



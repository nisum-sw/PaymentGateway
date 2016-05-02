'use strict';

/* App Module */

var nAutomationApp = angular.module('nAutomationApp', [
  'testAutomationControllers'
]);

var MyApp = angular.module('MyApp',['ngMaterial', 'ngMessages']);

MyApp.all("/*", function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With");
    res.header("Access-Control-Allow-Methods", "GET, PUT, POST");
    return next();
});
'use strict';

var myApp = angular.module('myApp',[]);

myApp.directive ('owntag', function(){
   return {
      template: '<p>Own Tag</p>',
      restrict : 'E'
   };
});

myApp.directive ('ownattribute', function(){
   return {
      template : '<p>{{text}}</p>',
      link : function (scope,elem,attrs) {
         scope.text=attrs.ownattribute;
      }
   };
});

myApp.controller ("myController", function($scope){
   $scope.counter=0;
   $scope.add = function(){
      $scope.counter++;
   };
});

myApp.directive ('counter', function(){
   return {
      restrict: 'E',
      template : '<p>{{counter}}</p>'
   };
});
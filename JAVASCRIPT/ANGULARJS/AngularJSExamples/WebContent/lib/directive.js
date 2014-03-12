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

myApp.directive ('templateExample', function(){
   return {
      templateUrl : 'template.html'
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

myApp.directive ('transcludeTrueExample', function(){
   return {
      restrict : 'A',
      transclude : true,
      template : '<div><p ng-transclude></p></div>'
   };
});

myApp.directive ('transcludeElementExample', function(){
   return {
      restrict : 'A',
      transclude : 'element',
      link : function (scope, element, attrs, controller, transcludeFn){
         element.after(transcludeFn());
         element.after("<p>Added Element</p>");
      }
   };
});
'use strict';

var myApp = angular.module('myApp',[]);

myApp.service('Users',function($http){
	this.query = function() {
	   return $http({method:'GET',url:'users.json'});
	};
});

myApp.controller('HttpController',function($scope,$http){
	$http.get('users.json').success(function(data){
		$scope.sameItems=data;
	});
});

myApp.controller('ServiceController',function($scope,Users){
	Users.query().success(function (data) {
		$scope.items=data;
	});
});
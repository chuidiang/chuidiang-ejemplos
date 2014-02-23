function HelloController($scope) {
	$scope.greeting = 'hello';
}

function CheckController ($scope) {
	$scope.checkbox='checked';
	$scope.visible = true;
	
	$scope.toggle = function () {
		$scope.visible = !$scope.visible;
	};
}

function RepeatController ($scope) {
	$scope.counter=1;
	$scope.array=[0];
	
	$scope.updateModel = function () {
		$scope.array=[];
		for (var i=0;i<$scope.counter;i++){
			$scope.array.push(i);
		}
	};
}
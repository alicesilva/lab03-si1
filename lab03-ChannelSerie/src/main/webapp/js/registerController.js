/**
 * Controller para view register
 */
app.controller("RegisterController", function($scope, $http) {

	$scope.user = new Object();
	$scope.user.name = "";
	$scope.user.email = "";
	$scope.user.password = "";

	$scope.register = function() {
		$http.post("/userRegistration", $scope.user)
	};

});
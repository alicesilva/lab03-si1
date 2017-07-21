/**
 * Controller para view register
 */
app.controller("RegisterController", function($scope, $http) {

	$scope.user = new Object();
	$scope.user.name = "";
	$scope.user.email = "";
	$scope.user.password = "";

	$scope.register = function() {
		$http.post("/userRegistration", $scope.user).then(function(response) {
			if (response.data == "") {
				alert("There is already a user with this email!");
			} else {
				alert("Registered user successfully!")
			}
		})
	};

});
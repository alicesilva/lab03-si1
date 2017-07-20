/**
 * Controller para view login
 */
app
.controller("LoginController",
		function($scope, $http, $rootScope, $location) {

	$scope.user = new Object();
	$scope.user.email = "";
	$scope.user.password = "";

	$scope.login = function() {
		$http.post("/authenticate", $scope.user).then(
				function(response) {
					localStorage.setItem("userToken",
							response.data.token);
					searchUserLoggedIn($scope.user.password);
					$location.path("/home");
				}, function(response) {
				})
	};

	var searchUserLoggedIn = function(password) {
		token = localStorage.getItem("userToken")
		$http.defaults.headers.common.Authorization = 'Bearer '
			+ token;
		$http.get("/admin/searchUser/" + password).then(
				function(response) {
					$rootScope.userLoggedIn = response.data;
				}, function(response) {
				});
	};

});
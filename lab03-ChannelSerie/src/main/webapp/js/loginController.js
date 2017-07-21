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
									searchUserLoggedIn($scope.user);
									$location.path("/home");
								}, function(response) {
									alert("Invalid email or password!");
								})
					};

					var searchUserLoggedIn = function(user) {
						token = localStorage.getItem("userToken")
						$http.defaults.headers.common.Authorization = 'Bearer '
								+ token;
						$http.post("/admin/searchUser", user).then(
								function(response) {
									$rootScope.userLoggedIn = response.data;
								}, function(response) {
								});
					};

				});
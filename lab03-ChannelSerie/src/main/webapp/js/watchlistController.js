/**
 * 
 */
app.controller("WatchlistController", function($scope, $http, $rootScope,
		$location) {

	$scope.serieProfile = new Object();
	$scope.addSerieToProfile = function(name, year, image) {

		$scope.serieProfile.name = name;
		$scope.serieProfile.year = year;
		$scope.serieProfile.image = image;

		$http.post(
				"admin/registerWatchlistSerieOnProfile/"
						+ $rootScope.userLoggedIn.id, $scope.serieProfile)
				.then(function(response) {
					$rootScope.userLoggedIn = response.data;
				}, function(response) {

				})
	};

	$scope.backToHomePage = function() {
		$location.path('/home');
	};

})
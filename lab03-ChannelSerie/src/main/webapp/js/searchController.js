/**
 * Controller para view search
 */
app
		.controller(
				"SearchController",
				function($scope, $rootScope, $http, $location) {

					$scope.nameSerie = $rootScope.nameSerieSearch;

					token = localStorage.getItem("userToken")
					$http.defaults.headers.common.Authorization = 'Bearer '
							+ token;
					$http.get(
							'https://omdbapi.com/?s=' + $scope.nameSerie
									+ '&type=series&r=json&apikey=93330d3c', {
								headers : {
									'Authorization' : undefined
								}
							}).success(function(data) {
						if (data.Response == "False") {
							alert("Serie not found!");
						} else {
							$scope.series = data;
						}
					});

					$scope.serieProfile = new Object();

					$scope.addSerieToProfile = function(name, year, image) {
						$scope.serieProfile.name = name;
						$scope.serieProfile.year = year;
						$scope.serieProfile.image = image;

						token = localStorage.getItem("userToken")
						$http.defaults.headers.common.Authorization = 'Bearer '
								+ token;
						$http
								.post(
										"/admin/registerSerieToProfile/"
												+ $rootScope.userLoggedIn.id,
										$scope.serieProfile)
								.then(
										function(response) {
											if (response.data == "") {
												alert("Series has already been added to the profile or watch list, it is impossible to add it again!");
											} else {
												$rootScope.userLoggedIn = response.data;
											}

										}, function(response) {

										})
					}
					$scope.serieWatchlist = new Object();

					$scope.addSerieToWatchlist = function(name, year, image) {
						$scope.serieWatchlist.name = name;
						$scope.serieWatchlist.year = year;
						$scope.serieWatchlist.image = image;

						token = localStorage.getItem("userToken")
						$http.defaults.headers.common.Authorization = 'Bearer '
								+ token;
						$http
								.post(
										"/admin/registerSerieToWatchlist/"
												+ $rootScope.userLoggedIn.id,
										$scope.serieWatchlist)
								.then(
										function(response) {
											if (response.data == "") {
												alert("Series has already been added to the profile or watch list, it is impossible to add it again");
											} else {
												$rootScope.userLoggedIn = response.data;
											}

										}, function(response) {

										})

					}
					$scope.backToHomePage = function() {
						$location.path('/home');
					};

				})
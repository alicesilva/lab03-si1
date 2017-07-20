/**
 * Controller para a view home
 */
app
.controller(
		"HomeController",
		function($scope, $location, $rootScope, $http) {
			$scope.serieName = "";

			$scope.search = function() {
				$rootScope.nameSerieSearch = $scope.serieName;
				$location.path('/search');
			};

			$scope.clickerChecker = false;
			$scope.showSeriesInformation = function(serieName) {
				$http
				.get(
						'https://omdbapi.com/?t='
						+ serieName
						+ '&type=series&r=json&apikey=93330d3c',{
							headers: {
								'Authorization': undefined
							}})
						.success(function(data) {
							$scope.series = data;
						});

				$scope.clickerChecker = true;
			};

			$scope.evaluationNote = 0;

			$scope.addEvaluationNote = function(serie) {
				
				var seriesToAddEvaluationNote = new Object();
				seriesToAddEvaluationNote.name = serie.Title;
				seriesToAddEvaluationNote.year = serie.Year;
				seriesToAddEvaluationNote.image = serie.Poster;
				
				seriesToAddEvaluationNote.nota = $scope.evaluationNote;

				token = localStorage.getItem("userToken")
				$http.defaults.headers.common.Authorization = 'Bearer '
					+ token;
				$http.post(
						"admin/addNoteEvaluationToSerie/"
						+ $rootScope.userLoggedIn.id, seriesToAddEvaluationNote)
						.then(function(response) {
							$rootScope.userLoggedIn = response.data;
							console.log(response.status);
						}, function(response) {

						})
						$scope.evaluationNote = 0;

			};

			$scope.chapter = 0;
			$scope.addChapter = function(serie) {
				var seriesToAddChapter = new Object();
				seriesToAddChapter.name = serie.Title;
				seriesToAddChapter.year = serie.Year;
				seriesToAddChapter.image = serie.Poster;
				seriesToAddChapter.lastChapter = $scope.chapter;

				token = localStorage.getItem("userToken")
				$http.defaults.headers.common.Authorization = 'Bearer '
					+ token;
				$http.post(
						"admin/addLastChapterWatchedTheSerie/"
						+ $rootScope.userLoggedIn.id, seriesToAddChapter)
						.then(function(response) {
							$rootScope.userLoggedIn = response.data;
							console.log(response.status);

						}, function(response) {

						})
						$scope.serie = new Object();
						$scope.chapter = 0;
			};
			 
			$scope.removeProfileSeries = function(serie) {

				var decisao = confirm("Do you really want to remove the profile series? Click Ok if your answer is yes!")
				if (decisao) {
					
					var serieDelete = new Object();
					serieDelete.name = serie.Title;
					serieDelete.year = serie.Year;
					serieDelete.image = serie.Poster;

					token = localStorage.getItem("userToken")
					$http.defaults.headers.common.Authorization = 'Bearer '
						+ token;
					$http.post(
							"admin/removeSeriesFromProfile/"
							+ $rootScope.userLoggedIn.id,
							serieDelete).then(function(response) {
								$rootScope.userLoggedIn = response.data;
								console.log(response.status);

							}, function(response) {

							})
							$scope.clickerChecker = false;
				}

			};

			$scope.redirectToWatchlist = function() {
				$location.path('/watchlist');
			};
		});
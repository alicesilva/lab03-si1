/**
 * 
 */
var app = angular.module('App', [ 'ngRoute' ])

.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/login', {
		controller : 'LoginController',
		templateUrl : 'view/login.html'
	})

	.when('/home', {
		controller : 'HomeController',
		templateUrl : 'view/home.html'
	})

	.when('/register', {
		controller : 'RegisterController',
		templateUrl : 'view/register.html'
	})

	.when('/search', {
		controller : 'SearchController',
		templateUrl : 'view/search.html'
	})

	.when('/watchlist', {
		controller : 'WatchlistController',
		templateUrl : 'view/watchlist.html'
	})

	.otherwise({
		redirectTo : '/login'
	});
} ])

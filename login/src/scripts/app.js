'use strict';

// declare modules
var app = angular.module('App', ['ngRoute','ngCookies'])

.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'modules/authentication/views/login.html'
        })

        .when('/', {
            controller: 'HomeController',
            templateUrl: 'modules/home/views/home.html'
        })

        .when('/register', {
            controller: 'ControllerRegister',
            templateUrl: 'modules/register/views/register.html'
        })

        .when('/search', {
            controller: 'ControllerSearch',
            templateUrl: 'modules/search/views/search.html'
        })

        .when('/watchlist', {
            controller: 'ControllerWatchlist',
            templateUrl: 'modules/watchlist/views/watchlist.html'
        })

        .otherwise({ redirectTo: '/login' });
}])

.run(['$rootScope', '$location', '$cookieStore', '$http',function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            var restrictedPage = $.inArray($location.path(), ['/login', '/register', '/']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }]);
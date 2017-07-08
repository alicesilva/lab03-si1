
    'use strict';
    
    app.controller('LoginController', ['$scope','$rootScope','$location', 'AuthenticationService', 'FlashService', function($scope,$rootScope, $location, AuthenticationService, FlashService) {
    
        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        $rootScope.serieName = "olá";
        $scope.login = function() {
            $scope.dataLoading = true;
            AuthenticationService.Login($scope.username, $scope.password, function (response) {
                if (response.success) {
                    AuthenticationService.SetCredentials($scope.username, $scope.password);
                    $location.path('/');
                } else {
                    FlashService.Error(response.message);
                    $scope.dataLoading = false;
                }
            });
        };
    }]);

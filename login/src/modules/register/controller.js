
    'use strict';
    app.controller('ControllerRegister', ['$scope','UserService', '$location', '$rootScope', 'FlashService', function($scope,UserService, $location, $rootScope, FlashService) {

        $scope.register = function(){
            $scope.dataLoading = true;
            UserService.Create($scope.user)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Registration successful', true);
                        $location.path('/login');
                    } else {
                        FlashService.Error(response.message);
                        $scope.dataLoading = false;
                    }
                });
        }
    }]);
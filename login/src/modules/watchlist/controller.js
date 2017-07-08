app.controller("ControllerWatchlist", ['$scope', '$rootScope','$location', function($scope, $rootScope, $location){
    $scope.seriesWatchlist = $rootScope.watchlistSeries;

    function Serie(name,year,img){
        this.name = name;
        this.year = year;
        this.img = img;
    };
    
    $scope.seriesProfileWatchlist = [];
    $scope.addSerieToProfile = function(name, year, image){
        var serie = new Serie(name, year, image);
        var j = 0;
        for(var k = 0; k < $scope.seriesProfileWatchlist.length; k++){
            if($scope.seriesProfileWatchlist[k]["name"] == name && $scope.seriesProfileWatchlist[k]["img"] == image){
                j ++;
            }
        }
        if(j == 0){    
            $scope.seriesProfileWatchlist.push(serie);
            $scope.remove(name)
        }else{
            alert("Series has already been added to the profile, it is impossible to add it again.");
        }


    };

    $scope.remove = function(serieName){
        for(var k = 0; k < $rootScope.watchlistSeries.length; k++){
            if($rootScope.watchlistSeries[k]["name"] == name){
                $rootScope.watchlistSeries.splice(k, 1);
            }
        }
    }

    $scope.backToHomePage = function(){
        $rootScope.seriesProfileWatchlist = $scope.seriesProfileWatchlist;
        $location.path('/');
    };
}]);
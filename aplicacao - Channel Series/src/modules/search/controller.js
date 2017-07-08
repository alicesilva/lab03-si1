app.controller('ControllerSearch', ['$scope', '$rootScope', '$location', '$http',function($scope, $rootScope, $location,  $http){
    $scope.nameSerie = $rootScope.nameSerieSearch;
    
    $http.get('https://omdbapi.com/?s='+$scope.nameSerie+'&type=series&r=json&apikey=93330d3c',{
        headers: {
                'Authorization': undefined
            }})
        .success(function (data) {
            if(data.Response == "False"){
                alert("Serie not found!");
            }else{
                $scope.series = data;
            }
    });
    
    function Serie(name,year,img){
        this.name = name;
        this.year = year;
        this.img = img;
    };
    
    $scope.seriesProfile = [];
    $scope.seriesWatchlist = [];
    $scope.addSerieToProfile = function(name, year, image){
        var serie = new Serie(name, year, image);
        var j = 0;
        var i = 0;
        for(var k = 0; k < $scope.seriesProfile.length; k++){
                if($scope.seriesProfile[k]["name"] == name && $scope.seriesProfile[k]["img"] == image){
                        j ++;
                    }
        }

        for(var k = 0; k < $scope.seriesWatchlist.length; k++){
                if($scope.seriesWatchlist[k]["name"] == name && $scope.seriesWatchlist[k]["img"] == image){
                        i ++;
                    }
        }

        if(j == 0 && i == 0){
            $scope.seriesProfile.push(serie);
        }else if(j !== 0){
            alert("Series has already been added to the profile, it is impossible to add it again.");
        }else{
            alert("Series has already been added to watchlist, it is impossible to add it again.");

        }
    };

    $scope.addSerieToWatchlist = function(name, year, image){
        var serie = new Serie(name, year, image);
        var j = 0;
        var i = 0;
        for(var k = 0; k < $scope.seriesWatchlist.length; k++){
            if($scope.seriesWatchlist[k]["name"] == name && $scope.seriesWatchlist[k]["img"] == image){
                j ++;
            }
        }

        for(var k = 0; k < $scope.seriesProfile.length; k++){
            if($scope.seriesProfile[k]["name"] == name && $scope.seriesProfile[k]["img"] == image){
                i ++;
            }
        }

        if(j == 0 && i == 0){
            $scope.seriesWatchlist.push(serie);
        }else if(j !== 0){
            alert("Series has already been added to watchlist, it is impossible to add it again.");
        }else{
            alert("Series has already been added to the profile, it is impossible to add it again.");
        }
    };
    $scope.backToHomePage = function(){
        $rootScope.profileSeries = $scope.seriesProfile;
        $rootScope.watchlistSeries = $scope.seriesWatchlist;
        $location.path('/');
    };
}]);
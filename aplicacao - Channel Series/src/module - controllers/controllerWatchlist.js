app.controller('ControllerWatchlist', function($scope, $http){
    $scope.seriesWatchlist = [];
    $scope.seriesProfile = [];
    $scope.init = function(){
        $scope.seriesWatchlist = JSON.parse(window.sessionStorage.getItem('watchlistSeries'));
        $scope.seriesProfile = JSON.parse(window.sessionStorage.getItem('profileSeries'));
        window.sessionStorage.removeItem('watchlistSeries');
        window.sessionStorage.removeItem('profileSeries');
    };
    $scope.init();

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
        for(var k = 0; k < $scope.seriesWatchlist.length; k++){
            if($scope.seriesWatchlist[k]["name"] == name){
                $scope.seriesWatchlist.splice(k, 1);
            }
        }
    }

    $scope.backToHomePage = function(){
        window.sessionStorage.setItem('seriesProfileWatchlist', JSON.stringify($scope.seriesProfileWatchlist));
        window.sessionStorage.setItem('seriesProfile', JSON.stringify($scope.seriesProfile));

        window.location.href='index.html'
    };
});
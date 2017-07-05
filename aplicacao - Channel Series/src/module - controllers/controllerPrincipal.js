app.controller('ControllerMain', function($scope, $http){
    $scope.serieName = "";
    $scope.search = function(){
        window.sessionStorage.setItem('serieName', JSON.stringify($scope.serieName));
        window.location.href='search.html'
    };
    
    $scope.seriesProfile = [];
    $scope.seriesProfileWatchlist = [];
    $scope.seriesWatchlist = [];
    $scope.init = function(){
        $scope.seriesProfile = JSON.parse(window.sessionStorage.getItem('seriesProfile'));
        $scope.seriesWatchlist = JSON.parse(window.sessionStorage.getItem('seriesWatchlist'));
        $scope.seriesProfileWatchlist = JSON.parse(window.sessionStorage.getItem('seriesProfileWatchlist'));
        
        window.sessionStorage.removeItem('seriesProfile');
        window.sessionStorage.removeItem('seriesWatchlist');
        window.sessionStorage.removeItem('seriesProfileWatchlist');
    };
    
    $scope.init();
    
    $scope.clickerChecker = false;
    $scope.showSeriesInformation = function(serieName){
        $http.get('https://omdbapi.com/?t='+serieName+'&type=series&r=json&apikey=93330d3c')
            .success(function (data) {
                $scope.series = data;
        });
        
        $scope.clickerChecker = true;
    };

    $scope.evaluationNote = 0;
    $scope.addEvaluationNote = function(serieName){
        for(var k = 0; k < $scope.seriesProfile.length; k++){
            if($scope.seriesProfile[k]["name"] == serieName){
                $scope.seriesProfile[k]["evaluationNote"] = $scope.evaluationNote;
            }
        }

        for(var k = 0; k < $scope.seriesProfileWatchlist.length; k++){
            if($scope.seriesProfileWatchlist[k]["name"] == serieName){
                $scope.seriesProfileWatchlist[k]["evaluationNote"] = $scope.evaluationNote;
            }
        }
        $scope.evaluationNote = 0;
    };
    
    $scope.showEvaluationNote = function(serieName){
        for(var k = 0; k < $scope.seriesProfile.length; k++){
            if($scope.seriesProfile[k]["name"] == serieName){
                return $scope.seriesProfile[k]["evaluationNote"];
            }
        }

        for(var k = 0; k < $scope.seriesProfileWatchlist.length; k++){
            if($scope.seriesProfileWatchlist[k]["name"] == serieName){
                return $scope.seriesProfileWatchlist[k]["evaluationNote"];
            }
        }
    };
    
    $scope.chapter = 0;
    $scope.addChapter = function(serieName){
        for(var k = 0; k < $scope.seriesProfile.length; k++){
            if($scope.seriesProfile[k]["name"] == serieName){
                $scope.seriesProfile[k]["chapter"] = $scope.chapter;
            }
        }
        
        for(var k = 0; k < $scope.seriesProfileWatchlist.length; k++){
            if($scope.seriesProfileWatchlist[k]["name"] == serieName){
                $scope.seriesProfileWatchlist[k]["chapter"] = $scope.chapter;
            }
        }
        $scope.chapter = 0;
    };
    
    $scope.showChapter = function(serieName){
        for(var k = 0; k < $scope.seriesProfile.length; k++){
            if($scope.seriesProfile[k]["name"] == serieName){
                return $scope.seriesProfile[k]["chapter"];
            }
        }

        for(var k = 0; k < $scope.seriesProfileWatchlist.length; k++){
            if($scope.seriesProfileWatchlist[k]["name"] == serieName){
                return $scope.seriesProfileWatchlist[k]["chapter"];
            }
        }
    };
    $scope.removeProfileSeries = function(serieName){
        decisao = confirm("Do you really want to remove the profile series? Click Ok if your answer is yes!")
        if(decisao){
            for(var k = 0; k < $scope.seriesProfile.length; k++){
                if($scope.seriesProfile[k]["name"] == serieName){
                    $scope.seriesProfile.splice(k, 1);
                }
            }
            
            $scope.clickerChecker = false;
            for(var k = 0; k < $scope.seriesProfileWatchlist.length; k++){
                if($scope.seriesProfileWatchlist[k]["name"] == serieName){
                    $scope.seriesProfileWatchlist.splice(k, 1);
                }
            }
            $scope.clickerChecker = false;
        }
    };
    
    $scope.redirectToWatchlist = function(){
        window.sessionStorage.setItem('watchlistSeries', JSON.stringify($scope.seriesWatchlist));
        window.sessionStorage.setItem('profileSeries', JSON.stringify($scope.seriesProfile));
        window.location.href='watchlist.html'
    };
});
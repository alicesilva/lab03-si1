'use strict';

app.controller('HomeController',['$scope', '$rootScope', '$http', '$location',function ($scope, $rootScope, $http, $location) {
    
    $scope.serieName = "";
    $scope.search = function(){
        $rootScope.nameSerieSearch = $scope.serieName;
        $location.path('/search');
    };
    
    $scope.seriesProfile = $rootScope.profileSeries ;
    $scope.seriesProfileWatchlist = $rootScope.seriesProfileWatchlist;
    
    $scope.clickerChecker = false;
    $scope.showSeriesInformation = function(serieName){
        $http.get('https://omdbapi.com/?t='+serieName+'&type=series&r=json&apikey=93330d3c',{
        headers: {
                'Authorization': undefined
            }})
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
        var decisao = confirm("Do you really want to remove the profile series? Click Ok if your answer is yes!")
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
         $location.path('/watchlist');
    };
}]);
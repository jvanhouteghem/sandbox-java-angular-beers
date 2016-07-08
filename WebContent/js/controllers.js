'use strict';

/* Controllers */

angular
  .module('BeerControllers', [])
  .controller('BeerListCtrl', ['$scope', '$http','$location', function($scope, $http, $location) {
    $scope.orderProp = 'alcohol';
    
    $scope.loadBeerList = function(){
    	console.log("loadBeerList");
        $http.get('BeerList').success(function(data) {
            $scope.beers = data;
          });
    }
	  
	  $scope.deleteBeer = function(input) {
		  	console.log("go" + input)
		  	$http({
				  method: 'POST',
				  url: 'BeerDelete',
				  data: {
					  'name': input
					  }
				  })
				  .success(function (data){
					  $scope.loadBeerList();
				  });
		    // $location/path(beermachin)
		  }
	  
	  $scope.loadBeerList();
	  
  }])
  .controller('BeerDetailCtrl', ['$scope', '$routeParams', '$http', function($scope, $routeParams, $http) {
	  $http.get('BeerDetail?id=' + $routeParams.beerId).success(function(data){
    //$http.get('beers/' + $routeParams.beerId + '.json').success(function(data) {
      $scope.beer = data;
    });
  }])
  // Appel de BeerAdd
  /*.controller('BeerAddCtrl', ['$scope', '$routeParams', '$http', function($scope, $routeParams, $http) {
	  $http.get('BeerAdd').success(function(data){
    //$http.get('beers/' + $routeParams.beerId + '.json').success(function(data) {
      //$scope.beer = data;
    });
  }])*/
  .controller('BeerAddCtrl',  ['$scope', '$http', "$location", function($scope, $http, $location) {
  $scope.submit = function() {
	  /*$http.post('BeerAdd?id=' + $routeParams.beerId ).success(function(data){
		  console.log("submit");
		  $scope.beer = data;
	  });*/
	  console.log("submit " + $scope.name);
	  $http({
		  method: 'POST',
		  url: 'BeerAdd?id="AffligemBlond',
		  data: {
			  'name': $scope.name,
			  'alcohol': $scope.alcohol,
			  'description': $scope.description,
			  'id': $scope.id,
			  'img': $scope.img,
			  'availability': $scope.availability,
			  'bewery': $scope.bewery,
			  'label': $scope.label,
			  'serving': $scope.serving,
			  'style': $scope.style
			  }
		  })
		  .success(function (data){ $location.path("/beers")});
	}
}
	
	
  ]);

/*'alcohol': $scope.alcohol,
'description': $scope.description,
'id': $scope.id,
'img': $scope.img,
'availability': $scope.availability,
'bewery': $scope.bewery,
'label': $scope.label,
'serving': $scope.serving,
'style': $scope.style*/
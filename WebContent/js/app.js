'use strict';

/* App Module */

var angularBeer = angular.module('AngularBeer', [
  'ngRoute',
  'BeerControllers',
  'BeerFilters'
]);

angularBeer.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/beers', {
        templateUrl: 'partials/beer-list.html',
        controller: 'BeerListCtrl'
      }).
      when('/beers/add/', {
          templateUrl: 'partials/beer-add.html',
          controller: 'BeerAddCtrl'
        }).
        when('/beers/update/', {
            templateUrl: 'partials/beer-update.html',
            controller: 'BeerUpdateCtrl'
          }).
      when('/beers/:beerId', {
        templateUrl: 'partials/beer-detail.html',
        controller: 'BeerDetailCtrl'
      }).
      otherwise({
        redirectTo: '/beers'
      });
  }]);
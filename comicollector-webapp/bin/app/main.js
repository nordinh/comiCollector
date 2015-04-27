var app = angular.module('comiCollectorApp', ['ngRoute','ngMaterial']);

app.controller('AppCtrl', ['$scope', '$mdSidenav', '$mdDialog', '$location', function($scope, $mdSidenav, $mdDialog, $location){
  $scope.toggleSidenav = function(menuId) {
    $mdSidenav(menuId).toggle();
  };
  $scope.navigateTo = function(route) {
	  $location.path(route);
  };
  $scope.isActive = function(route) {
      return route === $location.path();
  }
  $scope.doSomething = function() {
	    $mdDialog.show(
	      $mdDialog.alert()
	        .title('Doing something')
	        .content('busy...')
	        .ariaLabel('demo demo demo')
	        .ok('Neat!')
	        .targetEvent(event)
	    );
	  };
 
}]);

app.config(['$routeProvider', function($routeProvider) {
  $routeProvider.
    when('/home', {
      templateUrl: 'partials/home.html',
      controller: 'HomeCtrl'
    }).
    when('/collection', {
    	templateUrl: 'partials/collection.html',
    	controller: 'CollectionCtrl'
    }).
    when('/wishlist', {
    	templateUrl: 'partials/wishlist.html',
    	controller: 'WishlistCtrl'
    }).
    when('/people', {
    	templateUrl: 'partials/people.html',
    	controller: 'PeopleCtrl'
    }).
    when('/faq', {
    	templateUrl: 'partials/faq.html',
    	controller: 'FaqCtrl'
    }).
    when('/settings', {
    	templateUrl: 'partials/settings.html',
    	controller: 'SettingsCtrl'
    }).
    otherwise({
      redirectTo: '/home'
    });
}]);

app.controller('HomeCtrl', ['$scope', function($scope){
	 
}]);

app.controller('CollectionCtrl', ['$scope', function($scope){
	 
}]);

app.controller('WishlistCtrl', ['$scope', function($scope){
	
}]);

app.controller('PeopleCtrl', ['$scope', function($scope){
	
}]);

app.controller('FaqCtrl', ['$scope', function($scope){
	
}]);

app.controller('SettingsCtrl', ['$scope', function($scope){
	
}]);
angular.module('app', [ 'ngRoute' ])
  .config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'views/admin/home.html',
		controller : 'home'
	}).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

  });

angular.module('app').controller('home', function($rootScope, $scope, $http, $location) {
	
});
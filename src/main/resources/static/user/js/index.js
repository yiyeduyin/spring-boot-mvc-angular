var app = angular.module("frontApp", []);
app.controller('index',function($rootScope, $scope, $http, $location) {
    
	/*获取登录用户名*/
	$http.get('/resource', {}).success(function(data) {
		console.log(data);
    }).error(function() {
        $rootScope.authenticated = false;
    });
    
});


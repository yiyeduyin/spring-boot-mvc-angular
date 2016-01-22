angular.module('app', ['ngRoute']).config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'admin/views/home.html',
        controller: 'home'
    }).when('/login', {
        templateUrl: 'login.html',
        controller: 'navigation'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});
angular.module('app').controller('home',function($rootScope, $scope, $http, $location, $route) {
    
	/*获取登录用户名*/
	$http.get('/admin/rest/adminUser', {}).success(function(data) {
        if (data && data.name) {
            $rootScope.authenticated = true;
            $rootScope.adminName = data.name;
        } else {
            $rootScope.authenticated = false;
        }
    }).error(function() {
        $rootScope.authenticated = false;
    });
	
	
	
	$http.post('/admin/rest/product/create', {
		name:"测试",
		description:"测试一下",
		icon:"url",
		pictures:"url",
		salePrice:300,
		realPrice:500,
		productStock:55,
		isNew:1
	}).success(function() {
		console.log("create success");
    }).error(function(data) {
        console.log("create failed");
    });
	
	
    
});

angular.module('app').controller('navigation',function($rootScope, $scope, $http, $location, $route) {

        $scope.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticate = function(credentials, callback) {

            var headers = credentials ? {
                authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('user', {
                headers: headers
            }).success(function(data) {
                if (data.name) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback($rootScope.authenticated);
            }).error(function() {
                $rootScope.authenticated = false;
                callback && callback(false);
            });

        }

        authenticate();

        $scope.credentials = {};
        $scope.login = function() {
            authenticate($scope.credentials, function(authenticated) {
                if (authenticated) {
                    console.log("Login succeeded")
                    $location.path("/");
                    $scope.error = false;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed")
                    $location.path("/login");
                    $scope.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };

        $scope.logout = function() {
            $http.post('logout', {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function(data) {
                console.log("Logout failed")
                $rootScope.authenticated = false;
            });
        }

});

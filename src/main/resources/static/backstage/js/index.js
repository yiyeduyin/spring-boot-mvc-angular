angular.module('app', ['ngRoute']).config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'backstage/views/home.html',
        controller: 'home'
    })
    .when('/productType/list', {
         templateUrl: 'backstage/views/productTypeList.html',
         controller: 'ProductTypeListCtrl'
    })
    .when('/productType/add', {
         templateUrl: 'backstage/views/productTypeEdit.html',
         controller: 'ProductTypeEditCtrl'
    })
    .when('/productType/edit/:id', {
         templateUrl: 'backstage/views/productTypeEdit.html',
         controller: 'ProductTypeEditCtrl'
    })
    .when('/message/list', {
         templateUrl: 'backstage/views/messageList.html',
         controller: 'MessageListCtrl'
    })
    .when('/test', {
        templateUrl: 'backstage/views/test.html',
        controller: 'test'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});

angular.module('app').controller('initCtrl',function($rootScope, $scope, $http, $location) {
	$scope.initUsername = function(){
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
	}
	$scope.initUsername();
});

angular.module('app').controller('home',function($rootScope, $scope, $http, $location) {


});

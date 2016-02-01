'use strict'
angular.module('app', ['ngRoute', 'ngResource', 'ngFileUpload']).config(function($routeProvider) {

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
        .when('/message/edit/:id', {
            templateUrl: 'backstage/views/messageEdit.html',
            controller: 'MessageEditCtrl'
        })
        .when('/product/list', {
            templateUrl: 'backstage/views/productList.html',
            controller: 'ProductListCtrl'
        })
        .when('/product/add', {
            templateUrl: 'backstage/views/productEdit.html',
            controller: 'ProductEditCtrl'
        })
        .when('/product/edit/:id', {
            templateUrl: 'backstage/views/productEdit.html',
            controller: 'ProductEditCtrl'
        })
        .when('/admin/edit', {
            templateUrl: 'backstage/views/adminEdit.html',
            controller: 'AdminEditCtrl'
        })
        .when('/test', {
            templateUrl: 'backstage/views/test.html',
            controller: 'test'
        }).otherwise('/');

});

angular.module('app').controller('initCtrl', function($rootScope, $scope, $http, $location) {
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

angular.module('app').controller('home', function($rootScope, $scope, $http, $location) {


});

'use strict'
angular.module('app', ['ngRoute', 'ngResource', 'ngFileUpload']).config(function($routeProvider) {

    $routeProvider.when('/', {
            templateUrl: 'backstage/views/home.html',
            controller: 'home'
        })
        //产品类型
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
        //产品子类型
        .when('/subProductType/list/:pid', {
            templateUrl: 'backstage/views/subProductTypeList.html',
            controller: 'SubProductTypeListCtrl'
        })
        .when('/subProductType/:pid/add', {
            templateUrl: 'backstage/views/subProductTypeEdit.html',
            controller: 'SubProductTypeEditCtrl'
        })
        .when('/subProductType/:pid/edit/:id', {
            templateUrl: 'backstage/views/subProductTypeEdit.html',
            controller: 'SubProductTypeEditCtrl'
        })
        //消息
        .when('/message/list', {
            templateUrl: 'backstage/views/messageList.html',
            controller: 'MessageListCtrl'
        })
        .when('/message/edit/:id', {
            templateUrl: 'backstage/views/messageEdit.html',
            controller: 'MessageEditCtrl'
        })
        //产品
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
        //资质管理
         .when('/certificates/list', {
            templateUrl: 'backstage/views/certificatesList.html',
            controller: 'CertificatesListCtrl'
        })
        .when('/certificates/add', {
            templateUrl: 'backstage/views/certificatesEdit.html',
            controller: 'CertificatesEditCtrl'
        })
        .when('/certificates/edit/:id', {
            templateUrl: 'backstage/views/certificatesEdit.html',
            controller: 'CertificatesEditCtrl'
        })
         //工程技术
         .when('/engineerings/list', {
            templateUrl: 'backstage/views/engineeringsList.html',
            controller: 'EngineeringsListCtrl'
        })
        .when('/engineerings/add', {
            templateUrl: 'backstage/views/engineeringsEdit.html',
            controller: 'EngineeringsEditCtrl'
        })
        .when('/engineerings/edit/:id', {
            templateUrl: 'backstage/views/engineeringsEdit.html',
            controller: 'EngineeringsEditCtrl'
        })
        //管理员
        .when('/admin/edit', {
            templateUrl: 'backstage/views/adminEdit.html',
            controller: 'AdminEditCtrl'
        })
        //公司简介
        .when('/profile/detail', {
            templateUrl: 'backstage/views/profile.html',
            controller: 'ProfileCtrl'
        })
        //联系我们
        .when('/contact/list', {
            templateUrl: 'backstage/views/contactList.html',
            controller: 'ContactsListCtrl'
        })
        .when('/contact/add', {
            templateUrl: 'backstage/views/contactEdit.html',
            controller: 'ContactEditCtrl'
        })
        .when('/contact/edit/:id', {
            templateUrl: 'backstage/views/contactEdit.html',
            controller: 'ContactEditCtrl'
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

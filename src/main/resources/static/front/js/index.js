var app = angular.module("frontApp", []);

//获取产品类别
app.service('getProductTypeList', function($rootScope, $http) {
    if(!$rootScope.productTypeList){
		$http.get('/rest/productType/list', {}).success(function(res) {
            if (res.errcode == 0) {
                $rootScope.productTypeList = res.data;
            }
        });
	}
});


app.controller('index', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

app.controller('about', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

app.controller('certificates', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

app.controller('engineerings', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

app.controller('products', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

app.controller('profile', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

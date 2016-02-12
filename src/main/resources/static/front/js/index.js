var app = angular.module("frontApp", []);

//获取产品类别
app.service('getProductTypeList', function($rootScope, $http) {
    if (!$rootScope.leftProductTypeList) {
        $http.get('/rest/productType/list', {
            params: {
                pageNo: 1,
                pageSize: 999,
                type: 0
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $rootScope.leftProductTypeList = res.data.content;
            }
        });
    }
});


app.controller('index', function($rootScope, $scope, $http, $location, $window, getProductTypeList) {
	//获取新产品
    $scope.getProducts = function() {
        $http.get('/rest/product/list', {
            params: {
                pageNo: 1,
                pageSize: 8,
                isNew: 1
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.productList = res.data.content;
            }
        });
    }
    $scope.getProducts();

    //打开文件
    $scope.openProductFile = function(fileName){
    	$window.open('/rest/file/'+fileName);
    }

});

app.controller('about', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

app.controller('certificates', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

app.controller('engineerings', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

app.controller('products', function($rootScope, $scope, $http, $location, $window, getProductTypeList) {
    //获取URL参数
    function parse(val) {
        var result = "",
            tmp = [];
        location.search.substr(1).split("&").forEach(function(item) {
            tmp = item.split("=");
            if (tmp[0] === val) result = decodeURIComponent(tmp[1]);
        });
        return result;
    }
    var type = parse("type");

    $scope.total = 0;
    $scope.productList = [];
    $scope.productTypeList  = [];

    //获取产品子类型
    $scope.getSubProductType = function(){
    	$http.get('/rest/productType/list', {
            params: {
                pageNo: 1,
                pageSize: 999,
                type: 1,
                parentProductTypeId : type
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.productTypeList = res.data.content;
                $scope.total = res.data.totalElements;
                if($scope.total == 0){
                	//获取产品
                	$scope.getProducts();
                }
            }
        });
    }

    //获取产品
    $scope.getProducts = function() {
        $http.get('/rest/product/list', {
            params: {
                pageNo: $scope.pageNo,
                pageSize: $scope.pageSize
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.productList = res.data.content;
                console.log($scope.productList);
                $scope.total = res.data.totalElements;
                $scope.lastPageNo = parseInt($scope.total / $scope.pageSize);
                if (($scope.total % $scope.pageSize) > 0) {
                    $scope.lastPageNo += 1;
                }
            }
        });
    }

    $scope.getSubProductType();

    //打开文件
    $scope.openProductFile = function(fileName){
    	$window.open('/rest/file/'+fileName);
    }
});

app.controller('profile', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

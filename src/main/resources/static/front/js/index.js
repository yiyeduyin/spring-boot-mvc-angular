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
    $scope.certificatesList = [];
    $scope.findList = function() {
        $http.get('/rest/certificates/list', {}).success(function(res) {
            if (res.errcode == 0) {
                $scope.certificatesList = res.data.content;
                console.log($scope.certificatesList);
            }
        });
    }
    $scope.findList();

});

app.controller('engineerings', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

app.controller('productType', function($rootScope, $scope, $http, $location, $window, getProductTypeList) {
     $http.get('/rest/productType/list', {
        params: {
            pageNo: 1,
            pageSize: 999,
            type: 0
        }
    }).success(function(res) {
        if (res.errcode == 0) {
            $scope.productTypeList = res.data.content;
        }
    });

    $scope.goToProductPage = function(id){
        window.location.href = "/products?type=" + id;
    }
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
    var subType = parse("subType");

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
                pageSize: $scope.pageSize,
                productType: type,
                subProductType: subType
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.productList = res.data.content;
                $scope.total = res.data.totalElements;
                $scope.lastPageNo = parseInt($scope.total / $scope.pageSize);
                if (($scope.total % $scope.pageSize) > 0) {
                    $scope.lastPageNo += 1;
                }
            }
        });
    }



    //获取产品类型
    $scope.getProductType = function() {
        var tid = type? type : subType;
        $http.get('/rest/productType/' + tid, {}).success(function(res) {
            if (res.errcode == 0 && res.data) {
                $scope.productType = res.data;
            }
        });
    }

    $scope.init = function(){
        if(type){
            $scope.getSubProductType();
        }else if(subType){
            $scope.getProducts();
        }
        
        $scope.getProductType();
    }
    $scope.init();

    //获取产品
    // $scope.getSubProducts = function() {
    //     $http.get('/rest/product/list', {
    //         params: {
    //             pageNo: $scope.pageNo,
    //             pageSize: $scope.pageSize,
    //             subProductType: subType
    //         }
    //     }).success(function(res) {
    //         if (res.errcode == 0) {
    //             $scope.productList = res.data.content;
    //             $scope.total = res.data.totalElements;
    //             $scope.lastPageNo = parseInt($scope.total / $scope.pageSize);
    //             if (($scope.total % $scope.pageSize) > 0) {
    //                 $scope.lastPageNo += 1;
    //             }
    //         }
    //     });
    // }

    $scope.goToSubProductPage = function(id){
        window.location.href = "/products?subType=" + id;
    }

    //打开文件
    $scope.openProductFile = function(fileName){
        $window.open('/rest/file/'+fileName);
    }
});

app.controller('profile', function($rootScope, $scope, $http, $location, getProductTypeList) {


});

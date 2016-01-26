// 商品类型列表
angular.module('app').controller('ProductListCtrl', function($rootScope, $scope, $http, $location) {
    $scope.lastPageNo = 1;
    $scope.pageNo = 1;
    $scope.pageSize = 10;
    $scope.status = "";
    $scope.name = "";

    $scope.total = 0;
    $scope.productList = [];

    $scope.findList = function() {
        $http.get('/admin/rest/product/list', {
            params: {
                pageNo: $scope.pageNo,
                pageSize: $scope.pageSize,
                status: $scope.status,
                name: $scope.name
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.productList = res.data.content;
                $scope.total = res.data.totalElements;
                $scope.lastPageNo = parseInt($scope.total  / $scope.pageSize);
                if(($scope.total  % $scope.pageSize) > 0){
                    $scope.lastPageNo+=1;
                }
            }
        });
    }
    $scope.findList();

    //删除
    $scope.delete = function(id) {
        $http.delete('/admin/rest/product/' + id, {}).success(function(res) {
            if (res.errcode == 0) {
                $scope.findList();
            }
        });
    }

    //更新状态
    $scope.updateStatus = function(id, status) {
        $http.put('/admin/rest/product/' + id, {
            status: status
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.findList();
            }
        });
    }

    //跳转到编辑页
    $scope.goToEdit = function(id) {
        $location.path("/product/edit/" + id);
    }

    //更改页码
    $scope.changePageNo = function(pageNo) {
        $scope.pageNo = pageNo;
        $scope.findList();
    }
});

// 商品类型编辑
angular.module('app').controller('ProductEditCtrl', function($rootScope, $scope, $http, $routeParams, $location) {
    var id = $routeParams.id;
    $scope.product = {};

    $scope.productTypeList = [];


    $scope.getProductType = function () {
        $http.get('/admin/rest/productType/list', {
            params: {
                pageNo: 1,
                pageSize: 9999
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.productTypeList = res.data.content;
            }
        });
    }

    $scope.init = function() {
        $scope.getProductType();
        if (id) {
            $http.get('/admin/rest/product/' + id, {}).success(function(res) {
                if (res.errcode == 0) {
                    $scope.product = res.data;
                }
            });
        }
    }
    $scope.init();


    $scope.submit = function() {
        if (id) { //更新
            $http.put('/admin/rest/productType/' + id, {
                name: $scope.name
            }).success(function(res) {
                if (res.errcode == 0) {
                    $location.path("productType/list");
                }
            });
        } else { //创建
            $http.post('/admin/rest/productType', {
                name: $scope.name
            }).success(function(res) {
                if (res.errcode == 0) {
                    $location.path("productType/list");
                }
            });
        }
    }

});

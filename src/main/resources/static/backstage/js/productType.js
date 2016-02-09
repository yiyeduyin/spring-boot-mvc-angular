// 商品类型列表
angular.module('app').controller('ProductTypeListCtrl', function($rootScope, $scope, $http, $location) {
    $scope.lastPageNo = 1;
    $scope.pageNo = 1;
    $scope.pageSize = 10;
    $scope.status = "";
    $scope.name = "";

    $scope.total = 0;
    $scope.productTypeList = [];

    $scope.findList = function() {
        $http.get('/admin/rest/productType/list', {
            params: {
                pageNo: $scope.pageNo,
                pageSize: $scope.pageSize,
                status: $scope.status,
                name: $scope.name
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.productTypeList = res.data.content;
                $scope.total = res.data.totalElements;
                $scope.lastPageNo = parseInt($scope.total / $scope.pageSize);
                if (($scope.total % $scope.pageSize) > 0) {
                    $scope.lastPageNo += 1;
                }
            }
        });
    }
    $scope.findList();

    //删除
    $scope.delete = function(id) {
        bootbox.dialog({
            message: "确认删除该纪录吗？",
            title: "操作提示",
            buttons: {
                cancel: {
                    label: "取消",
                    className: "btn",
                    callback: function() {}
                },
                ok: {
                    label: "确定",
                    className: "btn-primary",
                    callback: function() {
                        $http.delete('/admin/rest/productType/' + id, {}).success(function(res) {
                            if (res.errcode == 0) {
                                $scope.findList();
                            }
                        });
                    }
                }
            }
        });
    }

    //更新状态
    $scope.updateStatus = function(id, status) {
        $http.put('/admin/rest/productType/' + id, {
            status: status
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.findList();
            }
        });
    }

    $scope.goToEdit = function(id) {
        $location.path("/productType/edit/" + id);
    }

    $scope.changePageNo = function(pageNo) {
        $scope.pageNo = pageNo;
        $scope.findList();
    }
});

// 商品类型编辑
angular.module('app').controller('ProductTypeEditCtrl', function($rootScope, $scope, $http, $routeParams, $location) {
    var id = $routeParams.id;
    $scope.name = "";

    $scope.init = function() {
        if (id) {
            $http.get('/admin/rest/productType/' + id, {}).success(function(res) {
                if (res.errcode == 0) {
                    $scope.name = res.data.name;
                }
            });
        }
    }
    $scope.init();

    //跳转到编辑页
    $scope.goBack = function(id) {
        $location.path("/productType/list");
    }


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

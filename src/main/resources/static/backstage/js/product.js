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
                        $http.delete('/admin/rest/product/' + id, {}).success(function(res) {
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
angular.module('app').controller('ProductEditCtrl', function($rootScope, $scope, $http, $routeParams, $location, Upload) {
    var id = $routeParams.id;
    $scope.product = {
        isNew: "0"
    };

    $scope.productTypeList = [];
    $scope.subProductTypeList = [];

    $scope.upload_image_message = "";
    $scope.upload_file_message = "";

    $scope.getProductType = function() {
        $http.get('/admin/rest/productType/list', {
            params: {
                pageNo: 1,
                pageSize: 9999,
                status: 1,
                type: 0
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
                    $scope.product.isNew = $scope.product.isNew + "";
                    if($scope.product.productType){
                        $scope.product.productTypeId = $scope.product.productType.id;
                    }
                    if($scope.product.subProductType){
                        $scope.product.subProductTypeId = $scope.product.subProductType.id;
                    }
                    $scope.selectSubType();
                }
            });
        }
    }
    $scope.init();


    $scope.selectSubType = function(change_flag) {
        if(change_flag){
            $scope.product.subProductTypeId = "";
        }
        
        if ($scope.product.productTypeId) {
            $http.get('/admin/rest/productType/list', {
                params: {
                    pageNo: 1,
                    pageSize: 9999,
                    status: 1,
                    type: 1,
                    parentProductTypeId: $scope.product.productTypeId
                }
            }).success(function(res) {
                if (res.errcode == 0) {
                    $scope.subProductTypeList = res.data.content;
                }
            });
        }
    }
    

    //跳转到编辑页
    $scope.goBack = function() {
        $location.path("/product/list");
    }


    // upload later on form submit or something similar
    $scope.uploadIcon = function() {
        if ($scope.icon) {
            Upload.upload({
                url: '/admin/rest/fileUpload',
                data: {
                    file: $scope.icon
                }
            }).then(function(res) {
                $scope.product.icon = res.data.data;
                $scope.upload_image_message = "上传成功";
            }, function(resp) {
                $scope.upload_image_message = "上传失败";
            }, function(evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                $scope.upload_image_message = "上传中 " + progressPercentage + '%';
            });
        }
    };

    $scope.removeIcon = function() {
        $scope.product.icon = "";
    }

    $scope.removeFile = function() {
        $scope.product.fileName = "";
    }

    $scope.uploadFile = function() {
        if ($scope.file) {
            Upload.upload({
                url: '/admin/rest/fileUpload',
                data: {
                    file: $scope.file
                }
            }).then(function(res) {
                $scope.product.fileName = res.data.data;
                $scope.upload_file_message = "上传成功";
            }, function(res) {
                $scope.upload_file_message = "上传失败";
            }, function(evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                $scope.upload_file_message = "上传中 " + progressPercentage + '%';
            });
        }
    };


    $scope.submit = function() {
        if (id) { //更新
            $scope.product.created = null;
            $http.put('/admin/rest/product/' + id, $scope.product).success(function(res) {
                if (res.errcode == 0) {
                    $location.path("product/list");
                }
            });
        } else { //创建
            $http.post('/admin/rest/product', $scope.product).success(function(res) {
                if (res.errcode == 0) {
                    $location.path("product/list");
                }
            });
        }
    }

});

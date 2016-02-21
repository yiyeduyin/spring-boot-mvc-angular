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
                name: $scope.name,
                type: 0
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


    $scope.goToSubList = function(id){
        $location.path("/subProductType/list/" + id);
    }
});

// 商品类型编辑
angular.module('app').controller('ProductTypeEditCtrl', function($rootScope, $scope, $http, $routeParams, $location, Upload) {
    var id = $routeParams.id;
    $scope.name = "";

    $scope.productType = {
        name : "",
        type : 0
    }

    $scope.init = function() {
        if (id) {
            $http.get('/admin/rest/productType/' + id, {}).success(function(res) {
                if (res.errcode == 0 && res.data) {
                    $scope.productType = res.data;
                    if(res.data.parentProductType){
                        $scope.productType.parentProductTypeId = res.data.parentProductType.id;
                    }
                }
            });
        }
    }
    $scope.init();

    //跳转到编辑页
    $scope.goBack = function() {
        $location.path("/productType/list");
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
                $scope.productType.icon = res.data.data;
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
        $scope.productType.icon = "";
    }


    $scope.submit = function() {
        if (id) { //更新
            $http.put('/admin/rest/productType/' + id, $scope.productType).success(function(res) {
                if (res.errcode == 0) {
                    $scope.goBack();
                }
            });
        } else { //创建
            $http.post('/admin/rest/productType', $scope.productType).success(function(res) {
                if (res.errcode == 0) {
                    $scope.goBack();
                }
            });
        }
    }

});

// 商品子类型列表
angular.module('app').controller('SubProductTypeListCtrl', function($rootScope, $scope, $http, $routeParams, $location) {
    var pid = $scope.pid = Number($routeParams.pid);
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
                name: $scope.name,
                type: 1,
                parentProductTypeId : pid
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

    $scope.goToSubEdit = function(id) {
        $location.path("/subProductType/" + pid + "/edit/" + id);
    }

    $scope.changePageNo = function(pageNo) {
        $scope.pageNo = pageNo;
        $scope.findList();
    }
});

// 商品子类型编辑
angular.module('app').controller('SubProductTypeEditCtrl', function($rootScope, $scope, $http, $routeParams, $location) {
    //父产品类型ID
    var pid = Number($routeParams.pid);
    //产品类型ID
    var id = $routeParams.id;

    $scope.productType = {
        name : "",
        type : 0,
        parentProductTypeId : pid
    }

    //获取产品类型
    $scope.findProductType = function() {
        $http.get('/admin/rest/productType/list', {
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
    }

    $scope.init = function() {
        if (id) {
            $http.get('/admin/rest/productType/' + id, {}).success(function(res) {
                if (res.errcode == 0 && res.data) {
                    $scope.productType.name = res.data.name;
                    if(res.data.parentProductType){
                        $scope.productType.parentProductTypeId = res.data.parentProductType.id;
                    }
                }
            });
        }
        $scope.findProductType();
    }
    $scope.init();

    //跳转到编辑页
    $scope.goBack = function() {
        $location.path("/subProductType/list/" + pid);
    }

    //提交
    $scope.submit = function() {
        if($scope.productType.parentProductTypeId){
            $scope.productType.type = 1;
        }else{
            $scope.productType.type = 0;
        }

        if (id) { //更新
            $http.put('/admin/rest/productType/' + id, $scope.productType).success(function(res) {
                if (res.errcode == 0) {
                    $scope.goBack();
                }
            });
        } else { //创建
            console.log($scope.productType);
            $http.post('/admin/rest/productType', $scope.productType).success(function(res) {
                if (res.errcode == 0) {
                    $scope.goBack();
                }
            });
        }
    }

});


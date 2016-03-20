// 商品类型列表
angular.module('app').controller('CertificatesListCtrl', function($rootScope, $scope, $http, $location) {
    $scope.lastPageNo = 1;
    $scope.pageNo = 1;
    $scope.pageSize = 10;
    $scope.name = "";

    $scope.total = 0;
    $scope.certificatesList = [];

    $scope.findList = function() {
        $http.get('/admin/rest/certificates/list', {
            params: {
                pageNo: $scope.pageNo,
                pageSize: $scope.pageSize,
                name: $scope.name
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.certificatesList = res.data.content;
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
                        $http.delete('/admin/rest/certificates/' + id, {}).success(function(res) {
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
        $http.put('/admin/rest/certificates/' + id, {
            status: status
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.findList();
            }
        });
    }

    //跳转到编辑页
    $scope.goToEdit = function(id) {
        $location.path("/certificates/edit/" + id);
    }

    //更改页码
    $scope.changePageNo = function(pageNo) {
        if(pageNo >= 1 && pageNo <= $scope.lastPageNo){
            $scope.pageNo = pageNo;
            $scope.findList();
        }
    }
});

// 商品类型编辑
angular.module('app').controller('CertificatesEditCtrl', function($rootScope, $scope, $http, $routeParams, $location, Upload) {
    var id = $routeParams.id;
    $scope.certificates = {
        orderd: 0
    };

    $scope.upload_image_message = "";

    $scope.init = function() {
        if (id) {
            $http.get('/admin/rest/certificates/' + id, {}).success(function(res) {
                if (res.errcode == 0) {
                    $scope.certificates = res.data;
                }
            });
        }
    }
    $scope.init();
    

    //跳转到编辑页
    $scope.goBack = function() {
        $location.path("/certificates/list");
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
                $scope.certificates.picture = res.data.data;
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
        $scope.certificates.picture = "";
    }


    $scope.submit = function() {
        if (id) { //更新
            $http.put('/admin/rest/certificates/' + id, $scope.certificates).success(function(res) {
                if (res.errcode == 0) {
                    $location.path("certificates/list");
                }
            });
        } else { //创建
            $http.post('/admin/rest/certificates', $scope.certificates).success(function(res) {
                if (res.errcode == 0) {
                    $location.path("certificates/list");
                }
            });
        }
    }

});

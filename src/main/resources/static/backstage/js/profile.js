// 商品类型列表
angular.module('app').controller('ProfileCtrl', function($rootScope, $scope, $http, $location, Upload) {
    $scope.profile = {};

    $scope.isEdit = false;

    $scope.find = function() {
        $http.get('/admin/rest/profile', {}).success(function(res) {
            if (res.errcode == 0) {
                if(res.data){
                    $scope.profile = res.data;
                }
            }
        });
    }
    $scope.find();

    $scope.edit = function(){
        $scope.isEdit = true;
    }

    $scope.cancleEdit = function(){
        $scope.isEdit = false;
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
                $scope.profile.icon = res.data.data;
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
        $scope.profile.icon = "";
    }


    $scope.submit = function() {
        $http.post('/admin/rest/profile', $scope.profile).success(function(res) {
            if (res.errcode == 0) {
                $scope.isEdit = false;
            }
        });
    }
});


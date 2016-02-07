// 管理员信息编辑
angular.module('app').controller('AdminEditCtrl', function($rootScope, $scope, $http, $routeParams, $location) {

    $scope.username = "";
    $scope.username_2 = "";
    $scope.password = "";

    $scope.usernameErrorMesssage = "";
    $scope.passwordErrorMesssage = "";

    $scope.validation = function(){
        console.log(GlobalToast);

        GlobalToast.tips("保存成功",1000,function(){
        });

        if($scope.username != $scope.username_2){
            $scope.usernameErrorMesssage = "两次密码不同！";
            return false;
        }else{
            $scope.usernameErrorMesssage = "";
            return true;
        }

        if(!$scope.password && $scope.password.length < 6){
            $scope.passwordErrorMesssage = "密码不能为空！"
            return false;
        }
    };

    $scope.updateUsername = function(){
        $http.put('/admin/rest/productType/' + id, {
            name: $scope.name
        }).success(function(res) {
            if (res.errcode == 0) {
                $location.path("productType/list");
            }
        });
    }

    $scope.deleteTitle=function(){
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
                        // var rs2 = $resource(FORUM_REMOTE_URL+'/admin/forum/honorTitle/'+id+'/delete');
                        // rs2.save({
                        //     _id: id
                        // },function(){
                        //     $scope.findList();
                        // });
                    }
                }
            }
        });
    };




    $scope.submit = function() {
        if (id) { //更新
            $http.put('/admin/rest/'+ $rootScope.adminName+'/username', {
                username: $scope.username,
                password: $scope.password
            }).success(function(res) {
                if (res.errcode == 0) {
                    
                }
            });
        } 
    }
});

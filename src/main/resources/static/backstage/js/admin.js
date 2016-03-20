// 管理员信息编辑
angular.module('app').controller('AdminEditCtrl', function($rootScope, $scope, $http, $routeParams, $location) {

    $scope.username = "";
    $scope.username_2 = "";
    $scope.password = "";
    $scope.newPassword = "";
    $scope.newPassword_2 = "";

    $scope.usernameErrorMesssage = "";
    $scope.passwordErrorMesssage = "";

    $scope.step = 1;

    $scope.validation = function() {
        if ($scope.step == 1) {
            if ($scope.username != $scope.username_2) {
                $scope.usernameErrorMesssage = "两次用户名不同！";
                return false;
            } else {
                $scope.usernameErrorMesssage = "";
                return true;
            }

            if (!$scope.password && $scope.password.length < 6) {
                $scope.passwordErrorMesssage = "密码不能为空！"
                return false;
            }
        } else if ($scope.step == 2) {
            if ($scope.newPassword != $scope.newPassword_2) {
                $scope.newPasswordErrorMesssage = "两次密码不同！";
                return false;
            } else {
                $scope.newPasswordErrorMesssage = "";
                return true;
            }

            if (!$scope.password && $scope.password.length < 6) {
                $scope.passwordErrorMesssage = "密码不能为空！"
                return false;
            }
        }
    };

    $scope.submit = function() {
        if ($scope.step == 1) { //更新用户名
            $http.put('/admin/rest/' + $rootScope.adminName + '/username', {
                username: $scope.username,
                password: $scope.password
            }).success(function(res) {
                if (res.errcode == 0) {
                    GlobalToast.tips("保存成功", 1000, function() {
                        $http.post('/logout', {}).success(function(res) {
                            window.location.href = "/login"
                        });
                    });
                }
            });
        } else if ($scope.step == 2) {
            $http.put('/admin/rest/' + $rootScope.adminName + '/password', {
                oldPassword: $scope.password,
                newPassword: $scope.newPassword
            }).success(function(res) {
                if (res.errcode == 0) {
                    GlobalToast.tips("保存成功", 1000, function() {
                        $http.post('/logout', {}).success(function(res) {
                            window.location.href = "/login"
                        });
                    });
                }
            });
        }
    }
});

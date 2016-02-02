// 管理员信息编辑
angular.module('app').controller('AdminEditCtrl', function($rootScope, $scope, $http, $routeParams, $location) {
    // var id = $routeParams.username;

    console.log($rootScope.adminName);


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

// 商品类型列表
angular.module('app').controller('MessageListCtrl', function($rootScope, $scope, $http, $location) {

    $scope.pageNo = 1;
    $scope.pageSize = 10;
    $scope.status = "";
    $scope.username = "";
    $scope.mobile = "";
    $scope.address = "";
    $scope.email = "";
    $scope.content = "";
    $scope.type = "";

    $scope.total = 0;
    $scope.messageList = [];

    $scope.findList = function() {
        $http.get('/admin/rest/message/list', {
            params: {
                pageNo: $scope.pageNo,
                pageSize: $scope.pageSize,
                status: $scope.status,
                username: $scope.username,
                mobile: $scope.mobile,
                address: $scope.address,
                email: $scope.email,
                content: $scope.content,
                type: $scope.type
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.messageList = res.data.content;
                $scope.total = res.data.totalElements;
                angular.forEach($scope.messageList, function(message, i) {
                    if(message.content.length > 20){
                        message.content = message.content.slice(0,20) + "..."; 
                    }
                });
            }
        });
    }
    $scope.findList();

    //删除
    $scope.delete = function(id) {
        $http.delete('/admin/rest/message/' + id, {}).success(function(res) {
            if (res.errcode == 0) {
                $scope.findList();
            }
        });
    }

});

// 商品类型编辑
angular.module('app').controller('MessageCtrlEdit', function($rootScope, $scope, $http, $routeParams, $location) {
    var id = $routeParams.id;
    $scope.name = "";

    $scope.init = function(argument) {
        if (id) {
            $http.get('/admin/rest/productType/' + id, {}).success(function(res) {
                if (res.errcode == 0) {
                    $scope.name = res.data.name;
                }
            });
        }
    }


    $scope.submit = function(argument) {
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

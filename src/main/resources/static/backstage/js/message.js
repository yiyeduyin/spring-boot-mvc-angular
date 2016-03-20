// 商品类型列表
angular.module('app').controller('MessageListCtrl', function($rootScope, $scope, $http, $location) {
    $scope.lastPageNo = 1;
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
                $scope.lastPageNo = parseInt($scope.total / $scope.pageSize);
                if (($scope.total % $scope.pageSize) > 0) {
                    $scope.lastPageNo += 1;
                }
                angular.forEach($scope.messageList, function(message, i) {
                    if (message.content.length > 20) {
                        message.content = message.content.slice(0, 20) + "...";
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

    $scope.goToEdit = function(id) {
        $location.path("/message/edit/" + id);
    }

    $scope.changePageNo = function(pageNo) {
        if(pageNo >= 1 && pageNo <= $scope.lastPageNo){
            $scope.pageNo = pageNo;
            $scope.findList();
        }
    }
});

// 商品类型编辑
angular.module('app').controller('MessageEditCtrl', function($rootScope, $scope, $http, $routeParams, $location) {
    var id = $routeParams.id;
    $scope.message = {};
    $scope.init = function() {
        if (id) {
            $http.get('/admin/rest/message/' + id, {}).success(function(res) {
                if (res.errcode == 0) {
                    $scope.message = res.data;
                }
            });
        }
    }
    $scope.init();
});

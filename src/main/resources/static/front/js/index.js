var app = angular.module("frontApp", []);

//获取产品类别
app.service('getProductTypeList', function($rootScope, $http) {
    if (!$rootScope.leftProductTypeList) {
        $http.get('/rest/productType/list', {
            params: {
                pageNo: 1,
                pageSize: 999,
                type: 0
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $rootScope.leftProductTypeList = res.data.content;
            }
        });
    }

    $rootScope.searchName = "";
    $rootScope.searchProduct = function() {
        window.location.href = "/search?searchName=" + $rootScope.searchName;
    }
});


app.controller('index', function($rootScope, $scope, $http, $location, $window, getProductTypeList) {
    //获取新产品
    $scope.getProducts = function() {
        $http.get('/rest/product/list', {
            params: {
                pageNo: 1,
                pageSize: 8,
                isNew: 1
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.productList = res.data.content;
            }
        });
    }
    $scope.getProducts();

    //打开文件
    $scope.openProductFile = function(fileName) {
        $window.open('/rest/file/' + fileName);
    }

});

app.controller('certificates', function($rootScope, $scope, $http, $location, getProductTypeList) {
    $scope.certificatesList = [];
    $scope.findList = function() {
        $http.get('/rest/certificates/list', {}).success(function(res) {
            if (res.errcode == 0) {
                $scope.certificatesList = res.data.content;
            }
        });
    }
    $scope.findList();
});

app.controller('engineerings', function($rootScope, $scope, $http, $location, getProductTypeList) {
    //显示的工程技术
    $scope.engineerings = {};
    //工程技术列表
    $scope.engineeringsList = [];
    $scope.findList = function() {
        $http.get('/rest/engineerings/list', {}).success(function(res) {
            if (res.errcode == 0) {
                $scope.engineeringsList = res.data.content;
                if ($scope.engineeringsList.length > 0) {
                    $scope.engineerings = $scope.engineeringsList[0];
                }
            }
        });
    }
    $scope.findList();

    $scope.changeShow = function(index) {
        $scope.engineerings = $scope.engineeringsList[index];
    }
});

app.controller('productType', function($rootScope, $scope, $http, $location, $window, getProductTypeList) {
    $http.get('/rest/productType/list', {
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

    $scope.goToProductPage = function(id) {
        window.location.href = "/products?type=" + id;
    }
});

app.controller('products', function($rootScope, $scope, $http, $location, $window, getProductTypeList) {
    //获取URL参数
    function parse(val) {
        var result = "",
            tmp = [];
        location.search.substr(1).split("&").forEach(function(item) {
            tmp = item.split("=");
            if (tmp[0] === val) result = decodeURIComponent(tmp[1]);
        });
        return result;
    }
    var type = parse("type");
    var subType = parse("subType");

    $scope.lastPageNo = 1;
    $scope.pageNo = 1;
    $scope.pageSize = 10;
    $scope.total = 0;
    $scope.productList = [];
    $scope.productTypeList = [];

    //获取产品子类型
    $scope.getSubProductType = function() {
        $http.get('/rest/productType/list', {
            params: {
                pageNo: 1,
                pageSize: 999,
                type: 1,
                parentProductTypeId: type
            }
        }).success(function(res) {
            if (res.errcode == 0) {
                $scope.productTypeList = res.data.content;
                $scope.total = res.data.totalElements;
                if ($scope.total == 0) {
                    //获取产品
                    $scope.getProducts();
                }
            }
        });
    }

    //获取产品
    $scope.getProducts = function() {
        $http.get('/rest/product/list', {
            params: {
                pageNo: $scope.pageNo,
                pageSize: $scope.pageSize,
                productType: type,
                subProductType: subType
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



    //获取产品类型
    $scope.getProductType = function() {
        var tid = type ? type : subType;
        $http.get('/rest/productType/' + tid, {}).success(function(res) {
            if (res.errcode == 0 && res.data) {
                $scope.productType = res.data;
            }
        });
    }

    $scope.init = function() {
        if (type) {
            $scope.getSubProductType();
        } else if (subType) {
            $scope.getProducts();
        }

        $scope.getProductType();
    }
    $scope.init();

    $scope.goToSubProductPage = function(id) {
        window.location.href = "/products?subType=" + id;
    }

    //更改页码
    $scope.changePageNo = function(pageNo) {
        if(pageNo >= 1 && pageNo <= $scope.lastPageNo){
            $scope.pageNo = pageNo;
            $scope.getProducts();
        }
    }

    //打开文件
    $scope.openProductFile = function(fileName) {
        $window.open('/rest/file/' + fileName);
    }
});

app.controller('message', function($rootScope, $scope, $http, $location, getProductTypeList) {
    $scope.message = {
        type: "0"
    };

    function validate() {
        if (!$scope.message.username) {
            tips("请填写姓名", 1000, function() {});
            return false;
        } else if (!$scope.message.mobile) {
            tips("请填写电话", 1000, function() {});
            return false;
        }
        // else if(!$scope.message.address){
        //     tips("请填写地址", 1000, function() {});
        //     return false;
        // }
        // else if(!$scope.message.email){
        //     tips("请填写邮箱", 1000, function() {});
        //     return false;
        // }
        else if (!$scope.message.content) {
            tips("请填写内容", 1000, function() {});
            return false;
        } else if ($scope.message.content.length < 10) {
            tips("内容不能少于10个字", 1000, function() {});
            return false;
        } else {
            return true;
        }
    }

    function tips(msg, timeout, callback) {
        if (msg !== null && msg !== undefined && msg !== "") {
            $("#resultalert").html(msg);
        } else {
            return false;
        }
        timeout = timeout || 3000;
        $('#resultalert').show();
        setTimeout(function() {
            $('#resultalert').hide();
            if (callback) {
                callback();
            }
        }, timeout);
    }

    $scope.submit = function() {
        if (validate()) {
            $http.post('/rest/message', $scope.message).success(function(res) {
                if (res.errcode == 0) {
                    tips("感谢您的留言！请耐心等待管理员的审核和回复！", 3000, function() {
                        window.location.href = "/";
                    });
                }
            });
        }
    }
});

app.controller('search', function($rootScope, $scope, $http, $location, $window, getProductTypeList) {
    //获取URL参数
    function parse(val) {
        var result = "",
            tmp = [];
        location.search.substr(1).split("&").forEach(function(item) {
            tmp = item.split("=");
            if (tmp[0] === val) result = decodeURIComponent(tmp[1]);
        });
        return result;
    }
    var searchName = parse("searchName");

    $scope.lastPageNo = 1;
    $scope.pageNo = 1;
    $scope.pageSize = 10;
    $scope.total = 0;
    $scope.productList = [];

    //获取产品
    $scope.getProducts = function() {
        $http.get('/rest/product/list', {
            params: {
                pageNo: $scope.pageNo,
                pageSize: $scope.pageSize,
                drawingNo: searchName
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

    $scope.getProducts();

    //更改页码
    $scope.changePageNo = function(pageNo) {
        if(pageNo >= 1 && pageNo <= $scope.lastPageNo){
            $scope.pageNo = pageNo;
            $scope.getProducts();
        }
    }

    //打开文件
    $scope.openProductFile = function(fileName) {
        $window.open('/rest/file/' + fileName);
    }
});

app.controller('about', function($rootScope, $scope, $http, $location, getProductTypeList) {
   
});

app.controller('profile', function($rootScope, $scope, $http, $location, getProductTypeList) {
   
});


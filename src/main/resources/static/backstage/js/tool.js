    $scope.deleteTitle = function() {
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

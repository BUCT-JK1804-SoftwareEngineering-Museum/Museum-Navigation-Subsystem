<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <#include "./common/import_head.ftl">
</head>
<body>
<!-- Begin page -->

    <#include "./common/header.ftl">
    <div class="content-page">
        <div class="content">
            <div class="container-fluid" style="height: 100%">
                <div class="page-title-box">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><span class="fa fa-gear"></span></li>
                        <li class="breadcrumb-item">用户管理</li>
                    </ol>
                </div>
                <div class="row" style="height: calc(100% - 75px)">
                    <div class="col-12" style="height:100%">
                        <div class="card-box" style=" height:100%">
                            <div class="container-title">
                                <h5 class="header-title m-t-0 float-left">用户管理列表</h5>
                                <span class="button pull-right float-right">
								    <@shiro.hasPermission name="projectInformation:insert">
                                    <button class="btn btn-primary btn-sm" id="addButton">&nbsp;新增</button>
									</@shiro.hasPermission>
                                </span>
                            </div>
                            <div id="grid" style="background-color: transparent; height: calc(100% - 25px);"></div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div id="detailWindow"></div>
    <#include "./common/footer.ftl">
    <div style="display:none;">
        <input name="photo" id="uploadFile" type="file"/>
    </div>
<script type="text/javascript">
	
	
	$(function () {
        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "user/showAll",
                        dataType: "json",
                        type: "POST"
                    },
                    update: {
                        url: "user/update",
                        dataType: "json",
                        type: "POST",
                        contentType: "application/json"
                    },
                    destroy: {
                        url: "user/delete",
                        dataType: "json",
                        type: "POST",
                        contentType: "application/json"
                    },
                    create: {
                        url: "user/insert",
                        dataType: "json",
                        type: "POST",
                        contentType: "application/json"
                    },
                    parameterMap: function (options, operation) {
                        if (operation == "read") {
                            return options;
                        }
                        return kendo.stringify(options);
                    }
                },
                pageSize: 20,
                schema: {
                    model: {
                        id: "userId",
                        fields: {
                            userId: {type: "number", editable: true, defaultValue: null},
                            userName: {type: "string", editable: true, defaultValue: null},
                            userPassword: {type: "string", editable: true, defaultValue: null},
                            userPhone: {type: "string", editable: true, defaultValue: null},
                            userPic: {type: "string", editable: true, defaultValue: null},
                            userGender: {type: "string", editable: true, defaultValue: null},
                            user_site: {type: "string", editable: true, defaultValue: null},
                            userLevel: {type: "number", editable: true, defaultValue: null},
                            userPallow: {type: "number", editable: true, defaultValue: null}
                        }
                    }
                }
            },
            columns: [
                {field: "userPic", title: "头像",
                    template: function (item) {
                        var photo = item && item.userPic ?  item.userPic : "img/user.png";
                        return "<img class='userPhoto' data-user-id=\"" + item.userId + "\" src='" + photo + "' style='width: 60px; height: 60px' />";
                    }
                },
                {field: "userName", title: "用户名"},
                {field: "userPassword", title: "密码"},
                {field: "userPhone", title: "手机"},

                {field: "userGender", title: "用户性别"},
                {field: "user_site", title: "用户位置"},
                {field: "userLevel", title: "用户权限等级1级：普通用户；2级：管理员；3级：超级管理员"},
                {field: "userPallow", title: "是否允许评论：0不允许。1：允许"},

                {
                    title: "操作",
                    command: [
                        "edit",
                        {
                            name: "uploadPhoto",
                            text: "上传照片",
                            className: "btn btn-danger my-btn-light",
                            iconClass: "k-icon k-i-refresh",
                            click: function (e) {
                                e.preventDefault();
                                selectRow = $(e.target).closest("tr");
                                selectObj = this.dataItem(selectRow);
                                uploadPhoto(selectObj.userId);
                            }
                        },
                        {
                            name: "myRest",
                            text: "重置密码",
                            className: "btn btn-danger my-btn-light",
                            iconClass: "k-icon k-i-refresh",
                            click: function (e) {
                                e.preventDefault();
                                selectRow = $(e.target).closest("tr");
                                selectObj = this.dataItem(selectRow);
                                swal({
                                    title: "重置密码",
                                    text: "确定重置密码吗?",
                                    type: "warning",
                                    showCancelButton: true,
                                    confirmButtonText: "确定",
                                    cancelButtonText: "取消",
                                }).then((result) => {
                                    if (result.value) {
                                        $.ajax({
                                            type: "post",
                                            url: "user/restPassword",
                                            data: JSON.stringify(selectObj),
                                            contentType: "application/json",
                                            dataType: "json",
                                            success: function (data) {
                                                swal({
                                                    title: "提示消息",
                                                    text: "密码重置成功！",
                                                    type: "info",
                                                    showCancelButton: false,
                                                    confirmButtonText: "确定",
                                                }).then(() => {
                                                    $("#grid").data("kendoGrid").dataSource.read();
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        },
                        {
                            name: "myDelete",
                            text: "删除",
                            className: "btn btn-danger my-btn-light",
                            iconClass: "k-icon k-i-delete",
                            click: function (e) {
                                e.preventDefault();
                                selectRow = $(e.target).closest("tr");
                                selectObj = this.dataItem(selectRow);
                                swal({
                                    title: "删除确认",
                                    text: "确定删除吗?",
                                    type: "warning",
                                    showCancelButton: true,
                                    confirmButtonText: "确定",
                                    cancelButtonText: "取消",
                                }).then((result) => {
                                    if (result.value) {
                                        $("#grid").data("kendoGrid").removeRow(selectRow);
                                    }
                                });
                            }
                        }
                    ],
                    width: 270
                }
            ],

            resizable: true,
            pageable: {
                pageable: {
                    pageSize: 20,
                    pageSizes: [10, 20, 30, 50],
                    buttonCount: 10,
                },
            },
            editable: {
                mode: "popup",
                confirmation: false,
            },
            edit: function (e) {
                selectRow = e.container;
                selectObj = e.model;
                if (e.model.isNew()) {
                    e.container.data("kendoWindow").title("新建");
                } else {
                    e.container.data("kendoWindow").title("修改");
                }
            },
            selectable: "row",
            change: function (e) {
                selectRow = this.select()[0];
                selectObj = this.dataItem(selectRow);
            },
            filterable: false
        });

        $("#addButton").click(function () {
            $("#grid").data("kendoGrid").addRow()
        });




    });

    function uploadPhoto(userId) {
        var fileInput = $("#uploadFile");
        var tempUpload = fileInput.data("kendoUpload");
        if (tempUpload != null) {
            tempUpload.destroy();
        }
        fileInput.kendoUpload({
            async: {
                saveUrl: "uploadUserPhoto",
                autoUpload: true
            },
            // 多文件上传
            multiple: false,
            // 点击上传按钮
            upload: function (e) {

                e.data = {userId : userId};
            },
            // 上传文件成功后
            success: function (response) {
                $("#loading").hide();
                $("#uploadBtn").removeAttr("disabled");
                fileInput.data("kendoUpload").destroy();
                swal({
                    title: '<i>上传成功！</i>',
                    type: 'success',
                    html: "上传成功：" + JSON.stringify(response.response),
                    showConfirmButton: true,
                    showCancelButton: false,
                })
            },
            complete: function (e) {
            },
            error:function () {
                swal({
                    title: '<i>上传失败！</i>',
                    type: 'error',
                    html: "上传失败：",
                    showConfirmButton: true,
                    showCancelButton: false,
                })
            }
        });
        fileInput.data("kendoUpload");
        fileInput.click();
    }



</script>
</body>
</html>

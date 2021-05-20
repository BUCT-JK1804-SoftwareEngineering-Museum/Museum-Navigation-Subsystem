<header id="topnav" style="">
    <nav class="navbar-custom">
        <div class="logo" style="display: none;">

        </div>
        <div class="title right-bar-toggle">
            博物馆导览系统
        </div>

        <ul class="list-unstyled menu-left mb-0">
            <li class="float-left">
                <a class="button-menu-mobile open-left navbar-toggle">
                    <div class="lines">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                </a>
            </li>
        </ul>

        <ul class="loginInfo">

            <li class="dropdown notification-list">
                <a href="javascript:void(0);"  class="nav-link right-bar-toggle" data-target=".loginUser">
                    <i class="fa fa-user"></i>
                    <span class="ml-1"><@shiro.principal property='userName'/> <i class="mdi mdi-chevron-down"></i> </span>
                </a>
            </li>
        </ul>
    </nav>
    <!-- end navbar-custom -->
</header>






<#--用户信息-->
<div class="right-bar loginUser">

    <div>
        <div class="right-bar-container"
             style="margin:10px 0px 0px 0px; width: 100%; overflow: auto;">
            <a href="javascript:void(0);" data-toggle="modal" data-target="#_model_user_info"
               class="dropdown-item notify-item i-right-bar-close">
                <i class="fa fa-user"></i> <span>账户信息</span>
            </a>
            <a href="javascript:void(0);" data-toggle="modal" data-target="#_model_user_password"
               class="dropdown-item notify-item i-right-bar-close">
                <i class="fa fa-lock"></i> <span>修改密码</span>
            </a>
            <a href="logout" class="dropdown-item notify-item">
                <i class="fa fa-power-off"></i> <span>退出系统</span>
            </a>
        </div>
    </div>
</div>


<#--用户信息-->
<div class="modal right fade" id="_model_user_info" tabindex="-1" role="dialog" aria-labelledby="_label_user_info"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header navbar-fixed-top">
                <h4 class="modal-title" id="_label_user_info">用户信息</h4>
            </div>
            <div class="modal-body">
                <div class="col-12">
                    <div class="form-group row">
                        <label class="col-sm-3 control-label">登录账号：</label>
                        <div class="col-sm-7">
                            <p class="form-control-static"><@shiro.principal property='userName'/></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="navbar-fixed-bottom modal-footer">
                <div class="col-sm-12 text-center">
                    <button class="btn btn-primary" data-toggle="modal" data-target="#_model_user_info"
                            id="_ok_user_info"><i class="fa fa-check"></i> 确认
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<#--修改密码-->
<div class="modal right fade" id="_model_user_password" tabindex="-1" role="dialog"
     aria-labelledby="_label_user_password" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header navbar-fixed-top">
                <h4 class="modal-title" id="_label_user_password">修改密码</h4>
            </div>
            <div class="modal-body">
                <form id="_top_form" class="form-horizontal">
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-4 control-label">原始密码：</label>
                        <div class="col-sm-12">
                            <input type="password" id="_old_password" class="form-control" name="password"
                                   placeholder="原始密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-4 control-label">新密码：</label>
                        <div class="col-sm-12">
                            <input type="password" id="_password" class="form-control" name="password"
                                   placeholder="新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-4 control-label">确认密码：</label>
                        <div class="col-sm-12">
                            <input type="password" id="_confirm_password" class="form-control" name="confirmPassword"
                                   placeholder="确认密码">
                        </div>
                    </div>
                    <div class="form-group" id="_top_message">
                    </div>

                </form>
            </div>
            <div class="navbar-fixed-bottom modal-footer">
                <#--<div class="col-sm-12 text-center" id="_top_message" ></div>-->
                <div class="col-sm-12 text-center">
                    <button class="btn-primary btn" style="width: 100px" id="_ok_user_password"><i
                                class="fa fa-check"></i> 确认
                    </button>
                    <button class="btn btn-default" data-toggle="modal" data-target="#_model_user_password"
                            style="width: 100px" id="_cancel_user_password"><i class="fa fa-ban"></i> 取消
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    <#--var _user_name = "<@shiro.principal property='loginName'/>";-->
    var commandWidth = 180;

    // var _org_tree_view = new OrgTreeView(_org_tree_data);
    var _org_tree_select_data;
    $(function () {

        function _alt_message(msg){
            $("#_top_message").html(" <div class=\"alert alert-warning\">" + msg + "</div>");
        }

        $("#_ok_user_password").click(function () {
            var _old_password = $("#_old_password").val();
            var _password = $("#_password").val();
            var _confirm_password = $("#_confirm_password").val();
            if(!_old_password || _old_password.length == 0){
                _alt_message("请输入原始密码！");
                return;
            }
            if(!_password || _password.length == 0){
                _alt_message("请输入新密码！");
                return;
            }
            if (!_confirm_password || _confirm_password.length == 0) {
                _alt_message("请输入确认密码！");
                return;
            }
            if(_password != _confirm_password ){
                _alt_message("两次输入不一致！");
                return;
            }
            $("#_top_message").html("");
            $.ajax({
                type: "post",
                url: "user/changePassword",
                data:{oldPwd:_old_password, newPwd: _password},
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        var $swl ;
                        var maxTime = 5;
                        var time =window.setInterval(function (args,index) {
                            if ($swl) {
                                maxTime -= 1;
                                $("#swal2-content").html(maxTime+"秒后自动跳转登录页面");
                                return;
                            }
                            $swl = swal({
                                title: "密码修改成功！",
                                text: "5秒后自动跳转登录页面",
                                timer: 5000,
                                confirmButtonText:"立即跳转",
                                showCancelButton: false
                            }).then((result) => {
                                if (result.value) {
                                    window.location.href = "logout";
                                    swal.close();
                                    if (time) {
                                        clearInterval(time);
                                    }
                                } else {
                                    if (time) {
                                        clearInterval(time);
                                    }
                                    window.location.href = "logout";
                                }
                            })
                        }, 1000);
                    } else {
                        _alt_message("修改失败，原始密码不正确！");
                    }

                }
            });
        });

    })
</script>

<style>
    @media screen and (max-width: 600px) {
        .enlarged #wrapper .left-side-menu {
            width: 0px;
        }

        .switch_units {
            display: none !important;
        }
    }
    .left-side-menu li {
        list-style: none
    }

    .left-side-menu a {
        text-decoration: none;
    }

    /* lsm-sidebar */
    .left-side-menu, .lsm-popup.lsm-sidebar ul li, .lsm-container.lsm-mini .lsm-sidebar > ul > li.lsm-group > ul > li.lsm-group > ul {
        background-color: var(--lh-background-color-main, #505961);
    }

    .left-side-menu {
        -webkit-touch-callout: none;
        -webkit-user-select: none;
        -khtml-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    .left-side-menu {
        height: calc(100% - 50px);
        width: 240px;
    }

    .lsm-expand-btn {
        height: 65px;
    }

    .lsm-container {
        height: calc(100% - 35px);
        transition: all .3s;
        z-index: 100
    }

    .lsm-sidebar {
        height: calc(100% - 0px);
    }

    .lsm-container li > a.active {
        background: var(--mpm-left-active-bgcolor);
        color: var(--mpm-left-active-color);
    }

    .lsm-container li > a.active:hover {
        background: var(--mpm-left-active-hover);
    }
    ul {
        padding: 0px;
    }

    .lsm-sidebar a, i.lsm-icon, i.lsm-i-right {
        line-height: 40px;
        max-height: 40px;
    }

    .lsm-sidebar a {
        display: block;
        overflow: hidden;
        padding-left: 20px;
        color: var(--mpm-left-a-color);
        transition: all .3s;
    }

    .lsm-container ul:first-child > li > a, .lsm-container ul:first-child > li > a > * {
        line-height: 55px;
        max-height: 55px;
    }


    .lsm-sidebar > ul > li > a > span {
        padding-left: 6px;
        font-size: 15px;
    }

    .lsm-sidebar .lsm-group > ul > li > a {
        padding-left: 50px;
    }

    .lsm-sidebar .lsm-group .lsm-group > ul > li a {
        padding-left: 70px;
    }

    .lsm-group {
        position: relative;
    }

    .lsm-group.active {
        border-bottom: none;
    }

    .lsm-group ul {
        display: none;
        background-color: var(--mpm-left-ul-bgcolor);
    }

    .lsm-group.active ul {
        display: block;
    }

    .lsm-group > a:before, .lsm-container ul:first-child > li > a:before {
        content: "";
        position: absolute;
        left: 0px;
        width: 2px;
        height: 40px;
        background: #0baae0;
        opacity: 0;
        transition: all .3s;
    }

    .lsm-container .active > a:before, .lsm-group > a:hover:before, .lsm-container ul:first-child > li > a:hover:before {
        opacity: 1 !important;
    }

    .lsm-container .active > a {
        color: var(--mpm-left-a-active-color);
    }
    .lsm-container ul:first-child > li > a:before {
        height: 55px;
    }

    .lsm-sidebar ul:first-child > li > a > i:first-child {
        display: inline-block;
        font-size: 16px;
        width: 20px;
        text-align: center;
    }

    /* 此处修改导航图标 可自定义iconfont 替换*/
    .icon_1::after {
        content: "\e62b";
    }

    .icon_2::after {
        content: "\e669";
    }

    .icon_3::after {
        content: "\e61d";
    }

    .icon_3::after {
        content: "\e698";
    }

    .lsm-i-right {
        float: right;
        margin-right: 20px;
        font-size: 12px;
        transition: transform .3s;
    }

    /* 导航右侧箭头 换用其他字体需要替换*/
    .lsm-i-right::after {
        content: "\f054";
    }

    .lsm-container ul:first-child > li > a:hover {
        color: #FFF;
        background: var(--mpm-left-a-hover-bgcolor);
    }

    .active > a > i.lsm-i-right.fa {
        transform: rotate(90deg);
    }

    /*.active, .lsm-group>a:hover{color: #FFF;background: rgba(0, 0, 0, 0.2);}*/

    .lsm-group li > a:hover, .lsm-popup > div > ul > li > a:hover {
        color: #FFF;
        background: var(--mpm-left-a-hover-bgcolor);
    }

    .lsm-mini-btn {
        height: 70px;
        width: 70px;
    }

    .lsm-mini-btn svg {
        margin: -10px 0 0 -10px;
    }

    .lsm-mini-btn input[type="checkbox"] {
        display: none;
    }

    .lsm-mini-btn path {
        fill: none;
        stroke: #ffffff;
        stroke-width: 3;
        stroke-linecap: round;
        stroke-linejoin: round;
        --length: 24;
        --offset: -38;
        stroke-dasharray: var(--length) var(--total-length);
        stroke-dashoffset: var(--offset);
        transition: all .8s cubic-bezier(.645, .045, .355, 1);
    }

    .lsm-mini-btn circle {
        fill: #fff3;
        opacity: 0;
    }

    .lsm-mini-btn label {
        top: 0;
        right: 0;
    }

    .lsm-mini-btn label:hover circle {
        opacity: 1;
    }

    .lsm-mini-btn input:checked + svg .line--1, .lsm-mini-btn input:checked + svg .line--3 {
        --length: 8.602325267;
    }

    .lsm-mini-btn .line--1, .lsm-mini-btn .line--3 {
        --total-length: 126.38166809082031;
    }

    .lsm-mini-btn .line--2 {
        --total-length: 80;
    }

    .lsm-mini-btn input:checked + svg .line--1, .lsm-mini-btn input:checked + svg .line--3 {
        --offset: -109.1770175568;
    }

    .enlarged .lsm-container, .enlarged .lsm-container {
        width: 60px;
    }

    .enlarged .lsm-container .lsm-sidebar .lsm-icon { /* margin-left:-2px; */
    }

    .enlarged .left-side-menu ul:first-child > li.lsm-group > a span {
        display: none;
    }

    .enlarged .left-side-menu ul:first-child > li.lsm-group > a > i.lsm-i-right {
        margin-right: -20px;
    }

    .enlarged .lsm-container .lsm-sidebar > ul > li.lsm-group > ul > li.lsm-group > ul {
        display: none;
        position: absolute;
        top: 0px;
        left: 180px;
        width: 180px;
        z-index: 99;
        bottom: 0px;
        top: 0px;
        overflow: hidden;
    }

    .enlarged .left-side-menu ul:first-child > li > ul {
        display: none;
    }

    .enlarged .left-side-menu ul:first-child > li > a > span {
        display: none;
    }

    .transform {
        -webkit-transform: scale(1);
        -ms-transform: scale(1);
        transform: scale(1);
    }

    .lsm-popup div {
        background: #56648ed1;
        border-radius: 5px;
    }

    .lsm-popup {
        display: block;
        position: fixed;
        border: 3px solid rgba(60, 71, 76, 0);
    }

    .lsm-popup > div > a > i.lsm-i-right.fa {
        transform: rotate(90deg);
    }

    .lsm-popup.second {
        left: 60px;
    }

    .lsm-popup.third {
        left: 243px;
    }

    .lsm-popup.third.lsm-sidebar > div > ul {
        display: block;
    }



    .lsm-popup .lsm-icon {
        display: none;
    }

    /*.lsm-popup ul > li > a > span{*/
    /*    display: block;*/
    /*}*/
    .lsm-popup.lsm-sidebar a > span {
        display: inline-block;
        margin-left: 0px;
    }

    .lsm-popup.lsm-sidebar > div > ul > li.lsm-group > ul {
        position: absolute;
        top: 0px;
        left: 180px;
        width: 180px;
        z-index: 99;
    }

    .lsm-popup div, .lsm-popup.lsm-sidebar ul, .lsm-popup.lsm-sidebar ul li {
        width: 180px;
    }

    .lsm-popup.lsm-sidebar ul li:last-child, .lsm-popup > div > ul > li:last-child > a {
        border-radius: 0 0 5px 5px;
    }
</style>
<#--左侧菜单-->
<div class="left-side-menu">
    <div class="lsm-container">
        <div class="lsm-scroll">
            <div class="lsm-sidebar">
                <ul>
                    <li class="lsm-group">
                        <a href="/user/page">
                            <i class="lsm-icon fa fa-user"></i> <span>用户管理</span> <i class="lsm-i-right fa"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        // $('.lsm-container ul ul').css("display", "none");
        var active = $(".lsm-container a.active");
        active.parent().parent().parent().addClass("active");
        active.parent().parent().parent().parent().parent().addClass("active");
        // lsm-sidebar收缩展开
        $('.lsm-sidebar a').on('click', function () {
            $('.lsm-scroll').slimscroll({
                height: 'auto',
                position: 'right',
                size: "8px",
                color: '#9ea5ab',
                wheelStep: 5,
                touchScrollStep: 50
            });
            if (!$('body').hasClass('enlarged')) {
                $(this).parent("li").siblings("li.lsm-group").children('ul').slideUp(200);
                if ($(this).next().css('display') == "none") {
                    //展开未展开
                    // $('.lsm-group').children('ul').slideUp(300);
                    $(this).next('ul').slideDown(200);
                    $(this).parent('li').addClass('active').siblings('li').removeClass('active');
                } else {
                    //收缩已展开
                    $(this).next('ul').slideUp(200);
                    //$('.lsm-group.active').removeClass('active');
                    $(this).parent('li').removeClass('active');
                }
            }
        });
        //enlarged
        $('.button-menu-mobile').on('click', function (event) {
            event.preventDefault();
            $("body").toggleClass("enlarged");
            if ($("body").hasClass("enlarged")) {
                $('.lsm-group.active').removeClass('active');
                $('.lsm-container ul').removeAttr('style');
                //$('.left-side-menu').addClass('enlarged');
                // $('.left-side-menu').stop().animate({width : 60},0);
            } else {
                //$('.left-side-menu').removeClass('enlarged');
                $('.lsm-container ul ul').css("display", "none");
                // $('.left-side-menu').stop().animate({width: 240},0);
            }
        });

        $(document).on('mouseover', '.enlarged .lsm-container ul:first>li', function () {
            $(".lsm-popup.third").hide();
            $(".lsm-popup.second").length == 0 && ($(".lsm-container").append("<div class='second lsm-popup lsm-sidebar'><div></div></div>"));
            $(".lsm-popup.second>div").html($(this).html());
            $(".lsm-popup.second").show();
            $(".lsm-popup.third").hide();
            var top = $(this).offset().top;
            var d = $(window).height() - $(".lsm-popup.second>div").height();
            if (d - top <= 0) {
                top = d >= 0 ? d - 8 : 0;
            }
            $(".lsm-popup.second").stop().animate({"top": top}, 100);
        });

        $(document).on('mouseover', '.second.lsm-popup.lsm-sidebar > div > ul > li', function () {
            if (!$(this).hasClass("lsm-group")) {
                $(".lsm-popup.third").hide();
                return;
            }
            $(".lsm-popup.third").length == 0 && ($(".lsm-container").append("<div class='third lsm-popup lsm-sidebar'><div></div></div>"));
            $(".lsm-popup.third>div").html($(this).html());
            $(".lsm-popup.third").show();
            var top = $(this).offset().top;
            var d = $(window).height() - $(".lsm-popup.third").height();
            if (d - top <= 0) {
                top = d >= 0 ? d - 8 : 0;
            }
            $(".lsm-popup.third").stop().animate({"top": top}, 100);
        });

        $(document).on('mouseleave', '.enlarged .lsm-container ul:first, .enlarged .slimScrollBar,.second.lsm-popup ,.third.lsm-popup', function () {
            $(".lsm-popup.second").hide();
            $(".lsm-popup.third").hide();
        });

        $(document).on('mouseover', '.enlarged .slimScrollBar,.second.lsm-popup', function () {
            $(".lsm-popup.second").show();
        });

        $(document).on('mouseover', '.third.lsm-popup', function () {
            $(".lsm-popup.second").show();
            $(".lsm-popup.third").show();
        });

        $('.lsm-scroll').slimscroll({
            height: 'auto',
            position: 'right',
            railOpacity: 1,
            size: "5px",
            opacity: .4,
            color: '#fffafa',
            wheelStep: 5,
            touchScrollStep: 50
        });

        //菜单滚动到页面可见
        if ($(".lsm-group.active").length > 0) {
            $('.lsm-scroll').animate({scrollTop: $(".lsm-group.active").offset().top - $(window).height() + 300}, 500);
        } else if ($(".lsm-container li > a.active").length > 0) {
            $('.lsm-scroll').animate({scrollTop: $(".lsm-container li > a.active").offset().top - $(window).height() + 300}, 500);
        }
        $(document).on('click', "form span.k-input", function () {
            var width = $("form .k-dropdown-wrap.k-state-default.k-state-hover.k-state-focused.k-state-active.k-state-border-down").width()
            $("form ul.k-list.k-reset").parent().parent().parent().css("min-width", width + "px");
            $("form ul.k-list.k-reset").parent().parent().css("min-width", width + "px");
            $("form ul.k-list.k-reset").parent().css("min-width", width + "px");
            $("form ul.k-list.k-reset").css("min-width", width + "px");
        })






        $(document).on('click', '.right-bar-toggle', function (e) {
            let el = $(this).attr("data-target");
            if (el && $(el).length > 0) {
                $(el).toggleClass('right-bar-enabled');
            }
            // $('.project-tg').css("right", "0");
            //$('.project-tg').toggleClass('right-bar-enabled');
            // $('body').toggleClass('right-bar-enabled');
        });
        $(document).on('click', 'body', function (e) {
            if ($(e.target).closest('.right-bar-toggle, .right-bar').length > 0 && $(e.target).closest('.right-bar-close').length == 0) {
                return;
            }
            //$('body').removeClass('right-bar-enabled');
            $('.right-bar').removeClass('right-bar-enabled');
            return;
        });
        $(document).on('click', 'body', function (e) {
            if ($(e.target).closest('.right-bar-toggle, .right-bar').length > 0 && $(e.target).closest('.i-right-bar-close').length == 0) {
                return;
            }
            //$('body').removeClass('right-bar-enabled');
            $('.right-bar').removeClass('right-bar-enabled');
            return;
        });


    });
</script>
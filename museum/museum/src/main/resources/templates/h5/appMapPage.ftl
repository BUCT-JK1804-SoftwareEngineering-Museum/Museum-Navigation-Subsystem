<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>领航高精度定位</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="/libs/sm/css/sm.min.css">
    <link rel="stylesheet" href="/libs/sm/css/sm-extend.min.css">
    <script src="/libs/zepto/zepto.min.js"></script>
    <script type='text/javascript' src='/libs/sm/js/sm.js' charset='utf-8'></script>
    <script type='text/javascript' src='/libs/sm/js/sm-extend.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='/libs/vue/2.3.2/vue.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='/libs/hammer.js/2.0.8/hammer.min.js' charset='utf-8'></script>
</head>
<body>
    <div class="page" id='settingPage' >
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left back" href="#">
                <span class="icon icon-left"></span>
                返回
            </a>
            <button v-on:click="clear" class="button pull-right" id="clearBtn">
                清空
            </button>
            <div class="buttons-row">
                <button id="formatBtn" v-on:click="format" class="button" style="margin: auto 2px; border-radius: .25rem">
                    格式化
                </button>
                <button id="pauseBtn" v-on:click="pause" class="button" style="margin: auto 2px; border-radius: .25rem">
                    暂停
                </button>
            </div>
        </header>
        <div class="content">


        </div>
    <script>

    </script>
    </div>
</body>
</html>


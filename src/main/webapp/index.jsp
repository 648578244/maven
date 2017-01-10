<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>测试</title>

    <style type="text/css">
        body {padding: 0; border: 0; margin: 0; width: 100%;}
        img{vertical-align:bottom; width: 100%;}
        button{
            margin:80px 0;
        }
    </style>
</head>
<body>
<button id="list">标的列表</button>
<button id="home">首页</button>
<button id="mine">我的页面</button>
<button id="autoBid">自动投标页面</button>
<button id="coupon">红包优惠界面</button>
<button id="share">分享</button>
<script type="text/javascript" src="https://static1.weidai.com.cn/static/common/weiApp/appH5/weiyirong/lib/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="WEB-INF/js/wdnative-1.0.js"></script>
<script>
    //页面加载的时候先执行下所用的方法
    wdNative.onload = function() {
        setTimeout(function(){
            wdNative.callFun('setShareContent', '{"shareTitle":"跟我一起赚钱吧！来微贷网就送588元现金哦！","shareContent":"靠谱理财平台，注册即领588元现金红包，更专享11.2%高年化新手标！","shareIconUrl":"https://static1.weidai.com.cn/static/common/weiApp/appH5/weidai/resource/share_hongbao.png","shareUrl":"https://static1.weidai.com.cn/static/common/weiApp/appH5/weidai/resource/share_hongbao.png","shareUrl":"https://www.baidu.com"}');
        },100)
    };

    $("#list").click(function(){
        wdNative.callFun('gotoItemListController',{});
        return false;
    })
    $("#home").click(function(){
        wdNative.callFun('gotoHomeController',{});
        return false;
    })
    $("#mine").click(function(){
        wdNative.callFun('gotoMineController',{});
        return false;
    })
    $("#autoBid").click(function(){
        wdNative.callFun('gotoAutoBidController',{});
        return false;
    })
    $("#coupon").click(function(){
        wdNative.callFun('gotoCouponController',{});
        return false;
    })

    $("#share").click(function(){
        wdNative.callFun('toShare', {"shareTitle":"跟我一起赚钱吧！来微贷网就送588元现金哦！","shareContent":"靠谱理财平台，注册即领588元现金红包，更专享11.2%高年化新手标！","shareIconUrl":"https://static1.weidai.com.cn/static/common/weiApp/appH5/weidai/resource/share_hongbao.png","shareUrl":"https://static1.weidai.com.cn/static/common/weiApp/appH5/weidai/resource/share_hongbao.png","shareUrl":"https://www.baidu.com"});
        return false;

    })

</script>
</body>
</html>
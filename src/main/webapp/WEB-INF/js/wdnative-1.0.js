/**
 *  wdnative.js
 *  version -> 1.1
 *
 *   wdnative.callFun(funName, data) -> 调用APP函数
 *  @ arguments --> funName {function} 方法名
 *  @ arguments --> data {object} 数据对象
 *  @ return --> null
 *  @ creat in version 1.0
 *
 *   wdnative.openApp() -> 唤醒APP
 *  @ return null
 *  @ creat in version 1.0
 */

//wdNative
window.wdNative = {};

//init
wdNative.init = function(){
    wdNative.onload();
};

//onload
wdNative.onload = function(){};

//对android的属性
wdNative.forandroid = {
    openAppUrl: 'weidai://openapp',
    appDownloadUrl: 'http://oss.weidai.com.cn/weidaiwang-release.apk'
};

//对ios的属性
wdNative.forios = {
    // openAppUrl: 'WdaiIOSDev://cn.com.weidai.wd',
    openAppUrl: 'weidai://',
    appDownloadUrl: 'https://itunes.apple.com/us/app/wei-dai-wang-hu-lian-wang/id914404898?l=zh&ls=1&mt=8'
};

//调用native方法
wdNative.callFun = function(funName, data) {
    if (window.android) {
        window.android.WDJSCallNative(funName, JSON.stringify(data));

        //window.androidLocalFunction(funName, data);
        return false;
    }

    if ('ios' in wdNative) {
        wdNative.ios.callFun(funName, data);
        return false;
    }
}

wdNative.downloadApp = function(){
    var u = navigator.userAgent;
    var isios = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    var isandroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
    var downloadUrl = 'https://m.weidai.com.cn/weixin/registerDown4';

    if (isandroid) {
        //android
        downloadUrl = wdNative.forandroid.appDownloadUrl;
    }

    if (isios) {
        //ios
        downloadUrl = wdNative.forios.appDownloadUrl;

    }

    window.location = downloadUrl;
}

//app外部开启app
wdNative.openApp = function() {
    var u = navigator.userAgent;
    var isios = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    var isandroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
    var openUrl = 'https://m.weidai.com.cn/weixin/registerDown4';

    function is_weixn() {
        var ua = navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
            return true;
        } else {
            return false;
        }
    }

    if (is_weixn()) {
        window.location = openUrl;
        return false;
    }

    if (isandroid) {
        //android
        window.open(wdNative.forandroid.openAppUrl, '_blank');
    }

    if (isios) {
        //ios
        window.location = wdNative.forios.openAppUrl;
    }
}

function setupWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) {
        return callback(WebViewJavascriptBridge);
    }
    if (window.WVJBCallbacks) {
        return window.WVJBCallbacks.push(callback);
    }
    window.WVJBCallbacks = [callback];
    var WVJBIframe = document.createElement('iframe');
    WVJBIframe.style.display = 'none';
    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
    document.documentElement.appendChild(WVJBIframe);
    setTimeout(function() {
        document.documentElement.removeChild(WVJBIframe)
    }, 0)
}

setupWebViewJavascriptBridge(function(bridge) {
    //alert(bridge);
    wdNative.ios = {
        jsonForIos: '',
        callFun: ''
    }

    wdNative.ios.jsonForIos = {
        methodName: '',
        params: {}
    }

    wdNative.ios.callFun = function(funName, data) {
        wdNative.ios.jsonForIos.methodName = funName;
        wdNative.ios.jsonForIos.params = data ? data : '';
        bridge.callHandler('WDJSCallNative', wdNative.ios.jsonForIos);
    }

    bridge.registerHandler('WDNativeCallJS', function(data) {
        console.info('ios呼叫js: ' + data);
    });

    wdNative.init();
})

var wdNativeTimmer = setTimeout(function(){
    if(window.android){
        wdNative.init();
    }
    clearTimeout(wdNativeTimmer);
},100)
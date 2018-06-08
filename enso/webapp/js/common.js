/**
 * Created by mzh on 2017-08-17.
 */
var DEBUG = true;

// frontend document root
window.CONTEXT_PATH = '.';
// backend root
window.APP_ROOT = '.';
// 上线时候DEBUG设置为false
window.DEBUG = false;

var URL_SMS = window.APP_ROOT + '/student/sms';

var URL_UPLOAD = "http://118.190.150.189:8089";
var BASEURL = "http://www.tfjyzx.com/";

// 当前活动的ID
var DEFAULT_ACTIVITIY_ID = 1;

// handle IE / 手机浏览器
(function() {
    if (!!window.ActiveXObject || "ActiveXObject" in window) {
        // IE
        console.log("is IE");
        // window.location.href = "unsupport.jsp";
    } else {
        console.log("is not IE");
        
        // 检测是不是移动端
        $.ajax({
        	type: 'GET',
        	url: '/parse/userAgent',
        	dataType: 'json'
        }).done(function(data) {
        	switch (data.status) {
        	case 404:
        		location.href = "/unsupport.jsp";
        		break;
        	case 200: break;
        	case 500: break;
        	default:
        	}
        });
    }   
})();

window.onload = function() {

    /**
     * 回到顶部
     */
    (function(w) {
        // 首先将#back-to-top隐藏
        var $window = $(w);
        var $back = $("#back-to-top");
        if ($window.scrollTop() <= 600) {
            $back.hide();
        }

        // 当滚动条的位置处于距顶部600像素以下时，跳转链接出现，否则消失
        $window.scroll(function() {
            if ($(this).scrollTop() > 600) {
                $back.fadeIn(500);
            } else {
                $back.fadeOut(500);
            }
        });
        // 当点击跳转链接后，回到页面顶部位置
        $back.click(function() {
            $('body,html').animate({
                scrollTop: 0
            }, 500);
            return false;
        });
    })(window);
};

/**
 * 取得get参数 {videoId: "1", name: "xxx"}
 * @returns {{}}
 */
function enso_query_string(url) {
    var qs = {},
        query = null;
    if (typeof url === "undefined") {
        query = window.location.search.substring(1); // name1=value1&name2=value2
    } else {
        query = url.substring(url.indexOf('?') + 1);
    }
    var vars = query.split("&");
    if (vars instanceof Array) {
        var handler = function(elem) {
            var pair = elem.split("=");
            // If first entry with this name
            var name = pair[0],
                value = pair[1];
            if (typeof qs[name] === "undefined") {
                qs[name] = decodeURIComponent(value);
                // If second entry with this name
            } else if (typeof qs[name] === "string") {
                qs[name] = [qs[name], decodeURIComponent(value)];
                // If third or later entry with this name
            } else {
                qs[name].push(decodeURIComponent(value));
            }
        };
        if (vars.forEach) {
            vars.forEach(handler);
        } else {
            // for IE<=8
            for (var i = 0; i < vars.length; i++) {
                handler(vars[i]);
            }
        }
    }
    return qs;
}

/**
 * 动态引入javascript文件,
 * Usage:
 * function script_onload() {
 *    alert(1);
 * }
 * var load_js = new Script(script_onload);
 * load_js.set("http://static.gongju.com/js/jquery-1.4.4.min.js");
 *
 * @param callback js文件加载完了callback function
 */
function Script(callback) {
    var js = document.createElement("script");
    this.js = js;
    js.type = "text/javascript";
    document.body.appendChild(js);

    Script.prototype.set = function(url) {
        this.js.src = url;
    };
    if (callback) {
        if (navigator.appName.toLowerCase().indexOf('netscape') === -1) {
            js.onreadystatechange = function() {
                js.readyState === 'complete' && callback(this);
            }
        } else {
            js.onload = function() {
                callback(this);
            }
        }
    }
}

/**
 * 动态引入css
 * @param href
 * @returns
 */
function CSS(href) {
    var css = document.createElement("link");
    css.rel = "stylesheet";
    css.type = "text/css";
    css.href = href;
    document.head.appendChild(css);
}

/**
 * dom.cloneNode deep copy
 * @param node
 * @returns
 */
function enso_cloneNode(node) {
    var newNode = node.cloneNode();

    if (!node.hasChildNodes()) {
        return newNode;
    }
    for (var i = 0; i < node.childNodes.length; i++) {
        newNode.appendChild(enso_cloneNode(node.childNodes[i]));
    }
    return newNode;
}

if (!Array.prototype.map) {
    Array.prototype.map = function(callback /*, thisArg*/ ) {

        var T, A, k;
        if (this == null) {
            throw new TypeError('this is null or not defined');
        }
        var O = Object(this);
        var len = O.length >>> 0;

        if (typeof callback !== 'function') {
            throw new TypeError(callback + ' is not a function');
        }
        if (arguments.length > 1) {
            T = arguments[1];
        }
        A = new Array(len);
        k = 0;

        while (k < len) {
            var kValue, mappedValue;
            if (k in O) {
                kValue = O[k];
                mappedValue = callback.call(T, kValue, k, O);
                A[k] = mappedValue;
            }
            k++;
        }
        return A;
    };
}

/**
 * is {}
 * @param obj
 * @returns
 */
function isEmpty(obj) {
    for (var prop in obj) {
        if (obj.hasOwnProperty(prop))
            return false;
    }
    return JSON.stringify(obj) === JSON.stringify({});
}

function strip_tags(html) {
    var div = document.createElement("div");
    div.innerHTML = html;
    return (div.textContent || div.innerText || "");
}

/**
 * 格式化输出字符串 sprintf
 * e.g. document.write(printf("{0}正在参加{1}电视台举办的<br />{2}", "张三", "开封", "未来小作家"));
 * @returns String
 */
function printf() {
    var num = arguments.length;
    var oStr = arguments[0];
    for (var i = 1; i < num; i++) {
        var pattern = "\\{" + (i - 1) + "\\}";
        var re = new RegExp(pattern, "g");
        oStr = oStr.replace(re, arguments[i]);
    }
    return oStr;
}
(function() {
    var callback = function() {
    	var $exp = $(".experience");
        $exp.find(".close").on("click", function() {
            $(this).parent().hide();
            $("#footer").find(".copyright").css({ "height": "50px", "line-height": "50px" });
            $.cookie("experience", "show");
        })
        console.log("[cookie] experience=" + $.cookie("experience"));        
        if ("show" === $.cookie("experience")) {
            $exp.hide();
        } else {
            $exp.show();
            $("#footer").find(".copyright").css({ "height": "60px", "line-height": "30px" });           
        }

    };
    if ($.cookie) {
        callback();
    } else {    	
        var script = new Script(callback);   // @common.js
        script.set("/bootstrap/js/jquery.cookie.js");
    }
    
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

})();
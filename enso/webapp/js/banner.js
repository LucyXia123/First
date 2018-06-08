var myswiper = {
    init: function() {
        this.banner()
    },
    banner: function() {
        var n = new Swiper(".banner-container", {
            pagination: ".banner-pagination",
            loop: !0,
            slidesPerView: 1,
            speed: 1e3,
            autoplay: 5e3,
            grabCursor: !0,
            height: 300,
            paginationClickable: !0
        });
        $(".banner-container").hover(function() {
            n.stopAutoplay(),
                $("#banner-arrow-left,#banner-arrow-right").stop(!0, !0),
                $("#banner-arrow-left,#banner-arrow-right").fadeIn()
        }, function() {
            n.startAutoplay(),
                $("#banner-arrow-left,#banner-arrow-right").fadeOut()
        });
        $("#banner-arrow-left").click(function() {
            n.swipePrev()
        });
        $("#banner-arrow-right").click(function() {
            n.swipeNext()
        });
    }
};

$(function() {
    var json = {
    	"default": [
            {
                url:"javascript:;",  // act4guest.jsp
                img: "./images/carousel/xiaozuojia2.jpg",
                title: "未来小作家 征文活动"
            },
            {
                url:"http://www.thtf.com.cn/",
                img: "./images/carousel/index/tongfang.png",
                title: "同方"
            },
            {
            	url:"./more-program.jsp?area=商丘",
                img: "./images/carousel/index/shangqiu.png",
                title: "商丘电视台"
            },
            {
            	url:"./more-program.jsp?area=开封",
                img: "./images/carousel/index/kaifeng.png",
                title: "开封电视台"
            },
            {
            	url:"http://www.tsinghua.edu.cn",
                img: "./images/carousel/index/tsinghua.png",
                title: "清华"
            },
            {
            	url:"./more-program.jsp?area=濮阳", //"http://www.pytv.ha.cn/",
                img: "./images/carousel/index/puyang.png",
                title: "濮阳"
            },
            {
            	url:"http://www.xctv.cn/",
                img: "./images/carousel/index/xuchang.png",
                title: "许昌"
            }
    	]  
    };
    try {
    	/*
        var area = $("#js-area").val();        
        
        if ("null"!==area && null!==area && typeof area !=="undefined") {
            data = json[area];
        } else {
            data = json["default"];
        }
        */
        var data = json["default"];   // 都是同一组轮播图测试demo

        var $target = $("#js-swiper").empty();
        data.map(function(elem, i) {
            var $a = $("<a>").attr("href", elem.url).addClass("swiper-slide swiper-slide-duplicate")
                .attr("target", "_blank").css({"background":"url('"+
                elem.img
                +"') center center no-repeat rgb(245, 245, 245)"});
            if (elem.hasOwnProperty("title")) {
                $a.attr("title", elem.title);
            }
            $target.append($a);
            // 第0个轮播图a链接到 "未来小作家 征文活动", 这里跳转需要判断身份
            // ref: @file: ./js/fileter.js
            //      @file: ./include/nav.jsp
            if (i===0) {
            	$a.addClass("J_toActivity");
            }
            return $a;
        });
        // END load carousel
    } catch (e) {
    	console.error(e);
    }

    myswiper.init();
});

	



/**
 * Created by Administrator on 2017-10-28.
 */
$(function() {
    require.config({
        packages: [
            {
                name: 'echarts',
                location: 'assets/js/echarts/src',
                main: 'echarts'
            },
            {
                name: 'zrender',
                location: 'assets/js/zrender/src',
                main: 'zrender'
            }
        ]
    });
    
    var user_total, user_online;

    (function() {    	
        var dom1 = document.getElementById("realtime01");
        realtime(dom1);
        canvas_full_screen(dom1, realtime);

        var dom2 = document.getElementById("pie02");
        pie(dom2);
        canvas_full_screen(dom2, pie);

        var dom3 = document.getElementById('bar03');
        bar(dom3);
        canvas_full_screen(dom3, bar);

        var dom4 = document.getElementById('map04');
        henanmap(dom4);
        canvas_full_screen(dom4, henanmap);
    })();

    /**
     * 注册用户/在线用户
     */
    function realtime(dom) {
    	user_total = user_online = 0;
    	
    	var callback = function(user_total, user_online) {
            var option = {
                    tooltip : {
                        trigger: 'axis',
                        formatter: function(params) {
                            var a = [];
                            if (params[0]) {a.push("总用户数:"+params[0].data);}
                            if (params[1]) {a.push("在线用户数量"+params[1].data);}
                            return a.join(", ") + ".";
                        }
                    },
                    legend: {
                        data:['总用户数量','在线用户数量']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : ['用户']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            splitArea : {show : true}
                        }
                    ],
                    series : [
                        {
                            name:'总用户数量',
                            type:'bar',
                            data: [user_total]   // data:[80]
                        },
                        {
                            name:'在线用户数量',
                            type:'bar',
                            data: [user_online]  // data:[26]
                        }
                    ]
                };
                require(
                    [
                        'echarts',
                        'echarts/chart/bar'
                    ],
                    function (ec) {
                        if (dom === null || typeof dom === "undefined") {
                            dom = document.getElementById('realtime01');
                        }
                        var myChart = ec.init(dom);
                        myChart.setOption(option);
                    }
                );
    	}
    	
    	$.ajax({
    		type: 'GET',
    		url: "./userCount",
    	}).done(function(data) {
    		user_total = parseInt(data, 10);
    		// 在线用户总数 bug
    		/*
    		$.ajax({
    			type: 'GET',
    			url: "./onlineCount"
    		}).done(function(data) {
    			user_online = parseInt(data, 10);
    			if (user_online > user_total) {
    				console.log("取得在线用户数出错");
    				user_online = 0;
    			}
    			callback(user_total, user_online);
    		});
    		*/
    		callback(user_total, 0);
    	});
    }

    /**
     * 用户地区分布统计 开封/濮阳/商丘 (饼状图)
     */
    function pie(dom) {
    	var areaList = ['开封','濮阳','商丘'];
    	
    	$.ajax({
    		type: 'GET',
    		url: "./areaCountArray",
    		data: {area: areaList.join(',')},
    		cache: true
    	}).done(function(data) {
    		// var resultList = [{value:40, name:'开封'},{value:28, name:'濮阳'},{value:12, name:'商丘'}]
    		var resultList = (function(dataList, areaList) {
    			var list = [];
    			areaList.forEach(function(area, i) {
    				list.push({"value":dataList[i], "name":area});
    			})
    			return list;
    		})(data, areaList);
    		
            var option = {
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient : 'vertical',
                        x : 'left',
                        data:areaList
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {
                                show: true,
                                type: ['pie', 'funnel'],
                                option: {
                                    funnel: {
                                        x: '25%',
                                        width: '50%',
                                        funnelAlign: 'left',
                                        max: 1548
                                    }
                                }
                            },
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    series : [
                        {
                            name:'用户地区分布',
                            type:'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data: resultList
                        }
                    ]
                };
                require(
                    [
                        'echarts',
                        'echarts/chart/pie',
                        'echarts/chart/funnel'
                    ],
                    function (ec) {
                        var myChart = ec.init(dom); // document.getElementById('pie02')
                        myChart.setOption(option);
                    }
                );
    	}).fail(function(jqXHR, textStatus, errorThrown) {
    		console.error("xhr 02 pie chart error["+textStatus+"]: "+errorThrown);
    	});
    }

    /**
     * 学校每月用户增量
     */
    function bar(dom) {
    	// 当前11月: [6,7,8,9,10,11]  ['6月','7月','8月','9月','10月','11月']
    	var year = null;
        var month = null;
        var DURATION = 6;
        var monthList = (function() {
            var date = new Date();
            year = date.getFullYear();
            month = date.getMonth()+1;
            var ml = [];
            // 显示最近半年的月份
            for (var i = 0; i < DURATION; i++) {
                var m = (month-i<0) ? (12+month-i) : (month-i);
                m = m || 12;
                ml.unshift(m);
            }
            console.log(ml);           
            return ml;
        }).call();

        month = (month < 10)? ("0"+month) : month;
    	
    	$.ajax({
    		type: "GET",
    		url: "./getUserMonthInc",
    		data: {date:year + "-" + month, duration: DURATION}
    	}).done(function(data) {
    		var inc = data;
    		
    	    var option = {
    	            tooltip : {
    	                trigger: 'axis'
    	            },
    	            legend: {
    	                data:['每月用户增量']
    	            },
    	            toolbox: {
    	                show : true,
    	                feature : {
    	                    dataView : {show: true, readOnly: false},
    	                    magicType : {show: true, type: ['line', 'bar']},
    	                    restore : {show: true},
    	                    saveAsImage : {show: true}
    	                }
    	            },
    	            calculable : true,
    	            xAxis : [
    	                {
    	                    type : 'category',
    	                    data : monthList.map(function(elem) {return elem+"月";}) // ['6月','7月','8月','9月','10月','11月']
    	                }
    	            ],
    	            yAxis : [
    	                {
    	                    type : 'value',
    	                    splitArea : {show : true}
    	                }
    	            ],
    	            series : [
    	                {
    	                    name:'用户增量',
    	                    type:'bar',
    	                    data: inc  // [2, 5, 8, 10, 20, 3]
    	                },
    	            ]
    	        };
    	        require(
    	            [
    	                'echarts',
    	                'echarts/chart/bar',
    	                'echarts/chart/line'
    	            ],
    	            function (ec) {
    	                var myChart = ec.init(dom); // document.getElementById('bar03')
    	                myChart.setOption(option);
    	            }
    	        );
    	});
    }

    /**
     * 河南地图 示范校个数/注册用户/在线人数
     */
    function henanmap(dom) {
    	var areaList = ['开封','濮阳','商丘'];		
		var requestData = {"area": areaList.join(',')};
		
		var callback = function(modelList, userList, onlineList) {			
	        var option = {
	                title : {
	                    text: '河南地图',
	                    x:'center'
	                },
	                tooltip : {
	                    trigger: 'item',
	                    formatter: function(params) {
	                        var a = params.data.valueMap;
	                        var res = [];
	                        if (a) {
	                            if (typeof a[0] !== "undefined") {res.push("示范校:"+a[0]+"个");}
	                            if (typeof a[1] !== "undefined") {res.push("注册用户数:" + a[1]);}
	                            if (typeof a[2] !== "undefined") {res.push("在线人数:" + a[2]);}
	                        }
	                        return res.join(", ");
	                    }
	                },
	                legend: {
	                    orient: 'vertical',
	                    x:'left',
	                    data:['示范校个数','注册用户','在线人数']
	                },
	                dataRange: {
	                    min: 0,
	                    max: 100,
	                    x: 'left',
	                    y: 'bottom',
	                    text:['高','低'],           // 文本，默认为数值文本
	                    calculable : true
	                },
	                toolbox: {
	                    show: true,
	                    orient : 'vertical',
	                    x: 'right',
	                    y: 'center',
	                    feature : {
	                        mark : {show: true},
	                        dataView : {show: true, readOnly: false},
	                        restore : {show: true},
	                        saveAsImage : {show: true}
	                    }
	                },
	                series : [
	                    {
	                        name: '示范校个数',
	                        type: 'map',
	                        mapType: '河南',
	                        roam: false,
	                        itemStyle:{
	                            normal:{label:{show:true}},
	                            emphasis:{label:{show:true}}
	                        },
	                        data: [
	                        	{value:modelList[0], name:"开封市"},
	                        	{value:modelList[1], name:"濮阳市"},
	                        	{value:modelList[2], name:"商丘市"}
	                        ]
	                    },
	                    {
	                        name: '注册用户',
	                        type: 'map',
	                        mapType: '河南',
	                        itemStyle:{
	                            normal:{label:{show:true}},
	                            emphasis:{label:{show:true}}
	                        },
	                        data: [
	                        	{value:userList[0], name:"开封市"},
	                        	{value:userList[1], name:"濮阳市"},
	                        	{value:userList[2], name:"商丘市"}
	                        ]
	                    },
	                    {
	                        name: '在线人数',
	                        type: 'map',
	                        mapType: '河南',
	                        itemStyle:{
	                            normal:{label:{show:true}},
	                            emphasis:{label:{show:true}}
	                        },
	                        data: [
	                        	{value:onlineList[0], name:"开封市"},
	                        	{value:onlineList[1], name:"濮阳市"},
	                        	{value:onlineList[2], name:"商丘市"}
	                        ]
	                    }
	                ]
	            };
	        require(
	            ['echarts', 'echarts/chart/map'], function(ec) {
	                var myChart = ec.init(dom);
	                myChart.setOption(option);
	            }
	        );
		} // end callback
		
		$.ajax({
			type: 'GET',
			url: './modelSchoolCountArray',
			data: requestData
		}).done(function(modelList) {			
			
			$.ajax({
				type: 'GET',
				url: 'areaCountArray',
				data: requestData
			}).done(function(userList) {		
				$.ajax({
					type: 'GET',
					url: 'onlineCountByArea',
					data: requestData
				}).done(function(onlineList) {
					callback(modelList, userList, onlineList);
				});				
			});
		});
    }

    /**
     * 点击
     * @param dom1    canvas wrapper
     * @param render  draw graph
     */
    function canvas_full_screen(dom1, render) {
        // depends on common-scripts.js
        EventUtil.addHandler(dom1, "click", function (e) {
            e = e || window.event;
            e.preventDefault();

            var w = (window.innerWidth ? window.innerWidth : document.body.clientWidth) + "px";
            var h = (window.innerHeight ? window.innerHeight : document.body.clientHeight) + "px";

            var cover = document.getElementById("js-cover");
            setTimeout(function() {
                if (typeof cover === "undefined" || cover === null) {
                    var container = document.body.firstElementChild;
                    container.style.display = "hidden";
                    // add modal cover
                    cover = document.createElement("div");
                    cover.setAttribute("id", "js-cover");
                    cover.style.position = "fixed";
                    cover.style.top = 0;
                    cover.style.left = 0;
                    cover.style.width = w;
                    cover.style.height = h;
                    cover.style.zIndex = 999;
                    cover.style.backgroundColor = "#fefefe";
                    cover.style.opacity = 0.98;
                    document.body.appendChild(cover);

                    // regenerate canvas
                    var canvasWrapper = document.createElement("div");
                    canvasWrapper.style.width = w;
                    canvasWrapper.style.height = h;
                    cover.appendChild(canvasWrapper);
                    render(canvasWrapper);

                    // remove cover
                    var kdh = document.body.onkeydown;
                    // click
                    var button = document.createElement("button");
                    cover.appendChild(button);
                    button.classList.add("btn");
                    button.classList.add("btn-primary");
                    button.style.position = "fixed";
                    button.style.zIndex = 1000;
                    button.style.left = "100px";
                    button.style.top= "20px";
                    button.innerText = "返回";
                    button.title = "点击或按Esc返回";

                    EventUtil.addHandler(button, "click", function(e) {
                        e.preventDefault();
                        cover.parentNode.removeChild(cover);
                        document.body.onkeydown = kdh;
                        container.style.display = "block";
                    });
                    // esc
                    EventUtil.addHandler(document.body, "keydown", function(e) {
                        if (cover && cover.parentNode) {
                            if (e.keyCode === 27) {
                                cover.parentNode.removeChild(cover);
                                document.body.onkeydown = kdh;
                                container.style.display = "block";
                            }
                        }
                    });
                } else {
                    cover.parentNode.removeChild(cover);
                }
            }, 100);
        });
    }
});

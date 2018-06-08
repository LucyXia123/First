/**
 tab栏切换的公共样式
 */

$(function(){
	 /*1.一级分类默认渲染 第一个一级分类对应的二级分类*/
    getFirstCategoryData(function (data) {
        /*一级分类默认渲染*/
        /*模版的使用顺序：json数据,定义模版，调用模版，返回html*/
       // $('.cate_left ul').html(template('firstTemplate',data));
    	if(data.list){
			for(var i=0;i<data.list.length;i++){
				var str = $("<li>"+
                        "<a href='javasccript:;' data-toggle='tab' >"+
                         data.list[i].label+"</a></li>)");
				$("#myTab").append(str);
				
			}
		}
        /*绑定事件*/
        /*initSecondTapHandle();*/
        /*第一个一级分类对应的二级分类*/
        var categoryId = $('.cate_left ul li:first-child').find('a').attr('data-id');
        render(categoryId);
    });
			
});
/*获取一级分类的数据*/
var getFirstCategoryData = function (callback) {
    $.ajax({
        url:'/resource/topics',
        type:'get',
        data:'',
        dataType:'json',
        success:function (data) {
            callback && callback(data);
        }
    });
};
/*get带参数的获取数据ajax函数*/
var getReportListData = function (params, callback) {
    $.ajax({
        url: 'report/list',
        type: 'post',
        data: params,
        dataType: 'json',
        success: function (data) {
            /*存当前页码*/
        	 // console.log(data);
            //window.page = data.page;
            callback && callback(data);
        }
        
    });
};
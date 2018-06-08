$(function () {
   //	学校点亮区的功能
	getSchoolInfoData(function(data){
//		拿到数据定义模板引擎做渲染 
		if(data.schoolInfo){
			 $(".sr_lighter > .schoolName").append(
					  "<span class='schoolName'>"+data.schoolInfo.name+"</span><br>"
					   
		     );
			 $(".sr_lighter > .energyValue").html(
					    "当前获得了"+"<i "+"style='color:orangered'>"+data.schoolInfo.energy+"</i>点能量，暂时排名第"+"<i "+"style='color:orangered'>"+data.schoolInfo.rank+"</i>位"
			 );
			 if(data.schoolInfo.lighten){
				 $(".lightTips").text("恭喜！您的校区已被点亮!<br>\
	                不要骄傲，多多完成任务、积极投稿，继续集赞能量，获得更高奖励！");
			 }
			 else {
				 $(".lightTips").text("要继续加油哦！您的校区还没有被点亮!多多完成任务、积极投稿，继续集赞能量，与大家一起点亮校区！");
			 }
		} 
		return false;
});
   /*任务列表渲染*/
	getTaskListInfoData(function(data){
		
		//data.tasks="";
		if(!data.tasks){  //为空 则删除当前任务列表
			$(".sr_currentask").remove();
		}
       /*存在未做任务则渲染*/
		
	  for(var i=0;i<data.tasks.length;i++){
		
		  //当前只有两种任务 主题任务和答题任务
		  if(data.tasks[i].typeId == 1){
			
			 $(".sr_currentask .rec-l").html( "<strong>当前任务:     </strong>"+
			 		"<span>"+data.tasks[i].title+ "<a href='schoolReport-askAnswerTask.html'>去完成</a>" +"+" +data.tasks[i].energy+" 能量 <br></span>")
			 		
		             
		 
	       }else
	    	   {
	    	      $(".sr_currentask >.rec-l").html( "<strong>当前任务:     </strong>"+
				 		"<span>"+data.tasks[i].title+ "<a href='schoolReport_themeAsk.jsp'>去完成</a>" +"+" +data.tasks[i].energy+" 能量 <br></span>");
			    
	    	   
	    	   }
	     }
});
    
	getPublicThemeInfoData(function(data){
		
		/*tab栏切换数据获取  1：先渲染tab栏主题 一级 以及默认渲染第一个主题下的元素*/
		if(data.list){
			for(var i=0;i<data.list.length;i++){
				var str = $("<li>"+
                        "<a href='javasccript:;' data-toggle='tab' >"+
                          data.list[i].label+"</a></li>)");
				$("#myTab").append(str);
				$("#myTab li a").attr("data-id",i);
			  
			}
		}
}); 
		
			//当前公共主题
			
			var  params = {
		             pageNumber: 1 ,
		             pageSize: 12,
		             topicId:$("#myTab li a").attr("data-id"),
		             /*排序的方式*/
		         };
  
				 getReportListData(params,function(data){
					//当前主题
					if(!data){
						$("#myTabContent").text("当前主题还有具体内容!快来参与进来!").css({"margin":"30px auto","font-size":"26px","color":"red"});
					}
					$("#myTabContent").hide();
					
					$("#myTab li:first").find('a').addClass("tive");
					$("#myTabContent:first").show();
					for(i=0;i<data.reports.length;i++){
						//console.log(data);
		            var str1 = $( "<div class='pro-bd'>"+
                    "<ul>"+
                   "<li>" +
                       "<div class='pic'>" +" <img src='./images/pic1.png'></div>"+
                        "<div class='pro-title'>"+
                       "报道描述报道描述报道描述报道描述报道描述报道描述报道描述报道描述报道描述报道描述报道描述"+
                       "</div>"+
                        "<p>"+
                          "<span>by:"+data.reports[i].userName +"</span><br>"+
                          "<span> <img src='./images/u182.png'> "+data.reports[i].likeCount+"</span>&nbsp&nbsp&nbsp&nbsp&nbsp"+
                         " <span> <img src='./images/u185.png'>"+data.reports[i].commentCount +"</span>"+
                         "</p>"+
                       "<span id='reportStyle'>"+data.reports[i].typeName+"</span>" +
                   "</li>" +
                "</ul>"+
            "</div>"+
       "</div>");
		     $("#home").append(str1);	} 
	});
				 
				 $('#home').on('click','a',function (e) {
				        /*当前选中的时候不去加载*/
				        if($(this).parent().hasClass('tive'))
				        	return false;
				        /*样式的选中功能*/
				        $('#home li').removeClass('tive');
				        $(this).parent().addClass('tive');
				        /*数据的渲染*/
				        render( $(this).attr('data-id'));
				    });
//    	  
			
			 
				    
      });

				 
//});

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
var getPublicThemeInfoData = function (callback) {
    $.ajax({
        url:'/resource/topics',
        type:'get',
        data:'',
        dataType:'json',
        success:function (data) {
      //	console.log(data);
            callback && callback(data);
        }
    });
};
/*获取学校基本的数据*/
var getSchoolInfoData = function (callback) {
    $.ajax({
        url:'/report/school/info',
        type:'get',
        data:'',
        dataType:'json',
        success:function (data) {

            callback && callback(data);
        }
    });
};
/*获取任务列表的数据*/
var getTaskListInfoData = function (callback) {
    $.ajax({
        url:'task/list',
        type:'get',
        data:'',
        dataType:'json',
        success:function (data) {
        	
            callback && callback(data);
        }
    });
};


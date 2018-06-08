<%-- 
    Document   : img
    Created on : 2018-1-12, 19:29:41
    Author     : liwenbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--<link rel="stylesheet" type="text/css" href="../upload1/style.css">-->
        <!--<link rel="stylesheet" type="text/css" href="../upload1/jquery.fileupload.css">-->
    </head>
    <body>
<!--        <h1>Hello World!</h1>
         <a class="item"><span>添加文件</span><div id='fileupload' ><form id='form' method='post' enctype='multipart/form-data'><input type="file" id="file_window_id"  name="files" multiple />
                     activityId:<input type="text" name="activityId">
                     userNumber:<input type="text" name="userNumber">
                     instructor<input type="text" name="instructor">
                     slogan<input type="text" name="slogan">
                     <button submit   >oooooooooooooo</button>
                 </form></div></a>
        <div  id='progress' style="width: 100%;" >
            <div class="bar" style="width: 0%;height: 18px;" ><span style="color: black;position: relative;" id="progress_display_percent_id"></span></div>
        </div>-->
<!--<form method='post' action="/insertActivityParticipants" enctype='multipart/form-data'>
    <table>
        <tr>
            <td><input type="file" id="file_window_id"  name="files" multiple="multiple" /></td>
        </tr>
        <tr>
            <td>activityId<input type="text" name="activityId"></td>
        </tr>
        <tr>
            <td>userNumber<input type="text" name="userNumber"></td>
        </tr>
        <tr>
            <td>instructor<input type="text" name="instructor"></td>
        </tr>
        <tr>
            <td>slogan<input type="text" name="slogan"></td>
        </tr>
         <tr>
            <td>status<input type="status" name="status" value="已审核"></td>
        </tr>
        <tr>
            <td><input type="submit" value="上传报名信息"></td>
        </tr>
       
    </table>
</form>
<span>上传作品</span>-->
<form method='post' action="/uploadActivityMaterialsOfFile" enctype='multipart/form-data'>
    <table>
        <tr>
            <td><input type="file"  name="files" multiple="multiple" /></td>
        </tr>
        <tr>
            <td>participantId<input type="text" name="participantId"></td>
        </tr>
        <tr>
            <td>usernumber<input type="text" name="usernumber"></td>
        </tr>
        <tr>
            <td>title<input type="text" name="title"></td>
        </tr>
        <tr>
            <td>flag<input type="text" name="flag"></td>
        </tr><!--
         <tr>
            <td>status<input type="status" name="status" value="已审核"></td>
        </tr>-->
        <tr>
            <td><input type="submit" value="上传素材"></td>
        </tr>
       
    </table>
</form>
<button id="dddddddddd">00000000000</button>
<button id="hhhhh">hhhhhhhhhhhhhh</button>
<select id="one_level_id">    
</select>
<select id="two_level_id">
    
</select>
<button id="aaaa">点击</button>
<input type="button" id="btn_id" value="提交烟瘴">
         <img  src="http://localhost:8989/writerActivity/1234567890/1234567890.jpg"/>
                 <script src="../upload1/jquery.js"></script>
        <script src="../upload1/jquery-form.js"></script>
        <script src="../upload1/jquery.ui.widget.js"></script>
        <!--<script src="../upload1/jquery.iframe-transport.js"></script>-->
        <script src="../upload1/jquery.fileupload.js"></script>
        <!--<script src="../upload1/sprintf.min.js"></script>-->
        <script>
            $(function ()
            {
                var school= {
"citylist":[
   	{"p":"开封","c":[{"n":"市五中"},{"n":"市七中"},{"n":"市八中"},{"n":"市第十三中学"},{"n":"市第二十七中学"},{"n":"市十四中东校区(21中)"},{"n":"市33中"},{"n":"市集英小学"},{"n":"市回民中学"},{"n":"立洋外国语学校"}]},
   	{"p":"濮阳","c":[{"n":"市范县第一初级中学"},{"n":"市南乐县第二实验小学"},{"n":"市南乐县第二初级中学"},{"n":"市第三中学"},{"n":"市第八中学"},{"n":"市南乐县城关镇初级中学"},{"n":"市范县思源学校"},{"n":"市油田第十二中学"}]},
   	{"p":"商丘","c":[{"n":"市第一中学"},{"n":"市第一高级中学"},{"n":"市实验小学"},{"n":"市第一实验小学"},{"n":"市实验中学"},{"n":"市第二高级中学"},{"n":"市行知学校"},{"n":"工学院附属兴华学校"},{"n":"市外国语实验小学"}]}
   ]
}

$.map(school['citylist'],function(ele,index)
{
    var option = $("<option value="+ele.p+">").html(ele.p);
    $("#one_level_id").append(option);
});
var default_value =$("#one_level_id").val();
$.map(school['citylist'],function(ele,index)
{
    if (ele.p == default_value)
    {
        $.map(ele.c,function(child,index0)
        {
            var option = $("<option value="+child.n+">").html(child.n);
            $("#two_level_id").append(option);
        });
        
    }
});
$("#one_level_id").change(function()
{
    var change_name = $(this).val();
    console.log("--->"+change_name);
     $("#two_level_id").html("");
    $.map(school['citylist'],function(ele,index)
    {
        if (ele.p == change_name)
        {
            $.map(ele.c,function(child,index0)
            {
                var option = $("<option value="+child.n+">").html(child.n);
                $("#two_level_id").append(option);
            });

        }
    });
});

$("#btn_id").click(function()
{
    console.log("1=====>"+$("#one_level_id").val());
    console.log("2=====>"+$("#two_level_id").val());
});

//                var uploader = $("#fileupload"); onclick="file_window_id.click()"
//                uploader.fileupload({
//                url: "/insertActivityParticipants",
//                autoUpload: false,
//                singleFileUploads: false,   // true: 每个文件各自使用一个add事件进入队列，并单独占用一个POST请求上传
//                                            // false: 同一批次的文件集合通过一个add事件进入队列，并通过同一个POST请求上传
//                                            // 所谓批次，就是指一次选择（browse）或拖放（drag & drop）。
//                                            // 也就是说，每个add事件分别对应一个POST消息。
//                sequentialUploads: false,   // true: POST请求按序依次发出，且后一请求仅在前一请求执行完毕后才发出（阻塞模式）。
//                                            // false: POST请求同时发出，并行上传（非阻塞式）。
//                add: function (e, data) {
//                    alert(data.files.length);
//                    if (data.files.length > 0)
//                    {
//                        data.submit();
//                    } 
//                },
//                progressall: function (e, data) {
//                    var pg = parseInt(100 * data.loaded / data.total, 10);
//                    $("#progress .bar").css({"width": pg+"%", "background": "yellow"});
//                    if (pg >= 90)
//                    {
//                        $("#progress_display_percent_id").css("margin-left",(pg-15)+"%").html(pg+"%");
//                    }
//                    else
//                    {
//                        $("#progress_display_percent_id").css("margin-left",pg+"%").html(pg+"%");
//                    }
//
//                },
//                done: function (e, data) {
//                    // finish
//                    $("#progress .bar").css({"width": "100%", "background": "green"});
//                    $("#message_id").html("成功上传个数：" + data.result.count);
//                    $("#message_id").fadeIn();
//                    $("#message_id").fadeOut(10000);
//                },
//                fail: function (e, data) {
//                    // cancel or fail
//                    $("#progress .bar").css("background", "red");
//                    $("#message_id").html("文件上传失败！");
//                    $("#message_id").fadeIn();
//                    $("#message_id").fadeOut(10000);
//                }
//            });
$("#hhhhh").click(function()
{
    //"{\"avatarUrl\":\"C7D6BB1B1EB0000159B9F36BF62016F7\",\"activityId\":1,\"userNumber\":\"STU201709301537110001\",\"instructor\":\"2\",\"slogan\":\"1\"}"
    //JSON.stringify(map){\"avatarUrl\":\"C7D6BB1B1EB0000159B9F36BF62016F7\",\"activityId\":1,\"userNumber\":\"STU201709301537110001\",\"instructor\":\"2\",\"slogan\":\"1\"}
       
//        @RequestParam("avatarUrl") String avatarUrl,
//                @RequestParam("activityId") int activityId,
//                @RequestParam("userNumber") String userNumber,
//                @RequestParam("instructor") String instructor,
//                @RequestParam("slogan") String slogan
//                map['content']="我爱北京天安门";
//                map['usernumber']="STU201801171458220001";
//                map['participantId']=37;
//                map['title']="我爱北京天安门";
//            map['commentatorUsernumber']="STU201801141941140001";
//            map['participantUsernumber'] = "STU201709301537110001";
//            map['participantId'] = 37;
//            map['activityId'] = 1;
//                map['participantId'] = 37;
//                map['title'] = "0000000";
//                map['works'] = "[{\"img_url\":\"C7D6FC1129D000017CAF370083941B7B\"}]";
//                map['usernumber'] = "STU201801171537450002";
                //"{\"participantId\":37,\"title\":\"0000000\",\"works\":\"[{\"img_url\":\"C7D6FC1129D000017CAF370083941B7B\"}]\",\"usernumber\":\"STU201801171537450002\"}"
                
//                map['avatarUrl'] = "http://localhost:9090/avatar";
//                map['activityId'] = 1;
//                map['userNumber'] = "STU201801171537450002";
//                map['instructor'] = "付云";
//                map['slogan'] ="ooooooooooooo";
/**
 * private int participantId;
    private String title;
    private String content;
    private String usernumber;
 */
                var map = {};
                map['participantId'] = "69";
                map['title'] = "1ddddddddd";
                map['content'] = "1ddd555555555555555555ddddd";
                map['usernumber'] = "STU201801171537450002";
                console.log(JSON.stringify(map));
                $.ajax(
                        {
                            url:"http://localhost:8080/student/save",
                            type:"post",
                            dataType:"json",
//                            contentType:"application/json",
                            data:{"area":"开封","classname":"1","email":"11@qq.com","grade":"1","idnum":"411111111111111111","mobile":"13800000011","pwd":"000000","realname":"旺旺","school":"开封市五中","sms":"","username":"13800000001","version":"1","type":"学生"},
                            success:function(data)
                            {
                                console.log(data);
                            },
                            error: function(data)
                            {
                                console.log(data);
                            }
                        });
            });
            
            $("#dddddddddd").click(function()
            {
                alert("ppppppppppppppp");
//                var map = {};
//                map['participantId']=69;
//                map['title']="三九胃泰";
//                map['content']="好吃天天见";
//                map['usernumber']="STU201801181526460001";
//{"area":"开封","classname":"1","email":"","grade":"1","idnum":"","mobile":"13811111112","realname":"旺旺","school":"开封市七中","username":"test11","version":"1","usernumber":"STU201801191627270007","type":"学生"}
        var aa = {"area":"开封","classname":"1","email":"","grade":"1","idnum":"","mobile":"13811111112","realname":"旺旺","school":"开封市七中","username":"test11","version":"1","usernumber":"STU201801191627270007","type":"学生"};        
        $.ajax(
                        {
                            url:"http://localhost:8080/updateCustomInfo",
                            type:"post",
                            dataType:"json",
                            contentType:"application/json",
                            data:JSON.stringify(aa),
                            success:function(data)
                            {
                                console.log(data);
                            },
                            error: function(data)
                            {
                                console.log(data);
                            }
                        });
//                        var ddd ="濮阳";
//            var dddd = "濮阳市";
//            var dddddddd= "濮阳市11";
//            console.log("==>"+ddd.localeCompare("濮阳"));
//            console.log("==>"+dddd.localeCompare("濮阳市"));
//            console.log("===>"+dddddddd.localeCompare("濮阳市"));
            });
//  
$("#aaaa").click(function()
{
//    $.ajax(
//        {
//            url:"http://localhost:8080/activity/listActivityParticipants?activityId=1&&pageSize=0&&pageNumber=0&&school=&&grade=1&&classname=&&realname=2333",
//            type:"get",
//            dataType:"json",
//            contentType:"application/json",
//            success:function(data)
//            {
//                console.log(data);
//            }
//        });
alert("ok");
        $.ajax(
         {
             url:"http://localhost:8080/student/getuser?userid=STU2018012311183509013",
             type:"get",
             success:function(data)
             {
                console.log(data);
             },
             error:function(data)
             {
                 console.log(data);
             }
             
         });
});
});
                
        </script>
    </body>
</html>

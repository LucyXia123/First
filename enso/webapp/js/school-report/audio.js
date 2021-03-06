/**
 * Created by zhenye on 2018/6/1.
 */
$(function () {
    /*获取需要操作的元素*/
    var $video = $('video');
    /*因为api是属于原生的dom元素的*/
    var video = $video.get(0);

    /*播放和暂停*/
    var $switch = $('.switch');
    /*总时长*/
    var $total = $('.total');
    /*进度条*/
    var $line = $('.line');
    /*当前播放时间*/
    var $current = $('.current');
    /*全屏按钮*/
    var $expand = $('.expand');
    /*灰色进度条*/
    var $bar = $('.bar');

    var formatTime = function (time) {
        /*00:00:00*/
        var h = Math.floor(time/3600);
        var m = Math.floor(time%3600/60);
        var s = Math.floor(time%60);
        return (h<10?'0'+h:h)+':'+(m<10?'0'+m:m)+':'+(s<10?'0'+s:s)
    }

    /*1. 需要在加载是时候  显示加载状态*/
    /*当前视频加载到可以播放了就可以隐藏这个状态*/
    video.oncanplay = function () {
        $video.show();
        /*显示总时间*/
        var timeStr = formatTime(video.duration);
        /*6. 总时间显示*/
        $total.html(timeStr);
    }

    /*2. 播放*//*3. 暂停*/
    $switch.on('click',function () {
        if($switch.hasClass('fa-play')){
            video.play();
            $switch.removeClass('fa-play').addClass('fa-pause');
        }else{
            video.pause();
            $switch.removeClass('fa-pause').addClass('fa-play');
        }
    });

    /*4. 进度条显示*/
    video.ontimeupdate = function () {
        //console.log('当前播放时间：'+video.currentTime);
        /*计算  进度条的宽度根据  当前播放时间/总时间  */
        var p = video.currentTime/video.duration*100+'%';
        $line.css('width',p);
        /*5. 播放时间显示*/
        $current.html(formatTime(video.currentTime));
    }
    /*7. 全屏操作*/
    $expand.on('click',function () {
        if($(this).hasClass('fa-arrows-alt')){
            video.webkitRequestFullScreen();
            $(this).removeClass('fa-arrows-alt').addClass('fa-compress');
        }else{
            document.webkitCancelFullScreen();
            $(this).addClass('fa-arrows-alt').removeClass('fa-compress');
        }

    })
    /*8. 视频播放完成重置播放*/
    video.onended = function () {
        video.currentTime = 0;
        $switch.removeClass('fa-pause').addClass('fa-play');
    }
    /*9. 跃进功能*/
    $bar.on('click',function (e) {
        /*获取点击的位置距离进度条左侧的距离*/
        var p = e.offsetX/$bar.width();
        var goTime = p * video.duration;
        video.currentTime = goTime;
    })
});
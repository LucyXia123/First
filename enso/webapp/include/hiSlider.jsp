<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>

<link type="text/css" rel="stylesheet" href="./bootstrap/css/jquery.hiSlider.css" />
<style>
.hiSlider {
  overflow: hidden;
  height: 260px;
  width: 460px;
}
.hiSlider-item {
  float: left;
  width: 460px;
  position: absolute;
}
</style>
  <ul id="js-slider" class="hiSlider">
			<li class="hiSlider-item" data-title="我校举行教师教学比赛...">
              <a href="http://mp.weixin.qq.com/s?__biz=MzI3MTA3MDIwOQ==&mid=2657257058&idx=1&sn=025ec979922b8706928b74eb53a097c2&chksm=f151430cc626ca1a8e3d55ec1017b1963fa1dafc2f5903c45e0f630cf1f8531fc0902473d7e8&mpshare=1&scene=1&srcid=0928YDULJGL6eXzgHd3cctbY#rd" target="_blank">
                <img src="./images/school/slider/1.jpg" alt="我校举行教师教学比赛...">
			   </a>
            </li>
			<li class="hiSlider-item" data-title="我校开展“喜迎十九大，砥砺奋进的五年”演讲比赛...">
			  <a href="http://mp.weixin.qq.com/s?__biz=MzI3MTA3MDIwOQ==&mid=2657257035&idx=2&sn=1ac74022740e2f62ee8b9d6c7b435635&chksm=f1514325c626ca33172fc609e7683c9c21c9b7940cf37220a5c9081ad44d18c998b4b5cd1857&mpshare=1&scene=1&srcid=0922BpY5eCuhZWnw9v0VPwwH#rd"
                  target="_blank"> <img src="./images/school/slider/2.jpg"
                                        alt="我校开展“喜迎十九大，砥砺奋进的五年”演讲比赛...">
			  </a>
			</li>
			<li class="hiSlider-item" data-title="范县第一初级中学党支部推进“两学一做”工作座谈会...">
			  <a href="http://mp.weixin.qq.com/s?__biz=MzI3MTA3MDIwOQ==&mid=2657256625&idx=1&sn=e41e88b7aad29a017f844117c8c65c1d&chksm=f15141dfc626c8c966866e0e6be4bd92c290465317f16d557742fc3a11d1978dca48f5baf675&mpshare=1&scene=1&srcid=0511oS2Pb4jGD8asXrrWFLRA#rd"
                  target="_blank"> <img src="./images/school/slider/3.jpg"
                                        alt="范县第一初级中学党支部推进“两学一做”工作座谈会...">
			  </a>
			</li>
			<li class="hiSlider-item" data-title="商丘市实验小学四年级学生开展采摘花生社会实践活动...">
			  <a href="http://mp.weixin.qq.com/s?__biz=MzA3MzI0NjU5OQ==&mid=2651087764&idx=3&sn=35201fb4c9116e77e526934b23076ea3&chksm=84e1317cb396b86a41eadfd1a67c35407bd8c2c6803dc5c34d83fde69c71ed070e4b5de7378f&mpshare=1&scene=1&srcid=0928B0fWKawavzgVUoAlKcLr#rd"
                  target="_blank"> <img src="./images/school/slider/4.jpg"
                                        alt="商丘市实验小学四年级学生开展采摘花生社会实践活动...">
			  </a>
			</li>
			<li class="hiSlider-item" data-title="市十三中新学期拓展训练活动精彩纷呈"><a href="http://wap.kfqss.cn/html/jiaoyu/201709/17918.html?suid=4EC8099CEE410487&from=timeline" target="_blank">
              <img src="./images/school/slider/5.jpg" alt="市十三中新学期拓展训练活动精彩纷呈">
			</a></li>
 </ul>
 <script src="./bootstrap/js/jquery.hiSlider.js"></script>
 <script>
 // 新闻轮播
 $('#js-slider').hiSlider({
     isFlexible: true,
     mode: 'fade',
     intervalTime: 3e3,
     isSupportTouch: true,
     isShowTitle: true,
     isShowPage: true,
     titleAttr: function() {
         return $('img', this).attr('alt');
     },
     onSelected: function() {}
 });
 </script>
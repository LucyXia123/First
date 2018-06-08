<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
    <!--配合频道的链接-->
<style>
/* 配合频道的链接 */
.link {
  padding-bottom: 40px;
}

.link .wz {
  width: 900px;
  height: auto;
  overflow: hidden;
  margin: 0 auto;
}

.link .wz>a {
    display: inline-block;
    height: 22px;
    line-height: 20px;
    color: #525252;
    font-size: 22px;
    word-break: keep-all;
    padding: 1em;
}

.link .wz>a:first-child {
  padding-left: 1em;
}
</style>
    <div class="module link">
      <div class="module-header">
	    <h2>频道链接</h2>
      </div>
      <div class="wz" id="js-channel-link">
      <!-- <a href="http://www.hntv.tv/" target="_blank">河南电视台</a> -->
      </div>
    </div>
 <script>
 // 被 model-school.jsp引入
 (function() {
	 var target = document.getElementById("js-channel-link");
	 var xhr = window.XMLHttpRequest ? (new window.XMLHttpRequest()) : (new ActiveXObject("Microsoft.XMLHTTP"));
	 xhr.open("GET", "./data/link.json", true);
	 xhr.send();
	 xhr.onreadystatechange = function(e) {
		 if (xhr.readyState==4) {
			 if (xhr.status==200) {
				 var resp = e.currentTarget.response;
				 var data = JSON.parse(resp);   // {list:[{xxx},{xxx},...]]}
				 target.innerHTML = "";
				 var a = null;
				 data.list.map(function(elem) {
					a = document.createElement("a");
					a.href = elem.url;
					a.target = "_blank";
					a.innerText = elem.name;
					target.appendChild(a);
					return a;
				 });				 
			 } else {
				 console.error("xhr set channel link failed.");
			 }
		 }
	 }
 })();
 </script>
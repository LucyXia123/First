// depends on @file: common.js
// override alert
function alert(msg) {
	var $modal = $("#J_modal_alert");
	if ($modal.length===0) {
		$modal = $("<div id=\"J_modal_alert\" class=\"modal\" tabindex=\"-1\" role=\"dialog\">");
		var $dialog = $("<div class=\"modal-dialog modal-sm\" role=\"document\">");
		$modal.append($dialog);
		var $content = $("<div class=\"modal-content\">");
		$dialog.append($content);
		
		var $header = $("<div class=\"modal-header\">\r\n" + 
				"        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\r\n" + 
				"        <h4 class=\"modal-title\">&times;</h4>\r\n" +
				"      </div>");
		$content.append($header);
		
		var $body = $("<div class=\"modal-body\">").append($("<p>").html(msg));
		$content.append($body);
		
		var $footer = $("<div class=\"modal-footer\">");
		$footer.html("<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">确定</button>");
		$content.append($footer);
	}
	if ($modal.modal) {
		$modal.modal("show");
	} else {
		var script = new Script(function() {
			$modal.modal("show");
		});
		script.set("./bootstrap/js/bootstrap.min.js");
	}	
}
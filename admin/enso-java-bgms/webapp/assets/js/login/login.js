$(function() {
	// show/hide placeholder
	(function() {
		var form = document.forms["login"];
		var username = form.username, password = form.password;
		var placeholders = document.getElementsByClassName("placeholder");

		window.onload = function() {
			placeholders[0].style.display = (username.value === "") ? "block"
					: "none";
			placeholders[1].style.display = (password.value === "") ? "block"
					: "none";
		};

		var createHolder = function(input) {
			input.onblur = function() {
				if (!this.value) {
					this.nextElementSibling.style.display = "block";
				}
			};
			input.onfocus = function() {};
			input.onkeydown = function() {
				this.nextElementSibling.style.display = "none";
			}
		};
		createHolder(username);
		createHolder(password);

		Array.prototype.forEach.call(placeholders, function(elem) {
			elem.onclick = function(e) {
				e.preventDefault();
				// this.style.display = 'none';
				this.previousElementSibling.focus();
			};
		});
	}).call(this);

	// login form
	$('.page-container form').submit(function(e) {
		e.preventDefault();
		var form = this;

		var username = $(this).find('.username').val();
		var password = $(this).find('.password').val();
		// username required
		if (username == '') {
			$(this).find('.error').fadeOut('fast', function() {
				$(this).css('top', '27px');
			});
			$(this).find('.error').fadeIn('fast', function() {
				$(this).parent().find('.username').focus();
			});
			return false;
		}
		// password required
		if (password === '') {
			$(this).find('.error').fadeOut('fast', function() {
				$(this).css('top', '96px');
			});
			$(this).find('.error').fadeIn('fast', function() {
				$(this).parent().find('.password').focus();
			});
			return false;
		}
		$.getJSON("./data/login.json", function(data) {
			if (username === data.username && password === data.password) {
				$.cookie("username", username, {"expires":1});
				location.href = "index.html";
			} else {
				// <div class="alert alert-danger" role="alert">用户名或密码错误</div>
				var $alert = $(".alert");
				if ($alert.length <1) {
					$alert = $("<div>").addClass("alert").addClass("alert-danger")
							.attr("role", "alert").html("用户名或密码错误");
					$(form).prepend($alert.show());	
				} else {
					$alert.show();
				}
			}
		});
	});

	$('.page-container form .username, .page-container form .password').keyup(
			function() {
				$(this).parent().find('.error').fadeOut('fast');
			});
	$(document.body).trigger("click");

});

function nexRegister() {
		var data = {
		    "lastModifiedBy": "nitin talwar",
		    "sportsType": "Individual",
		    "userEmailAddress": $('#nex-email-id').val(),
		    "userPassword": $('#nex-password').val(),
		    "userTypePID": 0
		}; 
		$.ajax({
			url : 'http://localhost:9080/nSports/rest/sports/registerUser',
			dataType : 'json',
			type : 'post',
			contentType : 'application/json',
			data : JSON.stringify(data),
			success : function(data, textStatus, jQxhr) {
				console.log(data);
			},
			error : function(jqXhr, textStatus, errorThrown) {
				console.log(errorThrown);
			}
		});

	}
	function selectProfile(className, text) {
		$('.' + className).attr("data-placeholder", text);
		$('#reg-span').html(text);
		$('.reg-ul').css("z-index", "10");
		$('.reg-ul').css("display", "none");
		//data-placeholder

	}

	function popButton(obj) {
		$.each($('.nex-btn'), function(index, item) {
			$(item).css("z-index", "10");
			$(item).attr('placeholder', $(item).attr('data-placeholder'));
		});
		$(obj).css("z-index", "10000");
		$('.login-msg').text($(obj).attr('data-msg'));

		var regFlag = $(obj).hasClass('register-btn-reg');
		var ptFlag = $(obj).hasClass('register-btn-pt');
		$('.reg-ul').css("z-index", "10");
		$('.reg-ul').css("display", "none");
		if (!regFlag && !ptFlag) {
			$(obj).attr('placeholder', /* $(obj).attr('data-msg') */'...');
			$(obj).focus();
		} else if (ptFlag) {
			$('.reg-ul').css("z-index", "10001");
			$('.reg-ul').css("display", "block");
		} else {

		}

	}
UTIL = function() {

	send = function(URL, options, successCallBack, errCallBack) {

		$.ajax({
			url : URL,
			cache : false,
			data : JSON.stringify(options),
			method : "POST",
			contentType : "application/JSON",
			// dataType : "application/JSON",
			success : function(data, textStatus, xhr) {
				if (successCallBack) {
					successCallBack(data)
				}
			},
			error : function(xhr, textStatus, error) {
				if(xhr.responseJSON && xhr.responseJSON.errorDetails){
					var msg = "";
					var count = 0;
					for (val in xhr.responseJSON.errorDetails){
						var errData = xhr.responseJSON.errorDetails[val];
						if(msg != "")
							msg += '<br><br>';							
						msg += (++count) + ") " + val;
						if(errData && errData.length > 0)
							msg += " : " + errData.join();
					}					
					toastMessage(msg);
				}
			},
			complete : function(xhr, textStatus) {
			}
		})
	},

	renderUITemplate = function(source, data, destId) {
		var template = Handlebars.compile(source);
		$('#' + destId).html(template(data));
	},

	scrollToTop = function() {
		document.body.scrollTop = 0;
		document.documentElement.scrollTop = 0;
	},

	validateForm = function() {
		clearFormValidation();
		$('form').find('input:enabled, select:enabled').each(
				function(idx, node) {					
					if ($(this).data('rules')) {
						var rules = JSON.parse($(this).data('rules').replace(/'/g, '"'));
						$(rules).each(function(idx, rule) {
							if (rule.mandatory && $(node).val().trim().length == 0) {
									$(node).next().append($('<span/>', {'text' : rule.mandatory}));
							} else if (rule.number_mandatory && (!$.isNumeric($(node).val().trim()) || $(node).val() == 0)) {
								$(node).next().append($('<span/>', {'text' : rule.number_mandatory}));
							}else if (rule.number && !$.isNumeric($(node).val().trim())) {
								$(node).next().append($('<span/>', {'text' : rule.number}));
							}
						});
						if($(node).next().find('span').length > 0){
							$(node).parent().addClass('error-container');
						}
					}
				});
		return $('form').find('div.error-container').length == 0;
	},
	
	clearFormValidation = function(){
		$('form').find('.error-container').each(function(idx, node){
			$(node).removeClass('error-container').children('.error-text').empty();
		});
	}, 
	
	toastMessage = function(msg){
		toastr.options = {
				progressBar : true,
				positionClass : 'toast-top-right',
				showDuration : 300,
				hideDuration : 1000,
				showEasing : 'swing',
				hideEasing : 'linear'				
		}
		toastr['error'](msg);
	}

	return {
		send : send,
		renderUITemplate : renderUITemplate,
		scrollToTop : scrollToTop,
		validateForm : validateForm,
		clearFormValidation : clearFormValidation,
		toastMessage : toastMessage
	}

}();
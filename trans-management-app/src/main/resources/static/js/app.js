$(document).ready(
		function(event) {

			$('body').on(
					'click',
					'.main-menu',
					function(event) {

						if ($(this).find('i').hasClass('fa-angle-down')) {

							$('#main_header').find('.fa-angle-up').each(
									function(idx, val) {
										$(val).removeClass('fa-angle-up')
												.addClass('fa-angle-down');
										$('#' + $(val).parent().data('target'))
												.hide(700);
									});
							$('#' + $(this).data('parent')).addClass(
							'white-header');
							$('#' + $(this).data('target')).show(700);							
							$(this).find('i').removeClass('fa-angle-down')
									.addClass('fa-angle-up');
						} else {
							var that = this;
							$('#' + $(this).data('target')).hide(700, function(){
								$('#' + $(that).data('parent')).removeClass(
								'white-header');
								$(that).find('i').removeClass('fa-angle-up')
								.addClass('fa-angle-down');
							});
							
							
						}

					});
		});
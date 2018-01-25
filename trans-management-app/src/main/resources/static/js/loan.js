$(document).ready(
		function(event) {

			$('.datepicker').datepicker({
				format : 'dd-M-yyyy',
				todayHighlight : true
			});
			
			var fieldTypes = ["loan_status", "loan_type"];
			var flTypeMapping = {"loan_status" : "loan_status", "loan_type" : "loan_type"};
			UTIL.send('/onetime/findOneTimes', fieldTypes, onetimeCallback);			
			function onetimeCallback(data){
				if(data != null && data.details != null){					
					for (oneTimeMapKey in data.details){						
						$(data.details[oneTimeMapKey]).each(function(idx, onetime){
							$('#'+flTypeMapping[oneTimeMapKey]).append(
									'<option value="' + onetime.fieldVal
											+ '">' + onetime.fieldVal
											+ '</option>');
						});
					}
				}
			}

			UTIL.send('/loan/findAll', null, findAllCallback);

			function findAllCallback(data) {
				UTIL.renderUITemplate($('#loan_details_template').html(), data,
						'loan_details_row');
				resetOrPopulateForm(null);
			}

			$('form').on('click', '#btn_save', function(event) {
				if (UTIL.validateForm()) {
					var data = new CreateLoan();
					UTIL.send('/loan/save', data, findAllCallback);
				}
			});

			$('form').on('click', '#btn_clear', function(event) {
				UTIL.clearFormValidation();
				UTIL.send('/loan/findAll', null, findAllCallback);
			});

			$('form').on(
					'blur',
					'#loan_name',
					function(event) {
						UTIL.send('/loan/find/' + $('#loan_name').val(), null,
								findCallback);
			});
			
			function findCallback(data){
				if(data != null && data.details != null){
					resetOrPopulateForm(data);
				}
			}

			$('body').on(
					'click',
					'.data-container',
					function(event) {
						if ($(event.target).is('span')) {
							UTIL.send('/loan/delete/' + $(this).data('value'),
									null, findAllCallback);
						} else {
							UTIL.send('/loan/find/' + $(this).data('value'),
									null, resetOrPopulateForm);
						}
					});
		});

function CreateLoan() {
	this.loanName = $('#loan_name').val();
	this.loanDescription = $('#loan_description').val();
	this.loanAmount = $('#loan_amount').val();
	this.loanStartDate = $('#loan_startdate').val();
	this.loanEndDate = $('#loan_enddate').val();
	this.loanStatus = $('#loan_status').val();
	this.loanPeriod = $('#loan_period').val();
	this.loanType = $('#loan_type').val();
}

function resetOrPopulateForm(data) {
	$('#loan_name').val(data == null ? "" : data.details.loanName);
	$('#loan_description')
			.val(data == null ? "" : data.details.loanDescription);
	$('#loan_startdate').val(data == null ? "" : data.details.loanStartDate);
	$('#loan_enddate').val(data == null ? "" : data.details.loanEndDate);
	$('#loan_amount').val(data == null ? 0 : data.details.loanAmount);
	$('#loan_status').val(data == null ? "" : data.details.loanStatus);
	$('#loan_period').val(data == null ? 0 : data.details.loanPeriod);
	$('#loan_type').val(data == null ? 0 : data.details.loanType);
	$('#loan_name').prop('disabled', data == null ? false : true);
	UTIL.scrollToTop();
}
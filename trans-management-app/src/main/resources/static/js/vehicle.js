$(document).ready(
		function(event) {
			var fieldTypes = ["vehicle_type", "vehicle_status"];
			var flTypeMapping = {"vehicle_type" : "vehicle_type","vehicle_status" : "vehicle_status"};
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

			UTIL.send('/loan/findAll', null, LoansCallback);

			function LoansCallback(data) {
				if (data.details != null) {
					$(data.details).each(
							function(idx, val) {
								$('#vehicle_loans')
										.append(
												'<option>' + val.loanName
														+ '</option>');
							});
				}

				$('#vehicle_loans').selectpicker({
					multipleSeparator : ',',
					noneSelectedText : 'No Loans selected',
					tickIcon : 'fa fa-check'
				});

				$('#vehicle_loans').selectpicker('refresh');

			}

			UTIL.send('/vehicle/findAll', null, findAllCallback);

			function findAllCallback(data) {
				UTIL.renderUITemplate($('#vehicle_details_template').html(),
						data, 'vehicle_details_row');
				resetOrPopulateForm(null);
			}

			$('form').on('click', '#btn_save', function(event) {
				if (UTIL.validateForm()) {
					var data = new CreateVehicle();
					UTIL.send('/vehicle/save', data, findAllCallback);
				}
			});

			$('form').on('click', '#btn_clear', function(event) {
				UTIL.clearFormValidation();
				UTIL.send('/vehicle/findAll', null, findAllCallback);
			});

			$('form').on(
					'blur',
					'#vehicle_name',
					function(event) {
						UTIL.send('/vehicle/find/' + $('#vehicle_name').val(),
								null, findCallback);
					});

			function findCallback(data) {
				if (data != null && data.details != null) {
					resetOrPopulateForm(data);
				}
			}

			$('body').on(
					'click',
					'.data-container',
					function(event) {
						if ($(event.target).is('span')) {
							UTIL.send('/vehicle/delete/'
									+ $(this).data('value'), null,
									findAllCallback);
							resetOrPopulateForm(null);
						} else {
							UTIL.send('/vehicle/find/' + $(this).data('value'),
									null, resetOrPopulateForm);
						}
					});
		});

function CreateVehicle() {
	this.vehicleName = $('#vehicle_name').val();
	this.vehicleNumber = $('#vehicle_number').val();
	this.vehicleType = $('#vehicle_type').val();
	this.vehicleStatus = $('#vehicle_status').val();
	this.vehicleCost = $('#vehicle_cost').val();
	this.vehicleLoans = $('#vehicle_loans').val() != null ? $('#vehicle_loans')
			.val().join() : null;
	this.investment = $('#investment').val();
}

function resetOrPopulateForm(data) {
	$('#vehicle_name').val(data == null ? "" : data.details.vehicleName);
	$('#vehicle_number').val(data == null ? "" : data.details.vehicleNumber);
	$('#vehicle_type').val(data == null ? "" : data.details.vehicleType);
	$('#vehicle_status').val(data == null ? "" : data.details.vehicleStatus);
	$('#vehicle_cost').val(data == null ? 0 : data.details.vehicleCost);
	$('#investment').val(data == null ? 0 : data.details.investment);
	if (data == null || data.details.vehicleLoans == null)
		$('#vehicle_loans').selectpicker('deselectAll');
	else
		$('#vehicle_loans').selectpicker('val',
				data.details.vehicleLoans.split(','));
	$('#vehicle_name').prop('disabled', data == null ? false : true);
	UTIL.scrollToTop();
}

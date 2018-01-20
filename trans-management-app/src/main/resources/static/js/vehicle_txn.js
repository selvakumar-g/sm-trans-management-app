$(document).ready(
		function(event) {

			$('.datepicker').datepicker({
				format : 'dd-M-yyyy',
				todayHighlight : true
			});
			
			var fieldTypes = ["vehicle_txn_attribute"];
			var flTypeMapping = {"vehicle_txn_attribute":"transactionAttribute"};
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
			
			

			UTIL.send('/vehicle/findAll', null, findAllVehicleCallback);

			function findAllVehicleCallback(data) {
				if (data != null && data.details != null) {
					$(data.details).each(
							function(idx, vehicle) {
								$('#vehicleName').append(
										'<option value="' + vehicle.vehicleName
												+ '">' + vehicle.vehicleName
												+ '</option>');
							});
				}
			}

			$('form').on(
					'change',
					'#vehicleName',
					function(event) {
						if ($(this).val()) {
							UTIL.send('/vehicle_txn/findVehicleTxn/'
									+ $(this).val(), null,
									findAllVehicleTxnCallback);
							resetOrPopulateForm(null, false);
						} else {
							resetOrPopulateForm(null, true);
						}
					});

			$('form').on(
					'click',
					'#btn_save',
					function(event) {
						if (UTIL.validateForm()) {
							var data = new CreateVehicleTxn();
							UTIL.send('/vehicle_txn/save', data,
									findAllVehicleTxnCallback);
							resetOrPopulateForm(null, false);
						}
					});

			$('form').on('click', '#btn_clear', function(event) {
				UTIL.clearFormValidation();
				resetOrPopulateForm(null, true);
				createDataTable(null);
			});

		});

function CreateVehicleTxn() {
	this.vehicleName = $('#vehicleName').val();
	this.description = $('#description').val();
	this.transactionDate = $('#transactionDate').val();
	this.amount = $('#amount').val();
	this.sequenceNumber = $('#transactionRef').val();
	this.transactionAttribute = $('#transactionAttribute').val();
}

function resetOrPopulateForm(data, clearTxn) {
	if (clearTxn)
		$('#vehicleName').val(data == null ? "" : data.vehicleName);
	$('#description').val(data == null ? "" : data.description);
	$('#transactionDate').val(data == null ? "" : data.transactionDate);
	$('#amount').val(data == null ? 0 : data.amount);
	$('#transactionRef').val(data == null ? 0 : data.sequenceNumber);
	$('#transactionAttribute').val(data == null ? 0 : data.transactionAttribute);
	$('#vehicleName').prop('disabled', data == null ? false : true);
	UTIL.scrollToTop();
}

function findAllVehicleTxnCallback(data) {
	createDataTable(data != null && data.details != null ? data.details : null);
}

var loanDataTable;
function createDataTable(dataSet) {
	if (loanDataTable) {
		loanDataTable.clear();
		if (dataSet != null)
			loanDataTable.rows.add(dataSet);
		loanDataTable.draw();
	} else if (dataSet != null) {
		loanDataTable = $('#vehicle_data_table')
				.DataTable(
						{
							data : dataSet,
							dom : 'istp',
							createdRow : function(row, data, dataIndex) {
								$(row).addClass(data.sequenceNumber + "");
							},
							columns : [
									{
										title : "Vehicle Name",
										name : "Vehicle Name",
										data : "vehicleName"
									},
									{
										title : "Ref num",
										name : "Ref num",
										data : "sequenceNumber"
									},
									{
										title : "Description",
										name : "Description",
										data : "description"
									},
									{
										title : "Transaction Date",
										name : "Transaction Date",
										data : "transactionDate"
									},
									{
										title : "Transaction Attribute",
										name : "Transaction Attribute",
										data : "transactionAttribute"
									},
									{
										title : "Amount",
										name : "Amount",
										data : "amount"
									},
									{
										title : "Action",
										name : "Action",
										"orderable" : false,
										"render" : function(data, type, row,
												meta) {
											return '<div><button class="btn btn-outline-success btn-sm mr-2" data-val ="'
													+ row.sequenceNumber
													+ '">Update</button><button class="btn btn-outline-danger btn-sm" data-val ="'
													+ row.sequenceNumber
													+ '">Delete</button></div>';
										}
									} ]
						});

		$('#vehicle_data_table tbody').on(
				'click',
				'.btn',
				function() {
					if ($(this).hasClass('btn-outline-success')) {
						resetOrPopulateForm(loanDataTable.row(
								'.' + $(this).data('val')).data(), false)
					} else if ($(this).hasClass('btn-outline-danger')) {
						var rowData = loanDataTable.row(
								'.' + $(this).data('val')).data();
						UTIL.send('/vehicle_txn/delete/' + rowData.vehicleName
								+ "/" + rowData.sequenceNumber, null,
								findAllVehicleTxnCallback);
					}
				});
	}

}

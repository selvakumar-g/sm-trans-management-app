$(document).ready(
		function(event) {

			$('.datepicker').datepicker({
				format : 'dd-M-yyyy',
				todayHighlight : true
			});
			
			var fieldTypes = ["loan_txn_type"];
			var flTypeMapping = {"loan_txn_type" : "transactionType"};
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
			
			

			UTIL.send('/loan/findAll', null, findAllLoansCallback);

			function findAllLoansCallback(data) {
				if (data != null && data.details != null) {
					$(data.details).each(
							function(idx, loan) {
								$('#loan_name').append(
										'<option value="' + loan.loanName
												+ '">' + loan.loanName
												+ '</option>');
							});
				}
			}

			$('form').on(
					'change',
					'#loan_name',
					function(event) {
						if ($(this).val()) {
							UTIL.send('/loan_txn/findLoanTxn/' + $(this).val(),
									null, findAllLoanTxnCallback);
							resetOrPopulateForm(null, false);
						} else {
							resetOrPopulateForm(null, true);
						}
					});

			$('form').on('click', '#btn_save', function(event) {
				if (UTIL.validateForm()) {
					var data = new CreateLoanTxn();
					UTIL.send('/loan_txn/save', data, findAllLoanTxnCallback);
					resetOrPopulateForm(null, false);
				}
			});

			$('form').on('click', '#btn_clear', function(event) {
				UTIL.clearFormValidation();
				resetOrPopulateForm(null, true);
				createDataTable(null);
			});

		});

function CreateLoanTxn() {
	this.loanName = $('#loan_name').val();
	this.description = $('#description').val();
	this.transactionDate = $('#transactionDate').val();
	this.transactionType = $('#transactionType').val();
	this.amount = $('#amount').val();
	this.sequenceNumber = $('#transactionRef').val();
}

function resetOrPopulateForm(data, clearLoan) {
	if (clearLoan)
		$('#loan_name').val(data == null ? "" : data.loanName);
	$('#description').val(data == null ? "" : data.description);
	$('#transactionDate').val(data == null ? "" : data.transactionDate);
	$('#transactionType').val(data == null ? "" : data.transactionType);
	$('#amount').val(data == null ? 0 : data.amount);
	$('#transactionRef').val(data == null ? 0 : data.sequenceNumber);
	$('#loan_name').prop('disabled',data == null ? false : true);
	UTIL.scrollToTop();
}

function findAllLoanTxnCallback(data) {
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
		loanDataTable = $('#loan_data_table')
				.DataTable(
						{
							data : dataSet,
							dom : 'istp',
							createdRow : function(row, data, dataIndex) {
								$(row).addClass(data.sequenceNumber + "");
							},
							columns : [
									{
										title : "Loan Name",
										name : "Loan Name",
										data : "loanName"
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
										title : "Transaction Type",
										name : "Transaction Type",
										data : "transactionType"
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

		$('#loan_data_table tbody').on(
				'click',
				'.btn',
				function() {
					if ($(this).hasClass('btn-outline-success')) {
						resetOrPopulateForm(loanDataTable.row(
								'.' + $(this).data('val')).data(), false)
					} else if ($(this).hasClass('btn-outline-danger')) {
						var rowData = loanDataTable.row(
								'.' + $(this).data('val')).data();
						UTIL.send('/loan_txn/delete/' + rowData.loanName + "/"
								+ rowData.sequenceNumber, null,
								findAllLoanTxnCallback);
					}
				});
	}

}

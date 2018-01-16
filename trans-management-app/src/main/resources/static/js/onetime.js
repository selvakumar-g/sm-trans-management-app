$(document).ready(
		function(event) {

			UTIL.send('/onetime/findAll', null, populatefieldTypes);

			$('form').on(
					'change',
					'#fieldType_select',
					function(event) {
						if ($(this).val()) {
							UTIL.send('/onetime/find/' + $(this).val(), null,
									populateDTCallback);
							resetOrPopulateForm(null, false);
						} else {
							populateDTCallback(null);
							resetOrPopulateForm(null, true);
						}
					});

			$('form').on('click', '#btn_save', function(event) {
				var data = new CreateOneTime();
				UTIL.send('/onetime/save', data, saveCallback);
			});

			$('form').on('click', '#btn_clear', function(event) {
				UTIL.send('/onetime/findAll', null, populatefieldTypes);
				resetOrPopulateForm(null, true);
				createDataTable(null);
			});

		});

function CreateOneTime() {
	this.fieldType = $('#fieldType').val();
	this.fieldVal = $('#fieldVal').val();
}

function resetOrPopulateForm(data, clearTxn) {
	if (clearTxn)
		$('#fieldType_select').val("");
	$('#fieldType').val(data == null ? "" : data.fieldType);
	$('#fieldVal').val(data == null ? "" : data.fieldVal);
	if ($('#fieldType_select').val()) {
		$('#fieldType').val($('#fieldType_select').val());
		$('#fieldType').prop('disabled', true);
	} else {
		$('#fieldType').val("");
		$('#fieldType').prop('disabled', false);
	}
	UTIL.scrollToTop();
}

function populatefieldTypes(data) {
	if (data != null && data.details != null) {
		var fldTypes = [];
		$(data.details).each(
				function(idx, onetime) {
					if ($.inArray(onetime.fieldType, fldTypes) == -1) {
						fldTypes.push(onetime.fieldType);
						if ($('#fieldType_select option:contains("'
								+ onetime.fieldType + '")').length == 0) {
							$('#fieldType_select').append(
									'<option value="' + onetime.fieldType
											+ '">' + onetime.fieldType
											+ '</option>');
						}
					}
				});
		$('#fieldType_select').find('option').each(
				function(idx, option) {
					if ($(option).text() != "Choose"
							& $.inArray($(option).text(), fldTypes) == -1) {
						$(
								'#fieldType_select option:contains("'
										+ $(option).text() + '")').remove();
					}
				});

	} else {
		$('#fieldType_select').find('option').remove();
		$('#fieldType_select').append('<option value="">Choose</option>');
	}
}

function saveCallback(data) {
	populatefieldTypes(data);
	if ($('#fieldType_select').val() && data != null && data.details != null) {
		var selectedData = [];
		$(data.details).each(function(idx, val) {
			if (val.fieldType == $('#fieldType_select').val())
				selectedData.push(val);
		});
		createDataTable(selectedData);
	} else {
		createDataTable(null);
	}
	resetOrPopulateForm(null, false);
}

function populateDTCallback(data) {
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
		loanDataTable = $('#onetime_data_table')
				.DataTable(
						{
							data : dataSet,
							dom : 'istp',
							createdRow : function(row, data, dataIndex) {
								$(row).addClass(data.fieldVal);
							},
							columns : [
									{
										title : "Field type",
										name : "Field type",
										data : "fieldType"
									},
									{
										title : "Field Value",
										name : "Field Value",
										data : "fieldVal"
									},
									{
										title : "Action",
										name : "Action",
										"orderable" : false,
										"render" : function(data, type, row,
												meta) {
											return '<div><button class="btn btn-outline-success btn-sm mr-2" data-val ="'
													+ row.fieldVal
													+ '">Update</button><button class="btn btn-outline-danger btn-sm" data-val ="'
													+ row.fieldVal
													+ '">Delete</button></div>';
										}
									} ]
						});

		$('#onetime_data_table tbody').on(
				'click',
				'.btn',
				function() {
					if ($(this).hasClass('btn-outline-success')) {
						resetOrPopulateForm(loanDataTable.row(
								'.' + $(this).data('val')).data(), false);
					} else if ($(this).hasClass('btn-outline-danger')) {
						var rowData = loanDataTable.row(
								'.' + $(this).data('val')).data();
						UTIL.send('/onetime/delete/' + rowData.fieldType + "/"
								+ rowData.fieldVal, null, saveCallback);
					}
				});
	}

}

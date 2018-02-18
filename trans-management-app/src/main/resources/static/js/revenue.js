$(document).ready(function(event) {

	UTIL.send('/vehicle/findAll', null, vehicleCallback);

});

function vehicleCallback(data) {
	if (data != null && data.details != null) {

		$(data.details).each(
				function(idx, vehicle) {
					$('#vehicle_name').append(
							'<option value="' + vehicle.vehicleName + '">'
									+ vehicle.vehicleName + '</option>');
				});

		$('form').on(
				'change',
				'#vehicle_name',
				function(event) {
					if ($(this).val()) {
						UTIL.send('/revenue/findRevenueForVehicle/'
								+ $(this).val(), null, revenueCallback);
					} else {
						revenueCallback(null);
					}
				});

	}
}

function revenueCallback(data) {
	createDataTable("A",
			data != null && data.details != null ? data.details.byAll : null)
	createDataTable("M",
			data != null && data.details != null ? data.details.byMonth : null)
}

var allDataTable;
var monthDataTable;
function createDataTable(key, dataSet) {
	if ("A" == key && allDataTable) {
		allDataTable.clear();
		if (dataSet != null)
			allDataTable.rows.add(dataSet);
		allDataTable.draw();
	} else if ("M" == key && monthDataTable) {
		monthDataTable.clear();
		if (dataSet != null)
			monthDataTable.rows.add(dataSet);
		monthDataTable.draw();
	} else if (dataSet != null) {
		var id = "A" == key ? "all_data_table" : "month_data_table"
		var dt = $('#' + id).DataTable({
			data : dataSet,
			dom : 'istp',
			columns : [ {
				title : "vehicle Name",
				name : "Vehicle Name",
				data : "vehicleName"
			}, {
				title : "A" == key ? "Transaction date" : "Transaction month",
				name : "A" == key ? "Transaction date" : "Transaction month",
				data : "A" == key ? "transactionDate" : "transactionMonth"
			}, {
				title : "Earning",
				name : "Earning",
				data : "earning"
			}, {
				title : "Expense",
				name : "Expense",
				data : "expense"
			}, {
				title : "Gain",
				name : "Gain",
				data : "gain"
			} ]
		});

		if ("A" == key)
			allDataTable = dt;
		else
			monthDataTable = dt;

	}

}

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
	createDataTable(data != null ? data.details : null)
}

var loanDataTable;
function createDataTable(dataSet) {
	if (loanDataTable) {
		loanDataTable.clear();
		if (dataSet != null)
			loanDataTable.rows.add(dataSet);
		loanDataTable.draw();
	} else if (dataSet != null) {
		loanDataTable = $('#revenue_data_table').DataTable({
			data : dataSet,
			dom : 'istp',
			createdRow : function(row, data, dataIndex) {
				$(row).addClass(data.sequenceNumber + "");
			},
			columns : [ {
				title : "vehicle Name",
				name : "Vehicle Name",
				data : "vehicleName"
			},

			{
				title : "Transaction Date",
				name : "Transaction Date",
				data : "date"
			}, {
				title : "Earning",
				name : "Earning",
				data : "earning"
			}, {
				title : "Expense",
				name : "Expense",
				data : "earning"
			}, {
				title : "Profit",
				name : "Profit",
				data : "profit"
			} ]
		});

	}

}

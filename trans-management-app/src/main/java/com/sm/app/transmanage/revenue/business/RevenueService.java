package com.sm.app.transmanage.revenue.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.app.transmanage.loan.business.LoanService;
import com.sm.app.transmanage.loantransaction.business.LoanTransactionService;
import com.sm.app.transmanage.util.DozerMapper;
import com.sm.app.transmanage.vehicle.business.VehicleService;
import com.sm.app.transmanage.vehicletransaction.business.VehicleTransactionService;

/**
 * @author U811403
 *
 */
@Service
public class RevenueService {

	@Autowired
	DozerMapper dozerMapper;

	@Autowired
	LoanService loanService;

	@Autowired
	VehicleService vehicleService;

	@Autowired
	LoanTransactionService loanTxnService;

	@Autowired
	VehicleTransactionService vehicleTxnService;
	
	

}

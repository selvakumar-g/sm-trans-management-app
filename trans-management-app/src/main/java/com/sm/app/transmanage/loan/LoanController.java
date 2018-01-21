package com.sm.app.transmanage.loan;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sm.app.transmanage.loan.business.LoanService;
import com.sm.app.transmanage.loan.vo.LoanVO;
import com.sm.app.transmanage.loantransaction.business.LoanTransactionService;
import com.sm.app.transmanage.loantransaction.vo.LoanTransactionVO;
import com.sm.app.transmanage.util.Wrapper;
import com.sm.app.transmanage.vehicle.business.VehicleService;
import com.sm.app.transmanage.vehicle.vo.VehicleVO;

@RestController
public class LoanController {

	@Autowired
	private LoanService loanService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private LoanTransactionService loanTxnService;

	@RequestMapping(path = "/loan/save", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<LoanVO>>> save(@Valid @RequestBody LoanVO LoanVO, Errors errors) {
		Wrapper<List<LoanVO>> result = new Wrapper<List<LoanVO>>();
		if (errors != null && errors.hasErrors()) {
			result.setErrorDetails(errors.getAllErrors().stream().collect(toMap(ObjectError::getDefaultMessage, null)));
		} else {
			result.setDetails(loanService.save(LoanVO));
		}
		return new ResponseEntity<Wrapper<List<LoanVO>>>(result,
				result.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}

	@RequestMapping(path = "/loan/findAll", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<LoanVO>>> findAll() {
		List<LoanVO> result = loanService.findAll();
		return new ResponseEntity<Wrapper<List<LoanVO>>>(new Wrapper<List<LoanVO>>(result), HttpStatus.OK);
	}

	@RequestMapping(path = "/loan/delete/{loanName}", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<LoanVO>>> delete(@PathVariable("loanName") String loanName) {
		List<VehicleVO> vehicles = vehicleService.findVehicleLoans(loanName);
		List<LoanTransactionVO> txns = loanTxnService.findLoanTxn(loanName);
		Wrapper<List<LoanVO>> wrapper = new Wrapper<List<LoanVO>>();
		if ((vehicles == null || vehicles.size() == 0) && (txns == null || txns.size() == 0))
			wrapper.setDetails(loanService.delete(loanName));
		else {
			if (vehicles != null && vehicles.size() > 0)
				wrapper.addError("Loan is configured for the vehicles",
						vehicles.stream().map(VehicleVO::getVehicleName).collect(toList()));
			if (txns != null && txns.size() > 0)
				wrapper.addError("Transactions present for the loan", null);
		}
		return new ResponseEntity<Wrapper<List<LoanVO>>>(wrapper,
				wrapper.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}

	@RequestMapping(path = "/loan/find/{loanName}", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<LoanVO>> find(@PathVariable("loanName") String loanName) {
		LoanVO result = loanService.find(loanName);
		return new ResponseEntity<Wrapper<LoanVO>>(new Wrapper<LoanVO>(result), HttpStatus.OK);
	}

}

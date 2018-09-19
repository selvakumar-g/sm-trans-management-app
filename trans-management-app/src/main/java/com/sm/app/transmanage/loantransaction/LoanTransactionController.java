package com.sm.app.transmanage.loantransaction;

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

import com.sm.app.transmanage.loantransaction.business.LoanTransactionService;
import com.sm.app.transmanage.loantransaction.vo.LoanTransactionVO;
import com.sm.app.transmanage.util.Wrapper;

@RestController
public class LoanTransactionController {

	@Autowired
	private LoanTransactionService loanTransactionService;

	@RequestMapping(path = "/loan_txn/findAll", method = RequestMethod.GET, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<LoanTransactionVO>>> findAll() {
		List<LoanTransactionVO> result = loanTransactionService.findAll();
		return new ResponseEntity<Wrapper<List<LoanTransactionVO>>>(new Wrapper<List<LoanTransactionVO>>(result),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/loan_txn/findLoanTxn/{loanName}", method = RequestMethod.GET, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<LoanTransactionVO>>> findLoanTxn(@PathVariable("loanName") String loanName) {
		List<LoanTransactionVO> result = loanTransactionService.findLoanTxn(loanName);
		return new ResponseEntity<Wrapper<List<LoanTransactionVO>>>(new Wrapper<List<LoanTransactionVO>>(result),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/loan_txn/save", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<LoanTransactionVO>>> save(@Valid @RequestBody LoanTransactionVO loanTransactionVO, Errors errors) {		
		Wrapper<List<LoanTransactionVO>> result = new Wrapper<List<LoanTransactionVO>>();
		if (errors != null && errors.hasErrors()) {
			result.setErrorDetails(errors.getAllErrors().stream().collect(toMap(ObjectError::getDefaultMessage, null)));
		} else {
			result.setDetails(loanTransactionService.save(loanTransactionVO));
		}
		return new ResponseEntity<Wrapper<List<LoanTransactionVO>>>(result,
				result.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}

	@RequestMapping(path = "/loan_txn/delete/{loanName}/{sequenceNumber}", method = RequestMethod.DELETE, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<LoanTransactionVO>>> delete(@PathVariable("loanName") String loanName,
			@PathVariable("sequenceNumber") long sequenceNumber) {
		List<LoanTransactionVO> result = loanTransactionService.delete(loanName, sequenceNumber);
		return new ResponseEntity<Wrapper<List<LoanTransactionVO>>>(new Wrapper<List<LoanTransactionVO>>(result),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/loan_txn/find/{loanName}/{sequenceNumber}", method = RequestMethod.GET, produces = "application/JSON")
	public ResponseEntity<Wrapper<LoanTransactionVO>> find(@PathVariable("loanName") String loanName,
			@PathVariable("sequenceNumber") long sequenceNumber) {
		LoanTransactionVO result = loanTransactionService.find(loanName, sequenceNumber);
		return new ResponseEntity<Wrapper<LoanTransactionVO>>(new Wrapper<LoanTransactionVO>(result), HttpStatus.OK);
	}

}

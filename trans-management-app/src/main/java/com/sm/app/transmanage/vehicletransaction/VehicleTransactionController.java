package com.sm.app.transmanage.vehicletransaction;

import static java.util.stream.Collectors.toMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

import com.sm.app.transmanage.util.Wrapper;
import com.sm.app.transmanage.vehicletransaction.business.VehicleTransactionService;
import com.sm.app.transmanage.vehicletransaction.vo.VehicleTransactionVO;

@RestController
public class VehicleTransactionController {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MMM-yyyy");

	@Autowired
	private VehicleTransactionService vehicleTransactionService;

	@RequestMapping(path = "/vehicle_txn/findAll", method = RequestMethod.GET, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<VehicleTransactionVO>>> findAll() {
		List<VehicleTransactionVO> result = vehicleTransactionService.findAll();
		return new ResponseEntity<Wrapper<List<VehicleTransactionVO>>>(new Wrapper<List<VehicleTransactionVO>>(result),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/vehicle_txn/findVehicleTxn/{vehicleName}", method = RequestMethod.GET, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<VehicleTransactionVO>>> findVehicleTxn(
			@PathVariable("vehicleName") String vehicleName) {
		List<VehicleTransactionVO> result = vehicleTransactionService.findVehicleTxn(vehicleName);
		return new ResponseEntity<Wrapper<List<VehicleTransactionVO>>>(new Wrapper<List<VehicleTransactionVO>>(result),
				HttpStatus.OK);
	}

	@RequestMapping(path = { "/vehicle_txn/findVehicleTxn/{vehicleName}/{transactionDate}" }, method = {
			RequestMethod.GET }, produces = { "application/JSON" })
	public ResponseEntity<Wrapper<List<VehicleTransactionVO>>> findVehicleTxn(
			@PathVariable(value = "vehicleName") String vehicleName,
			@PathVariable(value = "transactionDate") String transactionDate) {

		Date txnDate = null;
		try {
			txnDate = SDF.parse(transactionDate);
		} catch (ParseException pse) {
			Wrapper<List<VehicleTransactionVO>> wrapper = new Wrapper<>();
			wrapper.addError("Transaction date parsing failed", Arrays.asList(transactionDate));
			return new ResponseEntity<Wrapper<List<VehicleTransactionVO>>>(
					new Wrapper<List<VehicleTransactionVO>>(null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<VehicleTransactionVO> result = this.vehicleTransactionService.findVehicleTxn(vehicleName, txnDate);
		return new ResponseEntity<Wrapper<List<VehicleTransactionVO>>>(new Wrapper<List<VehicleTransactionVO>>(result),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/vehicle_txn/save", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<VehicleTransactionVO>>> save(
			@Valid @RequestBody VehicleTransactionVO VehicleTransactionVO, Errors errors) {
		Wrapper<List<VehicleTransactionVO>> result = new Wrapper<List<VehicleTransactionVO>>();
		if (errors != null && errors.hasErrors()) {
			result.setErrorDetails(errors.getAllErrors().stream().collect(toMap(ObjectError::getDefaultMessage, null)));
		} else {
			result.setDetails(vehicleTransactionService.save(VehicleTransactionVO));
		}
		return new ResponseEntity<Wrapper<List<VehicleTransactionVO>>>(result,
				result.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}

	@RequestMapping(path = "/vehicle_txn/batch", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<String>> batch(@Valid @RequestBody List<VehicleTransactionVO> VehicleTransactionVOs,
			Errors errors) {
		Wrapper<List<VehicleTransactionVO>> result = new Wrapper<List<VehicleTransactionVO>>();
		if (errors != null && errors.hasErrors()) {
			result.setErrorDetails(errors.getAllErrors().stream().collect(toMap(ObjectError::getDefaultMessage, null)));
		} else {
			vehicleTransactionService.save(VehicleTransactionVOs);
		}
		return new ResponseEntity<Wrapper<String>>(result.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}

	@RequestMapping(path = "/vehicle_txn/delete/{vehicleName}/{sequenceNumber}", method = RequestMethod.DELETE, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<VehicleTransactionVO>>> delete(@PathVariable("vehicleName") String vehicleName,
			@PathVariable("sequenceNumber") long sequenceNumber) {
		List<VehicleTransactionVO> result = vehicleTransactionService.delete(vehicleName, sequenceNumber);
		return new ResponseEntity<Wrapper<List<VehicleTransactionVO>>>(new Wrapper<List<VehicleTransactionVO>>(result),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/vehicle_txn/find/{vehicleName}/{sequenceNumber}", method = RequestMethod.GET, produces = "application/JSON")
	public ResponseEntity<Wrapper<VehicleTransactionVO>> find(@PathVariable("vehicleName") String vehicleName,
			@PathVariable("sequenceNumber") long sequenceNumber) {
		VehicleTransactionVO result = vehicleTransactionService.find(vehicleName, sequenceNumber);
		return new ResponseEntity<Wrapper<VehicleTransactionVO>>(new Wrapper<VehicleTransactionVO>(result),
				HttpStatus.OK);
	}

}

package com.sm.app.transmanage.vehicle;

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

import com.sm.app.transmanage.util.Wrapper;
import com.sm.app.transmanage.vehicle.business.VehicleService;
import com.sm.app.transmanage.vehicle.vo.VehicleVO;
import com.sm.app.transmanage.vehicletransaction.business.VehicleTransactionService;
import com.sm.app.transmanage.vehicletransaction.vo.VehicleTransactionVO;

@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private VehicleTransactionService vehicleTxnService;

	@RequestMapping(path = "/vehicle/save", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<VehicleVO>>> save(@Valid @RequestBody VehicleVO VehicleVO, Errors errors) {
		Wrapper<List<VehicleVO>> result = new Wrapper<List<VehicleVO>>();
		if (errors != null && errors.hasErrors()) {
			result.setErrorDetails(errors.getAllErrors().stream().collect(toMap(ObjectError::getDefaultMessage, null)));
		} else {
			result.setDetails(vehicleService.save(VehicleVO));
		}
		return new ResponseEntity<Wrapper<List<VehicleVO>>>(result,
				result.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}

	@RequestMapping(path = "/vehicle/findAll", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<VehicleVO>>> findAll() {
		List<VehicleVO> result = vehicleService.findAll();
		return new ResponseEntity<Wrapper<List<VehicleVO>>>(new Wrapper<List<VehicleVO>>(result), HttpStatus.OK);
	}

	@RequestMapping(path = "/vehicle/delete/{vehicleName}", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<VehicleVO>>> delete(@PathVariable("vehicleName") String vehicleName) {
		List<VehicleTransactionVO> txns = vehicleTxnService.findVehicleTxn(vehicleName);
		Wrapper<List<VehicleVO>> wrapper = new Wrapper<List<VehicleVO>>();
		if (txns == null || txns.size() == 0) {
			wrapper.setDetails(vehicleService.delete(vehicleName));
		} else
			wrapper.addError("Transactions present for the vehicle", null);
		return new ResponseEntity<Wrapper<List<VehicleVO>>>(wrapper,
				wrapper.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}

	@RequestMapping(path = "/vehicle/find/{vehicleName}", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<VehicleVO>> find(@PathVariable("vehicleName") String vehicleName) {
		VehicleVO result = vehicleService.find(vehicleName);
		return new ResponseEntity<Wrapper<VehicleVO>>(new Wrapper<VehicleVO>(result), HttpStatus.OK);
	}

}

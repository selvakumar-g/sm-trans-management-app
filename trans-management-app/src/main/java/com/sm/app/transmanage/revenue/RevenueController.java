package com.sm.app.transmanage.revenue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sm.app.transmanage.revenue.business.RevenueService;
import com.sm.app.transmanage.revenue.vo.RevenueVO;
import com.sm.app.transmanage.util.Wrapper;

@RestController
public class RevenueController {

	@Autowired
	private RevenueService service;

	@RequestMapping(path = "/revenue/findRevenueYearsForVehicle/{vehicleName}", method = RequestMethod.GET, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<String>>> findRevenueYears(@PathVariable("vehicleName") String vehicleName) {
		List<String> result = service.findRevenueYearsForVehicle(vehicleName);
		return new ResponseEntity<Wrapper<List<String>>>(new Wrapper<List<String>>(result), HttpStatus.OK);
	}

	@RequestMapping(path = "/revenue/findRevenueForVehicle/{vehicleName}/{year}", method = RequestMethod.GET, produces = "application/JSON")
	public ResponseEntity<Wrapper<RevenueVO>> findYealyRevenueForVehicle(
			@PathVariable("vehicleName") String vehicleName, @PathVariable("year") String year) {
		RevenueVO result = service.findVehRevenueForDateRange(vehicleName, year);
		return new ResponseEntity<Wrapper<RevenueVO>>(new Wrapper<RevenueVO>(result), HttpStatus.OK);
	}

	@RequestMapping(path = "/revenue/findRevenueForVehicle/{vehicleName}/{year}/{txnAttribute}", method = RequestMethod.GET, produces = "application/JSON")
	public ResponseEntity<Wrapper<RevenueVO>> findYearlyRevenueForVehicleTxn(
			@PathVariable("vehicleName") String vehicleName, @PathVariable("year") String year,
			@PathVariable("txnAttribute") String txnAttribute) {
		RevenueVO result = service.findVehRevenueForDateRangeAttr(vehicleName, year, txnAttribute);
		return new ResponseEntity<Wrapper<RevenueVO>>(new Wrapper<RevenueVO>(result), HttpStatus.OK);
	}
	
	 
}

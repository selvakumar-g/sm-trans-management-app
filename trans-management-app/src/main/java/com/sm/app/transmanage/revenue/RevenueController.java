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

	@RequestMapping(path = "/revenue/find/{vehicleName}", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<RevenueVO>>> findRevenueForVehicle(
			@PathVariable("vehicleName") String vehicleName) {
		List<RevenueVO> result = service.findRevenueForVehicle(vehicleName);
		return new ResponseEntity<Wrapper<List<RevenueVO>>>(new Wrapper<List<RevenueVO>>(result), HttpStatus.OK);
	}
}

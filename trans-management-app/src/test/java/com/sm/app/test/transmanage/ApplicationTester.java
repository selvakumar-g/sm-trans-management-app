package com.sm.app.test.transmanage;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.sm.app.transmanage.loan.business.LoanService;
import com.sm.app.transmanage.loan.vo.LoanVO;
import com.sm.app.transmanage.loantransaction.business.LoanTransactionService;
import com.sm.app.transmanage.loantransaction.vo.LoanTransactionVO;
import com.sm.app.transmanage.onetime.business.OneTimeService;
import com.sm.app.transmanage.onetime.vo.OneTimeVO;
import com.sm.app.transmanage.sequencegen.business.LoanSequenceGen;
import com.sm.app.transmanage.sequencegen.business.VehicleSequenceGen;
import com.sm.app.transmanage.vehicle.business.VehicleService;
import com.sm.app.transmanage.vehicle.vo.VehicleVO;
import com.sm.app.transmanage.vehicletransaction.business.VehicleTransactionService;
import com.sm.app.transmanage.vehicletransaction.vo.VehicleTransactionVO;

@RestController
@SpringBootApplication
public class ApplicationTester implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApplicationTester.class);
		app.setWebEnvironment(true);
		app.run(args);
	}

	@Autowired
	VehicleService vehicleService;

	@Autowired
	LoanService loanService;

	@Autowired
	OneTimeService oservice;

	@Autowired
	VehicleTransactionService vehicleTxnservice;

	@Autowired
	LoanTransactionService loanTxnservice;

	@Autowired
	VehicleSequenceGen vehicleSeqGen;

	@Autowired
	LoanSequenceGen loanSeqGen;

	@Override
	public void run(String... arg0) throws Exception {

		OneTimeVO onetimevo = new OneTimeVO();
		onetimevo.setFieldType("type1");
		onetimevo.setFieldVal("v");

		oservice.save(onetimevo);

		VehicleVO vvo = new VehicleVO();
		vvo.setVehicleName("name");
		vvo.setVehicleNumber("2");
		vvo.setVehicleStatus("A");
		vvo.setVehicleType("L");

		vehicleService.save(vvo);

		LoanVO lvo = new LoanVO();
		lvo.setLoanAmount(100);
		lvo.setLoanDescription("desc");
		lvo.setLoanName("l1");
		loanService.save(lvo);

		VehicleTransactionVO vmvo = new VehicleTransactionVO();
		vmvo.setAmount(110.33);
		vmvo.setDescription("desc");
		vmvo.setTransactionDate(new Date());
		vmvo.setSequenceNumber(vehicleSeqGen.nextIntValue());
		vmvo.setVehicleName("v1");
		vmvo.setLastupdatedTime(Timestamp.valueOf(LocalDateTime.now()));

		vehicleTxnservice.save(vmvo);

		LoanTransactionVO lmvo = new LoanTransactionVO();
		lmvo.setAmount(71717171.99);
		lmvo.setDescription("des");
		lmvo.setLoanName("l1");
		lmvo.setTransactionDate(new Date());
		lmvo.setTransactionType("I");
		lmvo.setSequenceNumber(loanSeqGen.nextIntValue());
		lmvo.setLastupdatedTime(Timestamp.valueOf(LocalDateTime.now()));
		loanTxnservice.save(lmvo);

	}

}

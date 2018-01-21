package com.sm.app.transmanage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	private static final String HOME_PAGE = "home";
	private static final String LOAN_PAGE = "loan";
	private static final String LOAN_TRANSACTION_PAGE = "loan_txn";
	private static final String ONETIME_PAGE = "onetime";
	private static final String VEHICLE_PAGE = "vehicle";
	private static final String VEHICLE_TRANSACTION_PAGE = "vehicle_txn";
	private static final String REVENUE_PAGE = "revenue";

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView renderHome(ModelAndView modelAndView) {
		modelAndView.setViewName(HOME_PAGE);
		return modelAndView;
	}

	@RequestMapping(value = "/loan", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView renderLoan(ModelAndView modelAndView) {
		modelAndView.setViewName(LOAN_PAGE);
		return modelAndView;
	}

	@RequestMapping(value = "/loan_txn", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView renderLoanTxn(ModelAndView modelAndView) {
		modelAndView.setViewName(LOAN_TRANSACTION_PAGE);
		return modelAndView;
	}

	@RequestMapping(value = "/onetime", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView renderOneTime(ModelAndView modelAndView) {
		modelAndView.setViewName(ONETIME_PAGE);
		return modelAndView;
	}

	@RequestMapping(value = "/vehicle", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView renderVehicle(ModelAndView modelAndView) {
		modelAndView.setViewName(VEHICLE_PAGE);
		return modelAndView;
	}

	@RequestMapping(value = "/vehicle_txn", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView renderVehicleTxn(ModelAndView modelAndView) {
		modelAndView.setViewName(VEHICLE_TRANSACTION_PAGE);
		return modelAndView;
	}

	@RequestMapping(value = "/revenue", method = RequestMethod.GET, produces = "text/html")
	public ModelAndView renderRevenue(ModelAndView modelAndView) {
		modelAndView.setViewName(REVENUE_PAGE);
		return modelAndView;
	}

}

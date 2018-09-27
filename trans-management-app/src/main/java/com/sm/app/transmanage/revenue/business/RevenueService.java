package com.sm.app.transmanage.revenue.business;

import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sm.app.transmanage.revenue.vo.RevenueVO;
import com.sm.app.transmanage.revenue.vo.YieldVO;
import com.sm.app.transmanage.vehicletransaction.business.VehicleTransactionService;
import com.sm.app.transmanage.vehicletransaction.vo.VehicleTransactionVO;

/**
 * @author U811403
 *
 */
@Service
public class RevenueService {

	@Autowired
	VehicleTransactionService vehicleTxnService;

	@Value("${sm.profit.vehicle.tx.attribute}")
	private String profitAttribute;

	public List<String> findRevenueYearsForVehicle(String vehicleName) {
		return vehicleTxnService.findVehicleTxnYears(vehicleName);
	}

	public RevenueVO findVehRevenueForDateRange(String vehicleName, String year) {
		List<VehicleTransactionVO> transactions = vehicleTxnService.findVehRevenueForDateRange(vehicleName,
				Date.from(LocalDate.of(2018, Month.JANUARY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				Date.from(LocalDate.of(2018, Month.DECEMBER, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return createRevenueDetails(vehicleName, transactions, true);
	}

	public RevenueVO findVehRevenueForDateRangeAttr(String vehicleName, String year, String attribute) {
		List<VehicleTransactionVO> transactions = vehicleTxnService.findVehRevenueForDateRangeAttr(vehicleName,
				Date.from(LocalDate.of(2018, Month.JANUARY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				Date.from(LocalDate.of(2018, Month.DECEMBER, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()),
				attribute);
		return createRevenueDetails(vehicleName, transactions, false);
	}

	public RevenueVO createRevenueDetails(String vehicleName, List<VehicleTransactionVO> vehicleTxns,
			boolean isAggNeeded) {
		List<String> profitAttributes = Arrays.asList(profitAttribute.split(","));

		RevenueVO result = new RevenueVO();
		if (vehicleTxns != null && vehicleTxns.size() > 0) {
			result.setByAll(new ArrayList<>());
			result.setByMonth(new ArrayList<>());
			Map<Date, List<VehicleTransactionVO>> vTxnGroup = vehicleTxns.stream()
					.collect(groupingBy(VehicleTransactionVO::getTransactionDate));
			vTxnGroup.forEach((date, txnList) -> {
				YieldVO yield = new YieldVO();
				yield.setVehicleName(vehicleName);
				yield.setTransactionDate(date);
				yield.setTransactionMonth(getMonth(yield.getTransactionDate().getMonth()));
				result.getByAll().add(yield);
				txnList.stream().forEach(txn -> {
					if (isAggNeeded) {
						if (profitAttributes.contains(txn.getTransactionAttribute()))
							yield.setEarning(yield.getEarning() + txn.getAmount());
						else
							yield.setExpense(yield.getExpense() + txn.getAmount());
					} else {
						yield.setGain(yield.getGain() + txn.getAmount());
					}

				});
				if (isAggNeeded)
					yield.setGain(yield.getEarning() - yield.getExpense());
			});

			result.getByAll().stream().collect(groupingBy(YieldVO::getTransactionMonth)).forEach((month, list) -> {
				YieldVO vo = new YieldVO();
				vo.setVehicleName(vehicleName);
				vo.setTransactionMonth(month);
				list.stream().forEach(l -> {
					vo.setEarning(vo.getEarning() + l.getEarning());
					vo.setExpense(vo.getExpense() + l.getExpense());
					vo.setGain(vo.getGain() + l.getGain());
				});
				result.getByMonth().add(vo);
			});

		}

		return result;
	}

	private String getMonth(int month) {
		String val = "";
		switch (month) {
		case 0:
			val = "Jan";
			break;
		case 1:
			val = "Feb";
			break;
		case 2:
			val = "Mar";
			break;
		case 3:
			val = "Apr";
			break;
		case 4:
			val = "May";
			break;
		case 5:
			val = "Jun";
			break;
		case 6:
			val = "Jul";
			break;
		case 7:
			val = "Aug";
			break;
		case 8:
			val = "Sep";
			break;
		case 9:
			val = "Oct";
			break;
		case 10:
			val = "Nov";
			break;
		case 11:
			val = "Dec";
			break;
		default:
			break;
		}
		return val;

	}

}

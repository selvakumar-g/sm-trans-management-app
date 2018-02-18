package com.sm.app.transmanage.revenue.business;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

	public RevenueVO findRevenueForVehicle(String vehicleName) {
		List<VehicleTransactionVO> vTxnList = vehicleTxnService.findVehicleTxn(vehicleName);
		List<String> profitAttributes = Arrays.asList(profitAttribute.split(","));
		RevenueVO result = new RevenueVO();
		if (vTxnList != null) {
			Map<Date, List<VehicleTransactionVO>> vTxnGroup = vTxnList.stream()
					.collect(groupingBy(VehicleTransactionVO::getTransactionDate));
			result.setTransactions(vTxnList);
			result.setByAll(new ArrayList<YieldVO>());
			result.setByMonth(new ArrayList<YieldVO>());
			vTxnGroup.forEach((date, txnList) -> {
				YieldVO yield = new YieldVO();
				yield.setVehicleName(vehicleName);
				yield.setTransactionDate(date);
				yield.setTransactionMonth(getMonth(yield.getTransactionDate().getMonth()));
				result.getByAll().add(yield);
				txnList.stream().forEach(txn -> {
					if (profitAttributes.contains(txn.getTransactionAttribute()))
						yield.setEarning(yield.getEarning() + txn.getAmount());
					else
						yield.setExpense(yield.getExpense() + txn.getAmount());

				});
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

package com.sm.app.transmanage.revenue.business;

import static java.util.stream.Collectors.groupingBy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	private static final SimpleDateFormat SDF = new SimpleDateFormat("YYYY");

	@Autowired
	VehicleTransactionService vehicleTxnService;

	@Value("${sm.profit.vehicle.tx.attribute}")
	private String profitAttribute;

	public RevenueVO findRevenueForVehicle(String vehicleName) {
		List<String> profitAttributes = Arrays.asList(profitAttribute.split(","));
		List<VehicleTransactionVO> vehicleTxns = vehicleTxnService.findVehicleTxn(vehicleName);
		Map<String, List<VehicleTransactionVO>> yearTxnsMap = vehicleTxns.stream()
				.collect(groupingBy(vo -> SDF.format(vo.getTransactionDate())));
		RevenueVO result = new RevenueVO();
		result.setTransactions(new HashMap<>());
		for (Entry<String, List<VehicleTransactionVO>> entry : yearTxnsMap.entrySet()) {
			Map<String, List<YieldVO>> yearlyTxnMap = new HashMap<>();
			result.getTransactions().put(entry.getKey(), yearlyTxnMap);

			List<VehicleTransactionVO> vTxnList = entry.getValue();
			Map<Date, List<VehicleTransactionVO>> vTxnGroup = vTxnList.stream()
					.collect(groupingBy(VehicleTransactionVO::getTransactionDate));
			result.getTransactions().get(entry.getKey()).put(RevenueVO.BY_ALL, new ArrayList<YieldVO>());
			result.getTransactions().get(entry.getKey()).put(RevenueVO.BY_MONTH, new ArrayList<YieldVO>());
			vTxnGroup.forEach((date, txnList) -> {
				YieldVO yield = new YieldVO();
				yield.setVehicleName(vehicleName);
				yield.setTransactionDate(date);
				yield.setTransactionMonth(getMonth(yield.getTransactionDate().getMonth()));
				result.getTransactions().get(entry.getKey()).get(RevenueVO.BY_ALL).add(yield);
				txnList.stream().forEach(txn -> {
					if (profitAttributes.contains(txn.getTransactionAttribute()))
						yield.setEarning(yield.getEarning() + txn.getAmount());
					else
						yield.setExpense(yield.getExpense() + txn.getAmount());

				});
				yield.setGain(yield.getEarning() - yield.getExpense());
			});

			result.getTransactions().get(entry.getKey()).get(RevenueVO.BY_ALL).stream()
					.collect(groupingBy(YieldVO::getTransactionMonth)).forEach((month, list) -> {
						YieldVO vo = new YieldVO();
						vo.setVehicleName(vehicleName);
						vo.setTransactionMonth(month);
						list.stream().forEach(l -> {
							vo.setEarning(vo.getEarning() + l.getEarning());
							vo.setExpense(vo.getExpense() + l.getExpense());
							vo.setGain(vo.getGain() + l.getGain());
						});
						result.getTransactions().get(entry.getKey()).get(RevenueVO.BY_MONTH).add(vo);
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

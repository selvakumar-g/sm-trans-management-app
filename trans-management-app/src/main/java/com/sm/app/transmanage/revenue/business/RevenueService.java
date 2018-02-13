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

	public List<RevenueVO> findRevenueForVehicle(String vehicleName) {
		List<VehicleTransactionVO> vTxnList = vehicleTxnService.findVehicleTxn(vehicleName);
		List<String> profitAttributes = Arrays.asList(profitAttribute.split(","));
		List<RevenueVO> result = new ArrayList<RevenueVO>();
		if (vTxnList != null) {
			Map<Date, List<VehicleTransactionVO>> vTxnGroup = vTxnList.stream()
					.collect(groupingBy(VehicleTransactionVO::getTransactionDate));
			vTxnGroup.forEach((date, txnList) -> {
				RevenueVO vo = new RevenueVO();
				vo.setVehicleName(vehicleName);
				vo.setTransactionDate(date);
				vo.setTransactions(txnList);
				result.add(vo);
				txnList.stream().forEach(txn -> {
					if (profitAttributes.contains(txn.getTransactionAttribute()))
						vo.setEarning(vo.getEarning() + txn.getAmount());
					else
						vo.setExpense(vo.getExpense() + txn.getAmount());

				});
				vo.setProfit(vo.getEarning() - vo.getExpense());
			});
		}
		return result;
	}

}

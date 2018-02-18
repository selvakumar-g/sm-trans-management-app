package com.sm.app.transmanage.revenue.vo;

import java.io.Serializable;
import java.util.List;

import com.sm.app.transmanage.vehicletransaction.vo.VehicleTransactionVO;

import lombok.Data;

public @Data class RevenueVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<YieldVO> byAll;

	private List<YieldVO> byMonth;

	private List<VehicleTransactionVO> transactions;

}

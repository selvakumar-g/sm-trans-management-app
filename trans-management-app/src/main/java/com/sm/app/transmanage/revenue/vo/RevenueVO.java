package com.sm.app.transmanage.revenue.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

public @Data class RevenueVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String BY_ALL = "byAll";
	
	public static final String BY_MONTH = "byMonth";
	
	private List<YieldVO> byAll;
	
	private List<YieldVO> byMonth;
}

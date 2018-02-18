package com.sm.app.transmanage.revenue.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sm.app.transmanage.util.JsonDateSerializer;
import com.sm.app.transmanage.util.JsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public @Data class YieldVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String vehicleName;

	@Getter(onMethod_ = { @JsonSerialize(using = JsonDateSerializer.class),
			@JsonDeserialize(using = JsonDateDeserializer.class) })
	@Setter
	private Date transactionDate;

	private String transactionMonth;

	private double earning;

	private double expense;

	private double gain;

}

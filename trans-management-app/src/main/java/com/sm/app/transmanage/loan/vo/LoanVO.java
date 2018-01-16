/**
 * 
 */
package com.sm.app.transmanage.loan.vo;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sm.app.transmanage.util.JsonDateSerializer;
import com.sm.app.transmanage.util.JsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author U811403
 *
 */
public @Data class LoanVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter(onMethod_ = @NotBlank(message = "Loan name cannot be empty"))
	@Setter
	private String loanName;

	@Getter(onMethod_ = @NotBlank(message = "Loan description cannot be empty"))
	@Setter
	private String loanDescription;

	@Getter(onMethod_ = @Range(min = 1, message = "Loan amount cannot be empty or zero"))
	@Setter
	private long loanAmount;

	@Getter(onMethod_ = { @JsonSerialize(using = JsonDateSerializer.class),
			@JsonDeserialize(using = JsonDateDeserializer.class),
			@NotNull(message = "Loan start date cannot be empty") })
	@Setter
	private Date loanStartDate;

	@Getter(onMethod_ = { @JsonSerialize(using = JsonDateSerializer.class),
			@JsonDeserialize(using = JsonDateDeserializer.class) })
	@Setter
	private Date loanEndDate;

	@Getter(onMethod_ = @NotBlank(message = "Loan status cannot be empty"))
	@Setter
	private String loanStatus;

	@Getter(onMethod_ = @Range(min = 1, message = "Loan period cannot be empty or zero"))
	@Setter
	private int loanPeriod;

	private String operationFlag;

}

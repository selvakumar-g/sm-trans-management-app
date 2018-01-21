/**
 * 
 */
package com.sm.app.transmanage.loantransaction.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sm.app.transmanage.util.JsonDateSerializer;
import com.sm.app.transmanage.util.JsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * @author U811403
 *
 */

public @Data class LoanTransactionVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter(onMethod_ = @NotBlank(message = "Loan name cannot be empty"))
	@Setter
	private String loanName;

	private long sequenceNumber;

	private String remarks;

	@Getter(onMethod_ = { @JsonSerialize(using = JsonDateSerializer.class),
			@JsonDeserialize(using = JsonDateDeserializer.class),
			@NotNull(message = "Transaction date cannot be empty") })
	@Setter
	private Date transactionDate;

	@Getter(onMethod_ = @NotBlank(message = "Traansaction type cannot be empty"))
	@Setter
	private String transactionType;

	@Getter(onMethod_ = @Range(min = 1, message = "Amount cannot be empty or zero"))
	@Setter
	private double amount;

	@Getter(onMethod_ = @JsonIgnore)
	@Setter
	private Timestamp lastupdatedTime;

}

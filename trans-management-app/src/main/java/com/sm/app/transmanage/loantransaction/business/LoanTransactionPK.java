package com.sm.app.transmanage.loantransaction.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class LoanTransactionPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String loanName;

	private long sequenceNumber;

	public LoanTransactionPK() {

	}

	public LoanTransactionPK(String loanName, long sequenceNumber) {
		setLoanName(loanName);
		setSequenceNumber(sequenceNumber);
	}

	@Column(name = "LOAN_NAME")
	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	@Column(name = "SEQUENCE_NUMBER")
	public long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("loanName:").append(loanName).append(",").append("sequenceNumber:")
				.append(sequenceNumber).toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof LoanTransactionPK))
			return false;
		LoanTransactionPK that = (LoanTransactionPK) obj;
		if (that.getLoanName() == null
				|| that.getLoanName().trim().length() == 0
				|| that.getSequenceNumber() == 0 
				|| this.getLoanName() == null
				|| this.getLoanName().trim().length() == 0
				|| this.getSequenceNumber() == 0)
			return false;
		return this.toString().equals(that.toString());
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.toString()).toHashCode();
	}

}

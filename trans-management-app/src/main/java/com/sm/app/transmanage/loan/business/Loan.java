/**
 * 
 */
package com.sm.app.transmanage.loan.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author U811403
 *
 */
@Entity
@Table(name = "LOAN")
public class Loan {

	@Id
	private String loanName;

	private String loanDescription;

	private long loanAmount;

	private Date loanStartDate;

	private Date loanEndDate;

	private String loanStatus;

	private int loanPeriod;

	private String loanType;

	public Loan() {

	}

	@Column(name = "LOAN_NAME")
	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	@Column(name = "LOAN_DESCRIPTION")
	public String getLoanDescription() {
		return loanDescription;
	}

	public void setLoanDescription(String loanDescription) {
		this.loanDescription = loanDescription;
	}

	@Column(name = "LOAN_AMOUNT")
	public long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	@Column(name = "LOAN_START_DATE")
	public Date getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	@Column(name = "LOAN_END_DATE")
	public Date getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	@Column(name = "LOAN_STATUS")
	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	@Column(name = "LOAN_PERIOD")
	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	@Column(name = "LOAN_TYPE")
	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("loanName:").append(loanName).append(",").append("loanDescription:")
				.append(loanDescription).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Loan))
			return false;
		Loan that = (Loan) obj;
		if (that.getLoanName() == null || that.getLoanName().trim().length() == 0 || this.getLoanName() == null
				|| this.getLoanName().trim().length() == 0)
			return false;
		return this.getLoanName().equals(that.getLoanName());
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(loanName).toHashCode();
	}

}

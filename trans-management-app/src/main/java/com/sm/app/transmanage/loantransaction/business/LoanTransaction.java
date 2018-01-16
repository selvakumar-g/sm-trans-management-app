/**
 * 
 */
package com.sm.app.transmanage.loantransaction.business;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author U811403
 *
 */
@Entity
@Table(name = "LOAN_TRANSACTION")
public class LoanTransaction {

	@EmbeddedId
	private LoanTransactionPK loanTransactionPK;

	private String description;

	private Date transactionDate;

	private String transactionType;

	private int amount;

	private Timestamp lastUpdatedTime;

	public LoanTransaction() {

	}

	public LoanTransactionPK getLoanTransactionPK() {
		return loanTransactionPK;
	}

	public void setLoanTransactionPK(LoanTransactionPK loanTransactionPK) {
		this.loanTransactionPK = loanTransactionPK;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TRANSACTION_DATE")
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Column(name = "TRANSACTION_TYPE")
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Column(name = "AMOUNT")
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column(name = "LAST_UPDATED_TIME")
	public Timestamp getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("loanTransactionPK:").append(loanTransactionPK.toString()).append(",")
				.append("transactionDate:").append(transactionDate).append(",").append("transactionType:")
				.append(transactionType).append(",").append("amount:").append(amount).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof LoanTransaction))
			return false;
		LoanTransaction that = (LoanTransaction) obj;
		if (that.getLoanTransactionPK() == null || that.getLoanTransactionPK().getLoanName() == null
				|| that.getLoanTransactionPK().getLoanName().trim().length() == 0
				|| that.getLoanTransactionPK().getSequenceNumber() == 0 || this.getLoanTransactionPK() == null
				|| this.getLoanTransactionPK().getLoanName() == null
				|| this.getLoanTransactionPK().getLoanName().trim().length() == 0
				|| this.getLoanTransactionPK().getSequenceNumber() == 0)
			return false;
		return this.getLoanTransactionPK().toString().equals(that.getLoanTransactionPK().toString());
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.getLoanTransactionPK().toString()).toHashCode();
	}

}

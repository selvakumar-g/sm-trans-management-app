/**
 * 
 */
package com.sm.app.transmanage.vehicletransaction.business;

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
@Table(name = "VEHICLE_TRANSACTION")
public class VehicleTransaction {

	@EmbeddedId
	private VehicleTransactionPK vehicleTransactionPK;

	private String description;

	private Date transactionDate;

	private String transactionType;
	
	private String transactionAttribute;

	private int amount;

	private Timestamp lastUpdatedTime;

	public VehicleTransaction() {

	}

	public VehicleTransactionPK getVehicleTransactionPK() {
		return vehicleTransactionPK;
	}

	public void setVehicleTransactionPK(VehicleTransactionPK vehicleTransactionPK) {
		this.vehicleTransactionPK = vehicleTransactionPK;
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
	
	@Column(name = "TRANSACTION_ATTRIBUTE")
	public String getTransactionAttribute() {
		return transactionAttribute;
	}

	public void setTransactionAttribute(String transactionAttribute) {
		this.transactionAttribute = transactionAttribute;
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
		return new StringBuilder().append("vehicleManagementPK:").append(getVehicleTransactionPK().toString())
				.append(",").append("transactionDate:").append(transactionDate).append(",").append("transactionType:")
				.append(transactionType).append(",").append("amount:").append(amount).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof VehicleTransaction))
			return false;
		VehicleTransaction that = (VehicleTransaction) obj;
		if (that.getVehicleTransactionPK() == null || that.getVehicleTransactionPK().getVehicleName() == null
				|| that.getVehicleTransactionPK().getVehicleName().trim().length() == 0
				|| that.getVehicleTransactionPK().getSequenceNumber() == 0 || this.getVehicleTransactionPK() == null
				|| this.getVehicleTransactionPK().getVehicleName() == null
				|| this.getVehicleTransactionPK().getVehicleName().trim().length() == 0
				|| this.getVehicleTransactionPK().getSequenceNumber() == 0)
			return false;
		return this.getVehicleTransactionPK().toString().equals(that.getVehicleTransactionPK().toString());
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.getVehicleTransactionPK().toString()).toHashCode();
	}

}

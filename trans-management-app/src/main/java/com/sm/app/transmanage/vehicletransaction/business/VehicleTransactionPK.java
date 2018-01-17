package com.sm.app.transmanage.vehicletransaction.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class VehicleTransactionPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String vehicleName;

	private long sequenceNumber;

	public VehicleTransactionPK() {

	}

	public VehicleTransactionPK(String vehicleName, long sequenceNumber) {
		setVehicleName(vehicleName);
		setSequenceNumber(sequenceNumber);
	}

	@Column(name = "VEHICLE_NAME")
	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
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
		return new StringBuilder().append("vehicleName:").append(vehicleName).append(",").append("sequenceNumber:")
				.append(sequenceNumber).toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof VehicleTransactionPK))
			return false;
		VehicleTransactionPK that = (VehicleTransactionPK) obj;
		if (that.getVehicleName() == null
				|| that.getVehicleName().trim().length() == 0
				|| that.getSequenceNumber() == 0 
				|| this.getVehicleName() == null
				|| this.getVehicleName().trim().length() == 0
				|| this.getSequenceNumber() == 0)
			return false;
		return this.toString().equals(that.toString());
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.toString()).toHashCode();
	} 

}

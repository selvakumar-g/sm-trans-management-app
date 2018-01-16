package com.sm.app.transmanage.vehicletransaction.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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

}

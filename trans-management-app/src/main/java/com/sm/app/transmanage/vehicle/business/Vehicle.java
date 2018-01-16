/**
 * 
 */
package com.sm.app.transmanage.vehicle.business;

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
@Table(name = "VEHICLE")
public class Vehicle {

	@Id
	private String vehicleName;

	private String vehicleNumber;

	private String VehicleType;

	private String vehicleStatus;

	private long vehicleCost;
	
	private String vehicleLoans;
	
	private long investment;

	

	public Vehicle() {

	}

	@Column(name = "VEHICLE_NAME")
	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	@Column(name = "VEHICLE_NUMBER")
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	@Column(name = "VEHICLE_TYPE")
	public String getVehicleType() {
		return VehicleType;
	}

	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}

	@Column(name = "VEHICLE_STATUS")
	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	@Column(name = "VEHICLE_COST")
	public long getVehicleCost() {
		return vehicleCost;
	}

	public void setVehicleCost(long vehicleCost) {
		this.vehicleCost = vehicleCost;
	}

	@Column(name = "VEHICLE_LOANS")
	public String getVehicleLoans() {
		return vehicleLoans;
	}

	public void setVehicleLoans(String vehicleLoans) {
		this.vehicleLoans = vehicleLoans;
	}
	
	@Column(name = "INVESTMENT")
	public long getInvestment() {
		return investment;
	}

	public void setInvestment(long investment) {
		this.investment = investment;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("vehicleName:").append(vehicleName).append(",").append("vehicleNumber:")
				.append(vehicleNumber).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Vehicle))
			return false;
		Vehicle that = (Vehicle) obj;
		if (that.getVehicleName() == null || that.getVehicleName().trim().length() == 0 || this.getVehicleName() == null
				|| this.getVehicleName().trim().length() == 0)
			return false;
		return this.getVehicleName().equals(that.getVehicleName());
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(vehicleName).toHashCode();
	}

	

}

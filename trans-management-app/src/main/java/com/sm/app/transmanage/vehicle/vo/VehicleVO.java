/**
 * 
 */
package com.sm.app.transmanage.vehicle.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author U811403
 *
 */
public @Data class VehicleVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter(onMethod_ = @NotBlank(message = "Vehicle name cannot be empty"))
	@Setter
	private String vehicleName;

	@Getter(onMethod_ = @NotBlank(message = "Vehicle number cannot be empty"))
	@Setter
	private String vehicleNumber;

	@Getter(onMethod_ = @NotBlank(message = "Vehicle type cannot be empty"))
	@Setter
	private String VehicleType;

	@Getter(onMethod_ = @NotBlank(message = "Vehicle status cannot be empty"))
	@Setter
	private String vehicleStatus;

	@Getter(onMethod_ = @Range(min = 1, message = "Vehicle cost cannot be empty or zero"))
	@Setter
	private long vehicleCost;

	private long investment;

	private String vehicleLoans;

	private String operationFlag;

}

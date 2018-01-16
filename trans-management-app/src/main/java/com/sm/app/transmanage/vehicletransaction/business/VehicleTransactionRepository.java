/**
 * 
 */
package com.sm.app.transmanage.vehicletransaction.business;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author U811403
 *
 */
public interface VehicleTransactionRepository extends JpaRepository<VehicleTransaction, VehicleTransactionPK> {

	@Query("select o from VehicleTransaction o where o.vehicleTransactionPK.vehicleName = ?1")
	public List<VehicleTransaction> findVehicleTxn(String vehicleName);

}

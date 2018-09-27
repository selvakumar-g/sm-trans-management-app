/**
 * 
 */
package com.sm.app.transmanage.vehicletransaction.business;

import java.util.Date;
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
	
	@Query(value="select o from VehicleTransaction o where o.vehicleTransactionPK.vehicleName = ?1 and o.transactionDate = ?2")
    public List<VehicleTransaction> findVehicleTxnWithDate(String vehicleName, Date transactionDate);
	

	@Query(value="select distinct year(o.transactionDate) from VehicleTransaction o where o.vehicleTransactionPK.vehicleName = ?1")
    public List<String> findVehicleTxnYears(String vehicleName);
	
	@Query(value="select o from VehicleTransaction o where o.vehicleTransactionPK.vehicleName = ?1 and o.transactionDate between ?2 and ?3")
    public List<VehicleTransaction> findVehRevenueForDateRange(String vehicleName, Date fromDate, Date toDate);
	
	@Query(value="select o from VehicleTransaction o where o.vehicleTransactionPK.vehicleName = ?1 and o.transactionDate between ?2 and ?3 and o.transactionAttribute=?4")
    public List<VehicleTransaction> findVehRevenueForDateRangeAttr(String vehicleName, Date fromDate, Date toDate, String attribute);
	 
}

/**
 * 
 */
package com.sm.app.transmanage.vehicle.business;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author U811403
 *
 */
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

	@Query("select o from Vehicle o where o.vehicleLoans like %?1%")
	public List<Vehicle> findByLoan(String loanName);
}

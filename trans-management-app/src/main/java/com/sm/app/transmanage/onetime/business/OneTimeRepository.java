/**
 * 
 */
package com.sm.app.transmanage.onetime.business;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author U811403
 *
 */
public interface OneTimeRepository extends JpaRepository<OneTime, OneTimePK> {

	@Query("select o from OneTime o where o.oneTimePK.fieldType=?1")
	List<OneTime> findByFieldType(String fieldType);
}

/**
 * 
 */
package com.sm.app.transmanage.sequencegen.business;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.stereotype.Service;
/**
 * @author U811403
 *
 */
@Service
public class LoanSequenceGen extends AbstractSequenceMaxValueIncrementer {
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public void afterPropertiesSet() {
		super.setDataSource(dataSource);
		super.setIncrementerName("LOAN_TRANSACTION_SEQUENCE");
		super.afterPropertiesSet();
	}

	@Override
	protected String getSequenceQuery() {
		return "Select LOAN_TRANSACTION_SEQUENCE.nextval from dual";
	}

	
}

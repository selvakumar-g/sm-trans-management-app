/**
 * 
 */
package com.sm.app.transmanage.loantransaction.business;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author U811403
 *
 */
public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, LoanTransactionPK> {

	@Query("select o from LoanTransaction o where o.loanTransactionPK.loanName = ?1")
	public List<LoanTransaction> findLoanTxn(String loanName);
}

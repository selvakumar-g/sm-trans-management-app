/**
 * 
 */
package com.sm.app.transmanage.loantransaction.business;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.app.transmanage.loantransaction.vo.LoanTransactionVO;
import com.sm.app.transmanage.sequencegen.business.LoanSequenceGen;
import com.sm.app.transmanage.util.DozerMapper;

/**
 * @author U811403
 *
 */
@Service
public class LoanTransactionService {

	private static final Logger LOG = LoggerFactory.getLogger(LoanTransactionService.class);

	@Autowired
	private DozerMapper dozerMapper;

	@Autowired
	private LoanTransactionRepository repo;

	@Autowired
	private LoanSequenceGen sequenceGen;

	public List<LoanTransactionVO> findAll() {
		return dozerMapper.mapList(repo.findAll(), LoanTransactionVO.class);
	}

	public List<LoanTransactionVO> save(LoanTransactionVO loanTransactionVO) {
		if (loanTransactionVO.getSequenceNumber() == 0) {
			loanTransactionVO.setSequenceNumber(sequenceGen.nextLongValue());
		}
		loanTransactionVO.setLastupdatedTime(Timestamp.valueOf(LocalDateTime.now()));
		repo.save(dozerMapper.map(loanTransactionVO, LoanTransaction.class));
		return findLoanTxn(loanTransactionVO.getLoanName());
	}

	public List<LoanTransactionVO> delete(String loanName, long sequenceNumber) {
		repo.delete(new LoanTransactionPK(loanName, sequenceNumber));
		return findLoanTxn(loanName);
	}

	public LoanTransactionVO find(String loanName, long sequenceNumber) {
		return dozerMapper.map(repo.findOne(new LoanTransactionPK(loanName, sequenceNumber)), LoanTransactionVO.class);
	}

	public List<LoanTransactionVO> findLoanTxn(String loanName) {
		return dozerMapper.mapList(repo.findLoanTxn(loanName), LoanTransactionVO.class);
	}

}

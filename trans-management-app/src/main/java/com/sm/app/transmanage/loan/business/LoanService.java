/**
 * 
 */
package com.sm.app.transmanage.loan.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.app.transmanage.loan.vo.LoanVO;
import com.sm.app.transmanage.util.DozerMapper;

/**
 * @author U811403
 *
 */
@Service
public class LoanService {
	private static final Logger LOG = LoggerFactory.getLogger(LoanService.class);

	@Autowired
	DozerMapper dozerMapper;

	@Autowired
	private LoanRepository repo;

	public List<LoanVO> save(LoanVO LoanVO) {
		repo.save(dozerMapper.map(LoanVO, Loan.class));
		return findAll();
	}

	public List<LoanVO> findAll() {
		List<Loan> entityResult = repo.findAll();
		if (entityResult != null && entityResult.size() > 0)
			return dozerMapper.mapList(repo.findAll(), LoanVO.class);
		else
			return null;
	}

	public List<LoanVO> delete(String loanName) {
		repo.delete(loanName);
		return findAll();
	}

	public LoanVO find(String loanName) {
		Loan entity = repo.findOne(loanName);
		if (entity != null)
			return dozerMapper.map(repo.findOne(loanName), LoanVO.class);
		else
			return null;
	}

	public boolean isLoanExist(String loanName) {
		return repo.exists(loanName);
	}

}

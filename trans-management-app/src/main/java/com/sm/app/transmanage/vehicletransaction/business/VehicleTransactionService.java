/**
 * 
 */
package com.sm.app.transmanage.vehicletransaction.business;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.app.transmanage.sequencegen.business.VehicleSequenceGen;
import com.sm.app.transmanage.util.DozerMapper;
import com.sm.app.transmanage.vehicletransaction.vo.VehicleTransactionVO;

/**
 * @author U811403
 *
 */
@Service
public class VehicleTransactionService {
	private static final Logger LOG = LoggerFactory.getLogger(VehicleTransactionService.class);

	@Autowired
	DozerMapper dozerMapper;

	@Autowired
	private VehicleTransactionRepository repo;

	@Autowired
	private VehicleSequenceGen sequenceGen;

	public List<VehicleTransactionVO> findAll() {
		List<VehicleTransaction> entityResult = repo.findAll();
		if (entityResult != null && entityResult.size() > 0)
			return dozerMapper.mapList(entityResult, VehicleTransactionVO.class);
		else
			return null;
	}

	public List<VehicleTransactionVO> save(VehicleTransactionVO VehicleTransactionVO) {
		if (VehicleTransactionVO.getSequenceNumber() == 0) {
			VehicleTransactionVO.setSequenceNumber(sequenceGen.nextLongValue());
		}
		VehicleTransactionVO.setLastupdatedTime(Timestamp.valueOf(LocalDateTime.now()));
		repo.save(dozerMapper.map(VehicleTransactionVO, VehicleTransaction.class));
		return findVehicleTxn(VehicleTransactionVO.getVehicleName());
	}

	public List<VehicleTransactionVO> delete(String vehicleName, long sequenceNumber) {
		repo.delete(new VehicleTransactionPK(vehicleName, sequenceNumber));
		return findVehicleTxn(vehicleName);
	}

	public VehicleTransactionVO find(String vehicleName, long sequenceNumber) {
		VehicleTransaction entityResult = repo.findOne(new VehicleTransactionPK(vehicleName, sequenceNumber));
		if (entityResult != null)
			return dozerMapper.map(entityResult, VehicleTransactionVO.class);
		else
			return null;
	}

	public List<VehicleTransactionVO> findVehicleTxn(String vehicleName) {
		List<VehicleTransaction> entityResult = repo.findVehicleTxn(vehicleName);
		if (entityResult != null && entityResult.size() > 0)
			return dozerMapper.mapList(entityResult, VehicleTransactionVO.class);
		else
			return null;
	}

}

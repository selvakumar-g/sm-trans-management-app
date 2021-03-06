/**
 * 
 */
package com.sm.app.transmanage.vehicletransaction.business;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
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
		return dozerMapper.mapList(repo.findAll(), VehicleTransactionVO.class);
	}

	public List<VehicleTransactionVO> save(VehicleTransactionVO VehicleTransactionVO) {
		if (VehicleTransactionVO.getSequenceNumber() == 0) {
			VehicleTransactionVO.setSequenceNumber(sequenceGen.nextLongValue());
		}
		VehicleTransactionVO.setLastupdatedTime(Timestamp.valueOf(LocalDateTime.now()));
		repo.save(dozerMapper.map(VehicleTransactionVO, VehicleTransaction.class));
		return findVehicleTxn(VehicleTransactionVO.getVehicleName());
	}
	
	public void save(List<VehicleTransactionVO> VehicleTransactionVOs) {
		VehicleTransactionVOs.stream().forEach(vo -> {
			vo.setSequenceNumber(sequenceGen.nextLongValue());
			vo.setLastupdatedTime(Timestamp.valueOf(LocalDateTime.now()));
			repo.save(dozerMapper.map(vo, VehicleTransaction.class));			
		});		
	}

	public List<VehicleTransactionVO> delete(String vehicleName, long sequenceNumber) {
		repo.delete(new VehicleTransactionPK(vehicleName, sequenceNumber));
		return findVehicleTxn(vehicleName);
	}

	public VehicleTransactionVO find(String vehicleName, long sequenceNumber) {
		return dozerMapper.map(repo.findOne(new VehicleTransactionPK(vehicleName, sequenceNumber)),
				VehicleTransactionVO.class);
	}

	public List<VehicleTransactionVO> findVehicleTxn(String vehicleName) {
		return dozerMapper.mapList(repo.findVehicleTxn(vehicleName), VehicleTransactionVO.class);
	}

	public List<VehicleTransactionVO> findVehicleTxn(String vehicleName, Date transactionDate) {
		return this.dozerMapper.mapList(this.repo.findVehicleTxnWithDate(vehicleName, transactionDate),
				VehicleTransactionVO.class);
	}
	
	public List<String> findVehicleTxnYears(String vehicleName){
		return repo.findVehicleTxnYears(vehicleName);
	}
	
	public List<VehicleTransactionVO> findVehRevenueForDateRange(String vehicleName, Date fromDate, Date toDate){
		return this.dozerMapper.mapList(this.repo.findVehRevenueForDateRange(vehicleName, fromDate, toDate),
				VehicleTransactionVO.class);
	}
	
	public List<VehicleTransactionVO> findVehRevenueForDateRangeAttr(String vehicleName, Date fromDate, Date toDate, String attribute){
		return this.dozerMapper.mapList(this.repo.findVehRevenueForDateRangeAttr(vehicleName, fromDate, toDate, attribute),
				VehicleTransactionVO.class);
	}

}

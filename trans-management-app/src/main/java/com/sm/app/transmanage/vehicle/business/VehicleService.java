/**
 * 
 */
package com.sm.app.transmanage.vehicle.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.app.transmanage.util.DozerMapper;
import com.sm.app.transmanage.vehicle.vo.VehicleVO;

/**
 * @author U811403
 *
 */
@Service
public class VehicleService {

	private static final Logger LOG = LoggerFactory.getLogger(VehicleService.class);

	@Autowired
	DozerMapper dozerMapper;

	@Autowired
	private VehicleRepository repo;

	public List<VehicleVO> save(VehicleVO vehicleVO) {
		LOG.info("Saving Vehicle ..");
		repo.save(dozerMapper.map(vehicleVO, Vehicle.class));
		LOG.info("finished saving Vehicle .. !");
		return findAll();
	}

	public List<VehicleVO> findAll() {
		List<Vehicle> entityValues = repo.findAll();
		return dozerMapper.mapList(entityValues, VehicleVO.class);
	}

	public List<VehicleVO> delete(String vehicleName) {
		repo.delete(vehicleName);
		return findAll();
	}

	public VehicleVO find(String vehicleName) {
		return dozerMapper.map(repo.findOne(vehicleName), VehicleVO.class);
	}

	public List<VehicleVO> findVehicleLoans(String loanName) {
		return dozerMapper.mapList(repo.findByLoan(loanName), VehicleVO.class);
	}

	public boolean isVehicleExist(String vehicleName) {
		return repo.exists(vehicleName);
	}

}

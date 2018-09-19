/**
 * 
 */
package com.sm.app.transmanage.onetime.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.app.transmanage.onetime.vo.OneTimeVO;
import com.sm.app.transmanage.util.DozerMapper;

/**
 * @author U811403
 *
 */
@Service
public class OneTimeService {

	@Autowired
	DozerMapper dozerMapper;

	@Autowired
	private OneTimeRepository repo;

	public List<OneTimeVO> save(OneTimeVO oneTimeVO) {
		repo.save(dozerMapper.map(oneTimeVO, OneTime.class));
		return find(oneTimeVO.getFieldType());
	}

	public Map<String, List<OneTimeVO>> findOneTimes(List<String> fieldTypes) {
		Map<String, List<OneTimeVO>> oneTimes = new HashMap<String, List<OneTimeVO>>();
		fieldTypes.stream().forEach(fldTyp -> {
			List<OneTime> oneTime = repo.findByFieldType(fldTyp);
			if (oneTime != null && oneTime.size() > 0) {
				oneTimes.put(fldTyp, new ArrayList<OneTimeVO>());
				oneTime.stream().forEach(ety -> oneTimes.get(fldTyp).add(dozerMapper.map(ety, OneTimeVO.class)));
			}
		});
		return oneTimes;
	}

	public List<OneTimeVO> findAll() {
		List<OneTime> onetimes = repo.findAll();
		if (onetimes != null && onetimes.size() > 0)
			return dozerMapper.mapList(onetimes, OneTimeVO.class);
		else
			return null;
	}

	public List<OneTimeVO> delete(String fieldType, String fieldVal) {
		repo.delete(new OneTimePK(fieldType, fieldVal));
		return find(fieldType);
	}

	public List<OneTimeVO> find(String fieldType) {
		List<OneTime> onetimes = repo.findByFieldType(fieldType);
		if (onetimes != null && onetimes.size() > 0)
			return dozerMapper.mapList(onetimes, OneTimeVO.class);
		else
			return null;
	}

}

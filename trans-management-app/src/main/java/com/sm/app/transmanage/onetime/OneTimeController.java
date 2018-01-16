package com.sm.app.transmanage.onetime;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sm.app.transmanage.onetime.business.OneTimeService;
import com.sm.app.transmanage.onetime.vo.OneTimeVO;
import com.sm.app.transmanage.util.Wrapper;

@RestController
public class OneTimeController {

	@Autowired
	private OneTimeService oneTimeService;

	@RequestMapping(path = "/onetime/findOneTimes", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<Map<String, List<OneTimeVO>>>> findOneTimes(@RequestBody List<String> fieldTypes) {
		return new ResponseEntity<Wrapper<Map<String, List<OneTimeVO>>>>(
				new Wrapper<Map<String, List<OneTimeVO>>>(oneTimeService.findOneTimes(fieldTypes)), HttpStatus.OK);
	}

	@RequestMapping(path = "/onetime/save", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<OneTimeVO>>> save(@RequestBody OneTimeVO oneTimeVO) {
		List<OneTimeVO> result = oneTimeService.save(oneTimeVO);
		return new ResponseEntity<Wrapper<List<OneTimeVO>>>(new Wrapper<List<OneTimeVO>>(result), HttpStatus.OK);
	}

	@RequestMapping(path = "/onetime/findAll", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<OneTimeVO>>> findAll() {
		List<OneTimeVO> result = oneTimeService.findAll();
		return new ResponseEntity<Wrapper<List<OneTimeVO>>>(new Wrapper<List<OneTimeVO>>(result), HttpStatus.OK);
	}

	@RequestMapping(path = "/onetime/delete/{fieldType}/{fieldVal}", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<OneTimeVO>>> delete(@PathVariable("fieldType") String fieldType,
			@PathVariable("fieldVal") String fieldVal) {
		List<OneTimeVO> result = oneTimeService.delete(fieldType, fieldVal);
		return new ResponseEntity<Wrapper<List<OneTimeVO>>>(new Wrapper<List<OneTimeVO>>(result), HttpStatus.OK);
	}

	@RequestMapping(path = "/onetime/find/{fieldType}", method = RequestMethod.POST, produces = "application/JSON")
	public ResponseEntity<Wrapper<List<OneTimeVO>>> find(@PathVariable("fieldType") String fieldType) {
		List<OneTimeVO> result = oneTimeService.find(fieldType);
		return new ResponseEntity<Wrapper<List<OneTimeVO>>>(new Wrapper<List<OneTimeVO>>(result), HttpStatus.OK);
	}

}

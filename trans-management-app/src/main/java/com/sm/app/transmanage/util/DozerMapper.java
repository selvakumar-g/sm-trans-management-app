/**
 * 
 */
package com.sm.app.transmanage.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author U811403
 *
 */
@Component
public class DozerMapper {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	public <T, S> Collection<T> mapCollection(Collection<S> source, Class<T> destinationClass) {
		if (source != null && source.size() > 0) {
			Collection<T> result = new ArrayList<T>();
			source.stream().forEach(o -> {
				result.add(map(o, destinationClass));
			});
			return result;
		} else
			return null;
	}

	public <T, S> List<T> mapList(List<S> source, Class<T> destinationClass) {
		if (source != null && source.size() > 0) {
			List<T> result = new ArrayList<T>();
			source.stream().forEach(o -> {
				result.add(map(o, destinationClass));
			});
			return result;
		} else
			return null;
	}

	public <T, S> T map(S source, Class<T> destinationClass) {
		if (source != null)
			return dozerBeanMapper.map(source, destinationClass);
		else
			return null;
	}
}

package com.sm.app.transmanage.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wrapper<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public Wrapper() {

	}

	public Wrapper(T details) {
		setDetails(details);
	}

	public Wrapper(T details, boolean error) {
		setDetails(details);
		setError(error);
	}

	private T details;

	private boolean error;

	private Map<String, List<String>> errorDetails;

	public T getDetails() {
		return details;
	}

	public void setDetails(T details) {
		this.details = details;
	}

	public boolean getError() {
		return errorDetails != null;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Map<String, List<String>> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(Map<String, List<String>> errorDetails) {
		this.errorDetails = errorDetails;
	}

	public void addError(String errorMsg, List<String> errorData) {
		if (errorDetails == null)
			errorDetails = new HashMap<String, List<String>>();
		if (errorDetails.containsKey(errorMsg))
			errorDetails.get(errorMsg).addAll(errorData);
		else
			errorDetails.put(errorMsg, errorData);
	}

}

package com.sm.app.transmanage.onetime.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class OneTimePK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fieldType;

	private String fieldVal;

	public OneTimePK() {

	}

	public OneTimePK(String fieldType, String fieldVal) {
		setFieldType(fieldType);
		setFieldVal(fieldVal);
	}

	@Column(name = "FIELD_TYPE")
	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "FIELD_VAL")
	public String getFieldVal() {
		return fieldVal;
	}

	public void setFieldVal(String fieldVal) {
		this.fieldVal = fieldVal;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("fieldType:").append(fieldType).append(",").append("fieldVal:")
				.append(fieldVal).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof OneTimePK))
			return false;
		OneTimePK that = (OneTimePK) obj;
		if (that.getFieldType() == null || that.getFieldType().trim().length() == 0 || that.getFieldVal() == null
				|| that.getFieldVal().trim().length() == 0 || this.getFieldType() == null
				|| this.getFieldType().trim().length() == 0 || this.getFieldVal() == null
				|| this.getFieldVal().trim().length() == 0)
			return false;
		return this.toString().equals(that.toString());
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.toString()).toHashCode();
	}
}

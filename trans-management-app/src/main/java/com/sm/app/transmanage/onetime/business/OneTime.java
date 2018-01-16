/**
 * 
 */
package com.sm.app.transmanage.onetime.business;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author U811403
 *
 */
@Entity
@Table(name = "ONE_TIME")
public class OneTime {

	@EmbeddedId
	private OneTimePK oneTimePK;

	public OneTime() {

	}

	public OneTime(OneTimePK oneTimePK) {
		setOneTimePK(oneTimePK);
	}

	public OneTimePK getOneTimePK() {
		return oneTimePK;
	}

	public void setOneTimePK(OneTimePK oneTimePK) {
		this.oneTimePK = oneTimePK;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("oneTimePK:").append(oneTimePK.toString()).append(",").toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof OneTime))
			return false;
		OneTime that = (OneTime) obj;
		if (that.getOneTimePK() == null || that.getOneTimePK().getFieldType() == null
				|| that.getOneTimePK().getFieldType().trim().length() == 0 || that.getOneTimePK().getFieldVal() == null
				|| that.getOneTimePK().getFieldVal().trim().length() == 0 || this.getOneTimePK() == null
				|| this.getOneTimePK().getFieldType() == null || this.getOneTimePK().getFieldType().trim().length() == 0
				|| this.getOneTimePK().getFieldVal() == null || this.getOneTimePK().getFieldVal().trim().length() == 0)
			return false;
		return this.getOneTimePK().toString().equals(that.getOneTimePK().toString());
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.getOneTimePK().toString()).toHashCode();
	}
}

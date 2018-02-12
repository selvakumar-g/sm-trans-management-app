package com.sm.app.transmanage.revenue.vo;

import java.util.Date;
import java.util.List;

import com.sm.app.transmanage.vehicletransaction.vo.VehicleTransactionVO;

public class RevenueVO {

	private String vehicleName;

	private Date date;

	private double earning;

	private double expense;

	private double profit;

	private List<VehicleTransactionVO> transactions;

	public RevenueVO(String vehicleName, Date date) {
		this.vehicleName = vehicleName;
		this.date = date;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getEarning() {
		return earning;
	}

	public void setEarning(double earning) {
		this.earning = earning;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public double getProfit() {
		return profit;
	}

	public List<VehicleTransactionVO> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<VehicleTransactionVO> transactions) {
		this.transactions = transactions;
	}

	public void addEarning(double value) {
		earning += value;
		profit = earning - expense;
	}

	public void addExpense(double value) {
		expense += value;
		profit = earning - expense;
	}

}

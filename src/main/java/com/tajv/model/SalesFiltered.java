package com.tajv.model;

public class SalesFiltered {
	String dateFrom;
	String dateTo;
	Boolean showOrders;
	Boolean showSales;

	Boolean showOrdersInProgress;
	Boolean showCompletedOrders;

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public Boolean getShowOrders() {
		return showOrders;
	}

	public void setShowOrders(Boolean showOrders) {
		this.showOrders = showOrders;
	}

	public Boolean getShowSales() {
		return showSales;
	}

	public void setShowSales(Boolean showSale) {
		this.showSales = showSale;
	}

	public Boolean getShowOrdersInProgress() {
		return showOrdersInProgress;
	}

	public void setShowOrdersInProgress(Boolean showOrdersInProgress) {
		this.showOrdersInProgress = showOrdersInProgress;
	}

	public Boolean getShowCompletedOrders() {
		return showCompletedOrders;
	}

	public void setShowCompletedOrders(Boolean showCompletedOrders) {
		this.showCompletedOrders = showCompletedOrders;
	}

}

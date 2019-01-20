package com.tajv.dao;

import java.util.List;

import com.tajv.model.Sale;

public interface SaleDao {

	public Sale getSale(int id);

	public List<Sale> getAllSales();

	public void saveSale(Sale sale);

	public void deleteSale(Sale sale);

	public void updateSale(Sale sale);

}

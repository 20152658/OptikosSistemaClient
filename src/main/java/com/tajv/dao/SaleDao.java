package com.tajv.dao;

import com.tajv.model.Sale;

public interface SaleDao {

	public Sale getSale(int id);

	public void saveSale(Sale sale);

	public void deleteSale(Sale sale);

	public void updateSale(Sale sale);

}

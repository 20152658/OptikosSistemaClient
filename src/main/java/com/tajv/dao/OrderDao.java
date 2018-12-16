package com.tajv.dao;

import com.tajv.model.Order;

public interface OrderDao {

	public Order getOrderById(int id);

	public void saveOrder(Order order);

	public void deleteOrder(Order order);

	public void updateOder(Order order);

}

package com.tajv.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tajv.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Order getOrderById(int id) {
		String hql = "from Order where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Order> orderList = query.list();

		if (orderList != null && !orderList.isEmpty()) {
			return orderList.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public void saveOrder(Order order) {
		sessionFactory.getCurrentSession().save(order);
	}

	@Override
	@Transactional
	public void deleteOrder(Order order) {
		sessionFactory.getCurrentSession().delete(order);
	}

	@Override
	@Transactional
	public void updateOder(Order order) {
		sessionFactory.getCurrentSession().update(order);
	}

}

package com.tajv.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tajv.model.Sale;

@Repository
public class SaleDaoImpl implements SaleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Sale getSale(int id) {
		String hql = "from Sale where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Sale> saleList = query.list();

		if (saleList != null && !saleList.isEmpty()) {
			return saleList.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public void saveSale(Sale sale) {
		sessionFactory.getCurrentSession().save(sale);
	}

	@Override
	@Transactional
	public void deleteSale(Sale save) {
		sessionFactory.getCurrentSession().delete(save);
	}

	@Override
	@Transactional
	public void updateSale(Sale sale) {
		sessionFactory.getCurrentSession().update(sale);
	}

	@Override
	@Transactional
	public List<Sale> getAllSales() {
		String hql = "from Sale";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Sale> salesList = query.list();

		if (salesList != null && !salesList.isEmpty()) {
			return salesList;
		}
		return null;
	}

}

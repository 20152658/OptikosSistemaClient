package com.tajv.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tajv.model.Item;

@Repository
public class ItemDaoImpl implements ItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Item getItemById(int id) {
		String hql = "from Item where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Item> itemList = query.list();

		if (itemList != null && !itemList.isEmpty()) {
			return itemList.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public void saveItem(Item preke) {
		sessionFactory.getCurrentSession().save(preke);
	}

	@Override
	@Transactional
	public void deleteItem(Item item) {
		sessionFactory.getCurrentSession().delete(item);
	}

	@Override
	@Transactional
	public void updateItem(Item item) {
		sessionFactory.getCurrentSession().update(item);
	}

	@Override
	@Transactional
	public List<Item> getAllItems() {
		String hql = "from Item";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Item> itemList = query.list();

		if (itemList != null && !itemList.isEmpty()) {
			return itemList;
		}
		return null;
	}

}

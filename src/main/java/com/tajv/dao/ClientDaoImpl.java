package com.tajv.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tajv.model.Client;

@Repository
public class ClientDaoImpl implements ClientDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Client getClientById(int id) {
		String hql = "from Client where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Client> clientList = query.list();

		if (clientList != null && !clientList.isEmpty()) {
			return clientList.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public void saveClient(Client client) {
		sessionFactory.getCurrentSession().save(client);
	}

	@Override
	@Transactional
	public void deleteClient(Client client) {
		sessionFactory.getCurrentSession().delete(client);
	}

	@Override
	@Transactional
	public void updateClient(Client client) {
		sessionFactory.getCurrentSession().update(client);
	}

	@Override
	@Transactional
	public List<Client> getAllClients() {
		String hql = "from Client";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Client> clientList = query.list();
		if (clientList != null && !clientList.isEmpty()) {
			return clientList;
		}

		return null;
	}

}

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
		System.out.println("hey client 56");
		String hql = "from Client";
		System.out.println("hey client 58");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("hey client 60");
		@SuppressWarnings("unchecked")
		List<Client> clientList = query.list();
		System.out.println("hey client 63");
		if (clientList != null && !clientList.isEmpty()) {
			System.out.println("hey client 65");
			return clientList;
		}

		System.out.println("hey client 69");
		return null;
	}

}

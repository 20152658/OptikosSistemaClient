package com.tajv.dao;

import com.tajv.model.Client;

public interface ClientDao {

	public Client getClientById(int id);

	public void saveClient(Client client);

	public void deleteClient(Client client);
	
	public void updateClient(Client client);


}

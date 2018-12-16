package com.tajv.dao;

import java.util.List;

import com.tajv.model.Item;

public interface ItemDao {

	public Item getItemById(int id);

	public List<Item> getAllItems();

	public void saveItem(Item preke);

	public void deleteItem(Item preke);

	public void updateItem(Item preke);

}

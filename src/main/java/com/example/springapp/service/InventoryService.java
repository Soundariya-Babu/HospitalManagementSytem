package com.example.springapp.service;

import java.util.List;
import java.util.Optional;


import com.example.springapp.model.Inventory;

public interface InventoryService {

	 public void saveInventory(Inventory inventory);
	 public void updateInventory( Inventory inventory);
	 public String deleteInventory(Long id);
	 public List<Inventory> getAllInventory();
	 public Optional<Inventory> getInventoryById(Long id);
	 
}

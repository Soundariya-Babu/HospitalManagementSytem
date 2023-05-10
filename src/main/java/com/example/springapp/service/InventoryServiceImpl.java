package com.example.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Inventory;
import com.example.springapp.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepo;

	@Override
	public void saveInventory(Inventory inventory) {
		inventoryRepo.save(inventory);
	}

	@Override
	public void updateInventory(Inventory inventory) {
		if(inventoryRepo.existsById(inventory.getId()))
		{
			inventoryRepo.save(inventory);
	    }
	}

	@Override
	public String deleteInventory(Long id) {
		if(inventoryRepo.existsById(id))
		{
		inventoryRepo.deleteById(id);
		return "Deleted";
		}
		return "ID not exists";
	}

	@Override
	public List<Inventory> getAllInventory() {
		 List<Inventory> inventories= inventoryRepo.findAll();
				 return inventories;
	}

	@Override
	public Optional<Inventory> getInventoryById(Long id) {
		Optional<Inventory> inventory=inventoryRepo.findById(id);
		return inventory;
	}

}

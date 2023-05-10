package com.example.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.model.Inventory;
import com.example.springapp.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping
	public ResponseEntity<Inventory> save(@RequestBody Inventory inventory){
			
		inventoryService.saveInventory(inventory);
		return new ResponseEntity<>(inventory, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Inventory> update(@RequestBody Inventory inventory){
			
		inventoryService.updateInventory(inventory);
		return new ResponseEntity<>(inventory, HttpStatus.CREATED);
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam Long id){
			
		String s =inventoryService.deleteInventory(id);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity <List<Inventory>> getAll(){
			
		List <Inventory> inventory=inventoryService.getAllInventory();
		return new ResponseEntity<> (inventory, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Optional<Inventory>> getById(@RequestParam Long id){
			
		Optional<Inventory> inventory=inventoryService.getInventoryById(id);
		if(inventory.isEmpty())
		{
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>(inventory, HttpStatus.OK);
	}
	
}

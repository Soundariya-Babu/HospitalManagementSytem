package com.example.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springapp.model.Staff;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
	
	@Query(value = "SELECT s FROM Staff s where s.jobTitle= :jobTitle")
	public List<Staff> findByJobTitle(String jobTitle);

}

package com.scb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scb.model.CustomerRequestData;

public interface CustomerDataReposatory extends JpaRepository<CustomerRequestData, Long> {

}

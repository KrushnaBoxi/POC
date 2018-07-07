package com.scb.utils;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.scb.model.CustomerRequest;
import com.scb.model.CustomerRequestData;

@Component
public class SCBCommeonMethods {
	
	public LocalDateTime getCurrentDateTime(){
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime;
	}
	
	public CustomerRequestData getCustomerDataFromRequest(CustomerRequest customerRequest){
		
		CustomerRequestData customerDataReposatory = CustomerRequestData.builder()
				.customerAccType(customerRequest.getCustomerAccType())
				.customerId(customerRequest.getCustomerId())
				.customerName(customerRequest.getCustomerName())
				.customerRegion(customerRequest.getCustomerRegion())
				.timeStamp(getCurrentDateTime())
				.build();
		return customerDataReposatory;
	}

}

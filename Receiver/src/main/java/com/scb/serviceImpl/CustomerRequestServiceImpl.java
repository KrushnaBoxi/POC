package com.scb.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scb.dao.CustomerDataReposatory;
import com.scb.model.CustomerRequest;
import com.scb.model.CustomerRequestData;
import com.scb.model.CustomerResponse;
import com.scb.service.CustomerRequestService;
import com.scb.utils.SCBCommeonMethods;
@Service
public class CustomerRequestServiceImpl implements CustomerRequestService{
	@Autowired
	private CustomerDataReposatory customerDataReposatory;
	@Autowired
	private SCBCommeonMethods commonMethods;

	@Override
	public CustomerResponse customerRequestHandleService(CustomerRequest customerRequest) {
		CustomerRequestData customerRequestData = commonMethods.getCustomerDataFromRequest(customerRequest);
		return CustomerResponse.builder().customerRequestData(customerRequestData).responseCode(200).responseMessage("success").build();
	}
	

}

package com.scb.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scb.dao.CustomerDataReposatory;
import com.scb.model.CustomerRequest;
import com.scb.model.CustomerRequestData;
import com.scb.model.CustomerResponse;
import com.scb.service.CustomerRequestService;
import com.scb.utils.SCBCommonMethods;

@Service
public class CustomerRequestServiceImpl implements CustomerRequestService {
	@Autowired
	private CustomerDataReposatory customerDataReposatory;
	@Autowired
	private SCBCommonMethods commonMethods;

	@Override
	public CustomerResponse customerRequestHandleService(CustomerRequest customerRequest) {
		List<CustomerRequestData> customerList = customerDataReposatory
				.findByCustomerName(customerRequest.getCustomerName());
		if (customerList.isEmpty()) {
			return commonMethods.getSuccessResponse(
					customerDataReposatory.save(commonMethods.getCustomerDataFromRequest(customerRequest)));
		} else {
			return commonMethods.getErrorResponse("Duplicate User");
		}

	}

}

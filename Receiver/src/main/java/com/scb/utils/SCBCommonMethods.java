package com.scb.utils;

import java.time.LocalDateTime;

import org.hamcrest.text.IsEmptyString;
import org.springframework.stereotype.Component;

import com.scb.model.CustomerRequest;
import com.scb.model.CustomerRequestData;
import com.scb.model.CustomerResponse;

@Component
public class SCBCommonMethods {

	public LocalDateTime getCurrentDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime;
	}

	public CustomerRequestData getCustomerDataFromRequest(CustomerRequest customerRequest) {

		CustomerRequestData customerDataReposatory = CustomerRequestData.builder()
				.customerAccType(customerRequest.getCustomerAccType()).customerId(customerRequest.getCustomerId())
				.customerName(customerRequest.getCustomerName()).customerRegion(customerRequest.getCustomerRegion())
				.timeStamp(getCurrentDateTime()).build();
		return customerDataReposatory;
	}

	public CustomerResponse getSuccessResponse(CustomerRequestData customerRequestData) {
		return CustomerResponse.builder().customerRequestData(customerRequestData).responseCode(200)
				.responseMessage("Success").build();

	}

	public CustomerResponse getErrorResponse() {
		return CustomerResponse.builder().responseCode(400).responseMessage("Error Excution ").build();

	}

	public CustomerResponse getErrorResponseDetails(int errorCode, String errorMessage) {
		return CustomerResponse.builder().responseCode(errorCode).responseMessage(errorMessage).build();

	}
	
	
	public boolean isValidateCustomerRequest(CustomerRequest customerRequest) {
		if(null == customerRequest){
			return false;
		} else if(customerRequest.getCustomerName().isEmpty() || "IND".equals(customerRequest.getCustomerName()) ){
			return false;			
		} else if(customerRequest.getCustomerId() == 0){
			return false;
		}
		return true;
	}

	public CustomerResponse getErrorResponse(String errorMessage) {
		return CustomerResponse.builder().responseCode(500).responseMessage(errorMessage).build();
	}


}

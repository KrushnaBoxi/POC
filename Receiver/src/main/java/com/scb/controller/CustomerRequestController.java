package com.scb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.scb.model.CustomerRequest;
import com.scb.model.CustomerResponse;
import com.scb.service.CustomerRequestService;
import com.scb.utils.ReceiverConstants;
import com.scb.utils.SCBCommonMethods;


@RestController
@RequestMapping(ReceiverConstants.CUSTOMER_URL)
public class CustomerRequestController {
	@Autowired
	private CustomerRequestService customerRequestService;
	@Autowired
	private SCBCommonMethods commonMethods;

	@RequestMapping(value = ReceiverConstants.CUSTOMER_REQUEST_HANDLE_URL, method = RequestMethod.POST, produces = { "application/json", "application/xml" })
	public CustomerResponse customerRequestHandle(@RequestBody CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		if (commonMethods.isValidateCustomerRequest(customerRequest)) {
			customerResponse = customerRequestService.customerRequestHandleService(customerRequest);
		} else {
			return commonMethods.getErrorResponse("Request Validation Error");
		}
		return customerResponse;
	}

	@RequestMapping(value = ReceiverConstants.CUSTOMER_REQUEST_HANDLE_URL_REQUEST)
	public CustomerRequest customerRequestHandleExampleRequest() {

		return CustomerRequest.builder().customerAccType("Saving").customerId(22).customerName("Test Customer")
				.customerRegion("India").build();
		// return
		// customerRequestService.customerRequestHandleService(customerRequest);

	}

}

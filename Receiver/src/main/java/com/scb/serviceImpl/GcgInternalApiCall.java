package com.scb.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.scb.config.CustomerConfig;
import com.scb.model.AuditLog;
import com.scb.model.CustomerRequestData;
import com.scb.model.CustomerResponse;
import com.scb.model.MsErrorLog;
import com.scb.utils.SCBCommonMethods;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GcgInternalApiCall {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CustomerConfig customerConfig;
	@Autowired
	private SCBCommonMethods commonMethods;

	public ResponseEntity<AuditLog> msAuditLogApiCall(AuditLog auditLog) {
		ResponseEntity<AuditLog> responseAuditLog = null;
		try {
			log.debug("GCG internal call audit");
			HttpEntity<AuditLog> entity = new HttpEntity<AuditLog>(auditLog);
			responseAuditLog = restTemplate.exchange(customerConfig.getAuditLogURL(), HttpMethod.POST, entity,
					AuditLog.class);
		} catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerEx) {
			MsErrorLog msErrorLog = commonMethods.getErrorLogDetails(httpClientOrServerEx);
			msErrorLog.setErrorCode(httpClientOrServerEx.getStatusCode().toString());
			msErrorLog.setUuid(auditLog.getUuid());
			msErrorLog.setTimeStamp(auditLog.getTimeStamp());
			if (HttpStatus.INTERNAL_SERVER_ERROR.equals(httpClientOrServerEx.getStatusCode())) {
				msErrorLogApiCall(msErrorLog);
				// retry logic goes here
			} else {
				// do something
			}
		} catch (Exception e) {
			MsErrorLog msErrorLog = commonMethods.getErrorLogDetails(e);
			msErrorLog.setUuid(auditLog.getUuid());
			msErrorLog.setTimeStamp(auditLog.getTimeStamp());
			msErrorLogApiCall(msErrorLog);
		}
		return responseAuditLog;
	}

	public void msErrorLogApiCall(MsErrorLog msErrorLog) {
		try {
			restTemplate.postForObject(customerConfig.getErrorLogURL(), msErrorLog, MsErrorLog.class);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public ResponseEntity<CustomerResponse> msCustomerPersistApiCall(CustomerRequestData customerRequestData) {
		ResponseEntity<CustomerResponse> responseOfCustomerApi = null;
		try {
			HttpEntity<CustomerRequestData> entity = new HttpEntity<CustomerRequestData>(customerRequestData);
			responseOfCustomerApi = restTemplate.exchange(customerConfig.getCustomerRequestPersistURL(),
					HttpMethod.POST, entity, CustomerResponse.class);
		} catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerEx) {
			MsErrorLog msErrorLog = commonMethods.getErrorLogDetails(httpClientOrServerEx);
			msErrorLog.setErrorCode(httpClientOrServerEx.getStatusCode().toString());
			msErrorLog.setUuid(customerRequestData.getTransactionId());
			msErrorLog.setTimeStamp(customerRequestData.getTimeStamp());
			if (HttpStatus.INTERNAL_SERVER_ERROR.equals(httpClientOrServerEx.getStatusCode())) {
				msErrorLogApiCall(msErrorLog);
				return new ResponseEntity<CustomerResponse>( commonMethods.getErrorResponse("Problem While calling persister api"), HttpStatus.BAD_GATEWAY);
				//return responseOfCustomerApi;
				// retry logic goes here
			} else {
				return new ResponseEntity<CustomerResponse>( commonMethods.getErrorResponse("Problem While calling persister api"), HttpStatus.BAD_GATEWAY);
				
				// do something
			}
		} catch (Exception e) {
			MsErrorLog msErrorLog = commonMethods.getErrorLogDetails(e);
			msErrorLog.setUuid(customerRequestData.getTransactionId());
			msErrorLog.setTimeStamp(customerRequestData.getTimeStamp());
			msErrorLogApiCall(msErrorLog);
			return new ResponseEntity<CustomerResponse>( commonMethods.getErrorResponse("Problem While calling persister api"), HttpStatus.BAD_GATEWAY);
			
		}
		return responseOfCustomerApi;
	}

	public ResponseEntity<CustomerResponse> msDownStreamCall(CustomerRequestData customerRequestData) {
		ResponseEntity<CustomerResponse> responseOfCustomerApi = null;
		//ResponseEntity<CustomerRequestData> responseCustomerRequestData = null;
		try{
			HttpEntity<CustomerRequestData> entity = new HttpEntity<CustomerRequestData>(customerRequestData);
			responseOfCustomerApi = restTemplate.exchange(customerConfig.getDownStreamURL(), HttpMethod.POST, entity,
					CustomerResponse.class);
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerEx) {
			MsErrorLog msErrorLog = commonMethods.getErrorLogDetails(httpClientOrServerEx);
			msErrorLog.setErrorCode(httpClientOrServerEx.getStatusCode().toString());
			msErrorLog.setUuid(customerRequestData.getTransactionId());
			msErrorLog.setTimeStamp(customerRequestData.getTimeStamp());
			if (HttpStatus.INTERNAL_SERVER_ERROR.equals(httpClientOrServerEx.getStatusCode())) {
				msErrorLogApiCall(msErrorLog);
				return new ResponseEntity<CustomerResponse>( commonMethods.getErrorResponse("Problem While calling downstream api"), HttpStatus.BAD_GATEWAY);
				
				// retry logic goes here
			} else {
				return new ResponseEntity<CustomerResponse>( commonMethods.getErrorResponse("Problem While calling downstream api"), HttpStatus.BAD_GATEWAY);
				
				// do something
			}
		} catch (Exception e) {
			MsErrorLog msErrorLog = commonMethods.getErrorLogDetails(e);
			msErrorLog.setUuid(customerRequestData.getTransactionId());
			msErrorLog.setTimeStamp(customerRequestData.getTimeStamp());
			msErrorLogApiCall(msErrorLog);
			return new ResponseEntity<CustomerResponse>( commonMethods.getErrorResponse("Problem While calling downstream api"), HttpStatus.BAD_GATEWAY);
			
		}
		return responseOfCustomerApi;
	}

}

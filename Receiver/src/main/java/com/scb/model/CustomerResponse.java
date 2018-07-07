package com.scb.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.scb.dao.CustomerDataReposatory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor @XmlRootElement
public class CustomerResponse {
	
	private long responseCode;
	private String responseMessage;
	private CustomerRequestData customerRequestData;

}

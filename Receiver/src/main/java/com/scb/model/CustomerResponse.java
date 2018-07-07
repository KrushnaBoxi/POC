package com.scb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.scb.dao.CustomerDataReposatory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor @XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerResponse {
	
	private long responseCode;
	private String responseMessage;
	//@XmlElement(name = "CustomerResponse")
	private CustomerRequestData customerRequestData;

}

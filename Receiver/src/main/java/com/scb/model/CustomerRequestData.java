package com.scb.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @XmlRootElement
public class CustomerRequestData {
	@Column
	private long customerId;
	@Column
	private String customerName;
	@Id
	private int slNo;
	@Column
	private String customerAccType;
	@Column
	private String customerRegion;
	@Column
	private LocalDateTime timeStamp;
	
	
	

}

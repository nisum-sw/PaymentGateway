package com.sw.payment.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@lombok.Getter
@lombok.Setter
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Card implements Serializable{
	
	

	private Long cardId;

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("cardholder_name")
	private String name;

	@Column(name="card_number")
	@JsonProperty("card_number")
	private String number;
	
	@JsonProperty("exp_date")
	private String expiryDt;
    @JsonProperty("cvv")
	private String cvv;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="card")
    private List<Transaction> transactions;

		
}

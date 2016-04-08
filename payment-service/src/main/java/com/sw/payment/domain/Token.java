package com.sw.payment.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
@Getter
@Setter
@Entity
@Table(name = "TOKEN")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Token {
	
	@Id
	@SequenceGenerator( name="EMPLOYEE_SEQ1", initialValue=5,sequenceName="EMPLOYEE_SEQ1", allocationSize=1 )
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ1")
	private long tokenId;

	
	@JsonProperty("token_type")
	private String tokenType;
	
	@JsonProperty("token_data")
	@OneToOne(cascade = {CascadeType.ALL})
	private Transarmor tokenData;

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Transarmor getTokenData() {
		return tokenData;
	}

	public void setTokenData(Transarmor tokenData) {
		this.tokenData = tokenData;
	}
}

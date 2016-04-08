package com.sw.payment.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "Transarmor")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Transarmor extends Card{

	@Id
	@SequenceGenerator( name="EMPLOYEE_SEQ1", initialValue=5,sequenceName="EMPLOYEE_SEQ1", allocationSize=1 )
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ1")
	private long transarmorId;
	
	@JsonProperty("value")
	private String token;
	
	
	public String getValue() {
		return token;
	}
	public void setValue(String value) {
		this.token = value;
	}

}

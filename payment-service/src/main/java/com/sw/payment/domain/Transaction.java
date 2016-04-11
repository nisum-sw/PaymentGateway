package com.sw.payment.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

@lombok.Getter
@lombok.Setter
/*@Entity
@Table(name="Transaction")*/
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Transaction implements Serializable{

	/*@Id
	@SequenceGenerator( name="EMPLOYEE_SEQ1", initialValue=5,sequenceName="EMPLOYEE_SEQ1", allocationSize=1 )
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ1")
	private Long id;
*/
	@Column(name="transactionId")
	@JsonProperty("transaction_id")
	private String transactionId;

	@Column(name="amount")
	private String amount;

	@JsonProperty("currency")
	private String currency;

/*	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "card_id")
	@JsonProperty("card")
*/
	/*@ManyToOne
	private Card card;
	*/
}

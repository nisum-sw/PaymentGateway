package com.sw.payment.domain;

import java.util.ArrayList;

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

@Getter
@Setter
@Entity
@Table(name = "ERROR")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Error {
	
	@Id
	@SequenceGenerator( name="EMPLOYEE_SEQ1", initialValue=5,sequenceName="EMPLOYEE_SEQ1", allocationSize=1 )
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ1")
	private long errorId;

	
    @JsonProperty("messages")
    private ArrayList<String> messages;
    
    
    public ArrayList<String> getMessages() {
        return messages;
    }
    
    
    public void setMessage(ArrayList<String> message) {
        this.messages = message;
    }
}

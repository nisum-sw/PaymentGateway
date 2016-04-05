/**
 * 
 */
/**
 * @author Nisum-User
 *
 */
package com.sw.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sw.payment.domain.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String>{
	
}
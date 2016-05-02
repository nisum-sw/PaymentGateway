package com.nisum.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nisum.services.dto.TestSuite;

@RepositoryRestResource(collectionResourceRel = "testsuites", path = "testsuites")
public interface TestSuiteRepository extends MongoRepository<TestSuite, String> {

}

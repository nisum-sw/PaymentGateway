package com.nisum.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nisum.services.dto.TestCase;

@RepositoryRestResource(collectionResourceRel = "testcases", path = "testcases")
public interface TestCaseRepository extends MongoRepository<TestCase, String> {

}

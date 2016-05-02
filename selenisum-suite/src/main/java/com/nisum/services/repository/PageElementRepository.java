package com.nisum.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nisum.services.dto.PageElement;

@RepositoryRestResource(collectionResourceRel = "pgElements", path = "pgElements")
public interface PageElementRepository extends
		MongoRepository<PageElement, String> {

}

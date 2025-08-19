package com.idm.challenge.credits.mscredits.repository.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.idm.challenge.credits.mscredits.domain.model.Credit;
import com.idm.challenge.credits.mscredits.repository.CustomCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CustomCreditRepositoryImpl implements CustomCreditRepository {

  @Autowired
  private ReactiveMongoTemplate mongoTemplate;

  @Override
  public Flux<Credit> findByClientFirstNameAndLastName(String firstName,
      String lastName) {
    Query query = new Query(where("client.firstName").is(firstName)
        .and("client.lastName").is(lastName));
    return mongoTemplate.find(query, Credit.class);
  }

  @Override
  public Flux<Credit> findByClientDocumentNumber(String documentNumber) {
    Query query = new Query(where("client.documentNumber").is(documentNumber));
    return mongoTemplate.find(query, Credit.class);
  }

  @Override
  public Mono<Credit> findByClientDocumentNumberAndCreditType(String documentNumber,
      Integer creditType) {
    Query query = new Query(where("client.documentNumber").is(documentNumber)
        .and("type").is(creditType));
    return mongoTemplate.findOne(query, Credit.class);
  }

}
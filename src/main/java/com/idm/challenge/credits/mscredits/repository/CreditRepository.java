package com.idm.challenge.credits.mscredits.repository;

import com.idm.challenge.credits.mscredits.domain.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit, String>, CustomCreditRepository {

  Mono<Credit> findByProductId(String number);

  Mono<Credit> findByNumber(String number);
  Mono<Long> countByClientDocumentNumber(String documentNumber);
}

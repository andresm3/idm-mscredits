package com.idm.challenge.credits.mscredits.repository;

import com.idm.challenge.credits.mscredits.domain.model.Credit;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CustomCreditRepository {

  Flux<Credit> findByClientFirstNameAndLastName(String firstName, String lastName);

  Flux<Credit> findByClientDocumentNumber(String documentNumber);

  Mono<Credit> findByClientDocumentNumberAndCreditType(String documentNumber, Integer creditType);
}

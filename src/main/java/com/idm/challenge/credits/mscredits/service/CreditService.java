package com.idm.challenge.credits.mscredits.service;

import com.idm.challenge.credits.mscredits.domain.dto.CreditRequest;
import com.idm.challenge.credits.mscredits.domain.model.Credit;
import java.math.BigDecimal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
  Mono<Credit> createCredit(CreditRequest credit);
  Mono<Credit> getCreditById(String id);
  Mono<Credit> getCreditByNumber(String number);
  Flux<Credit> getCreditByClientName(String firstName, String lastName);
  Mono<Credit> checkIfClientOwnsCreditCard(String documentNumber);
}

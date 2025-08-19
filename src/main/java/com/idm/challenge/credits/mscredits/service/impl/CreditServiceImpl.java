package com.idm.challenge.credits.mscredits.service.impl;

import com.idm.challenge.credits.mscredits.domain.dto.CreditRequest;
import com.idm.challenge.credits.mscredits.domain.model.Credit;
import com.idm.challenge.credits.mscredits.exception.CustomInformationException;
import com.idm.challenge.credits.mscredits.exception.CustomNotFoundException;
import com.idm.challenge.credits.mscredits.repository.CreditRepository;
import com.idm.challenge.credits.mscredits.service.CreditService;
import com.idm.challenge.credits.mscredits.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

  @Autowired
  private CreditRepository creditRepository;

  @Override
  public Mono<Credit> createCredit(CreditRequest request) {
    return creditRepository.findByNumber(request.number())
        .flatMap(existingCredit -> Mono.error(new CustomInformationException("Credit number has already been created")))
        .switchIfEmpty(
            creditRepository.countByClientDocumentNumber(request.client().documentNumber())
                .flatMap(count -> {
                  if (request.client().type() == Constants.ClientType.PERSONAL && count > 0) {
                    System.out.println(">>exception more than 1 personal credit");
                    return Mono.error(new CustomInformationException("The client type allows only 1 credit"));
                  }
                  return Mono.just(request);
                })
                .map(this::mapToCredit)
                .flatMap(creditRepository::save)
        )
        .cast(Credit.class);
  }

  @Override
  public Mono<Credit> getCreditById(String id) {

    return creditRepository.findById(id)
        .switchIfEmpty(Mono
            .error(new CustomNotFoundException(Constants.CreditErrorMsg.MONO_NOT_FOUND_MESSAGE)));

  }

  @Override
  public Mono<Credit> getCreditByNumber(String number) {
    return creditRepository.findByNumber(number)
        .switchIfEmpty(Mono
            .error(new CustomNotFoundException(Constants.CreditErrorMsg.MONO_NOT_FOUND_MESSAGE)));

  }

  @Override
  public Flux<Credit> getCreditByClientName(String firstName, String lastName) {

    return creditRepository.findByClientFirstNameAndLastName(firstName, lastName)
        .switchIfEmpty(Mono
            .error(new CustomNotFoundException(Constants.CreditErrorMsg.FLUX_NOT_FOUND_MESSAGE)));

  }

  @Override
  public Mono<Credit> checkIfClientOwnsCreditCard(String documentNumber) {
    System.out.println("checkIfClientOwnsCreditCard: " + documentNumber);
    return creditRepository
        .findByClientDocumentNumberAndCreditType(documentNumber, Constants.CreditType.CARD)
        .switchIfEmpty(Mono.empty());
  }


  private Credit mapToCredit(CreditRequest request) {
    return Credit.builder()
        .productId(request.productId())
        .creditBalance(request.creditBalance())
        .type(request.type())
        .client(request.client())
        .number(request.number())
        .creditCard(request.creditCard())
        .build();
  }
}

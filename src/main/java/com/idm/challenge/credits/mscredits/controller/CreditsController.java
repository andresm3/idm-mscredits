package com.idm.challenge.credits.mscredits.controller;

import com.idm.challenge.credits.mscredits.domain.dto.CreditRequest;
import com.idm.challenge.credits.mscredits.domain.model.Credit;
import com.idm.challenge.credits.mscredits.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credits")
public class CreditsController {

  @Autowired
  private CreditService service;

  @PostMapping("/createCreditCard")
  public Mono<Credit> createCreditCard(@RequestBody CreditRequest request) {
    System.out.println(">>CreditResource=> createCard ");
    return service.createCredit(request);
  }

  @GetMapping("/id/{id}")
  public Mono<Credit> findById(@PathVariable String id) {
    return service.getCreditById(id);
  }

  @GetMapping("/number/{number}")
  public Mono<Credit> searchCreditByNumber(@PathVariable("number") String number) {
    return service.getCreditByNumber(number);
  }

  @GetMapping("/client/firstName/{firstName}/lastName/{lastName}")
  public Flux<Credit> findByCreditFirstNameAndLastName(@PathVariable String firstName,
      @PathVariable String lastName) {
    return service.getCreditByClientName(firstName, lastName);
  }

  @GetMapping("/clientOwnsCard/{documentNumber}")
  public Mono<Credit> checkIfClientOwnsCreditCard(
      @PathVariable("documentNumber") String documentNumber) {
    return service.checkIfClientOwnsCreditCard(documentNumber);
  }

}

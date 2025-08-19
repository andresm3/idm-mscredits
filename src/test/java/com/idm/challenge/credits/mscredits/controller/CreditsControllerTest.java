package com.idm.challenge.credits.mscredits.controller;

import com.idm.challenge.credits.mscredits.domain.dto.CreditRequest;
import com.idm.challenge.credits.mscredits.domain.model.Credit;
import com.idm.challenge.credits.mscredits.mock.MockData;
import com.idm.challenge.credits.mscredits.service.CreditService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@WebFluxTest(CreditsController.class)
class CreditsControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private CreditService creditService;

    @Test
    void testCreateCreditCard() {
        CreditRequest request = new CreditRequest("1", "1234567890",
            "1234-5678-9012-3456", "prod-001", MockData.mockClient(), 1,
            new BigDecimal("10000.00"), new BigDecimal("5000.00"), 15,
            new BigDecimal("0.05"), 12, Boolean.TRUE);

        Mockito.when(creditService.createCredit(request)).thenReturn(Mono.just(MockData.mockCredit()));

        webTestClient.post()
            .uri("/credits/createCreditCard")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Credit.class)
            .isEqualTo(MockData.mockCredit());
    }

    @Test
    void testFindById() {
        String id = "1";

        Mockito.when(creditService.getCreditById(id)).thenReturn(Mono.just(MockData.mockCredit()));

        webTestClient.get()
            .uri("/credits/id/{id}", id)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Credit.class)
            .isEqualTo(MockData.mockCredit());
    }

    @Test
    void testSearchCreditByNumber() {
        String number = "1234567890";

        Mockito.when(creditService.getCreditByNumber(number)).thenReturn(Mono.just(MockData.mockCredit()));

        webTestClient.get()
            .uri("/credits/number/{number}", number)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Credit.class)
            .isEqualTo(MockData.mockCredit());
    }

    @Test
    void testFindByCreditFirstNameAndLastName() {
        String firstName = "Juan";
        String lastName = "Perez";

        Mockito.when(creditService.getCreditByClientName(firstName, lastName))
            .thenReturn(Flux.just(MockData.mockCredit()));

        webTestClient.get()
            .uri("/credits/client/firstName/{firstName}/lastName/{lastName}", firstName, lastName)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Credit.class)
            .contains(MockData.mockCredit());
    }

    @Test
    void testCheckIfClientOwnsCreditCard() {
        String documentNumber = "12345679";

        Mockito.when(creditService.checkIfClientOwnsCreditCard(documentNumber)).thenReturn(Mono.just(MockData.mockCredit()));

        webTestClient.get()
            .uri("/credits/clientOwnsCard/{documentNumber}", documentNumber)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Credit.class)
            .isEqualTo(MockData.mockCredit());
    }
}
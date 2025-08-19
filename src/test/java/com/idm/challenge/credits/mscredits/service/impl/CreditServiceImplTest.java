package com.idm.challenge.credits.mscredits.service.impl;

import com.idm.challenge.credits.mscredits.domain.dto.CreditRequest;
import com.idm.challenge.credits.mscredits.exception.CustomInformationException;
import com.idm.challenge.credits.mscredits.exception.CustomNotFoundException;
import com.idm.challenge.credits.mscredits.mock.MockData;
import com.idm.challenge.credits.mscredits.repository.CreditRepository;
import com.idm.challenge.credits.mscredits.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class CreditServiceImplTest {

    @Mock
    private CreditRepository creditRepository;

    @InjectMocks
    private CreditServiceImpl creditService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateCredit_WhenClientHasPersonalCredit_ShouldThrowException() {
        CreditRequest request = new CreditRequest("1", "1234567890",
            "1234-5678-9012-3456", "prod-001", MockData.mockClient(), 1,
            new BigDecimal("10000.00"), new BigDecimal("5000.00"), 15,
            new BigDecimal("0.05"), 12, Boolean.TRUE);
        when(creditRepository.findByNumber(request.number())).thenReturn(Mono.empty());
        when(creditRepository.countByClientDocumentNumber(request.client().documentNumber())).thenReturn(Mono.just(1L));

        StepVerifier.create(creditService.createCredit(request))
            .expectErrorMatches(throwable -> throwable instanceof CustomInformationException &&
                throwable.getMessage().equals("The client type allows only 1 credit"))
            .verify();

        verify(creditRepository, times(1)).findByNumber(request.number());
        verify(creditRepository, times(1)).countByClientDocumentNumber(request.client().documentNumber());
    }

    @Test
    void testGetCreditById_WhenCreditExists_ShouldReturnCredit() {
        String id = "123";
        CreditRequest request = new CreditRequest("1", "1234567890",
            "1234-5678-9012-3456", "prod-001", MockData.mockClient(), 1,
            new BigDecimal("10000.00"), new BigDecimal("5000.00"), 15,
            new BigDecimal("0.05"), 12, Boolean.TRUE);
        when(creditRepository.findById(id)).thenReturn(Mono.just(MockData.mockCredit()));

        StepVerifier.create(creditService.getCreditById(id))
            .expectNext(MockData.mockCredit())
            .verifyComplete();

        verify(creditRepository, times(1)).findById(id);
    }

    @Test
    void testGetCreditById_WhenCreditDoesNotExist_ShouldThrowException() {
        String id = "123";
        when(creditRepository.findById(id)).thenReturn(Mono.empty());

        StepVerifier.create(creditService.getCreditById(id))
            .expectErrorMatches(throwable -> throwable instanceof CustomNotFoundException &&
                throwable.getMessage().equals(Constants.CreditErrorMsg.MONO_NOT_FOUND_MESSAGE))
            .verify();

        verify(creditRepository, times(1)).findById(id);
    }

    @Test
    void testGetCreditByNumber_WhenCreditExists_ShouldReturnCredit() {
        String number = "456";
        CreditRequest request = new CreditRequest("1", "1234567890",
            "1234-5678-9012-3456", "prod-001", MockData.mockClient(), 1,
            new BigDecimal("10000.00"), new BigDecimal("5000.00"), 15,
            new BigDecimal("0.05"), 12, Boolean.TRUE);
        when(creditRepository.findByNumber(number)).thenReturn(Mono.just(MockData.mockCredit()));

        StepVerifier.create(creditService.getCreditByNumber(number))
            .expectNext(MockData.mockCredit())
            .verifyComplete();

        verify(creditRepository, times(1)).findByNumber(number);
    }

}
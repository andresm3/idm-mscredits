package com.idm.challenge.credits.mscredits.mock;

import com.idm.challenge.credits.mscredits.domain.model.Client;
import com.idm.challenge.credits.mscredits.domain.model.Credit;
import com.idm.challenge.credits.mscredits.domain.model.LoanDebt;
import java.math.BigDecimal;

public class MockData {

  public static Client mockClient() {
    return new Client(
        "client-001",
        "DNI",
        "12345678",
        "Juan",
        "Perez",
        1,
        1,
        true,
        "cat-001");
  }
  public static Client mockClient2() {
    return new Client(
        "client-001",
        "DNI",
        "12345679",
        "Pepe",
        "Lota",
        1,
        1,
        true,
        "cat-001");
  }

  public static Credit mockCredit() {
    return Credit.builder()
        .id("1")
        .number("1234567890")
        .creditCard("1234-5678-9012-3456")
        .productId("prod-001")
        .client(mockClient())
        .type(1)
        .creditTotal(new BigDecimal("10000.00"))
        .creditBalance(new BigDecimal("5000.00"))
        .monthlyPaymentExpirationDay(15)
        .percentageInterestRate(new BigDecimal("0.05"))
        .quotes(12)
        .active(true)
        .build();
  }

}

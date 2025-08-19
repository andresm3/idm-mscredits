package com.idm.challenge.credits.mscredits.domain.dto;

import com.idm.challenge.credits.mscredits.domain.model.Client;
import com.idm.challenge.credits.mscredits.domain.model.LoanDebt;
import java.math.BigDecimal;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

public record CreditRequest (
    String id,
    String number,
    String creditCard,
    String productId,
    Client client,
    int type,
    @Field(targetType = FieldType.DECIMAL128)
    BigDecimal creditTotal,
    @Field(targetType = FieldType.DECIMAL128)
    BigDecimal creditBalance,
    int monthlyPaymentExpirationDay,
    @Field(targetType = FieldType.DECIMAL128)
    BigDecimal percentageInterestRate,
    int quotes,
    boolean active
    //LoanDebt loanDebt
) {}

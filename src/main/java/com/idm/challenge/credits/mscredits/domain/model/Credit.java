package com.idm.challenge.credits.mscredits.domain.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Data
@Builder
@Document("credits")
public class Credit {

    private String id;
    private String number;
    private String creditCard;
    private String productId;
    private Client client;
    private int type;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal creditTotal;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal creditBalance;
    private int monthlyPaymentExpirationDay;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal percentageInterestRate;
    private int quotes;
    private LoanDebt loanDebt;
    private boolean active;
}

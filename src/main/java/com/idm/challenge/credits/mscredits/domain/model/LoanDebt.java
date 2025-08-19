package com.idm.challenge.credits.mscredits.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

public record LoanDebt(
    String id,
    String clientDocumentNumber,
    String productId,
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate expirationDate,
    @Field(targetType = FieldType.DECIMAL128)
    BigDecimal amount,
    int status
) {}

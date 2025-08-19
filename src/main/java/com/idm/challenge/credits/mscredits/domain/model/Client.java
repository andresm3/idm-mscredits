package com.idm.challenge.credits.mscredits.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public record Client(
    @JsonProperty("id") String id,
    String documentType,
    String documentNumber,
    String firstName,
    String lastName,
    int type,
    int profile,
    boolean active,
    String idClientCategory
) {}

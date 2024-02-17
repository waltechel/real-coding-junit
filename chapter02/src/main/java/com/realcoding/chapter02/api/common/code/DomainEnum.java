package com.realcoding.chapter02.api.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DomainEnum {

    FLIGHT("FLIGHT"), PASSENGER("PASSENGER"), TRAVEL("TRAVEL");

    private final String value;

}

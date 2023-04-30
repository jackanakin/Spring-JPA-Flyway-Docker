package br.kuhn.dev.springboot.foo.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FooTypeEnum {
    BAR, CAR;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}

package br.kuhn.dev.springboot.foo.entity;

public enum FooTypeEnum {
    BAR("Bar"), CAR("Car");

    private String type;

    FooTypeEnum(String type) {
        this.type = type;
    }
}

package br.kuhn.dev.springboot.foo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import br.kuhn.dev.springboot._common.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "foos")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Foo extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private FooTypeEnum type;

    @Override
    public String toString() {
        return this.name;
    }
}
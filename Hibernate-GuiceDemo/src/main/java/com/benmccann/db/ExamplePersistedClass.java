package com.benmccann.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExamplePersistedClass {

    @Id
    @GeneratedValue
    private Long id;

    private String otherField;

    public ExamplePersistedClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getOtherField() {
        return otherField;
    }

    public void setOtherField(final String otherField) {
        this.otherField = otherField;
    }

}
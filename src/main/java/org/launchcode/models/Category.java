package org.launchcode.models;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    // default constuctor req for Hibernate to create/retrieve objects
    public Category() {}

    public Category(String name) {
        this.name = name;
    }
}


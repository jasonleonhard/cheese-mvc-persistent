package org.launchcode.models;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message="Cannot be Blank.")
    @NotNull(message="Cannot be Null.")
    @Size(min=3, max=20, message = "Name must be between 3-20 letters.")
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Cheese> cheeses = new ArrayList<>();

    // default constuctor req for Hibernate to create/retrieve objects
    public Category() {}
    public Category(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Cheese> getCheeses() {
        return cheeses;
    }
}

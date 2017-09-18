package org.launchcode.models;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cheese {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message="Cannot be Blank.")
    @NotNull(message="Cannot be Null.")
    @Size(min=3, max=20, message = "Name must be between 3-20 letters.")
    private String name;

    @NotBlank(message="Cannot be Blank.")
    @NotNull(message="Cannot be Null.")
    @Size(min=3, max=40, message = "Description must be between 3-40 letters.")
    private String description;

    @ManyToOne
    private Category category;

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() { }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}

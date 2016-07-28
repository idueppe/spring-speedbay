package io.crowdcode.speedbay.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Entity
public class Product {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Column(unique = true)
    @NotNull
    private String uuid;

    @NotNull
    private String title;

    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product withId(final Long id) {
        this.id = id;
        return this;
    }

    public Product withUuid(final String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Product withTitle(final String title) {
        this.title = title;
        return this;
    }

    public Product withDescription(final String description) {
        this.description = description;
        return this;
    }

}

package io.crowdcode.speedbay.imagestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Entity
public class ImageEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String hash;

    private String ownerUuid;

    @Column(unique = true)
    private String name;

    private String mimetype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(String ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public ImageEntity withId(final Long id) {
        this.id = id;
        return this;
    }

    public ImageEntity withHash(final String hash) {
        this.hash = hash;
        return this;
    }

    public ImageEntity withName(final String name) {
        this.name = name;
        return this;
    }

    public ImageEntity withMimetype(final String mimetype) {
        this.mimetype = mimetype;
        return this;
    }

    public ImageEntity withOwnerUuid(final String ownerUuid) {
        this.ownerUuid = ownerUuid;
        return this;
    }

}

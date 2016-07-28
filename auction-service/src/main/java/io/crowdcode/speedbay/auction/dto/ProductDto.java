package io.crowdcode.speedbay.auction.dto;


/**
 * @author Ingo Düppe (Crowdcode)
 */
public class ProductDto {

    private String uuid;
    private String title;
    private String description;

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

    public ProductDto withUuid(final String uuid) {
        this.uuid = uuid;
        return this;
    }

    public ProductDto withTitle(final String title) {
        this.title = title;
        return this;
    }

    public ProductDto withDescription(final String description) {
        this.description = description;
        return this;
    }


}

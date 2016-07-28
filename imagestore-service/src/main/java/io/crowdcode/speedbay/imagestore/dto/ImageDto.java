package io.crowdcode.speedbay.imagestore.dto;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class ImageDto {

    private String hash;
    private String name;
    private byte[] data;
    private String mimeType;
    private long length;

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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getLength() {
        return (data == null) ? 0 : data.length;
    }

    public ImageDto withHash(final String hash) {
        this.hash = hash;
        return this;
    }

    public ImageDto withName(final String name) {
        this.name = name;
        return this;
    }

    public ImageDto withData(final byte[] data) {
        this.data = data;
        return this;
    }

    public ImageDto withMimeType(final String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

}
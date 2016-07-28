package io.crowdcode.speedbay.auction.remote;

import java.net.URI;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public interface ImageRestClient {

    URI findThumbnail(String ownerUuid);

}

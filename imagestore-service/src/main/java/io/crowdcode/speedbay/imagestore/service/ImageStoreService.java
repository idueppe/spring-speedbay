package io.crowdcode.speedbay.imagestore.service;

import io.crowdcode.speedbay.imagestore.dto.ImageDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public interface    ImageStoreService {

    String saveImage(String uuid, String fileName, String mimeType, InputStream inputStream) throws IOException;

    Optional<ImageDto> loadImage(String hash) throws IOException;

    List<String> listHashes(String uuid);
}

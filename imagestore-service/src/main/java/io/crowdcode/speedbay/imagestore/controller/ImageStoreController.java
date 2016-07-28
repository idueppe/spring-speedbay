package io.crowdcode.speedbay.imagestore.controller;

import io.crowdcode.speedbay.imagestore.dto.ImageDto;
import io.crowdcode.speedbay.imagestore.service.ImageStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RestController
@RequestMapping("/api")
public class ImageStoreController {

    private static final Logger log = LoggerFactory.getLogger(ImageStoreController.class);

    @Autowired
    private ImageStoreService imageStoreService;

    @RequestMapping(method = RequestMethod.POST, value = "/{ownerUuid}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> handleFileUpload(@PathVariable("ownerUuid") String ownerUuid, @RequestParam("file") MultipartFile file, UriComponentsBuilder uriBuilder) {
        try {
            String hash = imageStoreService.saveImage(ownerUuid, file.getOriginalFilename(), file.getContentType(), file.getInputStream());
            URI location = uriBuilder
                    .path("/api/images/{hash}")
                    .buildAndExpand(hash)
                    .toUri();
            log.info("created image under {}.", location.toString());
            return ResponseEntity.created(location).build();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{ownerUuid}")
    public ResponseEntity<List<String>> listImageHashes(@PathVariable("ownerUuid") String ownerUuid) {
        return ResponseEntity.ok(imageStoreService.listHashes(ownerUuid));
    }

    @RequestMapping("/images/{hash}")
    public ResponseEntity<byte[]> showImage(@PathVariable("hash") String hash) throws IOException {
        Optional<ImageDto> image = imageStoreService.loadImage(hash);

        if (image.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(image.get().getMimeType()));
            headers.setContentLength(image.get().getLength());
            return ResponseEntity.ok().headers(headers).body(image.get().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}

package io.crowdcode.speedbay.imagestore.service;

import com.google.common.io.ByteStreams;
import io.crowdcode.speedbay.imagestore.dto.ImageDto;
import io.crowdcode.speedbay.imagestore.model.ImageEntity;
import io.crowdcode.speedbay.imagestore.repository.ImageRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author Ingo Düppe (Crowdcode)
 */
@Service
public class ImageStoreServiceBean implements ImageStoreService {

    private static final Logger log = LoggerFactory.getLogger(ImageStoreServiceBean.class);

    @Value("${imagestore.image.dir}")
    private String imageStoreDir;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public String saveImage(String ownerUuid, String fileName, String mimeType, InputStream inputStream) throws IOException {

        byte[] image = ByteStreams.toByteArray(inputStream);

        ImageEntity imageEntity = new ImageEntity()
                .withOwnerUuid(ownerUuid)
                .withHash(DigestUtils.md5Hex(image))
                .withName(fileName)
                .withMimetype(mimeType);


        saveImageFile(imageEntity.getHash(), new ByteArrayInputStream(image));
        imageRepository.save(imageEntity);
        return imageEntity.getHash();
    }

    @Override
    public Optional<ImageDto> loadImage(String hash) throws IOException {
        Optional<ImageEntity> imageEntity = imageRepository.findByHash(hash);
        if (imageEntity.isPresent()) {
            File file = new File(imageStoreDir + "/" + imageEntity.get().getHash());
            return Optional.of(new ImageDto()
                    .withName(imageEntity.get().getName())
                    .withHash(imageEntity.get().getHash())
                    .withMimeType(imageEntity.get().getMimetype())
                    .withData(FileCopyUtils.copyToByteArray(file)));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<String> listHashes(String ownerUuid) {
        return imageRepository
                .findByOwnerUuid(ownerUuid)
                .stream()
                .map(e -> e.getHash())
                .collect(Collectors.toList());
    }

    private void saveImageFile(String fileHash, InputStream inputStream) {
        try {
            File uploadedFile = new File(imageStoreDir + "/" + fileHash);

            log.info("Saving file {}", uploadedFile.getAbsolutePath());
            OutputStream fos = new BufferedOutputStream(new FileOutputStream(uploadedFile));
            FileCopyUtils.copy(inputStream, fos);
            fos.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}

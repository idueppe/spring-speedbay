package io.crowdcode.speedbay.imagestore.repository;

import io.crowdcode.speedbay.imagestore.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ingo Düppe (Crowdcode)
 */

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    Optional<ImageEntity> findByHash(String hash);

    List<ImageEntity> findByOwnerUuid(String ownerUuid);
}

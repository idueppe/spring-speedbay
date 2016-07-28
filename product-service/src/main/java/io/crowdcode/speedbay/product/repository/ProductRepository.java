package io.crowdcode.speedbay.product.repository;

import io.crowdcode.speedbay.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByUuid(String uuid);

//    @Query("SELECT a FROM Auction a LEFT JOIN a.bids b WHERE b.email = :email")
//    Auction findByBidEmail(@Param("email") String bidder);

}

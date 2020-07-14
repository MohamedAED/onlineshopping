package edu.miu.waa.onlineShopping.repository;

import edu.miu.waa.onlineShopping.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Buyer findByUsername(String username);
}

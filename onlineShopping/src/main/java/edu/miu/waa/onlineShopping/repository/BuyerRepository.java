package edu.miu.waa.onlineShopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.miu.waa.onlineShopping.domain.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Long> {

    @Query("select b from Buyer b where b.userId=:id")
    Buyer findUserById(Long id);
}

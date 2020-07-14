package edu.miu.waa.onlineShopping.repository;

import edu.miu.waa.onlineShopping.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
    Seller findByUsername(String username);

    @Query("select s from Seller s where s.userId=:id")
    Seller findUserById(Long id);
}

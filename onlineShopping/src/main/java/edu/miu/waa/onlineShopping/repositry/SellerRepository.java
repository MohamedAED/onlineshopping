package edu.miu.waa.onlineShopping.repositry;

import edu.miu.waa.onlineShopping.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {

    Seller findByUsername(String username);

    @Query("select s from Seller s where s.approved = 0 ")
    List<Seller> findAllUnApprovedUsers();

    @Query("select s from Seller s where s.userId=:id")
    Seller findUserById(Long id);
}

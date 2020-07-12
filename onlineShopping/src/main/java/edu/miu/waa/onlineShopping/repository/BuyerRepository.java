package edu.miu.waa.onlineShopping.repository;


import edu.miu.waa.onlineShopping.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Buyer findByUsername(String username);

    @Query("select b from Buyer b where b.approved = 0 ")
    List<Buyer> findAllUnApprovedUsers();
}

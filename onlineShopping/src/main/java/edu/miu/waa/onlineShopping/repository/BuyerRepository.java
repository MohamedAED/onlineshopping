package edu.miu.waa.onlineShopping.repository;


import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Buyer findByUsername(String username);

    @Query("select b from Buyer b where b.approved = 0 ")
    List<Buyer> findAllUnApprovedUsers();

//    @Query(value = "select  user_name, password, approved, role from " +
//            "( select  user_name, password, approved, role from Admin union select  user_name, password, approved, role from seller " +
//            "union select  user_name, password, approved, role from buyer )where user_name =?1 and approved = 2", nativeQuery = true)
//    User getLoggedInUserDetails(String username);
}

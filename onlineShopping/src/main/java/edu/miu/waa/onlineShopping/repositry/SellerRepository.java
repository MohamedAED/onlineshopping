package edu.miu.waa.onlineShopping.repositry;

import edu.miu.waa.onlineShopping.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    @Query("select u from User u where u.approved = false ")
    List<User> findAllUnApprovedUsers();

    @Query("select u from User u where u.userId=:id")
    User findUserById(Long id);
}

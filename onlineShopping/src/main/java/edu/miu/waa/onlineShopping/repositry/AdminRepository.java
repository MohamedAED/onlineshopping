package edu.miu.waa.onlineShopping.repositry;

import edu.miu.waa.onlineShopping.domain.Admin;
import edu.miu.waa.onlineShopping.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {

    Admin findByUsername(String username);

}

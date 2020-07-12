package edu.miu.waa.onlineShopping.repositry;

import edu.miu.waa.onlineShopping.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query( value = "select  username, password, approved from ( select username, password, approved FROM  Buyer  " +
			"union select username , password, approved from seller  " +
			"union select username, password, approved from admin ) " +
			"where username = ?1 and approved = 2",  nativeQuery = true)
	Optional<User> findByUsername(String username);

}

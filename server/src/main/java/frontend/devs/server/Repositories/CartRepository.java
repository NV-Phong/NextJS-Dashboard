package frontend.devs.server.Repositories;

import frontend.devs.server.Entities.Cart;
import frontend.devs.server.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String>
{
	@Query("SELECT u FROM Cart u WHERE u.user.IDUser = :IDUser")
	List<Cart> findByIDUser(@Param("IDUser") String IDUser);


}

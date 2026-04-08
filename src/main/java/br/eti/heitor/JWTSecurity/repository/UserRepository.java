package br.eti.heitor.JWTSecurity.repository;

import br.eti.heitor.JWTSecurity.entities.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sesi3dia
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    Optional<User> findByEmail (String email);
}

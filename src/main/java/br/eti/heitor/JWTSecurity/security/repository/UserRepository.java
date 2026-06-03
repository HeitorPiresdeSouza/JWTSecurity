package br.eti.heitor.JWTSecurity.security.repository;

import br.eti.heitor.JWTSecurity.security.entities.User;
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

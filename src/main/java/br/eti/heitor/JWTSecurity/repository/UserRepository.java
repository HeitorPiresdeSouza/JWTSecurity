package br.eti.heitor.JWTSecurity.repository;

import br.eti.heitor.JWTSecurity.entities.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sesi3dia
 */
@Repository
public interface UserRepository {
    
    Optional<User> findByEmail (String email);
}

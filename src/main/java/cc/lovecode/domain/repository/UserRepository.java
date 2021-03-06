package cc.lovecode.domain.repository;

import cc.lovecode.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);

    List<User> findAllByOrderByCreatedAtDesc();
}

package lab.blps.repositories;

import lab.blps.bd.entites.RoleEnum;
import lab.blps.bd.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Boolean existsByEmail(String email);
    List<User> findAllByRolesName(RoleEnum role);
}

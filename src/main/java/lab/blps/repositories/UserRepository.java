package lab.blps.repositories;

import lab.blps.bd.entites.RoleEnum;
import lab.blps.bd.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findById(Long id);
    Boolean existsByLogin(String login);
    List<User> findAllByRolesName(RoleEnum role);
}

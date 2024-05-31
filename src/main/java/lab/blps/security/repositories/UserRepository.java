package lab.blps.security.repositories;

import lab.blps.security.bd.entities.RoleEnum;
import lab.blps.security.bd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findById(Long id);
    Boolean existsByLogin(String login);
    List<User> findAllByRolesName(RoleEnum role);

    @Modifying
    @Query(
        "update User user set user.amountRequest = user.amountRequest - :reductionNumber WHERE user.id = :userId"
    )
    void reduceAmountRequest(
        @Param("userId") Long userId,
        @Param("reductionNumber") Integer reductionNumber
    );
}

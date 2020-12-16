package pb.zio.games.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pb.zio.games.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaSearchRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Query(value = "SELECT u FROM UserEntity u where u.username = ?1 and u.password = ?2 ")
    Optional<UserEntity> login(String username, String password);

    Optional<UserEntity> findByToken(String token);

    @Query("select u from UserEntity u join u.favorites f where f.id = ?1")
    List<UserEntity> findByGameId(Long gameId);
}

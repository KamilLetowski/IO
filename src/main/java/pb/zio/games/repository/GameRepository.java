package pb.zio.games.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pb.zio.games.entity.GameEntity;
import pb.zio.games.entity.RateEntity;
import pb.zio.games.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GameRepository extends JpaSearchRepository<GameEntity, Long> {
    @Query(value = "SELECT g FROM GameEntity g where g.id in (?1) ")
    Optional<List<GameEntity>> getByIds(List<Long> ids);

    @Query("select g from GameEntity g join g.likes u where u.id = ?1")
    Set<GameEntity> findByUserId(Long userId);
}

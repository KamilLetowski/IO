package pb.zio.games.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pb.zio.games.entity.RateEntity;

import java.util.List;

@Repository
public interface RateRepository extends JpaSearchRepository<RateEntity, Long> {

    List<RateEntity> findByGameId(Long gameId);

    @Query("select avg(r.rating) from RateEntity r where r.game.id = ?1")
    Double getAverageRating(Long gameId);
}

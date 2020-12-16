package pb.zio.games.repository;

import org.springframework.stereotype.Repository;
import pb.zio.games.entity.CategoryEntity;
import pb.zio.games.entity.RateEntity;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaSearchRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}

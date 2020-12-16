package pb.zio.games.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import pb.zio.games.entity.AbstractEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface JpaSearchRepository<T extends AbstractEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}

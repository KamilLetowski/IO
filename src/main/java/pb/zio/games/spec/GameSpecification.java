package pb.zio.games.spec;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import pb.zio.games.dto.filters.GameFilterDTO;
import pb.zio.games.entity.GameEntity;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class GameSpecification {

    public Specification<GameEntity> getSpecification(GameFilterDTO filters, Sort sort) {
        return (Specification<GameEntity>) (root, query, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            //TODO: filtry statyczne, dynamiczne, sortowanie
            if (filters.getName() != null && !filters.getName().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get(GameEntity.Fields.name), filters.getName()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
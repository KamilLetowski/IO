package pb.zio.games.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pb.zio.games.dto.GameDTO;
import pb.zio.games.dto.filters.GameFilterDTO;
import pb.zio.games.entity.GameEntity;
import pb.zio.games.entity.UserEntity;
import pb.zio.games.mapper.AbstractMapper;
import pb.zio.games.mapper.GameMapper;
import pb.zio.games.repository.GameRepository;
import pb.zio.games.repository.JpaSearchRepository;
import pb.zio.games.repository.UserRepository;
import pb.zio.games.spec.GameSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService extends AbstractCrudServiceImpl<GameEntity, GameDTO, GameFilterDTO, Long> {

    private final @NonNull GameRepository repo;
    private final @NonNull GameMapper mapper;
    private final @NonNull UserRepository userRepository;

    @Override
    protected JpaSearchRepository<GameEntity, Long> getRepo() {
        return repo;
    }

    @Override
    protected AbstractMapper<GameEntity, GameDTO> getMapper() {
        return mapper;
    }

    @Override
    protected Specification<GameEntity> getSpecification(GameFilterDTO gameFilterDTO) {
        return GameSpecification.getSpecification(gameFilterDTO, Sort.unsorted());
    }

    @Override
    public void delete(Long id) {
        GameEntity entity = getRepo().findById(id).orElseThrow(
                () -> new EmptyResultDataAccessException("No entity with id: " + id, 1));
        for (UserEntity user : userRepository.findByGameId(id)) {
            user.getFavorites().remove(entity);
            userRepository.save(user);
        }
        getRepo().delete(entity);
    }
}

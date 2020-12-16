package pb.zio.games.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.zio.games.dto.RateDTO;
import pb.zio.games.entity.GameEntity;
import pb.zio.games.entity.RateEntity;
import pb.zio.games.mapper.RateMapper;
import pb.zio.games.repository.GameRepository;
import pb.zio.games.repository.RateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {

    private final @NonNull RateRepository repo;
    private final @NonNull RateMapper mapper;
    private final @NonNull GameRepository gameRepository;

    public RateDTO addRate(RateDTO rate) {
        RateEntity rateEntity = mapper.mapToEntity(rate);
        if (rateEntity != null) {
            repo.save(rateEntity);
            GameEntity game = gameRepository.findById(rate.getGameId()).get();
            Double avgRating = repo.getAverageRating(rate.getGameId());
            game.setAverageRating(avgRating);
            gameRepository.save(game);
            return mapper.mapToDto(rateEntity);
        }
        return null;
    }

    public List<RateDTO> findByGameId(Long gameId){
        return mapper.mapToDtos(repo.findByGameId(gameId));
    }
}

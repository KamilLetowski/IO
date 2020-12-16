package pb.zio.games.mapper;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Component;
import pb.zio.games.dto.RateDTO;
import pb.zio.games.entity.RateEntity;
import pb.zio.games.entity.UserEntity;
import pb.zio.games.repository.GameRepository;
import pb.zio.games.repository.RateRepository;
import pb.zio.games.repository.UserRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RateMapper extends AbstractMapper<RateEntity, RateDTO> {

    private final RateRepository rateRepository;

    private final UserRepository userRepository;

    private final GameRepository gameRepository;

    @Override
    public RateEntity mapToEntity(RateDTO dto) {
        var user = userRepository.findById(dto.getUserId());
        var game = gameRepository.findById(dto.getGameId());
        if(user.isPresent() && game.isPresent()){
            RateEntity rate = dto.getId() != null ? rateRepository.findById(dto.getId()).orElse(new RateEntity()) : new RateEntity();
            rate.setRating(dto.getRating());
            rate.setReason(dto.getReason());
            if (dto.getAddedDate() == null) {
                rate.setAddedDate(LocalDateTime.now());
            } else {
                rate.setAddedDate(dto.getAddedDate());
            }
            rate.setUserName(user.get().getUsername());
            rate.setGame(game.get());
            return rate;
        }
        return null;
    }

    @Override
    public RateDTO mapToDto(RateEntity entity) {
        var user = userRepository.findByUsername(entity.getUserName()).get();
        return RateDTO.builder()
                .id(entity.getId())
                .rating(entity.getRating())
                .reason(entity.getReason())
                .addedDate(entity.getAddedDate())
                .userId(user.getId())
                .username(user.getUsername())
                .gameId(entity.getGame().getId())
                .build();
    }
}

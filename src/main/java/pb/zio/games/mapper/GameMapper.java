package pb.zio.games.mapper;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Component;
import pb.zio.games.dto.GameDTO;
import pb.zio.games.entity.CategoryEntity;
import pb.zio.games.entity.GameEntity;
import pb.zio.games.repository.CategoryRepository;
import pb.zio.games.repository.GameRepository;

@Component
@RequiredArgsConstructor
public class GameMapper extends AbstractMapper<GameEntity, GameDTO>{

    private final GameRepository gameRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public GameEntity mapToEntity(GameDTO dto) {
        var category = categoryRepository.findByName(dto.getCategoryName());

        GameEntity game = dto.getId() != null ? gameRepository.findById(dto.getId()).orElse(new GameEntity()) : new GameEntity();
        game.setName(dto.getName());
        game.setImageURL(dto.getImageURL());
        game.setDescription(dto.getDescription());
        game.setAverageRating(dto.getAverageRating());
        game.setReleaseDate(dto.getReleaseDate());
        game.setPublisher(dto.getPublisher());
        if(category.isPresent()){
            game.setCategory(category.get());
        }
        return game;
    }

    @Override
    public GameDTO mapToDto(GameEntity entity) {
        return GameDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .imageURL(entity.getImageURL())
                .averageRating(entity.getAverageRating())
                .releaseDate(entity.getReleaseDate())
                .publisher(entity.getPublisher())
                .categoryName(entity.getCategory() != null ? entity.getCategory().getName() : null)
                .build();
    }
}

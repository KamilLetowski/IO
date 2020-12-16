package pb.zio.games.dto;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@Data
@NoArgsConstructor
public class GameDTO extends AbstractDTO {

    private Long id;
    private String name;
    private String imageURL;
    private String description;
    private Double averageRating;
    private LocalDateTime releaseDate;
    private String publisher;
    private String categoryName;

    @Builder
    public GameDTO(Long id, String name, String imageURL, String description, Double averageRating,
                   LocalDateTime releaseDate, String publisher, String categoryName) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
        this.averageRating = averageRating;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.categoryName = categoryName;
    }
}

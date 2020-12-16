package pb.zio.games.dto.filters;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameFilterDTO extends AbstractFilterDTO {

    private String name;
    private float averageRating;
    private LocalDateTime releaseDateFrom;
    private LocalDateTime releaseDateTo;
    private String publisher;

}

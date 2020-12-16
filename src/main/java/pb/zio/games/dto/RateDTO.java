package pb.zio.games.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import pb.zio.games.entity.GameEntity;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@Data
@NoArgsConstructor
public class RateDTO extends AbstractDTO {
    private Long id;
    private int rating;
    private Long userId;
    private String username;
    private String reason;
    private LocalDateTime addedDate;
    private Long gameId;

    @Builder
    public RateDTO(Long id, int rating, Long userId, String username, String reason, LocalDateTime addedDate, Long gameId) {
        this.id = id;
        this.rating = rating;
        this.userId = userId;
        this.username = username;
        this.reason = reason;
        this.addedDate = addedDate;
        this.gameId = gameId;
    }
}

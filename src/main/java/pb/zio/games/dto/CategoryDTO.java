package pb.zio.games.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import pb.zio.games.entity.GameEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@Data
@NoArgsConstructor
public class CategoryDTO extends AbstractDTO {
    private Long id;
    private String name;

    @Builder
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

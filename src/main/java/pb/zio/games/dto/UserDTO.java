package pb.zio.games.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserDTO extends AbstractDTO {

    private Long id;
    private String username;
    private String password;
    private LocalDateTime creationDate;

    @Builder
    public UserDTO(Long id, String username, String password, LocalDateTime creationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
    }
}

package pb.zio.games.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = RateEntity.TABLE_NAME)
@FieldNameConstants
public class RateEntity extends AbstractEntity<Long> {
    public static final String TABLE_NAME = "RATE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_RATING = "RATING";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_REASON = "REASON";
    public static final String COLUMN_ADDED_DATE = "ADDED_DATE";
    public static final String COLUMN_GAME_ID = "GAME_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID, nullable = false)
    private Long id;

    @Column(name = COLUMN_RATING, nullable = false)
    private int rating;

    @Column(name = COLUMN_USER_NAME, nullable = false)
    private String userName;

    @Column(name = COLUMN_REASON)
    private String reason;

    @Column(name = COLUMN_ADDED_DATE, nullable = false)
    private LocalDateTime addedDate;

    @ManyToOne
    @JoinColumn(name = COLUMN_GAME_ID)
    private GameEntity game;
}

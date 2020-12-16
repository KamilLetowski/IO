package pb.zio.games.entity;

import com.google.common.collect.Sets;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@ToString()
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity extends AbstractEntity<Long> {

    public static final String TABLE_NAME = "USER";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NICKNAME = "NICKNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_CREATION_DATE = "CREATION_DATE";
    public static final String COLUMN_TOKEN = "TOKEN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID, nullable = false)
    private Long id;

    @Column(name = COLUMN_NICKNAME, nullable = false, unique = true)
    private String username;

    @Column(name = COLUMN_PASSWORD, nullable = false)
    private String password;

    @Column(name = COLUMN_CREATION_DATE)
    private LocalDateTime creationDate;

    @Column(name = COLUMN_TOKEN)
    private String token;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "favourite_game",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<GameEntity> favorites = Sets.newHashSet();

    public void setFavorites(Set<GameEntity> games) {
        this.favorites.clear();
        this.favorites.addAll(games);
    }
}

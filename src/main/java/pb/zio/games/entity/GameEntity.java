package pb.zio.games.entity;

import com.google.common.collect.Sets;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@ToString()
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = GameEntity.TABLE_NAME)
@FieldNameConstants
public class GameEntity extends AbstractEntity<Long> {

    public static final String TABLE_NAME = "GAME";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_IMAGE_URL = "IMAGE_URL";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_AVERAGE_RATING = "AVERAGE_RATING";
    public static final String COLUMN_RELEASE_DATE = "RELEASE_DATE";
    public static final String COLUMN_PUBLISHER = "PUBLISHER";
    public static final String COLUMN_CATEGORY = "CATEGORY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID, nullable = false)
    private Long id;

    @Column(name = COLUMN_NAME, nullable = false)
    private String name;

    @Column(name = COLUMN_IMAGE_URL)
    private String imageURL;

    @Column(name = COLUMN_DESCRIPTION)
    private String description;

    @Column(name = COLUMN_AVERAGE_RATING)
    private Double averageRating;

    @Column(name = COLUMN_RELEASE_DATE)
    private LocalDateTime releaseDate;

    @Column(name = COLUMN_PUBLISHER)
    private String publisher;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<RateEntity> ratings;

    @ManyToMany(mappedBy = "favorites")
    private Set<UserEntity> likes = Sets.newHashSet();

}

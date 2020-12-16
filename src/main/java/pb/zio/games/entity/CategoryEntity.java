package pb.zio.games.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = CategoryEntity.TABLE_NAME)
@FieldNameConstants
public class CategoryEntity extends AbstractEntity<Long>{
    public static final String TABLE_NAME = "CATEGORY";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_USER_NAME = "USER NAME";
    public static final String COLUMN_REASON = "REASON";
    public static final String COLUMN_ADDED_DATE = "ADDED_DATE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID, nullable = false)
    private Long id;

    @Column(name = COLUMN_NAME, nullable = false)
    private String name;

    @OneToMany(mappedBy="category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GameEntity> games;
}

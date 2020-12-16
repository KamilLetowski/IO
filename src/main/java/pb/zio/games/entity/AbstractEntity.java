package pb.zio.games.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Persistable<ID>, Serializable {

    @Override
    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

}

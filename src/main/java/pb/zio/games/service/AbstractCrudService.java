package pb.zio.games.service;

import org.springframework.data.domain.Page;
import pb.zio.games.dto.AbstractDTO;
import pb.zio.games.dto.filters.AbstractFilterDTO;
import pb.zio.games.entity.AbstractEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface AbstractCrudService<E extends AbstractEntity<ID>, DTO extends AbstractDTO, FILTER extends AbstractFilterDTO, ID extends Serializable> {

    DTO save(DTO dto);

    DTO update(DTO dto);

    void delete(ID id);

    Optional<DTO> findById(ID id);

    List<DTO> findAll();

    Page<DTO> findAll(FILTER filter, Integer page, Integer size);

}
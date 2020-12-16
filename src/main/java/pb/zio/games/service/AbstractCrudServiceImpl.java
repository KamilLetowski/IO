package pb.zio.games.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pb.zio.games.dto.AbstractDTO;
import pb.zio.games.dto.filters.AbstractFilterDTO;
import pb.zio.games.entity.AbstractEntity;
import pb.zio.games.mapper.AbstractMapper;
import pb.zio.games.repository.JpaSearchRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractCrudServiceImpl<ENTITY extends AbstractEntity<ID>, DTO extends AbstractDTO, FILTER extends AbstractFilterDTO, ID extends Serializable>
        implements AbstractCrudService<ENTITY, DTO, FILTER, ID> {

    protected abstract JpaSearchRepository<ENTITY, ID> getRepo();

    protected abstract AbstractMapper<ENTITY, DTO> getMapper();

    protected abstract Specification<ENTITY> getSpecification(FILTER filter);

    @Override
    public DTO save(final DTO dto) {
        return doSave(dto);
    }

    @Override
    public DTO update(final DTO dto) {
        return doSave(dto);
    }

    protected DTO doSave(DTO dto) {
        DTO resultDTO = null;
        ENTITY entityToModify = getMapper().mapToEntity(dto);
        ENTITY modifiedEntity = getRepo().save(entityToModify);
        resultDTO = getMapper().mapToDto(modifiedEntity);
        return resultDTO;
    }

    @Override
    public void delete(final ID id) {
        ENTITY entity = getRepo().findById(id).orElseThrow(
                () -> new EmptyResultDataAccessException("No entity with id: " + id, 1));
        getRepo().delete(entity);
    }

    @Override
    public Optional<DTO> findById(final ID id) {
        Optional<ENTITY> fountEntity = getRepo().findById(id);
        return fountEntity.map(elem -> (getMapper().mapToDto(elem)));
    }

    @Override
    public Page<DTO> findAll(FILTER filter, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ENTITY> foundPage = getRepo().findAll(getSpecification(filter), pageable);
        return foundPage.map(elem -> getMapper().mapToDto(elem));
    }

    @Override
    public List<DTO> findAll() {
        return getMapper().mapToDtos(getRepo().findAll());
    }
}